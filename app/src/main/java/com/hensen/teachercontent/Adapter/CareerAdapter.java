package com.hensen.teachercontent.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hensen.shixiongsdk.Controller.ActivityController;
import com.hensen.teachercontent.Bean.Career;
import com.hensen.teachercontent.R;

import java.util.List;

public class CareerAdapter extends BaseAdapter implements View.OnClickListener {

    private List<Career> list;
    private Context context;
    private LayoutInflater mInflater;
    private ActivityController activityController;

    public CareerAdapter(Context context, List<Career> list) {
        this.list = list;
        this.context = context;
        mInflater = LayoutInflater.from(context);
        activityController = ActivityController.getInstance(context);
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
            convertView = mInflater.inflate(R.layout.adapter_career, null);
        }
        ViewHolder holder = getViewHolder(convertView);
        Career career = list.get(position);

        holder.tv_title.setText(career.getTitle());
        holder.tv_content.setText(career.getContent());
        holder.iv_career_img.setBackgroundResource(career.getImgId());
        holder.bt_jump.setTag(career.getJump_url());
        holder.bt_jump.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        String url = (String) v.getTag();
        activityController.startWebViewActivity(url);
    }

    private class ViewHolder {

        private ImageView iv_career_img;
        private TextView tv_title, tv_content;
        private Button bt_jump;

        ViewHolder(View view) {
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_content = (TextView) view.findViewById(R.id.tv_content);
            iv_career_img = (ImageView) view.findViewById(R.id.iv_career_img);
            bt_jump = (Button) view.findViewById(R.id.bt_jump);
        }
    }


}
