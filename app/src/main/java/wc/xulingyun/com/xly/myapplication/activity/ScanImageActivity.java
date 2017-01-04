package wc.xulingyun.com.xly.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wc.xulingyun.com.xly.myapplication.R;
import wc.xulingyun.com.xly.myapplication.dao.SerializableMap;

public class ScanImageActivity extends AppCompatActivity {

    @BindView(R.id.scan_view_page)
    ViewPager mViewPager;
    private Unbinder mUnBinder;
    SerializableMap mSerializableMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sacn_image);
        mUnBinder = ButterKnife.bind(this);
        Bundle lBundle = getIntent().getExtras();
        mSerializableMap = (SerializableMap) lBundle.get("totalMap");
        System.out.println("------map:"+mSerializableMap.getMap().size());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }
}
