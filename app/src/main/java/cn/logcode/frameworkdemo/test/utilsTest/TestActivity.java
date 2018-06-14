package cn.logcode.frameworkdemo.test.utilsTest;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import cn.logcode.frameworkdemo.R;
import cn.logcode.library.Log.LogUtils;
import cn.logcode.library.utils.ToastUtil;
import cn.logcode.library.utils.Utils;
import cn.logcode.library.widget.dialog.CustomBuilder;
import cn.logcode.library.widget.dialog.CustomDialog;
import cn.logcode.library.widget.dialog.DialogManager;

/**
 * Created by CaostGrace on 2018/5/29 16:25
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.frameworkdemo.test
 * @class_name: TestActivity
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //直接new不走生命周期
//        TestDialogFragment dialogFragment = new TestDialogFragment();
//
//        dialogFragment.show(getSupportFragmentManager().beginTransaction(),"ssss");

    }

    public void test01(View view) {
        DialogManager dialogManger = new DialogManager
                .Builder(this)
                .setHighModeColor(Color.GREEN)
                .setFragmentManager(getSupportFragmentManager())
                .create()
                .setMessage("这是内容")
                .setWindowAnimations(R.style.dialogAnimations)
                .setTitle("四六级", DialogManager.HighMode.HIGH)
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.init(TestActivity.this).makeText("点击了确定",Toast.LENGTH_LONG).show();
                    }
                }, DialogManager.HighMode.HIGH)
                .build();
        dialogManger.show();
    }

    public void test02(View view) {
        DialogManager dialogManger = new DialogManager
                .Builder(this)
                .setFragmentManager(getSupportFragmentManager())
                .createPoP()
                .addItem("大声说", DialogManager.HighMode.NEUTRAL)
                .addItem("颠三倒四")
                .addItem("大大", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogUtils.d(Utils.isWIFIConnected(TestActivity.this));
                    }
                })
                .setCancelText("确定")
                .setCancelHighMode(DialogManager.HighMode.HIGH)
                .setCancelListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.init(TestActivity.this).makeText("点击了确定",Toast.LENGTH_LONG).show();
                    }
                })
                .build();
        dialogManger.show();
    }

    public void test03(View view){
        LogUtils.d(getCacheDir().getAbsolutePath());
        LogUtils.d(getExternalCacheDir().getAbsolutePath());
        LogUtils.d(getExternalFilesDir("test"));

//        LogUtils.d(getDataDir().getAbsolutePath());

//        LogUtils.d(getDir("test",MODE_PRIVATE));


        File file =  getExternalCacheDir() ;

        File file1 = new File(file,"dsas.txt");

        try {
            file1.createNewFile();
            LogUtils.d("susses");
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file2 = Environment.getExternalStorageDirectory();

        File file3 = new File(file2,"fsdasdfsd.txt");
        try {
            file3.createNewFile();
            LogUtils.d("susses");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
