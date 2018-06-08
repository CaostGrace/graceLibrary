package cn.logcode.library.http;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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
public abstract class EntityObserver<T> implements Observer<BaseEntity<T>> {

    private static final String TAG = "EntityObserver";
    private Context mContext;

    protected EntityObserver(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public EntityObserver(){}

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseEntity<T> value) {
        if (value.code == 200) {
            T t = value.data;
            onHandleSuccess(t);
        } else {
            onHandleError(value.msg);
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "error:" + e.toString());
        onHandleError(e.getMessage());
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete");
    }


    protected abstract void onHandleSuccess(T t);

    protected void onHandleError(String msg) {
//        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}

