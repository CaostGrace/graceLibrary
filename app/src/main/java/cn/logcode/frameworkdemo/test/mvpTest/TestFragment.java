package cn.logcode.frameworkdemo.test.mvpTest;

import cn.logcode.frameworkdemo.R;
import cn.logcode.library.Log.LogUtils;
import cn.logcode.library.mvp.fragment.FragmentDelegate;

/**
 * Created by CaostGrace on 2018/6/14 11:06
 *
 * @project_name: graceLibrary
 * @package_name: cn.logcode.frameworkdemo.test.mvpTest
 * @class_name: TestFragment
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class TestFragment extends FragmentDelegate<FragmentView,TestModel> {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_mvp;
    }

    @Override
    public void init() {
        LogUtils.d("init");
    }

    @Override
    public Class<?> getViewClass() {
        return FragmentView.class;
    }

    @Override
    public Class<?> getModelClass() {
        return TestModel.class;
    }
}
