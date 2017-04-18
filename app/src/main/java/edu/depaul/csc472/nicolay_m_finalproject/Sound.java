package edu.depaul.csc472.nicolay_m_finalproject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mattn_000 on 11/10/2016.
 * Represents a sound on the soundboard
 */

public class Sound implements Parcelable{
    private String label;
    private String filePath;
    private int buttonID;

    public Sound(){
        label = "";
        filePath = "";
        buttonID = -1;
    }

    public Sound(String _label, String _filePath, int _buttonID){
        label = _label;
        filePath = _filePath;
        buttonID = _buttonID;
    }

    public String getLabel(){
        return label;
    }

    public String getFilePath(){
        return filePath;
    }

    public int getButtonID(){
        return buttonID;
    }

    public void setLabel(String _label){
        label = _label;
    }

    public void setFilePath(String _filePath){
        filePath = _filePath;
    }

    public void setButtonID(int _buttonID){
        buttonID = _buttonID;
    }

    /////////////// Parcelable implementation

    private Sound(Parcel in){
        label = in.readString();
        filePath = in.readString();
        buttonID = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(label);
        dest.writeString(filePath);
        dest.writeInt(buttonID);
    }

    //TODO other methods as necessary
}
