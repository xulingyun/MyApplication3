package wc.xulingyun.com.xly.myapplication.http.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import wc.xulingyun.com.xly.myapplication.R;
import wc.xulingyun.com.xly.myapplication.http.dao.SongListEntity;

/**
 * Created by 徐玲郓 on 2016/10/24.
 * 描述：
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> implements View.OnClickListener{

    private OnMoreListener mOnMoreListener;
    private OnItemListener mOnItemListener;
    Context mContext;
    List<SongListEntity> mSongListEntities;


    public void addData(List<SongListEntity> $SongListEntities){
        if(mSongListEntities==null){
            mSongListEntities = new ArrayList<>();
        }
        mSongListEntities.addAll($SongListEntities);
    }

    public void setOnMoreListener(OnMoreListener $OnMoreListener) {
        mOnMoreListener = $OnMoreListener;
    }

    public void setOnItemListener(OnItemListener $OnItemListener) {
        mOnItemListener = $OnItemListener;
    }

    public ImageAdapter(Context $Context, List<SongListEntity> $SongListEntities){
        this.mContext = $Context;
        this.mSongListEntities = $SongListEntities;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item,parent,false);
        v.setOnClickListener(this);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        SongListEntity lSongListEntity = mSongListEntities.get(position);
        Uri uri = Uri.parse(lSongListEntity.getPic_small());
        holder.mImageView.setImageURI(uri);
        holder.title.setText(lSongListEntity.getTitle());
        holder.author.setText(lSongListEntity.getAuthor() + "·" + lSongListEntity.getAlbum_title());
        holder.mAppCompatImageView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mSongListEntities.size();
    }

    @Override
    public void onClick(View $View) {
        if(mOnMoreListener!=null&&$View.getId()==R.id.more){
            mOnMoreListener.onClick($View);
        }else if(mOnItemListener!=null&&$View.getId()==R.id.music_item){
            mOnItemListener.onClick($View);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView mImageView;
        TextView title;
        TextView author;
        AppCompatImageView mAppCompatImageView;
        public ImageViewHolder(View itemView) {
            super(itemView);
            mImageView = (SimpleDraweeView) itemView.findViewById(R.id.album_imageview);
            title = (TextView) itemView.findViewById(R.id.song);
            author = (TextView) itemView.findViewById(R.id.song_author);
            mAppCompatImageView = (AppCompatImageView) itemView.findViewById(R.id.more);
        }
    }

}
