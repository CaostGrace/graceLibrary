package cn.logcode.frameworkdemo.test.mvpTest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.logcode.frameworkdemo.R;
import cn.logcode.library.mvp.activity.ActivityDelegate;
import cn.logcode.library.widget.dialog.DialogManager;
import cn.logcode.library.widget.loading.LoadDialog;

/**
 * Created by CaostGrace on 2018/5/29 16:25
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.frameworkdemo.test
 * @class_name: TestActivity
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class TestActivity extends ActivityDelegate<TestView, TestModel> {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_mvp);

    }


    @Override
    public Class<?> getViewClass() {
        return TestView.class;
    }

    @Override
    public Class<?> getModelClass() {
        return TestModel.class;
    }

}
