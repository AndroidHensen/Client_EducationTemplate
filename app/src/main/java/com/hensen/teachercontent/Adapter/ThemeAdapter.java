package com.hensen.teachercontent.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hensen.shixiongsdk.ImageLoader.CommonImageLoader;
import com.hensen.teachercontent.Bean.Data;
import com.hensen.teachercontent.R;

import java.util.List;

public class ThemeAdapter extends BaseAdapter {

    private List<Data> list;
    private Context context;
    private LayoutInflater mInflater;
    private CommonImageLoader commonImageLoader;

    public ThemeAdapter(Context context, List<Data> list) {
        this.list = list;
        this.context = context;
        mInflater = LayoutInflater.from(context);
        commonImageLoader = CommonImageLoader.getInstance(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_theme, null);
        }
        ViewHolder holder = getViewHolder(convertView);
        Data data = list.get(position);

        holder.tv_title.setText(data.getTitle());
        holder.tv_small_title.setText(data.getSmall_title());
        commonImageLoader.displayImage(data.getThumb(),holder.iv_theme);
        return convertView;
    }

    private ViewHolder getViewHolder(View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null) {
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        return holder;
    }

    private class ViewHolder {

        private TextView tv_title, tv_small_title;
        private ImageView iv_theme;

        ViewHolder(View view) {
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_small_title = (TextView) view.findViewById(R.id.tv_small_title);
            iv_theme = (ImageView) view.findViewById(R.id.iv_theme);
        }
    }

}
