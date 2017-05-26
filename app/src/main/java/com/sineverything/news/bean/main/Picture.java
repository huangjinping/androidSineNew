package com.sineverything.news.bean.main;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author Created by harrishuang on 2017/5/15.
 * email : huangjinping@hdfex.com
 */

public class Picture
        implements Parcelable {
    private String title;

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String imgSrc;


    public Picture() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.imgSrc);
    }

    protected Picture(Parcel in) {
        this.title = in.readString();
        this.imgSrc = in.readString();
    }

    public static final Creator<Picture> CREATOR = new Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel source) {
            return new Picture(source);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };

    @Override
    public String toString() {
        return "Picture{" +
                "title='" + title + '\'' +
                ", imgSrc='" + imgSrc + '\'' +
                '}';
    }
}
