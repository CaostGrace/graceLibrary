package cn.logcode.library.widget.dialog;

import android.content.Context;
import android.support.annotation.StyleRes;
import android.support.v4.app.FragmentManager;
import android.view.View;

/**
 * Created by CaostGrace on 2018/6/1 17:05
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.library.widget.dialog
 * @class_name: PoPBuilder
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class PopBuilder implements IBuilder {
    private PopDialogFragment.DialogParams params;

    private DialogManager.ColorMode colorMode;

    private FragmentManager fm;

    public PopBuilder(FragmentManager fm, DialogManager.ColorMode colorMode){
        params = new PopDialogFragment.DialogParams();
        this.colorMode = colorMode;
        this.fm = fm;
    }

    public PopBuilder addItem(String str){
        addItem(str,null, DialogManager.HighMode.NORMAL);
        return this;
    }
    public PopBuilder addItem(String str, DialogManager.HighMode mode){
        addItem(str,null,mode);
        return this;
    }

    public PopBuilder addItem(String str, View.OnClickListener listener){
        addItem(str,listener, DialogManager.HighMode.NORMAL);
        return this;
    }

    public PopBuilder addItem(String str, View.OnClickListener listener, DialogManager.HighMode mode){
        PopItem item = new PopItem();
        item.content = str;
        item.listener  =listener;
        item.mode = mode;
        addItem(item);
        return this;
    }

    public PopBuilder addItem(PopItem item){
        params.items.add(item);
        return this;
    }


    public PopBuilder setView(View view){
        params.view = view;
        params.isCustomView = true;
        return this;
    }

    //设置不能外部点击取消
    public PopBuilder setCancelable(boolean flag) {
        params.cancelable = flag;
        return this;
    }

    public PopBuilder setDimAmount(float dimAmount) {
        params.dimAmount = dimAmount;
        return this;
    }

    public PopBuilder setAlpha(float alpha){
        params .alpha = alpha;
        return this;
    }

    public PopBuilder setWindowAnimations(@StyleRes int id){
        params.animStyle = id;
        return this;
    }

    public PopBuilder setCancelListener(View.OnClickListener listener){
        params.cancelListener = listener;
        return this;
    }

    public PopBuilder setCancelText(String str){
        params.cancelText = str;
        return this;
    }

    public PopBuilder setCancelHighMode(DialogManager.HighMode mode){
        params.cancelMode = mode;
        return this;
    }

    @Override
    public DialogManager build() {
        params.colorMode = colorMode;
        return new DialogManager(PopDialogFragment.newInstance(fm,params));
    }
}
