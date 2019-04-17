package com.luyy.lib.view;

import android.content.Context;
import android.content.res.TypedArray;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.*;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.luyy.lib.R;
import com.luyy.lib.utils.KeyboardUtils;
import com.luyy.lib.utils.LogUtils;
import com.luyy.lib.utils.ScreenUtils;

/**
 * 作者：     陆阳洋
 * 创建日期： 2019/4/16 0016 下午 4:27
 * 功能描述：验证码输入框
 */
public class ValidateCodeView extends LinearLayout {
    private int count; //验证码长度
    private int inputBg; //输入框背景
    private int inputTargetBg; //目标输入框背景
    private float space;//空隙
    private int codeTextColor;
    private float codeTextSize;
    private EditText et_input;//隐藏输入框
    private  LinearLayout container;
    private int lastView=0;//记录上一次的输入的view

    private static final int DEFAULT_COUNT=4;
    private static final float DEFAULT_SPACE=20;
    private static final int DEFAULT_INPUTBG=R.drawable.bg_code_input_default;
    private static final int DEFAULT_INPUTTARGETBG=R.drawable.bg_code_input_target_default;
    private CodeInputListener listener;
    public ValidateCodeView(Context context) {
        this(context,null);
    }

    public ValidateCodeView(Context context,  AttributeSet attrs) {
        super(context, attrs,0);

        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.ValidateCodeView);
        count=ta.getInteger(R.styleable.ValidateCodeView_count,DEFAULT_COUNT);
        inputBg=  ta.getResourceId(R.styleable.ValidateCodeView_inputBg,DEFAULT_INPUTBG);
        inputTargetBg=  ta.getResourceId(R.styleable.ValidateCodeView_inputTargetBg,DEFAULT_INPUTTARGETBG);
        space=  ta.getDimension(R.styleable.ValidateCodeView_space,DEFAULT_SPACE);
        codeTextColor=ta.getColor(R.styleable.ValidateCodeView_codeTextColor, Color.WHITE);
        codeTextSize=ta.getDimension(R.styleable.ValidateCodeView_codeTextSize,14f);
        ta.recycle();

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
                fillView();
            }
        });
    }




    /**
     * 获取到宽高后进行填充
     */
    private void fillView(){
        int width= (int) ((getWidth()-space*(count-1))/count);
        View view=  LayoutInflater.from(getContext()).inflate(R.layout.view_validate_code,this,true);
        container=view.findViewById(R.id.ll_container);
        et_input=view.findViewById(R.id.et_input);
        et_input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(count)});
        et_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int c) {
                LogUtils.d("count="+c+",char="+s+",start="+start+",before="+before);

                if(before==0){
                    //正常输入
                    lastView=start;
                    container.getChildAt(lastView).setBackgroundResource(inputBg);
                    if(start<count-1){
                        container.getChildAt(start+1).setBackgroundResource(inputTargetBg);
                    }
                }else{
                    //删除
                    lastView=start+1;
                    if(lastView<count)
                        container.getChildAt(lastView).setBackgroundResource(inputBg);
                    container.getChildAt(start).setBackgroundResource(inputTargetBg);
                }
                if(c>0){
                    TextView tv= (TextView) container.getChildAt(start);
                    tv.setText(s.charAt(s.length()-1)+"");
                }else{
                    TextView tv= (TextView) container.getChildAt(start);
                    tv.setText("");
                }

                if(c>0&&start==count-1){
                    KeyboardUtils.toggle(getContext());
                    if(listener!=null)
                        listener.onComplete(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        et_input.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    container.getChildAt(et_input.getText().length())
                            .setBackgroundResource(inputTargetBg);
                }else{
                }
            }
        });
        for(int i=0;i<count;i++){
            TextView tv=new TextView(getContext());
            tv.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(width, width);
            tv.setTextSize(codeTextSize);
            tv.setPadding(ScreenUtils.dp2px(getContext(),5),ScreenUtils.dp2px(getContext(),10),ScreenUtils.dp2px(getContext(),5),ScreenUtils.dp2px(getContext(),10));
            tv.setTextColor(codeTextColor);
            tv.setBackgroundResource(inputBg);
            if(i==0){
                lp.leftMargin=0;
            }else{
                lp.leftMargin= (int) space;
            }
            tv.setLayoutParams(lp);
            container.addView(tv);
        }
    }

    public void setListener(CodeInputListener listener) {
        this.listener = listener;
    }

    interface CodeInputListener{
        void onComplete(String data);
    }
}
