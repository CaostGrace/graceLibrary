package cn.logcode.library.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import cn.logcode.library.ApplicationLibrary;
import cn.logcode.library.Log.LogUtils;

/**
 * Created by CaostGrace on 2018/6/5 21:21
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.library.utils
 * @class_name: Utils
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class Utils {

    public static boolean isNetworkConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) ApplicationLibrary.INSTANCE.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null){
            return false;
        }
        //MOBILE
        //WIFI
        LogUtils.d(networkInfo.getTypeName());
        if(networkInfo.isConnected()){
            LogUtils.d("connected");
            return true;
        }

        return false;
    }

    public static boolean isWIFIConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null){
            return false;
        }
        //MOBILE
        //WIFI
        LogUtils.d(networkInfo.getTypeName());
        if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
            LogUtils.d(networkInfo.getTypeName());
            return true;
        }

        return false;
    }

}
