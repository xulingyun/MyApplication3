package wc.xulingyun.com.xly.myapplication.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 徐玲郓 on 2016/12/28.
 * 描述：
 */

public class ImageDao implements Parcelable{

    private String name;
    private int width;
    private int height;
    private String path;

    public ImageDao(String $Name, String $Path, int $Width, int $Height) {
        height = $Height;
        name = $Name;
        path = $Path;
        width = $Width;
    }

    public ImageDao(String $Name, String $Path) {
        name = $Name;
        path = $Path;
    }

    protected ImageDao(Parcel in) {
        name = in.readString();
        path = in.readString();
        width = in.readInt();
        height = in.readInt();
    }

    public static final Creator<ImageDao> CREATOR = new Creator<ImageDao>() {
        @Override
        public ImageDao createFromParcel(Parcel in) {
            return new ImageDao(in);
        }

        @Override
        public ImageDao[] newArray(int size) {
            return new ImageDao[size];
        }
    };

    @Override
    public String toString() {
        return "ImageDao{" +
                "height=" + height +
                ", name='" + name + '\'' +
                ", width=" + width +
                ", path='" + path + '\'' +
                '}';
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int $Height) {
        height = $Height;
    }

    public String getName() {
        return name;
    }

    public void setName(String $Name) {
        name = $Name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String $Path) {
        path = $Path;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int $Width) {
        width = $Width;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(path);
        dest.writeInt(width);
        dest.writeInt(height);
    }
}
