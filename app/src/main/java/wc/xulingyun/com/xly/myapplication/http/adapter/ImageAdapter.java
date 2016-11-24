package wc.xulingyun.com.xly.myapplication.http.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import wc.xulingyun.com.xly.myapplication.R;
import wc.xulingyun.com.xly.myapplication.http.bean.SongListEntity;

/**
 * Created by 徐玲郓 on 2016/10/24.
 * 描述：
 */

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{

    private OnMoreListener mOnMoreListener;
    private OnItemListener mOnItemListener;
    private Context mContext;
    private List<SongListEntity> mSongListEntities;
    private FooterViewHolder mFooterViewHolder;

    public void setFooterTextView(String text){
        mFooterViewHolder.tv.setText(text);
        mFooterViewHolder.mProgressBar.setVisibility(View.INVISIBLE);
    }


    public void addData(List<SongListEntity> $SongListEntities){
        if(mSongListEntities==null){
            mSongListEntities = new ArrayList<>();
        }
        mSongListEntities.addAll($SongListEntities);
    }

    public void refreshData(List<SongListEntity> $SongListEntities){
        if(mSongListEntities==null){
            mSongListEntities = new ArrayList<>();
            mSongListEntities.addAll($SongListEntities);
        }else{
            mSongListEntities.clear();
            mSongListEntities.addAll($SongListEntities);
        }
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
            View v = LayoutInflater.from(mContext).inflate(R.layout.image_item,parent,false);
            v.setOnClickListener(this);
            return new MusicViewHolder(v);
        }else{
            View v = LayoutInflater.from(mContext).inflate(R.layout.footer_layout,parent,false);
            v.setOnClickListener(this);
            return new FooterViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MusicViewHolder){
            SongListEntity lSongListEntity = mSongListEntities.get(position);
            Uri uri = Uri.parse(lSongListEntity.getPic_small());
            ((MusicViewHolder)holder).mImageView.setImageURI(uri);
            ((MusicViewHolder)holder).title.setText(lSongListEntity.getTitle());
            ((MusicViewHolder)holder).author.setText(lSongListEntity.getAuthor() + "·" + lSongListEntity.getAlbum_title());
            ((MusicViewHolder)holder).mAppCompatImageView.setOnClickListener(this);
        }else if(holder instanceof FooterViewHolder){
            mFooterViewHolder = ((FooterViewHolder)holder);
            mFooterViewHolder.tv.setText(R.string.load_more_loading);
            mFooterViewHolder.mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mSongListEntities.size()+1;
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
        if(position<getItemCount()-1){
            return 0;
        }else{
            return 1;
        }
    }

    class MusicViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView mImageView;
        TextView title;
        TextView author;
        AppCompatImageView mAppCompatImageView;
        public MusicViewHolder(View itemView) {
            super(itemView);
            mImageView = (SimpleDraweeView) itemView.findViewById(R.id.album_imageview);
            title = (TextView) itemView.findViewById(R.id.song);
            author = (TextView) itemView.findViewById(R.id.song_author);
            mAppCompatImageView = (AppCompatImageView) itemView.findViewById(R.id.more);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ProgressBar mProgressBar;
        public FooterViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar);
        }
    }



}
