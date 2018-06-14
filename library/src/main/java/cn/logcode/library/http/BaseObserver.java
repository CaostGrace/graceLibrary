package cn.logcode.library.http;

import android.util.Log;

import cn.logcode.library.Log.LogUtils;
import cn.logcode.library.mvp.IView;
import cn.logcode.library.utils.Utils;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by CaostGrace on 2018/6/14 12:20
 *
 * @project_name: graceLibrary
 * @package_name: cn.logcode.library.http
 * @class_name: BaseObserver
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public abstract class BaseObserver<T> implements Observer<T> {
    private static final String TAG = "BaseObserver";


    private Disposable mDisposable;

    public Disposable getDisposable() {
        return mDisposable;
    }

    protected IView mView;

    public BaseObserver(IView iView){
        this.mView = iView;
    }

    public BaseObserver(){}


    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;

        if(!Utils.isNetworkConnected()) {
            LogUtils.d("无网络连接");
            mView.showErrorView("无网络连接");
            return;
        }
        if(mView != null){
            mView.showLoadingView("加载中");
            LogUtils.d("加载框");
        }
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.e(TAG, "error:" + e.toString());
        if(mView != null){
            mView.hideLoadingView();
            mView.showErrorMsg(e.getMessage());
            LogUtils.d("error:" + e.toString());
        }
        onHandleError(e.getMessage());
    }

    @Override
    public void onComplete() {
        LogUtils.d("onComplete" );
    }

    protected void onHandleError(String msg) {
    }

}
