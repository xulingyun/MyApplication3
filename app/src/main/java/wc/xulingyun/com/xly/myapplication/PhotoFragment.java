package wc.xulingyun.com.xly.myapplication;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import wc.xulingyun.com.xly.myapplication.dao.ImageDao;
import wc.xulingyun.com.xly.myapplication.http.adapter.PhotoAdapter;

import static android.R.attr.key;

/**
 * Created by 徐玲郓 on 2016/11/1.
 * 描述：
 */

public class PhotoFragment extends Fragment {
    RecyclerView mRecyclerView;
    public final static int spanCount = 3;
    private HashMap<String, List<ImageDao>> mGruopMap = new HashMap<String, List<ImageDao>>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photo_fragment,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.local_music_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(),LinearLayoutManager.HORIZONTAL));
        scanPhoto();
        return view;
    }


    private void scanPhoto(){
        ContentResolver lContentResolver = getActivity().getContentResolver();
        Uri lUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor lCursor = lContentResolver.query(lUri,null,null,null,MediaStore.Images.Media.DATE_MODIFIED);
        if(lCursor==null){
            return;
        }
        ArrayList<String> listKey = new ArrayList<>();
        ImageDao mImageDao;
        while (lCursor.moveToNext()){
            int i = lCursor.getColumnIndex(MediaStore.Images.Media.DATA);
            int j = lCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);
            int k = lCursor.getColumnIndex(MediaStore.Images.Media.DATE_MODIFIED);
            int l = lCursor.getColumnIndex(MediaStore.Images.Media.WIDTH);
            int m = lCursor.getColumnIndex(MediaStore.Images.Media.HEIGHT);
            String key = StringUtils.timeStampToDate(lCursor.getString(k));
            mImageDao = new ImageDao(lCursor.getString(j),lCursor.getString(i),lCursor.getInt(l),lCursor.getInt(m));

            if(!mGruopMap.containsKey(key)){
                List<ImageDao> temp_list = new ArrayList<>();
                temp_list.add(mImageDao);
                mGruopMap.put(key,temp_list);
                listKey.add(key);
            }else{
                mGruopMap.get(key).add(mImageDao);
            }
        }
        Collections.sort(listKey, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return -o1.compareTo(o2);
            }
        });

        lCursor.close();
        mRecyclerView.setAdapter(new PhotoAdapter(getActivity(),mGruopMap,listKey));


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
