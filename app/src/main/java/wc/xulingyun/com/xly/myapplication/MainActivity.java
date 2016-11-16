package wc.xulingyun.com.xly.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;

import java.util.ArrayList;

import static wc.xulingyun.com.xly.myapplication.R.id.imageView;
import static wc.xulingyun.com.xly.myapplication.R.id.toolbar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,OnShowOrHideListener{

    private int height;
    private int width;
    boolean isStartAnim;
    boolean isHideAnim;
    private int bottomHeight;

    LinearLayout lBottomNavigationView;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getWidthAndHeight();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction lFragmentTransaction = fm.beginTransaction();
        lFragmentTransaction.add(R.id.fl_body,new TotalMusicFragment(),"TotalMusicFragment");
        lFragmentTransaction.commit();

        lBottomNavigationView = (LinearLayout) findViewById(R.id.bottomNavigation);
        getBottomHeight();



//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
//
//        BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
//                ("Record", ContextCompat.getColor(this, R.color.red), R.drawable.ic_person_pin_blue_a100_36dp);
//        BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
//                ("Like", ContextCompat.getColor(this, R.color.withoutColoredBackground), R.drawable.ic_chat_blue_a100_36dp);
//        bottomNavigationView.addTab(bottomNavigationItem);
//        bottomNavigationView.addTab(bottomNavigationItem1);
    }

    public void createPopupWindow(){
        View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.popup_layout, null);
        PopupWindow mPopupWindow = new PopupWindow(contentView,width,height*1/2,true);
        mPopupWindow.setAnimationStyle(R.style.MyPopupWindow);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0xaa00ff00));
//        View rootview = LayoutInflater.from(MainActivity.this).inflate(R.layout.content_main, null);
//        Button lButton = (Button) findViewById(R.id.ceshi);
//        mPopupWindow.showAsDropDown(toolbar,0,0,Gravity.BOTTOM);
    }

    private void getWidthAndHeight(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        height = dm.heightPixels;
        width = dm.widthPixels;
    }

    private void getBottomHeight(){
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showBottomMenu() {
        if(isStartAnim||!isHideAnim) return;
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,bottomHeight);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(lBottomNavigationView, "alpha", 0, 1);

        final ViewGroup.LayoutParams layoutParams = lBottomNavigationView.getLayoutParams();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                layoutParams.height= (int) valueAnimator.getAnimatedValue();
                System.out.println("layoutParams.height:"+layoutParams.height);
                lBottomNavigationView.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isStartAnim = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isStartAnim = false;
                isHideAnim = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

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
        ValueAnimator valueAnimator = ValueAnimator.ofInt(bottomHeight,0);
        System.out.println("height11:"+height);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(lBottomNavigationView, "alpha", 1, 0);
        final ViewGroup.LayoutParams layoutParams = lBottomNavigationView.getLayoutParams();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                layoutParams.height= (int) valueAnimator.getAnimatedValue();
                System.out.println("layoutParams.height11:"+layoutParams.height);
                lBottomNavigationView.setLayoutParams(layoutParams);
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
