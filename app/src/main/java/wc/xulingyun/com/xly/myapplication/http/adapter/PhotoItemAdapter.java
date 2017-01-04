package wc.xulingyun.com.xly.myapplication.http.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

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
    private LayoutInflater mInflater;
    private int width = 0;
    private int showImageWidth;
    private Context mContext;
    private ViewGroup.LayoutParams lp;
    private int dynamicsHeight;

    OnItemListener mOnItemListener;

    public PhotoItemAdapter(Context context, List<ImageDao> list,OnItemListener listener) {
        this.mContext = context;
        this.list = list;
        this.mOnItemListener = listener;
        mInflater = LayoutInflater.from(context);
        width = Utils.getWindowWidth(context);
        showImageWidth = (int)((width-70)*1.0f/ spanCount);
    }

    @Override
    public ImageVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.photo_item_xv,parent,false);
        return new ImageVH(view,mOnItemListener);
    }

    @Override
    public void onBindViewHolder(ImageVH holder, int position) {
//        holder.simpleDraweeView.getHierarchy().setActualImageFocusPoint(focusPoint);
//        holder.simpleDraweeView.setImageURI(Uri.parse("file://"+list.get(position).getPath()));
        lp = holder.simpleDraweeView.getLayoutParams();
        dynamicsHeight = (int)(list.get(position).getHeight()*1.0f/list.get(position).getWidth()*width/spanCount);
        lp.width = showImageWidth;
        lp.height = dynamicsHeight;
        Picasso.with(mContext)
                .load(Uri.parse("file://"+list.get(position).getPath()))
                .resize(showImageWidth, dynamicsHeight)
                .config(Bitmap.Config.RGB_565)
                .centerInside()
                .into(holder.simpleDraweeView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ImageVH extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView simpleDraweeView;
        OnItemListener mListener;


        public ImageVH(View itemView,OnItemListener listener) {
            super(itemView);
            mListener = listener;
            itemView.setOnClickListener(this);
            simpleDraweeView = (ImageView) itemView.findViewById(R.id.photo);
            simpleDraweeView.getLayoutParams().height= showImageWidth;
        }

        @Override
        public void onClick(View v) {
            if(mListener==null)
                return;
            mListener.onClick(v,getAdapterPosition());

        }
    }
}
