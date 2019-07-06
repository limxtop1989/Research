package com.limxtop.research.animator.base;

/**
 * Created by limxtop on 4/23/16.
 */
public class ViewEntry {

    private String mText;
    private Class mClass;

    public ViewEntry(String text, Class clazz) {
        mText = text;
        mClass = clazz;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

    public Class getmClass() {
        return mClass;
    }

    public void setmClass(Class mClass) {
        this.mClass = mClass;
    }
}
