package com.phicdy.niconicofilterranking.video;

import android.os.Parcel;
import android.os.Parcelable;

public class NicoVideo implements Parcelable {

    private String title;
    private String url;
    private String playCount;
    private String commentCount;
    private String myListCount;
    private String publishedDate;
    private String thumbnailPath;
    private int hourTotalPoint;

    public NicoVideo(String title, String url, String playCount,
                     String commentCount, String myListCount,
                     String publishedDate, String thumbnailPath,
                     int hourTotalPoint) {
        this.title = title;
        this.url = url;
        this.playCount = playCount;
        this.commentCount = commentCount;
        this.myListCount = myListCount;
        this.publishedDate = publishedDate;
        this.thumbnailPath = thumbnailPath;
        this.hourTotalPoint = hourTotalPoint;
    }

    protected NicoVideo(Parcel in) {
        title = in.readString();
        url = in.readString();
        playCount = in.readString();
        commentCount = in.readString();
        myListCount = in.readString();
        publishedDate = in.readString();
        thumbnailPath = in.readString();
    }

    public static final Creator<NicoVideo> CREATOR = new Creator<NicoVideo>() {
        @Override
        public NicoVideo createFromParcel(Parcel in) {
            return new NicoVideo(in);
        }

        @Override
        public NicoVideo[] newArray(int size) {
            return new NicoVideo[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlayCount() {
        return playCount;
    }

    public void setPlayCount(String playCount) {
        this.playCount = playCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getMyListCount() {
        return myListCount;
    }

    public void setMyListCount(String myListCount) {
        this.myListCount = myListCount;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public int getHourTotalPoint() {
        return hourTotalPoint;
    }

    public void setHourTotalPoint(int hourTotalPoint) {
        this.hourTotalPoint = hourTotalPoint;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(url);
        dest.writeString(playCount);
        dest.writeString(commentCount);
        dest.writeString(myListCount);
        dest.writeString(publishedDate);
        dest.writeString(thumbnailPath);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof NicoVideo)) return false;
        NicoVideo video = (NicoVideo)o;
        return getHourTotalPoint() == video.getHourTotalPoint() &&
                getUrl().equals(video.getUrl()) &&
                getCommentCount().equals(video.getCommentCount()) &&
                getMyListCount().equals(video.getMyListCount()) &&
                getPlayCount().equals(video.getPlayCount()) &&
                getPublishedDate().equals(video.getPublishedDate()) &&
                getThumbnailPath().equals(video.getThumbnailPath()) &&
                getTitle().equals(video.getTitle());
    }
}
