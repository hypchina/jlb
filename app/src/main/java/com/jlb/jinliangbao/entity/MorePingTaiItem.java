package com.jlb.jinliangbao.entity;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2017/4/24.
 */
public class MorePingTaiItem {
    private String title;
    private Drawable icon;
    private String account;

    public String getTitle() {
        return title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getAccount() {
        return account;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
