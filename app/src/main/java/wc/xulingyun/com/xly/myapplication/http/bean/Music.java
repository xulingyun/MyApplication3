package wc.xulingyun.com.xly.myapplication.http.bean;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * Created by 徐玲郓 on 2016/10/19.
 * 描述：
 */

public class Music extends TextBean{

    /**
     * billboard_type : 1
     * billboard_no : 1986
     * update_date : 2016-10-18
     * billboard_songnum : 162
     * havemore : 1
     * name : 新歌榜
     * comment : 该榜单是根据百度音乐平台歌曲每日播放量自动生成的数据榜单，统计范围为近期发行的歌曲，每日更新一次
     * pic_s640 : http://c.hiphotos.baidu.com/ting/pic/item/f7246b600c33874495c4d089530fd9f9d62aa0c6.jpg
     * pic_s444 : http://d.hiphotos.baidu.com/ting/pic/item/78310a55b319ebc4845c84eb8026cffc1e17169f.jpg
     * pic_s260 : http://b.hiphotos.baidu.com/ting/pic/item/e850352ac65c1038cb0f3cb0b0119313b07e894b.jpg
     * pic_s210 : http://business.cdn.qianqian.com/qianqian/pic/bos_client_c49310115801d43d42a98fdc357f6057.jpg
     * web_url : http://music.baidu.com/top/new
     */

