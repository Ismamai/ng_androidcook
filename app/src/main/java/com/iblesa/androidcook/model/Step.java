package com.iblesa.androidcook.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Step implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("videoURL")
    @Expose
    private String videoURL;
    @SerializedName("thumbnailURL")
    @Expose
    private String thumbnailURL;

    private Step(Parcel source) {
        this.id = source.readInt();
        this.shortDescription = source.readString();
        this.description = source.readString();
        this.videoURL = source.readString();
        this.thumbnailURL = source.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(videoURL);
        dest.writeString(thumbnailURL);
    }

    public static final Parcelable.Creator<Step> CREATOR = new Parcelable.Creator<Step>() {

        @Override
        public Step createFromParcel(Parcel source) {
            return new Step(source);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                ", videoURL='" + videoURL + '\'' +
                ", thumbnailURL='" + thumbnailURL + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Step step = (Step) o;

        if (id != step.id) return false;
        if (shortDescription != null ? !shortDescription.equals(step.shortDescription) : step.shortDescription != null)
            return false;
        if (description != null ? !description.equals(step.description) : step.description != null)
            return false;
        if (videoURL != null ? !videoURL.equals(step.videoURL) : step.videoURL != null)
            return false;
        return thumbnailURL != null ? thumbnailURL.equals(step.thumbnailURL) : step.thumbnailURL == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (shortDescription != null ? shortDescription.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (videoURL != null ? videoURL.hashCode() : 0);
        result = 31 * result + (thumbnailURL != null ? thumbnailURL.hashCode() : 0);
        return result;
    }
}