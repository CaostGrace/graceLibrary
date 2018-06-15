package cn.logcode.frameworkdemo.test.mvpTest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.logcode.frameworkdemo.R;
import cn.logcode.frameworkdemo.test.httpTest.Bean;
import cn.logcode.frameworkdemo.test.httpTest.TestApi;
import cn.logcode.library.http.DefaultObserver;
import cn.logcode.library.http.RxSchedulers;
import cn.logcode.library.mvp.activity.ActivityDelegate;
import cn.logcode.library.utils.ToastUtil;
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

        mModel.addDisposable(TestModel.mHttpManager
                .createApi(TestApi.class)
                .defaultTest("", "成都天气")
                .compose(RxSchedulers.<Bean>defaultCompose())
                .subscribeWith(new DefaultObserver<Bean>(mView) {
                    @Override
                    public void onHandleSuccess(Bean bean) {
                        ToastUtil.init(TestActivity.this)
                                .makeText(bean.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        );


    }



    @OnClick({R.id.test01,R.id.test02})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.test01:
                mView.showErrorMsg("加载中...");
                break;
            case R.id.test02:
                TestFragment fragment = new TestFragment();
                mView.getFragmentManager().beginTransaction().add(R.id.frame_layout,fragment)
                        .commit();
                break;

        }
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
