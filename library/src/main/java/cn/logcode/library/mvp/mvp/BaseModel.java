package cn.logcode.library.mvp;

import android.content.Context;

import cn.logcode.library.ApplicationLibrary;
import cn.logcode.library.http.BaseObserver;
import cn.logcode.library.http.HttpManager;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by CaostGrace on 2018/6/7 20:45
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.library.mvp
 * @class_name: BaseModel
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class BaseModel implements IModel {


    private CompositeDisposable mCompositeDisposable;

    public IDelegate mDelegate;

    public Context mContext;

    public static HttpManager mHttpManager;

    public String basrUrl = "";

    @Override
    public void onAttach(IDelegate delegate) {
        mDelegate = delegate;
        mContext = delegate.getContext();
        mCompositeDisposable = new CompositeDisposable();
        if(ApplicationLibrary.INSTANCE != null){
            basrUrl =  ApplicationLibrary.INSTANCE.getBaseUrl();
        }
        if(basrUrl.equals("")){
            basrUrl = getHttpBaseUrl();
            if(basrUrl.equals("")){
                throw new IllegalStateException("使用mvp模式，需要继承ApplicationLibrary重写getBaseUrl方法返回BaseUrl 或者重写BaseModel的getHttpBaseUrl方法");
            }
        }
        if(mHttpManager == null){
            mHttpManager = HttpManager.getInstance(basrUrl);
        }
    }


    public void addDisposable(Disposable disposable){
        if(mCompositeDisposable != null){
            mCompositeDisposable.add(disposable);
        }
    }


    public void addDisposable(BaseObserver observer){
        if(mCompositeDisposable != null){
            mCompositeDisposable.add(observer.getDisposable());
        }
    }


    @Override
    public void deAttach() {
        mDelegate = null;
        mContext = null;
        if(mCompositeDisposable != null){
            mCompositeDisposable.dispose();
            mCompositeDisposable = null;
        }
    }
         



    public String getHttpBaseUrl(){
        return "";
    }

}