    private BillboardEntity billboard;
    /**
     * song_list : [{"artist_id":"121515348","language":"国语","pic_big":"http://musicdata.baidu.com/data2/pic/87d5e77f58c2f45d301c1e4054304194/272026106/272026106.jpg","pic_small":"http://musicdata.baidu.com/data2/pic/0882a197a2d146dc2f79b18f7f23fada/272026110/272026110.jpg","country":"内地","area":"0","publishtime":"2016-09-29","album_no":"1","lrclink":"http://musicdata.baidu.com/data2/lrc/ab304b53b70b6982ba1d04d139952f8a/272028281/272028281.lrc","copy_type":"1","hot":"348692","all_artist_ting_uid":"146801388","resource_type":"0","is_new":"1","rank_change":"0","rank":"1","all_artist_id":"121515348","style":"影视原声","del_status":"0","relate_status":"0","toneid":"0","all_rate":"64,128,256,320,flac","sound_effect":"0","file_duration":294,"has_mv_mobile":0,"versions":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_id":"272018331","title":"人间沙","ting_uid":"146801388","author":"陈学冬","album_id":"272018339","album_title":"人间沙","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","artist_name":"陈学冬"},{"artist_id":"15","language":"国语","pic_big":"http://musicdata.baidu.com/data2/pic/4367a6de6f7dc000c50962ca55c964a2/271831974/271831974.jpg","pic_small":"http://musicdata.baidu.com/data2/pic/5b13c6847088bda14e02a681f962061e/271831977/271831977.jpg","country":"内地","area":"0","publishtime":"2016-09-24","album_no":"1","lrclink":"http://musicdata.baidu.com/data2/lrc/49a5a8b8e3ee44190967da0152d79ad3/271833151/271833151.lrc","copy_type":"3","hot":"579400","all_artist_ting_uid":"45561","resource_type":"0","is_new":"1","rank_change":"0","rank":"2","all_artist_id":"15","style":"影视原声","del_status":"0","relate_status":"0","toneid":"0","all_rate":"64,128,256,320,flac","sound_effect":"0","file_duration":286,"has_mv_mobile":0,"versions":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_id":"271832017","title":"你在终点等我","ting_uid":"45561","author":"王菲","album_id":"271832019","album_title":"你在终点等我","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","artist_name":"王菲"},{"artist_id":"120","language":"国语","pic_big":"http://musicdata.baidu.com/data2/pic/799ca5caafd0bb73972a386394f3f370/273532806/273532806.jpg","pic_small":"http://musicdata.baidu.com/data2/pic/629d2a5b651a10507e0b405b4dc228bd/273532810/273532810.jpg","country":"内地","area":"0","publishtime":"2016-10-09","album_no":"1","lrclink":"http://musicdata.baidu.com/data2/lrc/5a195112a0b7b9064d9a51b3d7bd1292/272825976/272825976.lrc","copy_type":"1","hot":"143134","all_artist_ting_uid":"1095","resource_type":"0","is_new":"1","rank_change":"0","rank":"3","all_artist_id":"120","style":"流行","del_status":"0","relate_status":"0","toneid":"0","all_rate":"64,128,256,320,flac","sound_effect":"0","file_duration":232,"has_mv_mobile":0,"versions":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_id":"272825457","title":"呵护","ting_uid":"1095","author":"梁静茹","album_id":"272825468","album_title":"呵护","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","artist_name":"梁静茹"},{"artist_id":"1814","language":"国语","pic_big":"http://musicdata.baidu.com/data2/pic/f1ae6eb70111ef441cb090dd257b3472/272019133/272019133.jpg","pic_small":"http://musicdata.baidu.com/data2/pic/d2deed8f51a7b6dc027ed55b55b616f4/272019136/272019136.jpg","country":"港台","area":"1","publishtime":"2016-09-30","album_no":"1","lrclink":"http://musicdata.baidu.com/data2/lrc/2671863c980df11b1ad8b443439ed6a1/272022086/272022086.lrc","copy_type":"1","hot":"110742","all_artist_ting_uid":"7898","resource_type":"0","is_new":"1","rank_change":"0","rank":"4","all_artist_id":"1814","style":"电子","del_status":"0","relate_status":"0","toneid":"0","all_rate":"64,128,256,320,flac","sound_effect":"0","file_duration":245,"has_mv_mobile":0,"versions":"混音","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_id":"272019545","title":"再见","ting_uid":"7898","author":"G.E.M.邓紫棋","album_id":"272020208","album_title":"25 Looks","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","artist_name":"G.E.M.邓紫棋"},{"artist_id":"57297","language":"国语","pic_big":"http://musicdata.baidu.com/data2/pic/25b1faff5bf89f84b6491593e31381ed/271665166/271665166.jpg","pic_small":"http://musicdata.baidu.com/data2/pic/66991fda34cc1cb05d51d49641349a26/271665169/271665169.jpg","country":"内地","area":"0","publishtime":"2016-09-20","album_no":"1","lrclink":"http://musicdata.baidu.com/data2/lrc/1844d88d7d64d77c7af0c242ecb1090c/271665878/271665878.lrc","copy_type":"1","hot":"294192","all_artist_ting_uid":"245815,92458641","resource_type":"0","is_new":"1","rank_change":"0","rank":"5","all_artist_id":"57297,84841771","style":"流行","del_status":"0","relate_status":"0","toneid":"0","all_rate":"64,128,256,320,flac","sound_effect":"0","file_duration":238,"has_mv_mobile":0,"versions":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_id":"271665582","title":"恋红尘","ting_uid":"245815","author":"祁隆,彭丽嘉","album_id":"271665637","album_title":"恋红尘","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","artist_name":"祁隆,彭丽嘉"},{"artist_id":"88","language":"国语","pic_big":"http://musicdata.baidu.com/data2/pic/c31f17e00d3625c71128234d196e490b/270852059/270852059.jpg","pic_small":"http://musicdata.baidu.com/data2/pic/00620d8b800136c73a70e2e15ad2de51/270852062/270852062.jpg","country":"内地","area":"0","publishtime":"2016-09-12","album_no":"1","lrclink":"http://musicdata.baidu.com/data2/lrc/365ed222003474151e031913de67f77f/270901607/270901607.lrc","copy_type":"1","hot":"474869","all_artist_ting_uid":"2517,10561","resource_type":"0","is_new":"0","rank_change":"0","rank":"6","all_artist_id":"88,1709","style":"流行","del_status":"0","relate_status":"0","toneid":"0","all_rate":"64,128,256,320,flac","sound_effect":"0","file_duration":234,"has_mv_mobile":1,"versions":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_id":"270852097","title":"来日方长","ting_uid":"2517","author":"薛之谦,黄龄","album_id":"270852099","album_title":"来日方长","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":1,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0100000000","artist_name":"薛之谦,黄龄"},{"artist_id":"57297","language":"国语","pic_big":"http://musicdata.baidu.com/data2/pic/d798e271727323057b20d588defbac8d/271896418/271896418.jpg","pic_small":"http://musicdata.baidu.com/data2/pic/73be8d9e4d81ceed7fa8fdb5b69b4f2d/271896421/271896421.jpg","country":"内地","area":"0","publishtime":"2016-09-27","album_no":"1","lrclink":"http://musicdata.baidu.com/data2/lrc/44ad1c7accd4334730d297f50050eb9a/271897877/271897877.lrc","copy_type":"1","hot":"240139","all_artist_ting_uid":"245815,209045495","resource_type":"0","is_new":"1","rank_change":"1","rank":"7","all_artist_id":"57297,34138380","style":"流行","del_status":"0","relate_status":"0","toneid":"0","all_rate":"64,128,256,320,flac","sound_effect":"0","file_duration":252,"has_mv_mobile":0,"versions":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_id":"271896483","title":"今生遇见你","ting_uid":"245815","author":"祁隆,任妙音","album_id":"271896489","album_title":"今生遇见你","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","artist_name":"祁隆,任妙音"},{"artist_id":"444","language":"国语","pic_big":"http://musicdata.baidu.com/data2/pic/07292d355a96f83d406871b6a7aaad2e/272951900/272951900.jpg","pic_small":"http://musicdata.baidu.com/data2/pic/4b4102cfc621a8e3f54683be109f9499/272951909/272951909.jpg","country":"内地","area":"0","publishtime":"2016-10-10","album_no":"1","lrclink":"http://musicdata.baidu.com/data2/lrc/140ce0e551282b7ea4b478521b75f376/273156819/273156819.lrc","copy_type":"1","hot":"82857","all_artist_ting_uid":"1273","resource_type":"0","is_new":"1","rank_change":"3","rank":"8","all_artist_id":"444","style":"流行","del_status":"0","relate_status":"0","toneid":"0","all_rate":"64,128,256,320,flac","sound_effect":"0","file_duration":271,"has_mv_mobile":0,"versions":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_id":"272952711","title":"下完这场雨","ting_uid":"1273","author":"后弦","album_id":"272952776","album_title":"下完这场雨","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","artist_name":"后弦"},{"artist_id":"12","language":"英语","pic_big":"http://musicdata.baidu.com/data2/pic/53f4347723e234f9211140a7437a86dc/272029162/272029162.png","pic_small":"http://musicdata.baidu.com/data2/pic/96376f27eb54e79fd4bfd46a3592c34e/272029171/272029171.png","country":"港台","area":"1","publishtime":"2016-09-30","album_no":"1","lrclink":"http://musicdata.baidu.com/data2/lrc/a3eaa22c3a2c97765f7b6285b3c9e13d/272032747/272032747.lrc","copy_type":"1","hot":"86723","all_artist_ting_uid":"1034,25168525","resource_type":"0","is_new":"1","rank_change":"-2","rank":"9","all_artist_id":"12,14918694","style":"流行","del_status":"0","relate_status":"0","toneid":"0","all_rate":"64,128,256,320,flac","sound_effect":"0","file_duration":215,"has_mv_mobile":0,"versions":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_id":"272030105","title":"I Wanna Know","ting_uid":"1034","author":"蔡依林,Alesso","album_id":"272030369","album_title":"I Wanna Know","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","artist_name":"蔡依林,Alesso"},{"artist_id":"1814","language":"国语","pic_big":"http://musicdata.baidu.com/data2/pic/f1ae6eb70111ef441cb090dd257b3472/272019133/272019133.jpg","pic_small":"http://musicdata.baidu.com/data2/pic/d2deed8f51a7b6dc027ed55b55b616f4/272019136/272019136.jpg","country":"港台","area":"1","publishtime":"2016-09-30","album_no":"2","lrclink":"http://musicdata.baidu.com/data2/lrc/00b907f59b2de195a704b72ebe8c5e16/272022230/272022230.lrc","copy_type":"1","hot":"82921","all_artist_ting_uid":"7898","resource_type":"0","is_new":"1","rank_change":"0","rank":"10","all_artist_id":"1814","style":"电子","del_status":"0","relate_status":"0","toneid":"0","all_rate":"64,128,256,320,flac","sound_effect":"0","file_duration":234,"has_mv_mobile":0,"versions":"混音","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_id":"272019617","title":"多远都要在一起","ting_uid":"7898","author":"G.E.M.邓紫棋","album_id":"272020208","album_title":"25 Looks","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","artist_name":"G.E.M.邓紫棋"}]
     * billboard : {"billboard_type":"1","billboard_no":"1986","update_date":"2016-10-18","billboard_songnum":"162","havemore":1,"name":"新歌榜","comment":"该榜单是根据百度音乐平台歌曲每日播放量自动生成的数据榜单，统计范围为近期发行的歌曲，每日更新一次","pic_s640":"http://c.hiphotos.baidu.com/ting/pic/item/f7246b600c33874495c4d089530fd9f9d62aa0c6.jpg","pic_s444":"http://d.hiphotos.baidu.com/ting/pic/item/78310a55b319ebc4845c84eb8026cffc1e17169f.jpg","pic_s260":"http://b.hiphotos.baidu.com/ting/pic/item/e850352ac65c1038cb0f3cb0b0119313b07e894b.jpg","pic_s210":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_c49310115801d43d42a98fdc357f6057.jpg","web_url":"http://music.baidu.com/top/new"}
     * error_code : 22000
     */

