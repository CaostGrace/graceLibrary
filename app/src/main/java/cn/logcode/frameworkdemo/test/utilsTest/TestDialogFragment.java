package cn.logcode.frameworkdemo.test.utilsTest;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.logcode.library.Log.LogUtils;

/**
 * Created by CaostGrace on 2018/6/1 22:18
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.frameworkdemo.test.utilsTest
 * @class_name: TestDialogFragment
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class TestDialogFragment extends DialogFragment {

    // D/onCreate: [ (TestDialogFragment.java:28)#onCreate ] onCreate
    // D/onGetLayoutInflater: [ (TestDialogFragment.java:54)#onGetLayoutInflater ] onGetLayoutInflater
    // D/onCreateDialog: [ (TestDialogFragment.java:42)#onCreateDialog ] onCreateDialog
    // D/onCreateView: [ (TestDialogFragment.java:34)#onCreateView ] onCreateView
    // D/onActivityCreated: [ (TestDialogFragment.java:48)#onActivityCreated ] onActivityCreated


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d("onCreate","onCreate");
        LogUtils.d(getDialog() == null);   //  null
    }

    @Override
    public LayoutInflater onGetLayoutInflater(Bundle savedInstanceState) {
        LogUtils.d("onGetLayoutInflater","onGetLayoutInflater");
        LogUtils.d(getDialog() == null);   //  null
        return super.onGetLayoutInflater(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LogUtils.d("onCreateDialog","onCreateDialog");
        LogUtils.d(getDialog() == null);   // null
        return super.onCreateDialog(savedInstanceState); //调用之后不为空
    }


    //父类方法为空, getView()方法的值在这里得到
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.d("onCreateView","onCreateView");
        LogUtils.d(getDialog() == null);
        return inflater.inflate(cn.logcode.library.R.layout.library_dialog_default,null);
    }


    //View view = getView();
    //mDialog.setContentView(view);
    //mDialog.setCancelable(mCancelable);
    //mDialog.setOnCancelListener(this);
    //mDialog.setOnDismissListener(this);
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        LogUtils.d("onActivityCreated","onActivityCreated");
        LogUtils.d(getDialog() == null);
        super.onActivityCreated(savedInstanceState);
    }


}
