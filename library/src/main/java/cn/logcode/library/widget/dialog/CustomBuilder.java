package cn.logcode.library.widget.dialog;

import android.content.Context;
import android.support.annotation.StyleRes;
import android.support.v4.app.FragmentManager;
import android.view.View;

/**
 * Created by CaostGrace on 2018/6/1 11:26
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.library.widget.dialog
 * @class_name: CustomBuilder
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class CustomBuilder implements IBuilder {
    private DefaultDialogFragment.DialogParams params;

    private DialogManager.ColorMode colorMode;
    private FragmentManager fm;

    public CustomBuilder(FragmentManager fm, DialogManager.ColorMode colorMode) {
        params = new DefaultDialogFragment.DialogParams();
        this.colorMode = colorMode;
        this.fm = fm;
    }

    public DialogManager build() {
//        return new DialogManager(new CustomDialog(context, params,colorMode));
        params.colorMode = colorMode;
        return new DialogManager(DefaultDialogFragment.newInstance(fm,params));
    }

    //设置信息
    public CustomBuilder setMessage(String msg) {
        params.msg = msg;
        return this;
    }

    public CustomBuilder setMessage(String msg, DialogManager.HighMode mode) {
        params.msg = msg;
        params.highModeMap.put(CustomDialog.DialogParams.CONTENT, mode);
        return this;
    }

    //设置标题
    public CustomBuilder setTitle(String title) {
        params.title = title;
        return this;
    }

    public CustomBuilder setTitle(String title, DialogManager.HighMode mode) {
        params.title = title;
        params.highModeMap.put(CustomDialog.DialogParams.TITLE, mode);
        return this;
    }

    //确定按钮
    public CustomBuilder setPositiveButton(String text, View.OnClickListener listener) {
        params.positiveText = text;
        params.positiveListener = listener;
        return this;
    }

    public CustomBuilder setPositiveButton(String text, View.OnClickListener listener,
                                           DialogManager.HighMode mode) {
        params.positiveText = text;
        params.positiveListener = listener;
        params.highModeMap.put(CustomDialog.DialogParams.POSITIVE, mode);
        return this;
    }

    //取消按钮
    public CustomBuilder setNegativeButton(String text, View.OnClickListener listener) {
        params.negativeText = text;
        params.negativeListener = listener;
        return this;
    }

    public CustomBuilder setNegativeButton(String text, View.OnClickListener listener,
                                           DialogManager.HighMode mode) {
        params.negativeText = text;
        params.negativeListener = listener;
        params.highModeMap.put(CustomDialog.DialogParams.NEGATIVE, mode);
        return this;
    }

    //中间按钮
    public CustomBuilder setNeutralButton(String text, View.OnClickListener listener) {
        params.neutralText = text;
        params.neutralListener = listener;
        params.isVisibilityNeutralButton = true;
        return this;
    }

    public CustomBuilder setNeutralButton(String text, View.OnClickListener listener,
                                          DialogManager.HighMode mode) {
        params.neutralText = text;
        params.neutralListener = listener;
        params.isVisibilityNeutralButton = true;
        params.highModeMap.put(CustomDialog.DialogParams.NEUTRAL, mode);
        return this;
    }

    //设置不能外部点击取消
    public CustomBuilder setCancelable(boolean flag) {
        params.cancelable = flag;
        return this;
    }

    public CustomBuilder setOnlyDisPalyPositive(boolean flag) {
        params.onlyVisibilityPositiveButton = flag;
        return this;
    }

    public CustomBuilder setView(View view) {
        params.view = view;
        params.isCustomView = true;
        return this;
    }

    public CustomBuilder setDimAmount(float dimAmount) {
        params.dimAmount = dimAmount;
        return this;
    }

    public CustomBuilder setAlpha(float alpha){
        params .alpha = alpha;
        return this;
    }

    public CustomBuilder setGravity(int gravity) {
        params.gravity = gravity;
        return this;
    }

    public CustomBuilder setWindowAnimations(@StyleRes int id){
        params.animStyle = id;
        return this;
    }

}
