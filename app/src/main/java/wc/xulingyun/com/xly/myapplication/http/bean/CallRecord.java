package wc.xulingyun.com.xly.myapplication.http.bean;

/**
 * Created by 徐玲郓 on 2016/11/29.
 * 描述：
 */

public class CallRecord {

    String name;
    String time;
    String address;
    String carrieroperator;
    String telphone;

    public CallRecord(String $Address, String $Carrieroperator, String $Name, String $Time,String $Telphone) {
        address = $Address;
        carrieroperator = $Carrieroperator;
        name = $Name;
        time = $Time;
        telphone = $Telphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String $Address) {
        address = $Address;
    }

    public String getCarrieroperator() {
        return carrieroperator;
    }

    public void setCarrieroperator(String $Carrieroperator) {
        carrieroperator = $Carrieroperator;
    }

    public String getName() {
        return name;
    }

    public void setName(String $Name) {
        name = $Name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String $Time) {
        time = $Time;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String $Telphone) {
        telphone = $Telphone;
    }

    @Override
    public String toString() {
        return "CallRecord{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", carrieroperator='" + carrieroperator + '\'' +
                ", telphone='" + telphone + '\'' +
                '}';
    }
}
