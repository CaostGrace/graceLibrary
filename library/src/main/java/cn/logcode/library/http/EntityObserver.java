package cn.logcode.library.http;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import cn.logcode.library.Log.LogUtils;
import cn.logcode.library.mvp.IView;
import cn.logcode.library.utils.Utils;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by CaostGrace on 2018/5/28 22:10
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.library.http
 * @class_name: EntityObserver
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public abstract class EntityObserver<T> extends BaseObserver<BaseEntity<T>> {

    private static final String TAG = "EntityObserver";


    public EntityObserver(IView iView){
        super(iView);
    }

    public EntityObserver(){
        super();
    }



    @Override
    public void onNext(BaseEntity<T> value) {

        if (mView != null){
            mView.hideLoadingView();
            LogUtils.d("取消加载框");
        }

        if (value.code == 200) {
            T t = value.data;
            onHandleSuccess(t);
        } else {
            onHandleError(value.msg);
        }
    }


    protected abstract void onHandleSuccess(T t);

    protected void onHandleError(String msg) {
    }
}

