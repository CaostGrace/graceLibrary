package cn.logcode.library.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Matcher;

import cn.logcode.library.R;

/**
 * Created by CaostGrace on 2018/6/1 17:24
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.library.widget.dialog
 * @class_name: PopContentAdapter
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class PopContentAdapter extends BaseAdapter {
    private List<PopItem> data;
    private Context context;
    private Dialog dialog;
    int size ;
    private DialogManager.ColorMode colorMode;
    public PopContentAdapter(List<PopItem> data, Context context, Dialog dialog, DialogManager.ColorMode colorMode) {
        this.data = data;
        this.context = context;
        this.colorMode = colorMode;
        this.dialog = dialog;
        size = data.size();
    }

    @Override
    public int getCount() {
        return size;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(context, R.layout.library_dialog_pop_item,null);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.text.setText(data.get(position).content);
        if(position == size -1){
            viewHolder.divider.setVisibility(View.GONE);
        }
        performHighMode(data.get(position).mode,viewHolder.text);
        performListener(data.get(position).listener,viewHolder.text);
        return convertView;
    }

    //匹配及设置mode中的信息
    private void performHighMode(DialogManager.HighMode mode,TextView textView) {
        switch (mode){
            case NORMAL:
                textView.setTextColor(colorMode.normalColor);
                break;
            case NEUTRAL:
                textView.setTextColor(colorMode.neutralColor);
                break;
            case HIGH:
                textView.setTextColor(colorMode.highColor);
                break;
        }
    }
    //
    private void performListener(final View.OnClickListener listener, TextView textView){
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClick(v);
                }
                dialog.dismiss();
            }
        });
    }

    class ViewHolder{
        public TextView text;
        public View divider;

        public ViewHolder(View view) {
            this.text = view.findViewById(R.id.text);
            this.divider = view.findViewById(R.id.divider);
        }
    }
}
