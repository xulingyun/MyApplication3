package wc.xulingyun.com.xly.myapplication.dao;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 徐玲郓 on 2017/1/4.
 * 描述：
 */

public class SerializableMap implements Parcelable {
    /**
     * 所有的图片信息map
     */
    private HashMap<String, List<ImageDao>> map = new HashMap<>();
    /**
     * map的键
     */
    private ArrayList<String> listKey = new ArrayList<>();
    /**
     * 某一个list里面的索引
     */
    private int index;

    /**
     * 第几个list
     */
    private int listIndex;

    public SerializableMap() {

    }

    protected SerializableMap(Parcel in) {
        in.readList(listKey,String.class.getClassLoader());
        in.readMap(map,SerializableMap.class.getClassLoader());
        index = in.readInt();
        listIndex = in.readInt();
//        in.readArrayList()
//        listKey = in.createStringArrayList();
//        in.readList();
    }

    public static final Creator<SerializableMap> CREATOR = new Creator<SerializableMap>() {
        @Override
        public SerializableMap createFromParcel(Parcel in) {
            return new SerializableMap(in);
        }

        @Override
        public SerializableMap[] newArray(int size) {
            return new SerializableMap[size];
        }
    };

    public ArrayList<String> getListKey() {
        return listKey;
    }

    public void setListKey(ArrayList<String> $ListKey) {
        listKey = $ListKey;
    }

    public HashMap<String, List<ImageDao>> getMap() {
        return map;
    }

    public void setMap(HashMap<String, List<ImageDao>> $Map) {
        map = $Map;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int $Index) {
        index = $Index;
    }

    public int getListIndex() {
        return listIndex;
    }

    public void setListIndex(int $ListIndex) {
        listIndex = $ListIndex;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(listKey);
        dest.writeMap(map);
        dest.writeInt(index);
        dest.writeInt(listIndex);
    }
}
