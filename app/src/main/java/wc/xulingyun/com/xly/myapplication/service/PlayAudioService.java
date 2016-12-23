package wc.xulingyun.com.xly.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import wc.xulingyun.com.xly.myapplication.dao.SongInfo;
import wc.xulingyun.com.xly.myapplication.http.bean.SongListEntity;
import wc.xulingyun.com.xly.myapplication.http.util.LoadDataUtil;

public class PlayAudioService extends Service{

    boolean isConnected;
    MediaPlayer mediaPlayer;
    public static final int MODE_LOOP = 0;
    public static final int MODE_SINGLE_CYCLE = 1;
    public static final int MODE_RANDOM = 2;
    int PLAY_MODE = 0;
    Intent intent;


    public void setOnUiListener(OnUiListener $OnUiListener) {
        onUiListener = $OnUiListener;
    }

    public static final int INIT_UI_ALL = 0;
    public static final int UPDATA_PROGRESS = 1;
    public static final int INIT_UI = 2;
    ArrayList<SongListEntity> audioList;
    int postion;
    Random random;
    int progress;
    private OnProgressListener onProgressListener;
    private OnInitListener onInitListener;
    private OnUiListener onUiListener;
    SongInfo mSongInfo;

    public interface OnProgressListener {
        void onProgress(int progress);
    }

    public interface OnUiListener {
        void ui(SongInfo mSongInfo);
    }

    public interface OnInitListener {
        void init(SongInfo mSongInfo);
    }

    public void setOnProgressListener(OnProgressListener onProgressListener) {
        this.onProgressListener = onProgressListener;
    }

    public void setOnInitListener(OnInitListener onInitListener) {
        this.onInitListener = onInitListener;
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case INIT_UI://清除进度条，歌词这些的消息，然后初始化整个界面
//                    onInitListener.init(audioList.get(postion));
                    msg = obtainMessage(UPDATA_PROGRESS);
                    sendMessageDelayed(msg, 500);
                    break;
                case UPDATA_PROGRESS:
                    onProgressListener.onProgress(mediaPlayer.getCurrentPosition());
                    msg = obtainMessage(UPDATA_PROGRESS);
                    sendMessageDelayed(msg, 500);
                    break;
                case INIT_UI_ALL:
                    onUiListener.ui(mSongInfo);
                    msg = obtainMessage(UPDATA_PROGRESS);
                    sendMessageDelayed(msg, 0);
                    break;
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new SimpleBinder(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        mediaPlayer = null;
        mHandler.removeMessages(UPDATA_PROGRESS);
        mHandler.removeMessages(INIT_UI);
    }

    public class SimpleBinder extends Binder {
        Intent intent;
        public SimpleBinder(Intent $Intent) {
            this.intent = $Intent;
        }

        public PlayAudioService getService() {
            return PlayAudioService.this;
        }

        public void init() {
            audioList = (ArrayList<SongListEntity>) intent.getSerializableExtra("audioList");
            postion = intent.getIntExtra("postion", 0);
            LoadDataUtil.getSong("baidu.ting.song.play",audioList.get(postion).getSong_id(),PlayAudioService.this);

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (PLAY_MODE == MODE_LOOP) {
                        postion++;
                        if (postion >= audioList.size())
                            postion = 0;
                    } else if (PLAY_MODE == MODE_RANDOM) {
                        random = new Random();
                        postion = random.nextInt(audioList.size());
                    } else if (PLAY_MODE == MODE_SINGLE_CYCLE) {
//              单曲循环
                    }
                    mp.reset();
                    LoadDataUtil.getSong("baidu.ting.song.play",audioList.get(postion).getSong_id(),PlayAudioService.this);
                }
            });
        }
    }

    public int setPlayMode() {
        if (PLAY_MODE == MODE_LOOP) {
            PLAY_MODE = MODE_SINGLE_CYCLE;
        } else if (PLAY_MODE == MODE_SINGLE_CYCLE) {
            PLAY_MODE = MODE_RANDOM;
        } else if (PLAY_MODE == MODE_RANDOM) {
            PLAY_MODE = MODE_LOOP;
        }
        return PLAY_MODE;
    }

    /**
     * 返回值为1，表示暂停。为0表示播放
     */
    public int stopOrStart() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            return 1;
        } else {
            try {
                mediaPlayer.prepareAsync();
                mediaPlayer.start();
                return 0;
            } catch (Exception e) {
                e.printStackTrace();
                return 1;
            }
        }
    }

    public void previous() {
        if (PLAY_MODE == MODE_LOOP || PLAY_MODE == MODE_SINGLE_CYCLE) {
            postion--;
            if (postion < 0)
                postion = audioList.size() - 1;
        } else if (PLAY_MODE == MODE_RANDOM) {
            random = new Random();
            postion = random.nextInt(audioList.size());
        }
        mediaPlayer.reset();
        LoadDataUtil.getSong("baidu.ting.song.play",audioList.get(postion).getSong_id(),this);

    }

    public void next() {
        if (PLAY_MODE == MODE_LOOP || PLAY_MODE == MODE_SINGLE_CYCLE) {
            postion++;
            if (postion >= audioList.size())
                postion = 0;
        } else if (PLAY_MODE == MODE_RANDOM) {
            random = new Random();
            postion = random.nextInt(audioList.size());
        }
        mediaPlayer.reset();
        LoadDataUtil.getSong("baidu.ting.song.play",audioList.get(postion).getSong_id(),this);
    }

    public void initMediaPlayer(final SongInfo songInfo) {
        this.mSongInfo = songInfo;
        try {
            mediaPlayer.setDataSource(mSongInfo.getBitrate().getShow_link());
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
            initUiMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initUiMessage() {
        Message message = Message.obtain();
        message.what = INIT_UI_ALL;
        mHandler.sendMessage(message);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        mediaPlayer.seekTo(progress * 1000);
    }

    public void setConnected(boolean isConnected){
        this.isConnected = isConnected;
    }
}
