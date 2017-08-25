package com.dengzi.lib.ioc;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Title:绑定工具类
 * @Author: djk
 * @Time: 2017/8/2
 * @Version:1.0.0
 */
public class BindUtil {

    /**
     * 针对activity
     *
     * @param act
     */
    public static void inject(Activity act) {
        inject(new BindHelper(act), act);
    }

    /**
     * 针对View
     *
     * @param view
     */
    public static void inject(View view) {
        inject(new BindHelper(view), view);
    }

    /**
     * 针对Fragment
     *
     * @param view
     */
    public static void inject(View view, Object object) {
        inject(new BindHelper(view), object);
    }

    /**
     * @param binderHelper 辅助类
     * @param object       反射需要执行的类
     */
    public static void inject(BindHelper binderHelper, Object object) {
        injectView(binderHelper, object);
        injectEvent(binderHelper, object);
    }

    /**
     * 自动注入属性 省去了findViewById
     *
     * @param binderHelper
     * @param object
     */
    private static void injectView(BindHelper binderHelper, Object object) {
        //1、获取类里的属性
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        //2、获取ViewById里的value值-->view的id值
        for (Field field : fields) {
            if (field != null) {
                BindView binder = field.getAnnotation(BindView.class);
                if (binder != null) {
                    // 能够注入所有的修饰符，包含private的字段
                    field.setAccessible(true);
                    // 获取view对应的id
                    int viewID = binder.value();
                    if (viewID != -1) {
                        //3、findViewById找到对应的view
                        View view = binderHelper.findViewById(viewID);
                        if (view != null) {
                            try {
                                //4、把对应的view注入到类中
                                field.set(object, view);
                            } catch (IllegalAccessException e) {
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 自动注入点击事件 省去了setOnClickListener
     *
     * @param binderHelper
     * @param object
     */
    private static void injectEvent(BindHelper binderHelper, final Object object) {
        //1、获取类里的方法
        final Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (final Method method : methods) {
            if (method != null) {
                // 能够注入所有的方法，包含private的方法
                method.setAccessible(true);
                BindClick binderClick = method.getAnnotation(BindClick.class);
                if (binderClick != null) {
                    // 获取点击事件对应的id值
                    int[] viewIds = binderClick.value();
                    for (int viewId : viewIds) {
                        // 找到对应的view
                        View view = binderHelper.findViewById(viewId);
                        // 给对应的view设置点击事件
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    method.invoke(object, view);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            }
        }
    }

}
