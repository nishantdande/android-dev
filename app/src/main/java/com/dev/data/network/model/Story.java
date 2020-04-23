
package com.dev.data.network.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Story {

    @SerializedName("by")
    private String mBy;
    @SerializedName("descendants")
    private Long mDescendants;
    @SerializedName("id")
    private Long mId;
    @SerializedName("kids")
    private List<Long> mKids;
    @SerializedName("score")
    private Long mScore;
    @SerializedName("time")
    private Long mTime;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("type")
    private String mType;
    @SerializedName("url")
    private String mUrl;

    public String getBy() {
        return mBy;
    }

    public void setBy(String by) {
        mBy = by;
    }

    public Long getDescendants() {
        return mDescendants;
    }

    public void setDescendants(Long descendants) {
        mDescendants = descendants;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public List<Long> getKids() {
        return mKids;
    }

    public void setKids(List<Long> kids) {
        mKids = kids;
    }

    public Long getScore() {
        return mScore;
    }

    public void setScore(Long score) {
        mScore = score;
    }

    public Long getTime() {
        return mTime;
    }

    public void setTime(Long time) {
        mTime = time;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    @Override
    public String toString() {
        return "Story{" +
                "mBy='" + mBy + '\'' +
                ", mDescendants=" + mDescendants +
                ", mId=" + mId +
                ", mKids=" + mKids +
                ", mScore=" + mScore +
                ", mTime=" + mTime +
                ", mTitle='" + mTitle + '\'' +
                ", mType='" + mType + '\'' +
                ", mUrl='" + mUrl + '\'' +
                '}';
    }
}
