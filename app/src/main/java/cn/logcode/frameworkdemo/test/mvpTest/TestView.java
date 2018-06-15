package cn.logcode.frameworkdemo.test.mvpTest;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.tapadoo.alerter.Alert;
import com.tapadoo.alerter.Alerter;

import butterknife.BindView;
import butterknife.OnClick;
import cn.logcode.frameworkdemo.R;
import cn.logcode.library.Log.LogUtils;
import cn.logcode.library.mvp.BaseView;
import cn.logcode.library.utils.ToastUtil;
import cn.logcode.library.widget.dialog.DialogManager;
import cn.logcode.library.widget.loading.LoadDialog;

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

    LoadDialog mDialog;

    @BindView(R.id.frame_layout)
    public FrameLayout mFrameLayout;

    @Override
    public void showLoadingView(String str) {
        LogUtils.d(mContext.getCacheDir().getAbsolutePath());
        mDialog = LoadDialog.create(mContext)
                .setTitleText("加载中...");
        mDialog.show();
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
        if (mDialog != null){
            mDialog.cancel();
        }
    }

    @Override
    public void hideErrorView() {

    }

    @Override
    public void showErrorMsg(String str) {
        manager = new DialogManager
                .Builder()
                .setFragmentManager(mFragmentManager)
                .create()
                .setTitle("注意，这是一个错误信息", DialogManager.HighMode.HIGH)
                .setMessage(str)
                .build();
        manager.show();
    }

    @Override
    public void initView() {

    }

    @Override
    public void onCreate() {
        get(R.id.test01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.init(mContext)
                        .makeSuccessToast("拦截成功",Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}
