package wc.xulingyun.com.xly.myapplication.http.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import wc.xulingyun.com.xly.myapplication.R;
import wc.xulingyun.com.xly.myapplication.http.bean.LocalMusic;

/**
 * Created by 徐玲郓 on 2016/10/24.
 * 描述：
 */

public class LocalMusicAdapter extends RecyclerView.Adapter<LocalMusicAdapter.ImageViewHolder> implements View.OnClickListener{

    private OnMoreListener mOnMoreListener;
    private OnItemListener mOnItemListener;
    Context mContext;
    List<LocalMusic> mLocalMusic;

    public void setOnMoreListener(OnMoreListener $OnMoreListener) {
        mOnMoreListener = $OnMoreListener;
    }

    public void setOnItemListener(OnItemListener $OnItemListener) {
        mOnItemListener = $OnItemListener;
    }

    public LocalMusicAdapter(Context $Context, List<LocalMusic> $LocalMusic){
        this.mContext = $Context;
        this.mLocalMusic = $LocalMusic;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.local_music_item,parent,false);
        v.setOnClickListener(this);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        LocalMusic lLocalMusic =  mLocalMusic.get(position);
        holder.title.setText(lLocalMusic.getDisplayName());
        holder.author.setText(lLocalMusic.getSong()+"·"+lLocalMusic.getAlbum());
        holder.mAppCompatImageView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mLocalMusic.size();
    }

    @Override
    public void onClick(View $View) {
        if(mOnMoreListener!=null&&$View.getId()==R.id.more){
            mOnMoreListener.onClick($View);
        }
//        else if(mOnItemListener!=null&&$View.getId()==R.id.music_item){
//            mOnItemListener.onClick($View);
//        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView author;
        AppCompatImageView mAppCompatImageView;
        public ImageViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.song);
            this.author = (TextView) itemView.findViewById(R.id.song_author);
            this.mAppCompatImageView = (AppCompatImageView) itemView.findViewById(R.id.more);
        }
    }
}
