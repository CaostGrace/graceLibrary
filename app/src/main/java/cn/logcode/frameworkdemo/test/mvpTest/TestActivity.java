package cn.logcode.frameworkdemo.test.mvpTest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import cn.logcode.frameworkdemo.R;
import cn.logcode.frameworkdemo.test.httpTest.Bean;
import cn.logcode.frameworkdemo.test.httpTest.Mode;
import cn.logcode.frameworkdemo.test.httpTest.TestApi;
import cn.logcode.library.Log.LogUtils;
import cn.logcode.library.http.DefaultObserver;
import cn.logcode.library.http.HttpManager;
import cn.logcode.library.http.RxSchedulers;
import cn.logcode.library.mvp.BaseDelegate;
import cn.logcode.library.widget.dialog.DialogManager;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
public class TestActivity extends BaseDelegate<TestView, TestModel> {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_mvp);


        TextView textView = mView.get(R.id.test02);

        mView.get(R.id.test01).setOnClickListener((View v) -> {
            mModel.mHttpManager.createApi(TestApi.class)
                    .defaultTest("1cc454f2535844bd9b085ea71428f3f6", "成都天气")
                    .compose(RxSchedulers.defaultCompose())
                    .subscribe(new DefaultObserver<Bean>(mView) {
                        @Override
                        public void onHandleSuccess(Bean bean) {
                            textView.setText(bean.toString());
                        }
                    });

        });
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
