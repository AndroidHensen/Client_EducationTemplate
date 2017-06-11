package com.hensen.teachercontent.Bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.hensen.teachercontent.Api.RequestCenter;

/**
 * @author 许英俊 2017/5/7
 */
public class Data implements Parcelable {

    /**
     * news_id : 31
     * catid : 0
     * title :
     * small_title :
     * title_font_color : null
     * thumb : /upload/2017/05/07/590ed1e7d9893.png
     * keywords :
     * description :
     * posids :
     * listorder : 0
     * status : 1
     * copyfrom : null
     * username : admin
     * create_time : 1494143285
     * update_time : 0
     * count : 0
     * content : null
     * author : null
     */

    private String news_id;
    private String catid;
    private String title;
    private String small_title;
    private String title_font_color;
    private String thumb;
    private String keywords;
    private String description;
    private String posids;
    private String listorder;
    private String status;
    private String copyfrom;
    private String username;
    private String create_time;
    private String update_time;
    private String count;
    private String content;
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCopyfrom() {
        return copyfrom;
    }

    public void setCopyfrom(String copyfrom) {
        this.copyfrom = copyfrom;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getListorder() {
        return listorder;
    }

    public void setListorder(String listorder) {
        this.listorder = listorder;
    }

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }

    public String getPosids() {
        return posids;
    }

    public void setPosids(String posids) {
        this.posids = posids;
    }

    public String getSmall_title() {
        return small_title;
    }

    public void setSmall_title(String small_title) {
        this.small_title = small_title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getThumb() {
        if(TextUtils.isEmpty(thumb)){
            return "";
        }else{
            return RequestCenter.ROOT_URL + thumb;
        }
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_font_color() {
        return title_font_color;
    }

    public void setTitle_font_color(String title_font_color) {
        this.title_font_color = title_font_color;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Data() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.news_id);
        dest.writeString(this.catid);
        dest.writeString(this.title);
        dest.writeString(this.small_title);
        dest.writeString(this.title_font_color);
        dest.writeString(this.thumb);
        dest.writeString(this.keywords);
        dest.writeString(this.description);
        dest.writeString(this.posids);
        dest.writeString(this.listorder);
        dest.writeString(this.status);
        dest.writeString(this.copyfrom);
        dest.writeString(this.username);
        dest.writeString(this.create_time);
        dest.writeString(this.update_time);
        dest.writeString(this.count);
        dest.writeString(this.content);
        dest.writeString(this.author);
    }

    protected Data(Parcel in) {
        this.news_id = in.readString();
        this.catid = in.readString();
        this.title = in.readString();
        this.small_title = in.readString();
        this.title_font_color = in.readString();
        this.thumb = in.readString();
        this.keywords = in.readString();
        this.description = in.readString();
        this.posids = in.readString();
        this.listorder = in.readString();
        this.status = in.readString();
        this.copyfrom = in.readString();
        this.username = in.readString();
        this.create_time = in.readString();
        this.update_time = in.readString();
        this.count = in.readString();
        this.content = in.readString();
        this.author = in.readString();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel source) {
            return new Data(source);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
}
