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

    private HashMap<String, List<ImageDao>> map;

    ArrayList<String> listKey;


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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
