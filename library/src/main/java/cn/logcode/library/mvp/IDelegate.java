package cn.logcode.library.mvp;

import android.view.View;

/**
 * Created by CaostGrace on 2018/6/5 22:15
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.frameworkdemo.mvp
 * @class_name: IDelegate
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public interface IDelegate {

    View getRootView();

    Class<?> getViewClass();

    Class<?> getModelClass();


}
