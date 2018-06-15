package cn.logcode.library.mvp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.SparseArray;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.logcode.library.Log.LogUtils;

/**
 * Created by CaostGrace on 2018/6/6 20:09
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.frameworkdemo.mvp
 * @class_name: BaseView
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public abstract class BaseView implements IView {
    public View rootView;

    public IDelegate mDelegate;

    public Context mContext;

    public View getRootView() {
        return rootView;
    }

    private SparseArray<View> mViews = new SparseArray<>();


    protected FragmentManager mFragmentManager;

    public FragmentManager getFragmentManager() {
        return mFragmentManager;
    }

    public <T extends View> T bindView(int id) {
        T view = (T) mViews.get(id);
        if (view == null) {
            view = rootView.findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }

    public <T extends View> T get(int id) {
        return (T) bindView(id);
    }



    @Override
    public void onAttach(IDelegate delegate,boolean isFragment) {
        mDelegate = delegate;
        rootView = delegate.getRootView();
        mContext = delegate.getContext();

        if(isFragment){
            mFragmentManager = ((Fragment)delegate).getChildFragmentManager();
        }else{
            mFragmentManager = ((FragmentActivity)mContext).getSupportFragmentManager();
        }

        initView();
    }

    @Override
    public void onCreate() {


    }

    @Override
    public void deAttach() {
        mDelegate = null;
        mContext = null;
        mViews = null;
        rootView = null;
    }

    //这里面不能使用ButterKnife
    public void initView(){}

}
