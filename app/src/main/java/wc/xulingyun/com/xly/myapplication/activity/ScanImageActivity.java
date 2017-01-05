package wc.xulingyun.com.xly.myapplication.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wc.xulingyun.com.xly.myapplication.R;
import wc.xulingyun.com.xly.myapplication.dao.ImageDao;
import wc.xulingyun.com.xly.myapplication.dao.SerializableMap;

import static android.R.attr.bitmap;
import static cn.finalteam.loadingviewfinal.util.PtrLocalDisplay.dp2px;

public class ScanImageActivity extends AppCompatActivity {

    @BindView(R.id.scan_view_page)
    ViewPager mViewPager;
    private Unbinder mUnBinder;
    SerializableMap mSerializableMap;
    int totalNum;
    LayoutInflater mInflater;
    ImageView image_view;
    ArrayList<ImageView> image_list;
    int listIndex;
    int index;
    int selectIndex;
    int jishu = 0;
    ArrayList<ImageDao> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sacn_image);
        mUnBinder = ButterKnife.bind(this);

        mSerializableMap = getIntent().getParcelableExtra("totalMap");
        HashMap<String, List<ImageDao>> map = mSerializableMap.getMap();
        ArrayList<String> serializableList = mSerializableMap.getListKey();
        listIndex = mSerializableMap.getListIndex();
        index = mSerializableMap.getIndex();
        System.out.println("listIndex:"+listIndex+",index:"+index);

        totalNum = 0;
        jishu = 0;
        selectIndex = 0;
        mInflater = LayoutInflater.from(this);
        View view = mInflater.inflate(R.layout.item_image,null,false);
        image_view = (ImageView) view.findViewById(R.id.item_image);
        list = new ArrayList<>();
        for(String str:serializableList){
            if(jishu==listIndex)
                selectIndex=totalNum+index;
            jishu++;
            System.out.println("index:key:"+str);
            totalNum += map.get(str).size();
            list.addAll(map.get(str));
        }
        image_list = new ArrayList<>();
        for (int i=0;i<totalNum;i++){
            ImageView iv = new ImageView(this);
            image_list.add(iv);
        }
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(image_list.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView iv = image_list.get(position);
//                Bitmap b = getBitmap(list.get(position).getPath());
//                iv.setImageBitmap(b);
                Picasso.with(ScanImageActivity.this.getApplicationContext())
                        .load(Uri.parse("file://"+list.get(position).getPath()))
                        .resize(250,250)
                        .memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE)
                        .config(Bitmap.Config.RGB_565)
                        .centerInside()
                        .into(iv);
                container.addView(iv);
                return iv;
            }
        });
        mViewPager.setCurrentItem(selectIndex,true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }

    public Bitmap getBitmap(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(path);
    }
}
