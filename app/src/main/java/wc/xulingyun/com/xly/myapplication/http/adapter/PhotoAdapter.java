package wc.xulingyun.com.xly.myapplication.http.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import wc.xulingyun.com.xly.myapplication.R;
import wc.xulingyun.com.xly.myapplication.dao.ImageDao;

/**
 * Created by 徐玲郓 on 2016/12/28.
 * 描述：
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ImageVH>{

    List<ImageDao> list;
    LayoutInflater mInflater;

    public PhotoAdapter(Context context, List<ImageDao> list) {
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public ImageVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.photo_item,parent,false);
        return new ImageVH(view);
    }

    @Override
    public void onBindViewHolder(ImageVH holder, int position) {
        System.out.println("图片路径："+"content://"+list.get(position).getPath());

        holder.simpleDraweeView.setImageURI(Uri.parse("content://"+list.get(position).getPath()));
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
        }
    }
}
