package cn.logcode.frameworkdemo.test.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.logcode.frameworkdemo.R;
import cn.logcode.library.widget.dialog.DialogManager;

/**
 * Created by CaostGrace on 2018/6/14 10:50
 *
 * @project_name: graceLibrary
 * @package_name: cn.logcode.frameworkdemo.test.test
 * @class_name: Test
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class Test extends AppCompatActivity {


    @BindView(R.id.frame_layout)
    public FrameLayout mFrameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_mvp);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.test01,R.id.test02})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.test01:
                TestFragment testFragment = new TestFragment();

                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().add(R.id.frame_layout,testFragment).commit();

                break;
        }
    }

}
