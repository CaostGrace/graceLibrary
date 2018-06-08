package cn.logcode.library.mvp;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;

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
    public void onAttach(IDelegate delegate) {
        mDelegate = delegate;
        rootView = delegate.getRootView();
        mContext = (Context) mDelegate;
        initView();
    }


    @Override
    public void deAttach() {
        mDelegate = null;
        mContext = null;
        mViews = null;
        rootView = null;
    }

    public abstract void initView();

}
