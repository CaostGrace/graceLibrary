package cn.logcode.library.mvp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.logcode.library.mvp.IDelegate;
import cn.logcode.library.mvp.IModel;
import cn.logcode.library.mvp.IView;
import cn.logcode.library.utils.ActivityUtils;

/**
 * Created by CaostGrace on 2018/6/6 20:08
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.frameworkdemo.mvp
 * @class_name: BaseActivity
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public abstract class ActivityDelegate<V extends IView,M extends IModel> extends AppCompatActivity implements IDelegate {

    protected V mView;
    protected M mModel;

    protected View parent;

    public V getViewDelegate(){
        return mView;
    }

//    protected Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.addActivity(this);
        try {
            mView = (V) getViewClass().newInstance();
            mModel = (M) getModelClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mModel.onAttach(this);
    }


    @Override
    public View getRootView() {
        return parent;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setContentView(int layoutResID) {
        parent = View.inflate(this,layoutResID,null);
        super.setContentView(parent);
        mView.onAttach(this,false);
//        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mView.deAttach();
        mModel.deAttach();
//        mUnbinder.unbind();
        ActivityUtils.removeActivity(this);
    }


    

}
