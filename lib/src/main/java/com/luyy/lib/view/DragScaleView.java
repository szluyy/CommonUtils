package com.luyy.lib.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.*;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.customview.widget.ViewDragHelper;
import com.luyy.lib.R;
import com.luyy.lib.utils.LogUtils;
import com.luyy.lib.utils.ToastUtils;

public class DragScaleView extends FrameLayout{

    private ViewDragHelper mHelper;
    private View target;
    private RelativeLayout container;
    private RelativeLayout content;
    private CircleWithIconView circleLeftTop;
    private CircleWithIconView circleLeftBottom;
    private CircleWithIconView circleRightTop;
    private CircleWithIconView circleRightBottom;
    private float mPreviewScale=0f;
    public DragScaleView(Context context) {
        this(context,null);
    }

    public DragScaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
         mHelper=  ViewDragHelper.create(this, new ViewDragHelper.Callback() {
             @Override
             public boolean tryCaptureView(@NonNull View child, int pointerId) {

                 return child==container;
             }

             @Override
             public void onViewCaptured(@NonNull View capturedChild, int activePointerId) {
                 super.onViewCaptured(capturedChild, activePointerId);
                 content.setBackgroundResource(R.drawable.bg_white_border);
                showCorner();
             }

             @Override
             public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
                 return top;
             }

             @Override
             public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
                return left;
             }


         });

    }

    private void initView() {
        container = new RelativeLayout(getContext());
        addView(container);
        circleLeftBottom = new CircleWithIconView(getContext(), -1);
        circleLeftTop = new CircleWithIconView(getContext(), -1);
        circleRightBottom = new CircleWithIconView(getContext(), -1);
        circleRightTop = new CircleWithIconView(getContext(), R.mipmap.ic_rotate);

        content = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams lp_content = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = Math.max(Math.max(circleLeftBottom.getRadius(), circleLeftTop.getRadius()), Math.max(circleRightBottom.getRadius(), circleRightTop.getRadius()));
        lp_content.setMargins(margin, margin, margin, margin);
        content.setLayoutParams(lp_content);
        container.addView(content);

        RelativeLayout.LayoutParams lp_lb = new RelativeLayout.LayoutParams(0, 0);
        RelativeLayout.LayoutParams lp_lt = new RelativeLayout.LayoutParams(0, 0);
        RelativeLayout.LayoutParams lp_rb = new RelativeLayout.LayoutParams(0, 0);
        RelativeLayout.LayoutParams lp_rt = new RelativeLayout.LayoutParams(0, 0);

        lp_lb.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lp_lb.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lp_lb.leftMargin = margin - circleLeftBottom.getRadius();
        lp_lb.bottomMargin = margin - circleLeftBottom.getRadius();

        lp_lt.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lp_lt.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lp_lt.leftMargin = margin - circleLeftTop.getRadius();
        lp_lt.topMargin = margin - circleLeftTop.getRadius();


        lp_rb.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp_rb.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lp_rb.rightMargin = margin - circleRightBottom.getRadius();
        lp_rb.bottomMargin = margin - circleRightBottom.getRadius();

        lp_rt.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp_rt.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lp_rt.rightMargin = margin - circleRightTop.getRadius();
        lp_rt.topMargin = margin - circleRightTop.getRadius();

        circleLeftTop.setLayoutParams(lp_lt);
        circleRightTop.setLayoutParams(lp_rt);
        circleRightBottom.setLayoutParams(lp_rb);
        circleLeftBottom.setLayoutParams(lp_lb);


        container.addView(circleLeftTop);
        container.addView(circleRightTop);
        container.addView(circleRightBottom);
        container.addView(circleLeftBottom);

        circleLeftTop.setVisibility(GONE);
        circleRightTop.setVisibility(GONE);
        circleRightBottom.setVisibility(GONE);
        circleLeftBottom.setVisibility(GONE);
//
//        circleLeftTop.setOnTouchListener(this);
//        circleRightTop.setOnTouchListener(this);
//        circleRightBottom.setOnTouchListener(this);


        circleLeftBottom.setListener(new CircleWithIconView.ViewDragListener() {
            @Override
            public void onDrag(float rawX, float rawY) {
//                float infract=  -(container.getX()-rawX)*2/container.getWidth();
//
//                resize(infract+mPreviewScale);
//                mPreviewScale=infract;
                rawX=container.getLeft()+rawX;
                rawY=container.getTop()+rawY;
                double length=  Math.sqrt((container.getX()-rawX)*(container.getX()-rawX)+(container.getY()-rawY)*(container.getY()-rawY));
                double length2=Math.sqrt(container.getWidth()*container.getWidth()/4+container.getHeight()*container.getHeight()/4);
                resize(length/(length+length2));
            }
        });
    }
    public void addTargetView(View view){
        this.target=view;
        content.addView(view);
    }

        @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mHelper.processTouchEvent(event);
        return true;
    }

    //显示四个顶点
    private void showCorner(){

        LayoutParams lp= (LayoutParams) container.getLayoutParams();
        lp.width=target.getWidth();
        lp.height=target.getHeight();

        circleLeftBottom.setVisibility(VISIBLE);
        circleRightBottom.setVisibility(VISIBLE);
        circleRightTop.setVisibility(VISIBLE);
        circleLeftTop.setVisibility(VISIBLE);
    }


    private void resize(double yinzi){

        int nWidth= (int) (container.getWidth()*(1+yinzi));
        int nHeight= (int) (container.getHeight()*(1+yinzi));

//        container.setLayoutParams(lp);
        container.layout(container.getLeft()+container.getWidth()/2-nWidth/2,
                container.getTop()+container.getHeight()/2-nHeight/2,
                container.getLeft()+container.getWidth()/2+nWidth/2,
                container.getTop()+container.getHeight()/2+nHeight/2);
    }

}
