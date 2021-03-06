package wc.xulingyun.com.xly.myapplication.http.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import wc.xulingyun.com.xly.myapplication.R;
import wc.xulingyun.com.xly.myapplication.activity.ScanImageActivity;
import wc.xulingyun.com.xly.myapplication.dao.ImageDao;
import wc.xulingyun.com.xly.myapplication.dao.SerializableMap;
import wc.xulingyun.com.xly.myapplication.http.util.Utils;

import static wc.xulingyun.com.xly.myapplication.PhotoFragment.spanCount;

/**
 * Created by 徐玲郓 on 2016/12/28.
 * 描述：
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ImageVH>{

    private HashMap<String, List<ImageDao>> map;
    private ArrayList<String> list;
    LayoutInflater mInflater;
    int width = 0;
    int showImageHeight;
    private Context context;


    public PhotoAdapter(Context context, HashMap<String, List<ImageDao>> map,ArrayList<String> list) {
        this.context = context;
        this.map = map;
        this.list = list;
        mInflater = LayoutInflater.from(context);
        width = Utils.getWindowWidth(context);
        showImageHeight = (int)(width*1.0f/ spanCount);
    }

    @Override
    public ImageVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.photo_item,parent,false);
        return new ImageVH(view);
    }

    @Override
    public void onBindViewHolder(ImageVH holder, final int position) {
        holder.title.setText(list.get(position));
        holder.mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(spanCount,StaggeredGridLayoutManager.VERTICAL));
//        holder.mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        SpacesItemDecoration decoration=new SpacesItemDecoration(8);
//        holder.mRecyclerView.addItemDecoration(decoration);
//        holder.mRecyclerView.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.HORIZONTAL));
        PhotoItemAdapter adapter = new PhotoItemAdapter(context,map.get(list.get(position)),
            new OnItemListener() {
                @Override
                public void onClick(View $View, int postion) {
                    Intent lIntent = new Intent(context, ScanImageActivity.class);
                    SerializableMap mSerializableMap = new SerializableMap();
                    mSerializableMap.setMap(map);
                    mSerializableMap.setListKey(list);
                    mSerializableMap.setIndex(postion);
                    mSerializableMap.setListIndex(position);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("totalMap",mSerializableMap);
                    lIntent.putExtras(bundle);
                    context.startActivity(lIntent);
                }
            }
        );
        holder.mRecyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ImageVH extends RecyclerView.ViewHolder{
        TextView title;
        RecyclerView mRecyclerView;


        public ImageVH(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.item_recycler);
        }
    }
}
