package cn.logcode.frameworkdemo.test.mvpTest;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.tapadoo.alerter.Alert;
import com.tapadoo.alerter.Alerter;

import cn.logcode.library.Log.LogUtils;
import cn.logcode.library.mvp.BaseView;
import cn.logcode.library.utils.ToastUtil;
import cn.logcode.library.widget.dialog.DialogManager;

/**
 * Created by CaostGrace on 2018/6/6 21:57
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.frameworkdemo.test.mvpTest
 * @class_name: TestView
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class TestView extends BaseView {
    DialogManager manager;

    @Override
    public void showLoadingView(String str) {
        LogUtils.d(mContext.getCacheDir().getAbsolutePath());
        manager = new DialogManager
                .Builder()
                .setFragmentManager(((FragmentActivity)mContext).getSupportFragmentManager())
                .create()
                .setTitle("注意，这是一个加载框", DialogManager.HighMode.HIGH)
                .setMessage(str)
                .build();
        manager.show();
    }

    @Override
    public void showErrorView(String str) {
        Alerter.create((Activity) mContext)
                .setDuration(2000)
                .setTitle("错误信息")
                .setText(str)
                .show();
    }

    @Override
    public void hideLoadingView() {
        manager.getDialog().dismiss();
    }

    @Override
    public void hideErrorView() {

    }

    @Override
    public void showErrorMsg(String str) {
        ToastUtil.init(mContext)
                .makeText(str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initView() {

    }
}
