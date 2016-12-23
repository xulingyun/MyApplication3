package wc.xulingyun.com.xly.myapplication.dao;

import java.io.Serializable;

import wc.xulingyun.com.xly.myapplication.util.Util;

/**
 * Created by Administrator on 2016/3/2 0002.
 */
public class AudioDao extends BaseDao implements Serializable {
    private int id;
    private String name;
    private String url;
    private int time;
    private int playTime;
    private int totalTime;
    private String size;
    private String showTime;
    private String singerName;

    public String getSingName() {
        return singName;
    }

    public void setSingName(String singName) {
        this.singName = singName;
    }

    String singName;

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getShowTime() {
        return showTime;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
        showTime = Util.formatTime(time);
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

}
