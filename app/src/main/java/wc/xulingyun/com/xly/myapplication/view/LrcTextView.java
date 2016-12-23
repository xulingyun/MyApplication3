package wc.xulingyun.com.xly.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import java.util.ArrayList;

import wc.xulingyun.com.xly.myapplication.dao.LrcDao;


/**
 * Created by Administrator on 2016/3/6 0006.
 */
public class LrcTextView extends TextView {

    Paint mUnselect;
    Paint mSelect;
    ArrayList<LrcDao> mLrcList;
    int index = 0;
    boolean isHasSong;
    float lTextHeight;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setLrcDaoArrayList(ArrayList<LrcDao> mLrcList) {
        this.mLrcList = mLrcList;
    }

    public LrcTextView(Context context) {
        super(context);
        init();
    }

    public LrcTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LrcTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init(){
        mUnselect = new Paint();
        mUnselect.setTextSize(sp2px(getContext(),18));
        mUnselect.setColor(0xffffffff);
        mUnselect.setAntiAlias(true);
        mUnselect.setTextAlign(Paint.Align.CENTER);
        mUnselect.setTypeface(Typeface.DEFAULT);

        mSelect = new Paint();
        mSelect.setTextSize(sp2px(getContext(),20));
        mSelect.setColor(0xffffff00);
        mSelect.setAntiAlias(true);
        mSelect.setTextAlign(Paint.Align.CENTER);
        mSelect.setTypeface(Typeface.SERIF);
        lTextHeight = sp2px(getContext(),22);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(isHasSong) {
            canvas.drawText(mLrcList.get(index).getContent(), getWidth() / 2, getHeight() / 2, mSelect);
            float tempY = getHeight() / 2;
            //画出本句之前的句子
            for (int i = index - 1; i >= 0; i--) {
                //向上推移
                tempY -= lTextHeight;
                canvas.drawText(mLrcList.get(i).getContent(), getWidth() / 2, tempY, mUnselect);
            }
            tempY = getHeight() / 2;
            //画出本句之后的句子
            for (int i = index + 1; i < mLrcList.size(); i++) {
                //往下推移
                tempY += lTextHeight;
                canvas.drawText(mLrcList.get(i).getContent(), getWidth() / 2, tempY, mUnselect);
            }
        }else{
            canvas.drawText("你还没有歌词哦，小盆友！", getWidth() / 2, getHeight() / 2, mSelect);
        }
    }


    public void setHasSong(boolean b) {
        this.isHasSong = b;
    }

    public static int sp2px(Context context, float spVal)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }
}
