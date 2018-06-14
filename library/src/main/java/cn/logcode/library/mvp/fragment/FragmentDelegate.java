package cn.logcode.library.mvp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.logcode.library.mvp.IDelegate;
import cn.logcode.library.mvp.activity.ActivityDelegate;
import cn.logcode.library.mvp.IModel;
import cn.logcode.library.mvp.IView;

/**
 * Created by CaostGrace on 2018/6/14 9:54
 *
 * @project_name: graceLibrary
 * @package_name: cn.logcode.library.mvp.fragment
 * @class_name: FragmentDelegate
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public abstract class FragmentDelegate<V extends IView,M extends IModel> extends Fragment implements IDelegate {

    protected V mView;
    protected M mModel;

    protected View parent;

    protected Unbinder mUnbinder;

    public V getViewDelegate(){
        return mView;
    }

    public M getModel() {
        return mModel;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            mView = (V) getViewClass().newInstance();
            mModel = (M) getModelClass().newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mModel.onAttach(this);


    }

    public abstract @LayoutRes int getLayoutId();

    @Override
    public View getRootView() {
        return parent;
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parent =  inflater.inflate(getLayoutId(),null);
        mView.onAttach(this,true);
        mUnbinder = ButterKnife.bind(this,parent);
        init();
        return parent;
    }


    public abstract void init();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mView.deAttach();
        mModel.deAttach();
        mUnbinder.unbind();
    }
}
