package com.hensen.teachercontent.Bean;

/**
 * @author 许英俊 2017/5/17
 */
public class Career {

    private String title;
    private String content;
    private int imgId;
    private String jump_url;

    public Career(String title, String content, int imgId, String jump_url) {
        this.title = title;
        this.imgId = imgId;
        this.jump_url = jump_url;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getJump_url() {
        return jump_url;
    }

    public void setJump_url(String jump_url) {
        this.jump_url = jump_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
