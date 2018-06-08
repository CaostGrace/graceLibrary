package cn.logcode.library.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;


/**
 * Created by LB on 2017/10/15 0015.
 * Toast 工具类
 */

public class ToastUtil {
    private Toast toast;
    private Context context;
    private View view;
    private TextView textTv;
    private WindowManager.LayoutParams params;
    private int mDuration;
    private boolean isCustom = false;



    private Handler handler = new Handler();

    private ToastUtil(Context context) {
        this.context = context;
        toast = new Toast(context);
        params = getWindowParams();

        view = generateView();
        setGravity(Gravity.CENTER, 0, 0);
        if (params != null) {
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        } else {
            throw new NullPointerException();
        }

    }


    public static ToastUtil init(Context context) {
        return new ToastUtil(context);
    }


    /**
     * 设置吐司view
     *
     * @param v 视图
     */
    public ToastUtil setView(@Nullable View v) {
        if (v == null) {
            throw new NullPointerException("view must not null");
        }
        view = v;
        isCustom = true;
        return this;
    }

    /**
     * 设置吐司view
     *
     * @param viewId 视图
     */
    public ToastUtil setView(@Nullable int viewId) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(viewId, null);
        isCustom = true;
        return this;
    }


    /**
     * 得到view
     *
     * @return
     */
    public View getView() {
        return view;
    }

    /**
     * 设置gravity
     *
     * @param gravity
     * @param xOffset
     * @param yOffset
     * @return
     */
    public ToastUtil setGravity(int gravity, int xOffset, int yOffset) {
        toast.setGravity(gravity, UIUtils.dp2px(xOffset), UIUtils.dp2px(yOffset));
        return this;
    }

    public ToastUtil setGravity(int gravity) {
        setGravity(gravity, 0, 0);
        return this;
    }


    /**
     * 设置Toast高度
     *
     * @param height
     * @return
     */
    public ToastUtil setHeight(int height) {
        if (params != null) {
            params.height = UIUtils.dp2px(height);
        }
        return this;
    }

    /**
     * 设置Toast宽度
     *
     * @param width
     * @return
     */
    public ToastUtil setWidth(int width) {
        if (params != null) {
            params.width = UIUtils.dp2px(width);
        }
        return this;
    }


    /**
     * 设置view背景
     *
     * @return
     */
    public ToastUtil setViewBackgroundResource(int resid) {
        view.setBackgroundResource(resid);
        return this;
    }

    public ToastUtil setViewBackgroundColor(int color) {
        view.setBackgroundColor(color);
        return this;
    }

    public ToastUtil setViewBackgroundColor(String color) {
        view.setBackgroundColor(Color.parseColor(color));
        return this;
    }


    /**
     * 设置字体颜色
     *
     * @param color
     * @return
     */
    public ToastUtil setTextColor(int color) {
        if (!isCustom) {
            textTv.setTextColor(color);
        }
        return this;
    }

    public ToastUtil setTextColor(String color) {
        if (!isCustom) {
            textTv.setTextColor(Color.parseColor(color));
        }
        return this;
    }

    /**
     * 设置字体大小
     *
     * @param size
     * @return
     */
    public ToastUtil setTextSize(int size) {
        if (!isCustom) {
            textTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, UIUtils.dp2px(size));
        }
        return this;
    }


    public ToastUtil setWindowAnimations(int animId) {
        if (params != null) {
            params.windowAnimations = animId;
        }
        return this;
    }


    /**
     * 调用Toast
     */
    public Show makeText(CharSequence text, int duration) {
        if (!isCustom) {
            textTv.setText(text);
        }
        return makeText(duration);
    }

    /**
     * 调用自定义
     *
     * @param duration
     * @return
     */
    public Show makeText(int duration) {

        mDuration = duration;
        toast.setView(view);
        return new Show();
    }


    public class Show {
        private Show() {
        }

        public void show() {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mDuration <= Toast.LENGTH_SHORT) {
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (mDuration == Toast.LENGTH_LONG) {
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                        mDuration -= 4000;
                        handler.postDelayed(this, 3000 - 1);
                    }

                }
            }, 0);
        }

    }


    /**
     * 生成View
     *
     * @return
     */
    private View generateView() {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setBackgroundColor(Color.parseColor("#21201F"));


        textTv = new TextView(context);
        textTv.setTextColor(Color.WHITE);
        textTv.setGravity(Gravity.CENTER);
        textTv.setPadding(UIUtils.dp2px(15), UIUtils.dp2px(10), UIUtils.dp2px(15), UIUtils.dp2px(10));
        textTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, UIUtils.dp2px(16));


        linearLayout.addView(textTv);

        return linearLayout;
    }


    /**
     * 得到Toast的WindowManager.LayoutParams
     *
     * @return
     */
    private WindowManager.LayoutParams getWindowParams() {
        try {
            Object mTN;
            mTN = getField(toast, "mTN");
            if (mTN != null) {
                Object mParams = getField(mTN, "mParams");
                if (mParams != null
                        && mParams instanceof WindowManager.LayoutParams) {
                    return (WindowManager.LayoutParams) mParams;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 反射字段
     *
     * @param object    要反射的对象
     * @param fieldName 要反射的字段名称
     */
    private Object getField(Object object, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        if (field != null) {
            field.setAccessible(true);
            return field.get(object);
        }
        return null;
    }

}
