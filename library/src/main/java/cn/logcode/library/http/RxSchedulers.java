package cn.logcode.library.http;

import android.widget.Toast;

import cn.logcode.library.Log.LogUtils;
import cn.logcode.library.utils.Utils;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by CaostGrace on 2018/5/28 21:51
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.library.http
 * @class_name: RxSchedulers
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class RxSchedulers {

    public static <R> ObservableTransformer<BaseEntity<R>, R> compose() {
        return (Observable<BaseEntity<R>> upstream) -> {
            return upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map((BaseEntity<R> t) -> t.data);
        };
    }

    public static <T> ObservableTransformer<T,T> defaultCompose(){
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


}
