package cn.logcode.frameworkdemo.test.mvpTest;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import cn.logcode.frameworkdemo.R;
import cn.logcode.library.Log.LogUtils;
import cn.logcode.library.mvp.fragment.FragmentDelegate;
import cn.logcode.library.utils.ToastUtil;

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

    @BindView(R.id.test01)
    public TextView text;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mvp;
    }

    @Override
    public void init() {
        LogUtils.d("init");
        LogUtils.d(text == null);
    }

    @Override
    public void doSomething() {
        LogUtils.d("doSomething");
        LogUtils.d(text == null);
    }


    @OnClick({R.id.test01})
    public void onClick(View v){
        switch (v.getId())
        {
            case R.id.test01:
                ToastUtil.init(getContext())
                        .makeSuccessToast("成功",Toast.LENGTH_SHORT)
                        .show();
                break;
        }
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
