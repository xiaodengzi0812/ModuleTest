package com.dengzi.dzframework.skin.net;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.dengzi.lib.net.HttpCallBack;
import com.dengzi.lib.net.IHttpEngine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author Djk
 * @Title: 具体的okhttp的实例
 * @Time: 2017/8/22.
 * @Version:1.0.0
 */
public class OkHttpEngine implements IHttpEngine {

    public static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");
    private static final String TOKEN_TYPE = "AuthToken";
    private static final String CONTENT_TYPE_KEY = "Content-Type";
    private static final String CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded";
    private static final String FILE_TYPES = "jpg,png,jpeg";
    private static final MediaType IMAGE_TYPE = MediaType.parse("image");

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e("dengzi", "message = " + message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build();

    private static Headers.Builder getHeaderBuilder() {
        return new Headers.Builder().add(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE);
    }

    private static class Holder {
        static final OkHttpEngine INSTANCE = new OkHttpEngine();
    }

    public static OkHttpEngine getInstance() {
        return Holder.INSTANCE;
    }

    private OkHttpEngine() {
    }

    private Object readResolve() {
        return getInstance();
    }

    @Override
    public void get(Object tag, String url, Map<String, Object> params, HttpCallBack callBack) {
        url = addParamsToUrl(url, params);
        Request request = new Request.Builder().url(url)
                .headers(getHeaderBuilder().build())
                .tag(tag).build();
        okHttpClient.newCall(request).enqueue(new MyCallBack(callBack));
    }

    @Override
    public void post(Object tag, String url, Map<String, Object> params, HttpCallBack callBack) {
        FormBody requestBody = paramsToFormBody(params);
        Request request = new Request.Builder().url(url).tag(tag).post(requestBody)
                .headers(getHeaderBuilder().build())
                .build();
        okHttpClient.newCall(request).enqueue(new MyCallBack(callBack));
    }

    @Override
    public void put(Object tag, String url, Map<String, Object> params, HttpCallBack callBack) {
        String json = paramsToJson(params);
        RequestBody requestBody = RequestBody.create(JSON_TYPE, TextUtils.isEmpty(json) ? "{}" : json);
        Request request = new Request.Builder().url(url).tag(tag).put(requestBody)
                .headers(getHeaderBuilder().build())
                .build();
        okHttpClient.newCall(request).enqueue(new MyCallBack(callBack));
    }

    @Override
    public void delete(Object tag, String url, Map<String, Object> params, HttpCallBack callBack) {
        String json = paramsToJson(params);
        RequestBody requestBody = RequestBody.create(JSON_TYPE, TextUtils.isEmpty(json) ? "{}" : json);
        Request request = new Request.Builder().url(url).tag(tag).delete(requestBody)
                .headers(getHeaderBuilder().build())
                .build();
        okHttpClient.newCall(request).enqueue(new MyCallBack(callBack));
    }

    @Override
    public void download(Object tag, String url, String downloadLocation, HttpCallBack callback) {
        final Request request = new Request.Builder().tag(tag).url(url).build();
        okHttpClient.newCall(request).enqueue(new MyDownloadCallBack(downloadLocation, callback));
    }

    @Override
    public void upLoad(Object tag, String url, Map<String, Object> params, HttpCallBack callback) {
        try {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            //设置类型
            builder.setType(MultipartBody.FORM);
            //追加参数
            for (String key : params.keySet()) {
                Object object = params.get(key);
                if (!(object instanceof File)) {
                    builder.addFormDataPart(key, object.toString());
                } else {
                    File file = (File) object;
                    builder.addFormDataPart(key, file.getName(), RequestBody.create(null, file));
                }
            }
            //创建RequestBody
            RequestBody body = builder.build();
            //创建Request
            final Request request = new Request.Builder().url(url).post(body).build();
            okHttpClient.newCall(request).enqueue(new MyCallBack(callback));
        } catch (Exception e) {
        }
    }

    @Override
    public void cancelRequest(Object tag) {
        Iterator i$ = okHttpClient.dispatcher().queuedCalls().iterator();
        Call call;
        while (i$.hasNext()) {
            call = (Call) i$.next();
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        i$ = okHttpClient.dispatcher().runningCalls().iterator();
        while (i$.hasNext()) {
            call = (Call) i$.next();
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    private String paramsToJson(Object params) {
        return JSON.toJSONString(params, true);
    }

    private class MyCallBack implements Callback {
        private HttpCallBack httpCallBack;

        public MyCallBack(HttpCallBack httpCallBack) {
            this.httpCallBack = httpCallBack;
        }

        @Override
        public void onFailure(Call call, IOException e) {
            httpCallBack.onError(HttpCallBack.ERROR_CODE, e.toString());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String result = response.body().string();
            if (!TextUtils.isEmpty(result)) {
                httpCallBack.onSuccess(result);
            } else {
                httpCallBack.onError(HttpCallBack.ERROR_CODE, HttpCallBack.EMPTY_RESULT);
            }
        }
    }

    private FormBody paramsToFormBody(Map<String, Object> params) {
        FormBody.Builder formBody = new FormBody.Builder();

        for (String key : params.keySet()) {
            formBody.add(key, (String) params.get(key));
        }
        return formBody.build();
    }

    private class MyDownloadCallBack implements Callback {
        private HttpCallBack httpCallBack;
        private String downloadLocation;

        public MyDownloadCallBack(String downloadLocation, HttpCallBack httpCallBack) {
            this.httpCallBack = httpCallBack;
            this.downloadLocation = downloadLocation;
        }

        @Override
        public void onFailure(Call call, IOException e) {
            httpCallBack.onError(HttpCallBack.ERROR_CODE, e.toString());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            InputStream is = null;
            byte[] buf = new byte[1024];
            int len = 0;
            FileOutputStream fos = null;
            try {
                is = response.body().byteStream();
                File file = new File(downloadLocation);
                fos = new FileOutputStream(file);
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                fos.flush();
                httpCallBack.onSuccess(downloadLocation);
            } catch (IOException e) {
                httpCallBack.onError(HttpCallBack.ERROR_CODE, e.toString());
            } finally {
                try {
                    if (is != null) is.close();
                } catch (IOException e) {
                }
                try {
                    if (fos != null) fos.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /*拼装get参数*/
    private String addParamsToUrl(String url, Map<String, Object> params) {
        if (params != null) {
            StringBuffer sb = new StringBuffer();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                sb.append(entry.getKey() + "=" + entry.getValue());
                sb.append("&");
            }
            String endUrl = sb.toString();
            if (endUrl.endsWith("&")) {
                endUrl = endUrl.substring(0, endUrl.length() - 1);
            }
            if (!TextUtils.isEmpty(endUrl)) {
                if (url.contains("?")) {
                    url = url + "&" + endUrl;
                } else {
                    url = url + "?" + endUrl;
                }
            }
        }
        return url;
    }


}
