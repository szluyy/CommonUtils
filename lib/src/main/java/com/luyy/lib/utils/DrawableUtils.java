package com.luyy.lib.utils;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

/**
 * 作者：     陆阳洋
 * 创建日期： 2019/4/17 0017 下午 2:41
 * 功能描述： drawable 的相关操作
 */
public class DrawableUtils {

    public static class DrawableBuilder{
        private int solidColor;
        private int strokeWidth;
        private int strokeColor;
        private float leftTopCorner=0;
        private float leftBottomCorner=0;
        private float rightTopCorner=0;
        private float rightBottomCorner=0;
        private int startColor;
        private int centerColor;
        private int endColor;
        private GradientDrawable.Orientation orientation;

        public DrawableBuilder(){

        }

       public   DrawableBuilder radius(float radius){
            leftTopCorner=radius;
            leftBottomCorner=radius;
            rightTopCorner=radius;
            rightBottomCorner=radius;
            return this;
        }

        public  DrawableBuilder radius(float leftTop,float rightTop,float rightBottom,float leftBottom){
            leftTopCorner=leftTop;
            leftBottomCorner=leftBottom;
            rightTopCorner=rightTop;
            rightBottomCorner=rightBottom;
            return this;
        }

        public DrawableBuilder solidColor(int  solidColor){
           this.solidColor=solidColor;
            return this;
        }

        public DrawableBuilder stroke(int strokeWidth,int strokeColor){
            this.strokeWidth=strokeWidth;
            this.strokeColor=strokeColor;
            return this;
        }

        public DrawableBuilder gradient(int startColor, int centerColor, int endColor, GradientDrawable.Orientation orientation){
            this.startColor=startColor;
            this.centerColor=centerColor;
            this.endColor=endColor;
            this.orientation=orientation;
            return this;
        }


        public  Drawable build(){
            GradientDrawable drawable=new GradientDrawable(orientation,new int[]{startColor,centerColor,endColor});
            drawable.setCornerRadii(new float[]{leftTopCorner,leftTopCorner,rightTopCorner,rightTopCorner,rightBottomCorner,rightBottomCorner,leftBottomCorner,leftBottomCorner});
            if(solidColor>0){
                drawable.setColor(solidColor);
            }
            drawable.setStroke(strokeWidth,strokeColor);
            return drawable;
        }
    }
}
