package com.hensen.teachercontent.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hensen.shixiongsdk.ImageLoader.CommonImageLoader;
import com.hensen.teachercontent.Bean.Data;
import com.hensen.teachercontent.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeachAdapter extends BaseAdapter {

    private List<Data> list;
    private Context context;
    private LayoutInflater mInflater;
    private CommonImageLoader commonImageLoader;

    public TeachAdapter(Context context, List<Data> list) {
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
            convertView = mInflater.inflate(R.layout.adapter_teach, null);
        }
        ViewHolder holder = getViewHolder(convertView);
        Data data = list.get(position);

        if(TextUtils.isEmpty(data.getThumb())){
            holder.civ_teach.setBackgroundResource(R.drawable.ic_default_user);
        }else{
            commonImageLoader.displayImage(data.getThumb(), holder.civ_teach);
        }
        holder.tv_teach.setText(data.getTitle());
        holder.tv_teach_small_title.setText(data.getSmall_title());
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

        private TextView tv_teach, tv_teach_small_title;
        private CircleImageView civ_teach;

        ViewHolder(View view) {
            tv_teach = (TextView) view.findViewById(R.id.tv_teach);
            tv_teach_small_title = (TextView) view.findViewById(R.id.tv_teach_small_title);
            civ_teach = (CircleImageView) view.findViewById(R.id.civ_teach);
        }
    }
}
