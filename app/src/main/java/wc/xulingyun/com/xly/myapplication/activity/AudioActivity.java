package wc.xulingyun.com.xly.myapplication.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

import wc.xulingyun.com.xly.myapplication.R;
import wc.xulingyun.com.xly.myapplication.broadcast.SingBroadcastReceiver;
import wc.xulingyun.com.xly.myapplication.dao.LrcDao;
import wc.xulingyun.com.xly.myapplication.dao.SongInfo;
import wc.xulingyun.com.xly.myapplication.http.bean.SongListEntity;
import wc.xulingyun.com.xly.myapplication.http.util.LoadDataUtil;
import wc.xulingyun.com.xly.myapplication.service.PlayAudioService;
import wc.xulingyun.com.xly.myapplication.util.StringUtils;
import wc.xulingyun.com.xly.myapplication.view.LrcTextView;
import wc.xulingyun.com.xly.myapplication.view.MyTextView;

public class AudioActivity extends AppCompatActivity implements View.OnClickListener {
    TextView playTime;
    TextView totalTime;
    SeekBar progressBar;
    ImageView previous;
    ImageView stop_start;
    ImageView next;
    ImageView playMode;
    MyTextView singName;
    MyTextView singerName;
    LrcTextView song;
    NotificationManager notificationManager;
    public static final String BROADCAST_ACTION = "com.xulingyun.sing.BROADCAST";
    int pos;
    ArrayList<SongListEntity> list1;

    Intent intent;
    ArrayList<LrcDao> lrcDaoArrayList;
    int index_song = 0;
    boolean isHasSong;
    ServiceConnection sc;
    PlayAudioService ps;
    PlayAudioService.SimpleBinder serviceBinder;
    SingBroadcastReceiver broadcastReceiver;
    Notification notification;