    private int error_code;


    private List<SongListEntity> song_list;

    public static Music objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), Music.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public BillboardEntity getBillboard() {
        return billboard;
    }

    public void setBillboard(BillboardEntity billboard) {
        this.billboard = billboard;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<SongListEntity> getSong_list() {
        return song_list;
    }

    public void setSong_list(List<SongListEntity> song_list) {
        this.song_list = song_list;
    }

    public static class BillboardEntity {
        private String billboard_type;
        private String billboard_no;
        private String update_date;
        private String billboard_songnum;
        private int havemore;
        private String name;
        private String comment;
        private String pic_s640;
        private String pic_s444;
        private String pic_s260;
        private String pic_s210;
        private String web_url;

        public static BillboardEntity objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), BillboardEntity.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public String getBillboard_type() {
            return billboard_type;
        }

        public void setBillboard_type(String billboard_type) {
            this.billboard_type = billboard_type;
        }

        public String getBillboard_no() {
            return billboard_no;
        }

        public void setBillboard_no(String billboard_no) {
            this.billboard_no = billboard_no;
        }

        public String getUpdate_date() {
            return update_date;
        }

        public void setUpdate_date(String update_date) {
            this.update_date = update_date;
        }

        public String getBillboard_songnum() {
            return billboard_songnum;
        }

        public void setBillboard_songnum(String billboard_songnum) {
            this.billboard_songnum = billboard_songnum;
        }

        public int getHavemore() {
            return havemore;
        }

        public void setHavemore(int havemore) {
            this.havemore = havemore;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getPic_s640() {
            return pic_s640;
        }

        public void setPic_s640(String pic_s640) {
            this.pic_s640 = pic_s640;
        }

        public String getPic_s444() {
            return pic_s444;
        }

        public void setPic_s444(String pic_s444) {
            this.pic_s444 = pic_s444;
        }

        public String getPic_s260() {
            return pic_s260;
        }

        public void setPic_s260(String pic_s260) {
            this.pic_s260 = pic_s260;
        }

        public String getPic_s210() {
            return pic_s210;
        }

        public void setPic_s210(String pic_s210) {
            this.pic_s210 = pic_s210;
        }

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }
    }

}
