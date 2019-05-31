package com.luyy.lib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.luyy.lib.utils.LogUtils;


public class CircleWithIconView extends View {
    private int mIconRes;
    private Paint mPaint;
    private Paint mBitmapPaint;
    private int mRadiusWithIcon=25;
    private int mRadiusWithoutIcon=25;
    private float mLastX=0;
    private float mLastY=0;
    private ViewDragListener listener;
    public CircleWithIconView(Context context,int iconRes) {
        this(context,null);
        this.mIconRes=iconRes;
    }

    public CircleWithIconView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mPaint.setColor(Color.WHITE);
        mBitmapPaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mBitmapPaint.setFilterBitmap(true);
        setClickable(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if(mIconRes>0){
            setMeasuredDimension(mRadiusWithIcon*2,mRadiusWithIcon*2);
        }else{
            setMeasuredDimension(mRadiusWithoutIcon*2,mRadiusWithoutIcon*2);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mIconRes>0){
            canvas.drawCircle(getWidth()/2,getHeight()/2,mRadiusWithIcon,mPaint);
            Drawable drawable= getResources().getDrawable(mIconRes);
            BitmapDrawable bm= (BitmapDrawable) drawable;
            canvas.drawBitmap(bm.getBitmap(),null
                             ,new Rect((int) (2*mRadiusWithIcon*0.1),(int) (2*mRadiusWithIcon*0.1), (int) (2*mRadiusWithIcon*0.9), (int) (2*mRadiusWithIcon*0.9)),mBitmapPaint);
        }else{
            canvas.drawCircle(getWidth()/2,getHeight()/2,mRadiusWithoutIcon,mPaint);
        }
    }

    public int getRadius(){
        if(mIconRes>0)
            return mRadiusWithIcon;
        return mRadiusWithoutIcon;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            mLastX=event.getRawX();
            mLastY=event.getRawY();
        }else if(event.getAction()==MotionEvent.ACTION_MOVE){
            if(listener!=null){
                listener.onDrag(event.getX(),event.getY());
            }
            mLastX=event.getRawX();
            mLastY=event.getRawY();
        }

        return super.onTouchEvent(event);
    }

    public void setListener(ViewDragListener listener) {
        this.listener = listener;
    }

    public interface ViewDragListener{
        void onDrag(float rawX,float rawY);
    }
}
