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
public abstract class DefaultObserver<T> implements Observer<T> {

    public static final String TAG = DefaultObserver.class.getSimpleName();

    private IView mView;

    public DefaultObserver(){}

    public DefaultObserver(IView iView){
        this.mView = iView;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if(!Utils.isNetworkConnected()) {
            LogUtils.d("无网络连接");
            mView.showErrorMsg("无网络连接");
            return;
        }
        if(mView != null){
            mView.showLoadingView("加载中");
            LogUtils.d("加载框");
        }
    }

    @Override
    public void onNext(T t) {
        if (mView != null){
            mView.hideLoadingView();
            LogUtils.d("取消加载框");
        }
        onHandleSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        if(mView != null){
            mView.hideLoadingView();
            mView.showErrorView(e.getMessage());
            LogUtils.d("错误信息");
            LogUtils.d(e.getMessage());
        }
        onHandleError(e.getMessage());
    }

    @Override
    public void onComplete() {

    }

    public abstract void onHandleSuccess(T t);

    protected void onHandleError(String msg) {
    }
}
