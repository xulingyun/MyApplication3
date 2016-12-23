package wc.xulingyun.com.xly.myapplication.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 徐玲郓 on 2016/12/23.
 * 描述：
 */
@Entity
public class GreenDaoAudio {

    @Id(autoincrement = true)
    Long id;
    String songName;
    String songLrcPath;
    public String getSongLrcPath() {
        return this.songLrcPath;
    }
    public void setSongLrcPath(String songLrcPath) {
        this.songLrcPath = songLrcPath;
    }
    public String getSongName() {
        return this.songName;
    }
    public void setSongName(String songName) {
        this.songName = songName;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1023911830)
    public GreenDaoAudio(Long id, String songName, String songLrcPath) {
        this.id = id;
        this.songName = songName;
        this.songLrcPath = songLrcPath;
    }
    @Generated(hash = 83478055)
    public GreenDaoAudio() {
    }


}
