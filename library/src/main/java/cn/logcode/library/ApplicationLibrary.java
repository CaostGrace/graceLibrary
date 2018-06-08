package cn.logcode.library;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.os.Build;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;

import butterknife.BuildConfig;
import cn.logcode.library.Log.LogUtils;

/**
 * Created by CaostGrace on 2018/5/30 22:03
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.library
 * @class_name: Application
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public abstract class ApplicationLibrary extends Application {
    public static ApplicationLibrary INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        //测试时为true，发布时为false
//        CrashReport.initCrashReport(getApplicationContext(), "注册时申请的APPID", true);
//        CrashReport.initCrashReport(getApplicationContext(), "4a3804c4fe", true);

        if(!buglyAppId().equals("")){

            ApplicationInfo info= getApplicationInfo();
            boolean isDebug = (info.flags&ApplicationInfo.FLAG_DEBUGGABLE)!=0;
            LogUtils.d(isDebug);
            Bugly.init(getApplicationContext(), buglyAppId(), isDebug);
        }

    }
    public abstract String getBaseUrl();

    public String buglyAppId(){
        return "";
    }
}
