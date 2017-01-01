package com.android.lihou.agirl.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

/**
 * Created by Lihou.
 * <p>
 */
public class ResizeImageView extends ImageView {
    private Drawable placeHolder;

    private OnSizeListener mSizeListener;


    public ResizeImageView(Context context) {
        super(context);
    }


    public ResizeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final Drawable drawable = getDrawable();
        if (drawable != null && drawable != getPlaceHolder()) {
            final int width = MeasureSpec.getSize(widthMeasureSpec);
            final int height =
                    (int) Math.ceil((float) width
                            * (float) drawable.getIntrinsicHeight()
                            / (float) drawable.getIntrinsicWidth());
            if (mSizeListener != null) {
                mSizeListener.size(width, height);
            }
            setMeasuredDimension(width, height);
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setPlaceHolder(Drawable placeHolder){
        this.placeHolder = placeHolder;
    }

    public Drawable getPlaceHolder(){
        return this.placeHolder;
    }

    public void setOnSizeListener(OnSizeListener listener) {
        this.mSizeListener = listener;
    }


    public interface OnSizeListener {
        void size(int width, int height);
    }
}
