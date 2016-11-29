package wc.xulingyun.com.xly.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView.*;

import me.yokeyword.indexablerv.IndexableAdapter;

/**
 * Created by 徐玲郓 on 2016/11/22.
 * 描述：
 */

public class ContactsAdapter extends IndexableAdapter<ContactsEntity>{
    private LayoutInflater mInflater;

    public ContactsAdapter(Context $Context) {
        mInflater = LayoutInflater.from($Context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateTitleViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_tv_title_contact,parent,false);
        return new ContactsTitleVH(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_contact, parent, false);
        return new ContactsContentVH(view);
    }

    @Override
    public void onBindTitleViewHolder(ViewHolder holder, String indexTitle) {
        ContactsTitleVH vh = (ContactsTitleVH)holder;
        vh.zimu.setText(indexTitle);
    }

    @Override
    public void onBindContentViewHolder(ViewHolder holder, ContactsEntity entity) {
        ContactsContentVH vh = (ContactsContentVH)holder;
        vh.name.setText(entity.getName());
        vh.telphone.setText(entity.getTelphone()+"");
    }

    class ContactsContentVH extends ViewHolder{

        TextView name;
        TextView telphone;

        public ContactsContentVH(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            telphone = (TextView) itemView.findViewById(R.id.tv_mobile);
        }
    }

    class ContactsTitleVH extends ViewHolder{

        TextView zimu;

        public ContactsTitleVH(View itemView) {
            super(itemView);
            zimu = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }


}
