package wc.xulingyun.com.xly.myapplication;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import wc.xulingyun.com.xly.myapplication.dao.ImageDao;
import wc.xulingyun.com.xly.myapplication.http.adapter.PhotoAdapter;
import wc.xulingyun.com.xly.myapplication.http.adapter.RecycleViewDivider;

/**
 * Created by 徐玲郓 on 2016/11/1.
 * 描述：
 */

public class PhotoFragment extends Fragment {
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photo_fragment,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.local_music_recycler);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(),LinearLayoutManager.HORIZONTAL));
        scanMusic();
        return view;
    }


    private void scanMusic(){
        ContentResolver lContentResolver = getActivity().getContentResolver();
        Uri lUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor lCursor = lContentResolver.query(lUri,null,null,null,MediaStore.Images.Media.DATE_MODIFIED);
        if(lCursor==null){
            return;
        }
        ArrayList<ImageDao> list = new ArrayList<>();
        ImageDao mImageDao;
        while (lCursor.moveToNext()){
            int i = lCursor.getColumnIndex(MediaStore.Images.Media.DATA);
            int j = lCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);
//            int k = lCursor.getColumnIndex(MediaStore.Images.Media.WIDTH);
//            int l = lCursor.getColumnIndex(MediaStore.Images.Media.HEIGHT);
            mImageDao = new ImageDao(lCursor.getString(j),lCursor.getString(i));
            list.add(mImageDao);
        }
        mRecyclerView.setAdapter(new PhotoAdapter(getActivity(),list));


//        Observable.just(lCursor)
//                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
//                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
//                .subscribe(new Action1<Cursor>() {
//                    @Override
//                    public void call(Cursor lCursor) {
//
//                        lCursor.close();
//                    }
//                });


    }
}
