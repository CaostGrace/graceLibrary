package cn.logcode.library.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.logcode.library.Log.LogUtils;
import cn.logcode.library.R;
import cn.logcode.library.utils.UIUtils;

/**
 * Created by CaostGrace on 2018/6/1 17:04
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.library.widget.dialog
 * @class_name: PoPDialog
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class PopDialogFragment extends DialogFragment implements Idialog {
    public static final String TAG = PopDialogFragment.class.getSimpleName();

    private FrameLayout parent;

    private DialogParams params;
    private Dialog dialog;
    private Window window;

    private ListView content;
    private TextView cancel;

    private DialogManager.ColorMode colorMode;

    private FragmentManager fm;

    public static PopDialogFragment newInstance(FragmentManager fm, DialogParams params) {
        PopDialogFragment popDialogFragment = new PopDialogFragment();
        popDialogFragment.params = params;
        popDialogFragment.colorMode = params.colorMode;
        popDialogFragment.fm = fm;
        return popDialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parent = (FrameLayout) View.inflate(getContext(), R.layout.library_dailog_base, null);

        if(savedInstanceState != null){
            params = (DialogParams) savedInstanceState.getSerializable(TAG);
            colorMode = params.colorMode;
        }

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(TAG,params);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        dialog = new Dialog(getContext());
        window = dialog.getWindow();

        init();
        buildParams();
        return dialog;
    }


    //基本初始化
    private void init() {
        //去掉dialog的灰色背景
        window.setBackgroundDrawable(new ColorDrawable(0));
        //直接在phoneWindow中设置内容布局，而不是在Dialog中设置内容布局
        if (params.isCustomView) {
            parent.addView(params.view);
        } else {
            parent.addView(View.inflate(getContext(), R.layout.library_dialog_pop, null));
            initView();
            buidDisplay();
        }
        window.setContentView(parent);
    }

    //View Id 初始化
    private void initView() {
        content = parent.findViewById(R.id.list_content);
        cancel = parent.findViewById(R.id.cancel);

    }

    //调整布局及设置内容
    private void buidDisplay(){
        cancel.setText(params.cancelText);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(params.cancelListener != null){
                    params.cancelListener.onClick(v);
                }
                dismiss();
            }
        });

        switch (params.cancelMode){
            case HIGH:
                cancel.setTextColor(colorMode.highColor);
                break;
            case NORMAL:
                cancel.setTextColor(colorMode.normalColor);
                break;
            case NEUTRAL:
                cancel.setTextColor(colorMode.neutralColor);
                break;
        }

        content.setAdapter(new PopContentAdapter(params.items,getContext(),dialog,colorMode));

        int listViewHeight = 0;
        for (int i=0;i<params.items.size();i++){
            listViewHeight += UIUtils.dp2px(50);
            if(listViewHeight > (UIUtils.getScreenWidth()/2)){
                ViewGroup.LayoutParams layoutParams = content.getLayoutParams();
                layoutParams.height = UIUtils.getScreenWidth()/2;
                LogUtils.d(layoutParams.height);
                content.setLayoutParams(layoutParams);
                break;
            }
        }
    }

    //调整Dialog参数
    private void buildParams() {

        //设置Dialog占屏幕的宽度
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = UIUtils.getScreenWidth() - UIUtils.dp2px(30);
        FrameLayout.LayoutParams frameParams = (FrameLayout.LayoutParams) parent.getLayoutParams();
        frameParams.bottomMargin = UIUtils.dp2px(10);
        layoutParams.windowAnimations = R.style.dialogAnimations;
        if(params.animStyle != 0){
            layoutParams.windowAnimations = params.animStyle;
        }
        layoutParams.alpha = params.alpha;
        layoutParams.dimAmount = params.dimAmount;
        layoutParams.gravity = Gravity.BOTTOM;
        
        window.setAttributes(layoutParams);

    }


    @Override
    public Dialog getDailog() {
        return dialog;
    }

    @Override
    public void show() {
//        dialog.setCancelable(params.cancelable);
//        dialog.show();
        super.show(fm,TAG);
    }

    public static class DialogParams implements Serializable{
        public View view;
        //是否是自己定义的View
        public boolean isCustomView = false;
        //dialog动画
        public int animStyle = 0;
        //背景透明度
        public float dimAmount = 0.6f;
        //前景透明度
        public float alpha = 1.0f;
        //能否外部点击取消
        public boolean cancelable = true;
        //item
        public List<PopItem> items = new ArrayList<>();
        //取消按钮的文字
        public String cancelText = "取消";
        //取消点击事件
        public View.OnClickListener cancelListener;
        //取消高亮模式
        public DialogManager.HighMode cancelMode = DialogManager.HighMode.NORMAL;


        public DialogManager.ColorMode colorMode;
    }


}
