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
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wc.xulingyun.com.xly.myapplication.http.adapter.RecycleViewDivider;
import wc.xulingyun.com.xly.myapplication.http.bean.CallRecord;

public class CallPhoneFragment extends Fragment {

    @BindView(R.id.call_phone_recycler)
    RecyclerView mRecyclerView;
    List<CallRecord> mCallRecordList;
    List<String> removeSame;
    CallPhoneRecordAdapter mCallPhoneRecordAdapter;
    private static final int off_y = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_call_phone, container, false);
        ButterKnife.bind(this,view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(),LinearLayoutManager.HORIZONTAL));
        mCallPhoneRecordAdapter = new CallPhoneRecordAdapter(getContext());
        mRecyclerView.setAdapter(mCallPhoneRecordAdapter);
        initCallRecord();
        mCallPhoneRecordAdapter.addData(mCallRecordList);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        mCallPhoneRecordAdapter.setOnItemListener(new CallPhoneRecordAdapter.OnItemListener() {
            @Override
            public void onItemClick(final CallRecord callRecord) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("拨打电话")
                        .setMessage("是否拨打"+callRecord.getTelphone()+"?")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                Uri data = Uri.parse("tel:" + callRecord.getTelphone());
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

    private List<CallRecord> initCallRecord() {
        mCallRecordList = new ArrayList<>();
        removeSame = new ArrayList<>();
        ContentResolver lContentResolver = getActivity().getContentResolver();
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {

            Cursor cursor = lContentResolver.query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DEFAULT_SORT_ORDER);
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME))!=null?cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME)):"";
                String telphone = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER))!=null?cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER)):"";
                name = name.equals("")?telphone:name;
                String address = cursor.getString(cursor.getColumnIndex(CallLog.Calls.GEOCODED_LOCATION))!=null?cursor.getString(cursor.getColumnIndex(CallLog.Calls.GEOCODED_LOCATION)):"";
                String carrieroperator = cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE))!=null?cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE)):"";
                String time = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE))!=null?cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE)):"";
                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                String data = format.format(Long.parseLong(time));
                if(removeSame.contains(name)){
                    continue;
                }else{
                    removeSame.add(name);
                    CallRecord callRecord = new CallRecord(address,carrieroperator,name,data,telphone);
                    mCallRecordList.add(callRecord);
                }
            }
        }else{
            System.out.println("------:------");
        }
        return mCallRecordList;
    }


}
