package cn.logcode.library.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by CaostGrace on 2018/6/6 20:15
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.library.utils
 * @class_name: ActivityUtils
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class ActivityUtils {
    private static Stack<Activity> mActivitys;

    public static void addActivity(Activity activity){
        if(mActivitys == null){
            mActivitys = new Stack<>();
        }
        mActivitys.push(activity);
    }


    public static Activity getTopActivity(){
        if(mActivitys == null){
            return null;
        }
        return mActivitys.peek();
    }

    public static void removeActivity(Activity activity){
        if(mActivitys != null){
            mActivitys.remove(activity);
        }
    }
}
