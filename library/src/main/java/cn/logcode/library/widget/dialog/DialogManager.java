package cn.logcode.library.widget.dialog;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.Window;

import java.io.Serializable;

import cn.logcode.library.R;

/**
 * Created by CaostGrace on 2018/5/31 18:22
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.library.widget
 * @class_name: DialogManager
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class DialogManager {

    //文字高亮模式
    public enum HighMode implements Serializable{
        //高亮，正常，次之中间
        HIGH,NORMAL,NEUTRAL
    }

    private Idialog dialog;

    public Idialog getDialog(){
        return dialog;
    }

    DialogManager(Idialog dialog){
        this.dialog = dialog;
    }

    public View getView(){
        return dialog.getView();
    }

    public void show(){
        dialog.show();
    }

    public static class Builder{
        private Context context;
        private ColorMode colorMode;
        private FragmentManager fm;


        public Builder(){
            colorMode = new ColorMode();
        }

        public Builder(Context context){
            this.context = context;
            colorMode = new ColorMode();
        }

        public Builder setHighModeColor(int color){
            colorMode.highColor = color;
            return this;
        }
        public Builder setNormalModeColor(int color){
            colorMode.normalColor = color;
            return this;
        }

        public Builder setNeutralModeColor(int color){
            colorMode.neutralColor = color;
            return this;
        }
        public Builder setFragmentManager(FragmentManager fm){
            this.fm = fm;
            return this;
        }


        public CustomBuilder create(){
            return new CustomBuilder(fm,colorMode);
        }

        //创建底部弹出
        public PopBuilder createPoP(){
            return new PopBuilder(fm,colorMode);
        }

    }

    public static class ColorMode{
        public int highColor = 0xFFFF0000;
        public int normalColor = 0xFF1280FB;
        public int neutralColor = 0xFFbababa;
    }
}
