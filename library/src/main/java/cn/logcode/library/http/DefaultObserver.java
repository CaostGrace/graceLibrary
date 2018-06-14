package cn.logcode.library.http;

import cn.logcode.library.Log.LogUtils;
import cn.logcode.library.mvp.IView;
import cn.logcode.library.utils.Utils;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by CaostGrace on 2018/6/7 21:23
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.library.http
 * @class_name: DefaultObserver
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public abstract class DefaultObserver<T> extends BaseObserver<T> {

    public static final String TAG = DefaultObserver.class.getSimpleName();


    public DefaultObserver(){
        super();
    }

    public DefaultObserver(IView iView){
        super(iView);
    }

    @Override
    public void onNext(T t) {
        if (mView != null){
            mView.hideLoadingView();
            LogUtils.d("取消加载框");
        }
        onHandleSuccess(t);
    }

    public abstract void onHandleSuccess(T t);
}
