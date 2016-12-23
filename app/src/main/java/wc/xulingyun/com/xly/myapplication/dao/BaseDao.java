package wc.xulingyun.com.xly.myapplication.dao;

import java.io.Serializable;

/**
 * Created by hehe on 2016/3/9.
 */
public class BaseDao implements Serializable {

    int id;
    String name;
    String url;
    String size;

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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
