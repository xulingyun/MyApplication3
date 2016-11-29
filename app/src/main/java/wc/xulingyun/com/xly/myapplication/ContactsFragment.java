package wc.xulingyun.com.xly.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.indexablerv.IndexableLayout;
import wc.xulingyun.com.xly.myapplication.http.bean.CallRecord;

public class ContactsFragment extends Fragment {

    IndexableLayout mIndexableLayout;
    List<ContactsEntity> list;
    List<CallRecord> mCallRecordList;
    private static final int off_y = 2;
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        mIndexableLayout = (IndexableLayout) view.findViewById(R.id.indexable_layout);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        initCallRecord();
        initTelphoneDatas();
        ContactsAdapter lContactsAdapter = new ContactsAdapter(getActivity());
        mIndexableLayout.setAdapter(lContactsAdapter);
        lContactsAdapter.setDatas(list);
        mIndexableLayout.getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (off_y < Math.abs(dy)) {
                    if (dy > 0) {
                        ((MainActivity) getActivity()).hideBottomMenu();
                    } else if (dy < 0) {
                        ((MainActivity) getActivity()).showBottomMenu();
                    }
                }
            }
        });
        return view;
    }

    private List<ContactsEntity> initTelphoneDatas() {
        list = new ArrayList<>();
        ContentResolver lContentResolver = getActivity().getContentResolver();
        Cursor cursor = lContentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String telphone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            ContactsEntity contactEntity = new ContactsEntity(name, telphone);
            list.add(contactEntity);
        }
        return list;
    }

    private List<CallRecord> initCallRecord() {
        mCallRecordList = new ArrayList<>();
        ContentResolver lContentResolver = getActivity().getContentResolver();
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {

            Cursor cursor = lContentResolver.query(CallLog.Calls.CONTENT_URI, null, null, null, null);
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME))!=null?cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME)):"";
                String telphone = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER))!=null?cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER)):"";
                String address = cursor.getString(cursor.getColumnIndex(CallLog.Calls.GEOCODED_LOCATION))!=null?cursor.getString(cursor.getColumnIndex(CallLog.Calls.GEOCODED_LOCATION)):"";
                String carrieroperator = cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE))!=null?cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE)):"";
                String date = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE))!=null?cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE)):"";
                CallRecord callRecord = new CallRecord(address,carrieroperator,name,date,telphone);
                System.out.println("------:"+callRecord.toString());
                mCallRecordList.add(callRecord);
            }
        }else{
            System.out.println("------:------");
        }
        return mCallRecordList;
    }

    /**
     * 界面颜色的更改
     */
    @SuppressLint("NewApi")
    private void colorChange(int position) {
        // 用来提取颜色的Bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_announcement_cyan_700_24dp);
        // Palette的部分
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            /**
             * 提取完之后的回调方法
             */
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                toolbar.setBackgroundColor(vibrant.getRgb());
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    Window window = getActivity().getWindow();
                    // 很明显，这两货是新API才有的。
                    window.setStatusBarColor(colorBurn(vibrant.getRgb()));
                    window.setNavigationBarColor(colorBurn(vibrant.getRgb()));
                }
            }
        });
    }

    private int colorBurn(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }

}
