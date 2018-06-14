package cn.logcode.library.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.StyleRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.transition.Slide;
import android.util.ArrayMap;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Map;

import cn.logcode.library.Log.LogUtils;
import cn.logcode.library.R;
import cn.logcode.library.utils.UIUtils;

/**
 * Created by CaostGrace on 2018/5/31 15:39
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.library.utils
 * @class_name: DialogUtils
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class CustomDialog implements Idialog {

    private Context context;
    private DialogParams params;
    private FrameLayout parent;

    @Override
    public Dialog getDailog() {
        return dialog;
    }

    public View getView() {
        return parent;
    }

    private Dialog dialog;
    private Window window;

    public Window getWindow() {
        return window;
    }

    private TextView title;     //标题
    private TextView content;   //内容区域
    private TextView negative;  //取消按钮
    private TextView neutral;   //中间按钮
    private TextView positive;  //确定按钮

    private View vertical_divider01;     //第一个垂直分割线
    private View vertical_divider02;     //第二个垂直分割线

    private DialogManager.ColorMode colorMode;


    private DefaultDialogFragment defaultDialogFragment;

    public CustomDialog(Context context, DialogParams params, DialogManager.ColorMode colorMode) {
        this.context = context;
        this.params = params;
        this.colorMode = colorMode;
        parent = (FrameLayout) View.inflate(context, R.layout.library_dailog_base, null);

//        defaultDialogFragment = DefaultDialogFragment.newInstance(context);

        dialog = new Dialog(context);
//        dialog = defaultDialogFragment.getDialog();
        window = dialog.getWindow();
        init();
        buildParams();
    }

    //基本初始化
    private void init() {
        //去掉dialog的灰色背景
        window.setBackgroundDrawable(new ColorDrawable(0));
        //直接在phoneWindow中设置内容布局，而不是在Dialog中设置内容布局
        if (params.isCustomView) {
            parent.addView(params.view);
        } else {
            parent.addView(View.inflate(context, R.layout.library_dialog_default, null));
            initView();
            buidDisplay();
        }
        window.setContentView(parent);
    }

    //View Id 初始化
    private void initView() {
        title = parent.findViewById(R.id.title);
        content = parent.findViewById(R.id.content);
        negative = parent.findViewById(R.id.negative);
        neutral = parent.findViewById(R.id.neutral);
        positive = parent.findViewById(R.id.positive);
        vertical_divider01 = parent.findViewById(R.id.vertical_divider01);
        vertical_divider02 = parent.findViewById(R.id.vertical_divider02);
    }

    //调整Dialog参数
    private void buildParams() {

        //设置Dialog占屏幕的宽度
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = UIUtils.getScreenWidth() - UIUtils.dp2px(60);
        //设置显示位置
        if (params.gravity == Gravity.BOTTOM) {
            layoutParams.width = UIUtils.getScreenWidth() - UIUtils.dp2px(30);
            FrameLayout.LayoutParams frameParams = (FrameLayout.LayoutParams) parent.getLayoutParams();
            frameParams.bottomMargin = UIUtils.dp2px(10);
            layoutParams.windowAnimations = R.style.dialogAnimations;
        }
        if (params.animStyle != 0) {
            layoutParams.windowAnimations = params.animStyle;
        }
        layoutParams.gravity = params.gravity;
        layoutParams.alpha = params.alpha;
        layoutParams.dimAmount = params.dimAmount;


        window.setAttributes(layoutParams);

    }

    //调整布局及设置内容
    private void buidDisplay() {
        title.setText(params.title);
        content.setText(params.msg);

        if (params.onlyVisibilityPositiveButton) {
            vertical_divider01.setVisibility(View.GONE);
            vertical_divider02.setVisibility(View.GONE);
            negative.setVisibility(View.GONE);
            neutral.setVisibility(View.GONE);
        } else if (params.isVisibilityNeutralButton) {
            neutral.setText(params.neutralText);
            neutral.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (params.neutralListener != null) {
                        params.neutralListener.onClick(v);
                    }
                    dialog.dismiss();
                }
            });

            negative.setText(params.negativeText);
            negative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (params.negativeListener != null) {
                        params.negativeListener.onClick(v);
                    }
                    dialog.dismiss();
                }
            });
        } else {
            neutral.setVisibility(View.GONE);
            vertical_divider02.setVisibility(View.GONE);

            negative.setText(params.negativeText);
            negative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (params.negativeListener != null) {
                        params.negativeListener.onClick(v);
                    }
                    dialog.dismiss();
                }
            });
        }
        positive.setText(params.positiveText);
        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (params.positiveListener != null) {
                    params.positiveListener.onClick(v);
                }
                dialog.dismiss();
            }
        });
        setHighMode();
    }

    //设置文字高亮模式
    private void setHighMode() {
        if (title.getVisibility() == View.VISIBLE) {
            DialogManager.HighMode mode = params.highModeMap.get(DialogParams.TITLE);
            if (mode != null) {
                setHighText(mode, title);
            }
        }
        if (content.getVisibility() == View.VISIBLE) {
            DialogManager.HighMode mode = params.highModeMap.get(DialogParams.CONTENT);
            if (mode != null) {
                setHighText(mode, content);
            }
        }
        if (negative.getVisibility() == View.VISIBLE) {
            DialogManager.HighMode mode = params.highModeMap.get(DialogParams.NEGATIVE);
            if (mode != null) {
                setHighText(mode, negative);
            }
        }
        if (neutral.getVisibility() == View.VISIBLE) {
            DialogManager.HighMode mode = params.highModeMap.get(DialogParams.NEUTRAL);
            if (mode != null) {
                setHighText(mode, neutral);
            }
        }
        if (positive.getVisibility() == View.VISIBLE) {
            DialogManager.HighMode mode = params.highModeMap.get(DialogParams.POSITIVE);
            if (mode != null) {
                setHighText(mode, positive);
            }
        }
    }

    private void setHighText(DialogManager.HighMode mode, TextView view) {
        switch (mode) {
            case HIGH:
                view.setTextColor(colorMode.highColor);
                break;
            case NEUTRAL:
                view.setTextColor(colorMode.neutralColor);
                break;
            case NORMAL:
                view.setTextColor(colorMode.normalColor);
                break;
        }
    }

    //显示
    public void show() {
        dialog.setCancelable(params.cancelable);
        dialog.show();
//        defaultDialogFragment.show(((FragmentActivity)context).getSupportFragmentManager(),
//                "customDialog");
    }

    @Override
    public void dismiss() {
        dialog.dismiss();
    }


    public static class DialogParams {
        public static final String TITLE = "TITLE";
        public static final String CONTENT = "CONTENT";
        public static final String POSITIVE = "POSITIVE";
        public static final String NEUTRAL = "NEUTRAL";
        public static final String NEGATIVE = "NEGATIVE";


        public View view;
        //内容
        public String msg = "";
        //标题
        public String title = "Title";
        //确定按钮文本
        public String positiveText = "confirm";
        //确定按钮事件
        public View.OnClickListener positiveListener;
        //取消按钮文本
        public String negativeText = "cancel";
        //取消按钮事件
        public View.OnClickListener negativeListener;
        //中间按钮文本
        public String neutralText = "cancel";
        //中间按钮事件
        public View.OnClickListener neutralListener;
        //是否仅显示确定按钮
        public boolean onlyVisibilityPositiveButton = false;
        //是否显示中间按钮
        public boolean isVisibilityNeutralButton = false;
        //能否外部点击取消
        public boolean cancelable = true;
        //设置布局位置
        public int gravity = Gravity.CENTER;
        //是否是自己定义的View
        public boolean isCustomView = false;
        //高亮模式map
        public Map<String, DialogManager.HighMode> highModeMap = new ArrayMap<>();
        //背景透明度
        public float dimAmount = 0.6f;
        //前景透明度
        public float alpha = 1.0f;
        //dialog动画
        public int animStyle = 0;
    }
}
