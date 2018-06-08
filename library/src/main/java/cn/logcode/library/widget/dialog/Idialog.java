package cn.logcode.library.widget.dialog;

import android.app.Dialog;
import android.view.View;

import java.io.Serializable;

/**
 * Created by CaostGrace on 2018/6/1 11:13
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.library.widget.dialog
 * @class_name: Idialog
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public interface Idialog extends Serializable{
    Dialog getDailog();
    View getView();
    void show();
    void dismiss();
}
