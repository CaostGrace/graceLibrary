package cn.logcode.frameworkdemo.test.httpTest;

import cn.logcode.library.Log.LogUtils;
import cn.logcode.library.http.HttpManager;
import cn.logcode.library.http.RxSchedulers;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by CaostGrace on 2018/6/6 22:06
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.frameworkdemo.test.httpTest
 * @class_name: Mode
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class Mode {
    String str = "";
    public void getData(String key, String info, HttpManager manager, final CallBack callBack){

        manager.createApi(TestApi.class)
                .defaultTest(key,info)
                .compose(RxSchedulers.defaultCompose())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        callBack.getData(o.toString());
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public interface CallBack{
        void getData(String str);
    }
}
