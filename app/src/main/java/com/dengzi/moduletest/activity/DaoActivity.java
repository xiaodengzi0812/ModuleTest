package com.dengzi.moduletest.activity;

import android.content.Context;
import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.dengzi.lib.ioc.BindClick;
import com.dengzi.lib.ioc.BindUtil;
import com.dengzi.lib.util.AppUtils;
import com.dengzi.lib.util.SpanUtils;
import com.dengzi.moduletest.R;
import com.dengzi.moduletest.base.BaseBackActivity;

import java.io.File;


/**
 * @Title: 数据库工具类Demo
 * @Author: djk
 * @Time: 2017/8/2
 * @Version:1.0.0
 */
public class DaoActivity extends BaseBackActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, DaoActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_dao;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        getToolBar().setTitle("数据库框架");
        BindUtil.inject(this);
    }

    @Override
    public void doBusiness(Context context) {

    }

    @Override
    public void onWidgetClick(View view) {

    }

    @BindClick({R.id.test1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test1:
                break;
        }
    }

    private void test1() {

        String dbPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "dzdao" + File.separator;
        File dirFile = new File(dbPath);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File dbFile = new File(dirFile, "dengzi.db");

        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
        //创建表SQL语句
        String stu_table = "create table usertable(_id integer primary key autoincrement,sname text,snumber text)";
        //执行SQL语句
        db.execSQL(stu_table);
    }


}
