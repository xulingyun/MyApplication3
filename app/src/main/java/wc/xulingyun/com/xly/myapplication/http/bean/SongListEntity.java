package wc.xulingyun.com.xly.myapplication.http.bean;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by 徐玲郓 on 2016/10/24.
 * 描述：
 */
public class SongListEntity implements Serializable{

    /**
     * artist_id : 121515348
     * language : 国语
     * pic_big : http://musicdata.baidu.com/data2/pic/87d5e77f58c2f45d301c1e4054304194/272026106/272026106.jpg
     * pic_small : http://musicdata.baidu.com/data2/pic/0882a197a2d146dc2f79b18f7f23fada/272026110/272026110.jpg
     * country : 内地
     * area : 0
     * publishtime : 2016-09-29
     * album_no : 1
     * lrclink : http://musicdata.baidu.com/data2/lrc/ab304b53b70b6982ba1d04d139952f8a/272028281/272028281.lrc
     * copy_type : 1
     * hot : 348692
     * all_artist_ting_uid : 146801388
     * resource_type : 0
     * is_new : 1
     * rank_change : 0
     * rank : 1
     * all_artist_id : 121515348
     * style : 影视原声
     * del_status : 0
     * relate_status : 0
     * toneid : 0
     * all_rate : 64,128,256,320,flac
     * sound_effect : 0
     * file_duration : 294
     * has_mv_mobile : 0
     * versions :
     * bitrate_fee : {"0":"0|0","1":"0|0"}
     * song_id : 272018331
     * title : 人间沙
     * ting_uid : 146801388
     * author : 陈学冬
     * album_id : 272018339
     * album_title : 人间沙
     * is_first_publish : 0
     * havehigh : 2
     * charge : 0
     * has_mv : 0
     * learn : 0
     * song_source : web
     * piao_id : 0
     * korean_bb_song : 0
     * resource_type_ext : 0
     * mv_provider : 0000000000
     * artist_name : 陈学冬
     */
    private String artist_id;
    private String language;
    private String pic_big;
    private String pic_small;
    private String country;
    private String area;
    private String publishtime;
    private String album_no;
    private String lrclink;
    private String copy_type;
    private String hot;
    private String all_artist_ting_uid;
    private String resource_type;
    private String is_new;
    private String rank_change;
    private String rank;
    private String all_artist_id;
    private String style;
    private String del_status;
    private String relate_status;
    private String toneid;
    private String all_rate;
    private String sound_effect;
    private int file_duration;
    private int has_mv_mobile;
    private String versions;
    private String bitrate_fee;
    private String song_id;
    private String title;
    private String ting_uid;
    private String author;
    private String album_id;
    private String album_title;
    private int is_first_publish;
    private int havehigh;
    private int charge;
    private int has_mv;
    private int learn;
    private String song_source;
    private String piao_id;
    private String korean_bb_song;
    private String resource_type_ext;
    private String mv_provider;
    private String artist_name;

    public static SongListEntity objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), SongListEntity.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPic_big() {
        return pic_big;
    }

    public void setPic_big(String pic_big) {
        this.pic_big = pic_big;
    }

    public String getPic_small() {
        return pic_small;
    }

    public void setPic_small(String pic_small) {
        this.pic_small = pic_small;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getAlbum_no() {
        return album_no;
    }

    public void setAlbum_no(String album_no) {
        this.album_no = album_no;
    }

    public String getLrclink() {
        return lrclink;
    }

    public void setLrclink(String lrclink) {
        this.lrclink = lrclink;
    }

    public String getCopy_type() {
        return copy_type;
    }

    public void setCopy_type(String copy_type) {
        this.copy_type = copy_type;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getAll_artist_ting_uid() {
        return all_artist_ting_uid;
    }

    public void setAll_artist_ting_uid(String all_artist_ting_uid) {
        this.all_artist_ting_uid = all_artist_ting_uid;
    }

    public String getResource_type() {
        return resource_type;
    }

    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }

    public String getIs_new() {
        return is_new;
    }

    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }

    public String getRank_change() {
        return rank_change;
    }

    public void setRank_change(String rank_change) {
        this.rank_change = rank_change;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getAll_artist_id() {
        return all_artist_id;
    }

    public void setAll_artist_id(String all_artist_id) {
        this.all_artist_id = all_artist_id;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getDel_status() {
        return del_status;
    }

    public void setDel_status(String del_status) {
        this.del_status = del_status;
    }

    public String getRelate_status() {
        return relate_status;
    }

    public void setRelate_status(String relate_status) {
        this.relate_status = relate_status;
    }

    public String getToneid() {
        return toneid;
    }

    public void setToneid(String toneid) {
        this.toneid = toneid;
    }

    public String getAll_rate() {
        return all_rate;
    }

    public void setAll_rate(String all_rate) {
        this.all_rate = all_rate;
    }

    public String getSound_effect() {
        return sound_effect;
    }

    public void setSound_effect(String sound_effect) {
        this.sound_effect = sound_effect;
    }

    public int getFile_duration() {
        return file_duration;
    }

    public void setFile_duration(int file_duration) {
        this.file_duration = file_duration;
    }

    public int getHas_mv_mobile() {
        return has_mv_mobile;
    }

    public void setHas_mv_mobile(int has_mv_mobile) {
        this.has_mv_mobile = has_mv_mobile;
    }

    public String getVersions() {
        return versions;
    }

    public void setVersions(String versions) {
        this.versions = versions;
    }

    public String getBitrate_fee() {
        return bitrate_fee;
    }

    public void setBitrate_fee(String bitrate_fee) {
        this.bitrate_fee = bitrate_fee;
    }

    public String getSong_id() {
        return song_id;
    }

    public void setSong_id(String song_id) {
        this.song_id = song_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTing_uid() {
        return ting_uid;
    }

    public void setTing_uid(String ting_uid) {
        this.ting_uid = ting_uid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public String getAlbum_title() {
        return album_title;
    }

    public void setAlbum_title(String album_title) {
        this.album_title = album_title;
    }

    public int getIs_first_publish() {
        return is_first_publish;
    }

    public void setIs_first_publish(int is_first_publish) {
        this.is_first_publish = is_first_publish;
    }

    public int getHavehigh() {
        return havehigh;
    }

    public void setHavehigh(int havehigh) {
        this.havehigh = havehigh;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public int getHas_mv() {
        return has_mv;
    }

    public void setHas_mv(int has_mv) {
        this.has_mv = has_mv;
    }

    public int getLearn() {
        return learn;
    }

    public void setLearn(int learn) {
        this.learn = learn;
    }

    public String getSong_source() {
        return song_source;
    }

    public void setSong_source(String song_source) {
        this.song_source = song_source;
    }

    public String getPiao_id() {
        return piao_id;
    }

    public void setPiao_id(String piao_id) {
        this.piao_id = piao_id;
    }

    public String getKorean_bb_song() {
        return korean_bb_song;
    }

    public void setKorean_bb_song(String korean_bb_song) {
        this.korean_bb_song = korean_bb_song;
    }

    public String getResource_type_ext() {
        return resource_type_ext;
    }

    public void setResource_type_ext(String resource_type_ext) {
        this.resource_type_ext = resource_type_ext;
    }

    public String getMv_provider() {
        return mv_provider;
    }

    public void setMv_provider(String mv_provider) {
        this.mv_provider = mv_provider;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }
}
