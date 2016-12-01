package wc.xulingyun.com.xly.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wc.xulingyun.com.xly.myapplication.http.bean.CallRecord;

/**
 * Created by 徐玲郓 on 2016/11/30.
 * 描述：
 */

public class CallPhoneRecordAdapter extends Adapter<ViewHolder> {

    Context context;
    List<CallRecord> mRecordList;
    OnItemListener mOnItemListener;
    String emptyString = "暂无内容";

    public void setEmptyText(String str){
        this.emptyString = str;
    }

    public void setOnItemListener(OnItemListener $OnItemListener) {
        mOnItemListener = $OnItemListener;
    }

    interface OnItemListener{
        void onItemClick(CallRecord callRecord);
    }

    public CallPhoneRecordAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<CallRecord> list){
        mRecordList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0) {
            View view = LayoutInflater.from(context).inflate(R.layout.call_phone_record_item, parent, false);
            return new CallPhoneViewHolder(view);
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.empty, parent, false);
            return new CallPhoneEmptyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(getItemViewType(position)==0) {
            ((CallPhoneViewHolder)holder).name.setText(mRecordList.get(position).getName());
            ((CallPhoneViewHolder)holder).info.setText(mRecordList.get(position).getTime());
            ((CallPhoneViewHolder)holder).view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemListener.onItemClick(mRecordList.get(position));
                }
            });
        }else{
            ((CallPhoneEmptyViewHolder)holder).empty.setText(emptyString);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mRecordList.isEmpty()){
            return -1;
        }else{
            return super.getItemViewType(position);
        }
    }

    @Override
    public int getItemCount() {
        if(mRecordList.isEmpty())
            return 1;
        return mRecordList.size();
    }

    class CallPhoneViewHolder extends ViewHolder{

        @BindView(R.id.record_name)
        TextView name;
        @BindView(R.id.record_info)
        TextView info;
        @BindView(R.id.record_detailed)
        ImageView detailed;
        View view;

        public CallPhoneViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            view = itemView;
        }
    }

    class CallPhoneEmptyViewHolder extends ViewHolder{

        @BindView(R.id.empty)
        TextView empty;
        View view;

        public CallPhoneEmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            view = itemView;
        }
    }


}
