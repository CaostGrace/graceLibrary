package cn.logcode.frameworkdemo;

import android.app.Application;

import cn.logcode.library.ApplicationLibrary;

/**
 * Created by CaostGrace on 2018/5/28 20:14
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.frameworkdemo
 * @class_name: App
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class App extends ApplicationLibrary {
    public static App INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    @Override
    public String getBaseUrl() {
        return "http://www.tuling123.com/";
    }

}
