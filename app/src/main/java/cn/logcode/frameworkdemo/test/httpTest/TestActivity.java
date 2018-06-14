package cn.logcode.frameworkdemo.test.httpTest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;

import cn.logcode.frameworkdemo.R;
import cn.logcode.library.Log.LogUtils;
import cn.logcode.library.http.DefaultObserver;
import cn.logcode.library.http.HttpManager;
import cn.logcode.library.http.RxSchedulers;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
public class TestActivity extends AppCompatActivity {
//    private final String url =“http://www.tuling123.com/openapi/api”;
//    private final String key =“1cc454f2535844bd9b085ea71428f3f6”;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpManager httpManger = new HttpManager
                .Builder()
                .baseUrl("http://www.tuling123.com/")
                .build();



        httpManger
                .createApi(TestApi.class)
                .defaultTest("1cc454f2535844bd9b085ea71428f3f6","成都天气")
                .compose(RxSchedulers.<Bean>defaultCompose())
                .subscribe(new DefaultObserver<Bean>() {
                    @Override
                    public void onHandleSuccess(Bean bean) {

                    }
                });

        httpManger
                .createApi(TestApi.class)
                .defaultTest("1cc454f2535844bd9b085ea71428f3f6","成都天气")
                .compose(RxSchedulers.<Bean>defaultCompose())
                .subscribeWith(new DefaultObserver<Bean>() {
                    @Override
                    public void onHandleSuccess(Bean bean) {

                    }

                });




    }
}
