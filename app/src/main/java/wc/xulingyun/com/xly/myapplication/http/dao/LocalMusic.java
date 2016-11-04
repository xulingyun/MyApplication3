package wc.xulingyun.com.xly.myapplication.http.dao;

/**
 * Created by 徐玲郓 on 2016/11/2.
 * 描述：
 */

public class LocalMusic {

    String displayName;
    String song;
    String album;

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String $Album) {
        album = $Album;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String $DisplayName) {
        displayName = $DisplayName;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String $Song) {
        song = $Song;
    }

    public LocalMusic(String $Album, String $DisplayName, String $Song) {
        album = $Album;
        displayName = $DisplayName;
        song = $Song;
    }
}