    private void setProgressBar(int progress) {
        progressBar.setProgress(progress / 1000);
        playTime.setText(StringUtils.generateTime(progress));
        if (isHasSong) {
            for (int i = index_song; i < lrcDaoArrayList.size(); i++) {
                if (progress < lrcDaoArrayList.get(i).getTime()) {
                    if (i != 0) {
                        song.setIndex(--i);
                    }
                    break;
                } else if (i == lrcDaoArrayList.size() - 1) {
                    song.setIndex(i);
                    break;
                }
            }
            song.invalidate();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        intent = getIntent();
        Intent intent1 = new Intent(this,PlayAudioService.class);
        pos = intent.getIntExtra("postion", 0);
        list1 = (ArrayList<SongListEntity>) intent.getSerializableExtra("audioList");
        intent1.putExtra("postion", intent.getIntExtra("postion", 0));
        Bundle bundle = new Bundle();
        bundle.putSerializable("audioList", (ArrayList<SongListEntity>) intent.getSerializableExtra("audioList"));
        intent1.putExtras(bundle);
        intent1.setAction("com.xulingyun.media.MUSIC_SERVICE");
        bindService(intent1, sc, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_layout);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        findViewById();
        setLister();
        sc = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                serviceBinder = (PlayAudioService.SimpleBinder) service;
                serviceBinder.init();
                ps = serviceBinder.getService();
                ps.setConnected(true);
                createNotifications();
                ps.setOnProgressListener(new PlayAudioService.OnProgressListener() {
                    @Override
                    public void onProgress(int progress) {
                        setProgressBar(progress);
                    }
                });
                ps.setOnInitListener(new PlayAudioService.OnInitListener() {
                    @Override
                    public void init(SongInfo mSongInfo) {

                    }
                });
                ps.setOnUiListener(new PlayAudioService.OnUiListener() {
                    @Override
                    public void ui(final SongInfo mSongInfo) {
                        initUi(mSongInfo);
                        notification.tickerText=mSongInfo.getSonginfo().getTitle();
                        notification.contentView.setTextViewText(R.id.notifications_sing,mSongInfo.getSonginfo().getTitle());
                        notification.contentView.setTextViewText(R.id.notifications_singer,mSongInfo.getSonginfo().getAuthor()+" - " +mSongInfo.getSonginfo().getAlbum_title());

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
//                                    String temp = mSongInfo.getSonginfo().getPic_small();
                                    URL picUrl = new URL(mSongInfo.getSonginfo().getPic_small());
                                    Bitmap pngBM = BitmapFactory.decodeStream(picUrl.openStream());
                                    notification.contentView.setImageViewBitmap(R.id.sing_icon,pngBM);
                                } catch (Exception $E) {
                                    $E.printStackTrace();
                                }finally {
                                }
                            }
                        }).start();
                        notificationManager.notify(1500,notification);
                    }
                });
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };
        progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    progressBar.setProgress(progress);
                    ps.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        IntentFilter filter = new IntentFilter();
        filter.addAction(BROADCAST_ACTION);
        broadcastReceiver = new SingBroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                super.onReceive(context, intent);
                if(intent.getIntExtra("index",-1)==0){
                    ps.stopOrStart();
                }if(intent.getIntExtra("index",-1)==1){
                    ps.next();
                }if(intent.getIntExtra("index",-1)==2){
                    finish();
                }
            }
        };
        registerReceiver(broadcastReceiver,filter);

    }

    private void initUi(SongInfo mSongInfo) {
        progressBar.setMax(mSongInfo.getBitrate().getFile_duration());
        progressBar.setProgress(0);
        totalTime.setText(StringUtils.generateTime(mSongInfo.getBitrate().getFile_duration()*1000));
        playTime.setText(R.string.initTime);
        singName.setText(mSongInfo.getSonginfo().getTitle());
        singerName.setText(mSongInfo.getSonginfo().getAuthor());
        stop_start.setImageResource(R.drawable.ic_play_circle_outline_white_36dp);
        LoadDataUtil.loadLrc(mSongInfo.getSonginfo().getLrclink().replace("http://musicdata.baidu.com/data2/lrc/",""),mSongInfo.getSonginfo().getTitle(),this);
    }

    void setLister() {
        playMode.setOnClickListener(this);
        previous.setOnClickListener(this);
        stop_start.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    public void initSong(String filePath) {
        lrcDaoArrayList = StringUtils.parseLrcSong(filePath);
        if (lrcDaoArrayList != null) {
            isHasSong = true;
            song.setLrcDaoArrayList(lrcDaoArrayList);
            song.setIndex(0);
        } else {
            isHasSong = false;
        }
        song.setHasSong(isHasSong);
    }

    private void findViewById() {
        song = (LrcTextView) findViewById(R.id.song);
        singName = (MyTextView) findViewById(R.id.sing_name);
        singerName = (MyTextView) findViewById(R.id.singer_name);
        playTime = (TextView) findViewById(R.id.play_time);
        totalTime = (TextView) findViewById(R.id.total_time);
        progressBar = (SeekBar) findViewById(R.id.progress_bar);
        playMode = (ImageView) findViewById(R.id.play_mode);
        previous = (ImageView) findViewById(R.id.previous);
        stop_start = (ImageView) findViewById(R.id.stop_start);
        next = (ImageView) findViewById(R.id.next);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_mode:
                int mode = ps.setPlayMode();
                if (mode == 1) {
                    playMode.setImageResource(R.drawable.ic_loop_white_36dp);
                } else if (mode == 2) {
                    playMode.setImageResource(R.drawable.ic_shuffle_white_36dp);
                } else if (mode == 3) {
                    playMode.setImageResource(R.drawable.ic_loop_white_36dp);
                }
                break;
            case R.id.previous:
                ps.previous();
                break;
            case R.id.stop_start:
                int state = ps.stopOrStart();
                if (state == 1) {
                    stop_start.setImageResource(R.drawable.ic_pause_circle_outline_white_36dp);
                } else {
                    stop_start.setImageResource(R.drawable.ic_play_circle_outline_white_36dp);
                }
                break;
            case R.id.next:
                ps.next();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
        notificationManager.cancel(1500);
        unbindService(sc);
    }

    private void createNotifications(){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.notification_layout);
        Intent stopOrStartIntent = new Intent();
        stopOrStartIntent.setAction(BROADCAST_ACTION);
        stopOrStartIntent.putExtra("index",0);
        PendingIntent stopOrStartPendingIntent = PendingIntent.getBroadcast(this,0,stopOrStartIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.notifications_stop,stopOrStartPendingIntent);

        Intent nextIntent = new Intent();
        nextIntent.setAction(BROADCAST_ACTION);
        nextIntent.putExtra("index",1);
        PendingIntent nextPendingIntent = PendingIntent.getBroadcast(this,1,nextIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.notifications_next,nextPendingIntent);

        Intent closeIntent = new Intent();
        closeIntent.setAction(BROADCAST_ACTION);
        closeIntent.putExtra("index",2);
        PendingIntent colsePendingIntent = PendingIntent.getBroadcast(this,2,closeIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.notifications_close,colsePendingIntent);

        Intent intent = new Intent(this,AudioActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, 0);
        mBuilder.setContent(remoteViews)
                .setTicker("通知开始播放歌曲了！")
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_music_note_red_900_24dp)
                .setContentIntent(pendingIntent)
                .setColor(0xffffff)
                .setOngoing(true);
        notification = mBuilder.build();
        notification.flags = Notification.FLAG_ONGOING_EVENT;
    }
}
