package wc.xulingyun.com.xly.myapplication.http.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import wc.xulingyun.com.xly.myapplication.R;
import wc.xulingyun.com.xly.myapplication.dao.ImageDao;
import wc.xulingyun.com.xly.myapplication.http.util.Utils;

import static wc.xulingyun.com.xly.myapplication.PhotoFragment.spanCount;

/**
 * Created by 徐玲郓 on 2016/12/28.
 * 描述：
 */

public class PhotoItemAdapter extends RecyclerView.Adapter<PhotoItemAdapter.ImageVH>{

    private List<ImageDao> list;
    LayoutInflater mInflater;
    int width = 0;
    int showImageHeight;


    public PhotoItemAdapter(Context context, List<ImageDao> list) {
        this.list = list;
        mInflater = LayoutInflater.from(context);
        width = Utils.getWindowWidth(context);
        showImageHeight = (int)(width*1.0f/ spanCount);
    }

    @Override
    public ImageVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.photo_item_xv,parent,false);
        return new ImageVH(view);
    }

    @Override
    public void onBindViewHolder(ImageVH holder, int position) {
        holder.simpleDraweeView.setImageURI(Uri.parse("file://"+list.get(position).getPath()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ImageVH extends RecyclerView.ViewHolder{
        SimpleDraweeView simpleDraweeView;


        public ImageVH(View itemView) {
            super(itemView);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.photo);
            simpleDraweeView.getLayoutParams().height= showImageHeight;
        }
    }
}
