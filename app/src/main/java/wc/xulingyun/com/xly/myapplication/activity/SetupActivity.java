package wc.xulingyun.com.xly.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import wc.xulingyun.com.xly.myapplication.MainActivity;
import wc.xulingyun.com.xly.myapplication.R;

public class SetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        Intent intent = new Intent(SetupActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
