package wc.xulingyun.com.xly.myapplication;

import android.Manifest;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableLayout;
import wc.xulingyun.com.xly.myapplication.http.adapter.RecycleViewDivider;
import wc.xulingyun.com.xly.myapplication.http.bean.CallRecord;

public class ContactsFragment extends Fragment {

    @BindView(R.id.indexable_layout)
    IndexableLayout mIndexableLayout;
    List<ContactsEntity> list;
    List<CallRecord> mCallRecordList;
    private static final int off_y = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        ButterKnife.bind(this,view);

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
        mIndexableLayout.getRecyclerView().addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL));
        lContactsAdapter.setOnItemContentClickListener(new IndexableAdapter.OnItemContentClickListener<ContactsEntity>() {
            @Override
            public void onItemClick(View v, int originalPosition, int currentPosition, final ContactsEntity entity) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("拨打电话")
                        .setMessage("是否拨打"+entity.getTelphone()+"?")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                Uri data = Uri.parse("tel:" + entity.getTelphone());
                                intent.setData(data);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("打个毛",null);
                builder.show();
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

            Cursor cursor = lContentResolver.query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DEFAULT_SORT_ORDER);
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


}
