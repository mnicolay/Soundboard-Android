package edu.depaul.csc472.nicolay_m_finalproject.sound;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mattn_000 on 11/10/2016.
 * Represents a sound on the soundboard
 */

public class Sound implements Parcelable{
    private String label;
    private Uri filePath;
    private int resourceID;
    //0 <= buttonNum < 9
    private int buttonNum;

    public Sound(){
        label = "";
        filePath = null;
        resourceID = -1;
        buttonNum = 9;
    }

    public Sound(String label, Uri filePath, int resourceID, int buttonNum){
        this.label = label;
        this.filePath = filePath;
        this.resourceID = resourceID;
        this.buttonNum = buttonNum;
    }

    public String getLabel(){
        return label;
    }

    public Uri getFilePath(){
        return filePath;
    }

    public int getResourceID(){
        return resourceID;
    }

    public int getButtonNum() { return buttonNum; }

    public void setLabel(String label){
        this.label = label;
    }

    public void setFilePath(Uri filePath){
        this.filePath = filePath;
    }

    public void setFilePath(String stringFilePath){
        filePath = filePath.parse(stringFilePath);
    }

    public void setResourceID(int resourceID){
        this.resourceID = resourceID;
    }

    public void setButtonNum(int buttonNum) { this.buttonNum = buttonNum; }

    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(!(o instanceof Sound))
            return false;
        Sound that = (Sound) o;
        return label.equals(that.getLabel());
//                && filePath.equals(that.getFilePath())
//                && resourceID == that.getResourceID()
//                && buttonNum == that.getButtonNum();
    }

    public String toString() {
        return filePath == null ? label + ",," + resourceID + "," + buttonNum : label + "," + filePath + "," + resourceID + "," + buttonNum;
    }

    /////////////// Parcelable implementation

    private Sound(Parcel in){
        label = in.readString();
        filePath = in.readParcelable(null);
        resourceID = in.readInt();
        buttonNum = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(label);
        dest.writeParcelable(filePath, flags);
        dest.writeInt(resourceID);
        dest.writeInt(buttonNum);
    }

    public static final Creator<Sound> CREATOR
            = new Creator<Sound>() {
        public Sound createFromParcel(Parcel in) {
            return new Sound(in);
        }

        public Sound[] newArray(int size) {
            return new Sound[size];
        }
    };

    //TODO other methods as necessary
}
