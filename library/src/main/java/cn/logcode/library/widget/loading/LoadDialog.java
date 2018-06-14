package cn.logcode.library.widget.loading;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import cn.logcode.library.Log.LogUtils;
import cn.logcode.library.R;
import cn.logcode.library.utils.UIUtils;

/**
 * Created by CaostGrace on 2018/6/9 19:33
 *
 * @project_name: graceLibrary
 * @package_name: cn.logcode.library.widget.loading
 * @class_name: LoadDialog
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class LoadDialog extends Dialog {
    private Context mContext;

    private View mDialogView;
    private Animation mModalInAnim;
    private Animation mModalOutAnim;
    private TextView mTitleTextView;

    private static GradientDrawable gradientDrawable;

    private Handler handler = new Handler();


    public LoadDialog(@NonNull Context context) {
        super(context, R.style.alert_dialog);
        mContext = context;

        setCancelable(true);
        setCanceledOnTouchOutside(false);
        mModalInAnim = AnimationUtils.loadAnimation(mContext, R.anim.modal_in);
        mModalOutAnim = AnimationUtils.loadAnimation(mContext, R.anim.modal_out);
        mModalOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mDialogView.post(new Runnable() {
                    @Override
                    public void run() {
                        LoadDialog.super.cancel();
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        if (gradientDrawable == null) {
            gradientDrawable = new GradientDrawable();

            gradientDrawable.setColor(Color.parseColor("#a7333333"));

            gradientDrawable.setCornerRadius(UIUtils.dp2px(8));
        }

        mDialogView = View.inflate(mContext, R.layout.library_dialog_loadview, null);
        mDialogView.setBackground(gradientDrawable);
        mTitleTextView = mDialogView.findViewById(R.id.title_text);

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mDialogView);

    }


    public static LoadDialog create(Context context) {
        return new LoadDialog(context);
    }

    public LoadDialog setTitleText(String text) {
        if (mTitleTextView != null) {
            mTitleTextView.setText(text);
        }
        return this;
    }


    protected void onStart() {
        mDialogView.startAnimation(mModalInAnim);
    }
    
    @Override
    public void cancel() {
        mDialogView.startAnimation(mModalOutAnim);
    }


    public void show(long time) {
        show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cancel();
            }
        }, time);
    }
}
