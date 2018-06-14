package cn.logcode.frameworkdemo.test.mvpTest;

import android.view.View;
import android.widget.Toast;

import butterknife.OnClick;
import cn.logcode.frameworkdemo.R;
import cn.logcode.library.mvp.BaseView;
import cn.logcode.library.utils.ToastUtil;

/**
 * Created by CaostGrace on 2018/6/14 11:07
 *
 * @project_name: graceLibrary
 * @package_name: cn.logcode.frameworkdemo.test.mvpTest
 * @class_name: FragmentView
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class FragmentView extends BaseView {
    @Override
    public void initView() {

    }

    @OnClick({R.id.test01})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.test01:
                ToastUtil.init(mContext).makeSuccessToast("成功", Toast.LENGTH_SHORT).show();
                break;

        }
    }


    @Override
    public void showLoadingView(String str) {

    }

    @Override
    public void showErrorView(String str) {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void hideErrorView() {

    }

    @Override
    public void showErrorMsg(String str) {

    }
}
