package wc.xulingyun.com.xly.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        return null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.contacts_item, parent, false);
        return new ContactsVH(view);
    }

    @Override
    public void onBindTitleViewHolder(RecyclerView.ViewHolder holder, String indexTitle) {

    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, ContactsEntity entity) {
        ContactsVH vh = (ContactsVH)holder;
        vh.name.setText(entity.getName());
        vh.telphone.setText(entity.getTelphone()+"");
    }

    class ContactsVH extends RecyclerView.ViewHolder{

        TextView name;
        TextView telphone;

        public ContactsVH(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            telphone = (TextView) itemView.findViewById(R.id.telphone);
        }
    }
}
