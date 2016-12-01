package wc.xulingyun.com.xly.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements OnShowOrHideListener{

    private int height;
    private int width;
    boolean isStartAnim;
    boolean isHideAnim;
    private int bottomHeight;
    Unbinder mUnBinder;
    @BindView(R.id.bottomNavigation)
    BottomNavigationView lBottomNavigationView;
    @BindView(R.id.main_view_pager)
    NOScollViewPager mainViewPage;
    ArrayList<Fragment> mFragmentArrayList;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getBottomHeight() {
        return bottomHeight;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mUnBinder = ButterKnife.bind(this);
        getWidthAndHeight();

        getFirstBottomHeight();

        TotalMusicFragment lFragment1 = new TotalMusicFragment();
        MusicFragment lFragment2 = MusicFragment.newInstance("11");
        TotalContactsFragment lFragment3 = new TotalContactsFragment();
        KuaidiFragment lFragment4 = new KuaidiFragment();
        mFragmentArrayList = new ArrayList<>();
        mFragmentArrayList.add(lFragment1);
        mFragmentArrayList.add(lFragment2);
        mFragmentArrayList.add(lFragment3);
        mFragmentArrayList.add(lFragment4);
        mainViewPage.setAdapter(new Mypageradapter(getSupportFragmentManager(),mFragmentArrayList,new String[]{"音乐","照片","电话","快递"}));

        lBottomNavigationView.setUpWithViewPager(mainViewPage,
                new int[]{R.color.red,R.color.colorPrimary,R.color.gray,R.color.default_indexBar_textColor},
                new int[]{R.drawable.ic_music_video_white_36dp,R.drawable.ic_photo_size_select_actual_white_36dp,
                        R.drawable.ic_local_phone_white_36dp,R.drawable.ic_bug_report_white_36dp}
        );
        lBottomNavigationView.isColoredBackground(false);
        lBottomNavigationView.setItemActiveColorWithoutColoredBackground(R.color.red);
    }

    private void getWidthAndHeight(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        height = dm.heightPixels;
        width = dm.widthPixels;
    }

    private void getFirstBottomHeight(){
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        lBottomNavigationView.measure(w, h);
        bottomHeight = lBottomNavigationView.getMeasuredHeight();
//        int width = lBottomNavigationView.getMeasuredWidth();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("退出")
                .setMessage("确定退出应用?")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("否",null);
        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }

    @Override
    public void showBottomMenu() {
        if(isStartAnim||!isHideAnim) return;
        ValueAnimator valueAnimator = ValueAnimator.ofInt(bottomHeight,0);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(lBottomNavigationView, "alpha", 0, 1);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                lBottomNavigationView.setTranslationY((int)valueAnimator.getAnimatedValue());
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                isStartAnim = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isStartAnim = false;
                isHideAnim = false;
            }
        });
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(valueAnimator,alpha);
        animatorSet.start();
    }

    @Override
    public void hideBottomMenu(){
        if(isStartAnim||isHideAnim) return;
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,bottomHeight);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(lBottomNavigationView, "alpha", 1, 0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                lBottomNavigationView.setTranslationY((int)valueAnimator.getAnimatedValue());
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                isStartAnim = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isStartAnim = false;
                isHideAnim = true;
            }
        });
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(valueAnimator,alpha);
        animatorSet.start();
    }

}
