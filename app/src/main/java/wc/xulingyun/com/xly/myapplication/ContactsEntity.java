package wc.xulingyun.com.xly.myapplication;


import me.yokeyword.indexablerv.IndexableEntity;

/**
 * Created by 徐玲郓 on 2016/11/22.
 * 描述：
 */

public class ContactsEntity implements IndexableEntity {

    String name;
    String telphone;
    String pinyin;

    public ContactsEntity(String $Name, String $Telphone) {
        name = $Name;
        telphone = $Telphone;
    }

    public String getName() {
        return name;
    }

    public void setName(String $Name) {
        name = $Name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String $Pinyin) {
        pinyin = $Pinyin;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String $Telphone) {
        telphone = $Telphone;
    }

    @Override
    public String getFieldIndexBy() {
        return name;
    }

    @Override
    public void setFieldIndexBy(String indexField) {
        this.name = indexField;
    }

    @Override
    public void setFieldPinyinIndexBy(String pinyin) {
        this.pinyin = pinyin;
    }
}
