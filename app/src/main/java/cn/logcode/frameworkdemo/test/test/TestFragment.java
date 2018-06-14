package cn.logcode.frameworkdemo.test.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.logcode.frameworkdemo.R;
import cn.logcode.library.Log.LogUtils;

/**
 * Created by CaostGrace on 2018/6/14 11:22
 *
 * @project_name: graceLibrary
 * @package_name: cn.logcode.frameworkdemo.test.test
 * @class_name: TestFragment
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class TestFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.d(getActivity());
        LogUtils.d(getContext());


        if(getContext() instanceof FragmentActivity){
            LogUtils.d("true");
        }

        View v = inflater.inflate(R.layout.fragment_mvp,null);
        return v;
    }
}
