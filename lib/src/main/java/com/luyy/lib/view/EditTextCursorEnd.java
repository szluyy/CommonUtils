package com.luyy.lib.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * 作者：     陆阳洋
 * 创建日期： 2019/4/17 0017 上午 9:53
 * 功能描述： EditText 光标始终停留在最后
 */
public class EditTextCursorEnd extends androidx.appcompat.widget.AppCompatEditText {
    public EditTextCursorEnd(Context context) {
        this(context,null);
    }

    public EditTextCursorEnd(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        setSelection(getText().length());
    }
}
