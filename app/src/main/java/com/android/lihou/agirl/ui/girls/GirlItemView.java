package com.android.lihou.agirl.ui.girls;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import com.android.lihou.agirl.ui.recyclerview.viewmodel.GirlModel;
import com.android.lihou.agirl.utils.DimensionUtil;
import com.android.lihou.agirl.utils.ScrimUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.lang.ref.WeakReference;

/**
 * Created by Lihou.
 * see http://stackoverflow.com/a/27719126
 */
public class GirlItemView extends ImageView {
    private static final String EMPTY_STRING = "";
    public static final int DEFAULT_SIZE = 0;
    public static final int LABEL_SIZE_SP = 21;/*sp*/
    public static final int LABEL_PADDING_DP = 16;/*dp*/
    private static final String TYPEFACE = "sans-serif-black";
    private static final int TYPEFACE_STYLE = Typeface.NORMAL;
    private static final int DEFAULT_PLACE_HOLDER_COLOR = 0;

    private int imageHeight = DEFAULT_SIZE;
    private int imageWidth = DEFAULT_SIZE;
    private String label = EMPTY_STRING;
    private Paint labelPaint;
    private float labelTextPadding;
    private int labelAreaHeight;

    private Drawable labelBackground;
    private WeakReference<ColorDrawable> placeholder;

    private GirlModel girlModel;


    public GirlItemView(Context context) {
        this(context,null);
    }


    public GirlItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }


    public GirlItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        labelPaint = new TextPaint(
                Paint.ANTI_ALIAS_FLAG | Paint.SUBPIXEL_TEXT_FLAG);
        labelPaint.setTypeface(Typeface.create(TYPEFACE, TYPEFACE_STYLE));
        labelPaint.setColor(Color.WHITE);
        final float labelTextSize = DimensionUtil.sp2px(LABEL_SIZE_SP);
        labelPaint.setTextSize(labelTextSize);

        final Paint.FontMetrics fontMetrics = labelPaint.getFontMetrics();
        labelTextPadding = DimensionUtil.dp2px(LABEL_PADDING_DP);
        labelAreaHeight = (int) (fontMetrics.bottom - fontMetrics.ascent +
                labelTextPadding + labelTextPadding);
        labelPaint.setXfermode(
                new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        labelBackground =
                ScrimUtil.makeCubicGradientScrimDrawable(0x4f000000, 2, Gravity.BOTTOM);
    }


    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh) {
            labelBackground.setBounds(
                    getPaddingLeft(),
                    h - labelAreaHeight,
                    w - getPaddingRight(),
                    h - getPaddingBottom()
            );
        }
    }


    private boolean isShowImage() {
        return getDrawable() != null && getDrawable() != getPlaceHolder();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (imageHeight != DEFAULT_SIZE && imageWidth != DEFAULT_SIZE) {
            setMeasuredDimension(imageWidth, imageHeight);
            return;
        }
        final Drawable drawable = getDrawable();
        if (drawable != null && drawable != getPlaceHolder()) {
            final int width = MeasureSpec.getSize(widthMeasureSpec);
            final int height = (int) Math.ceil((float) width
                    * (float) drawable.getIntrinsicHeight()
                    / (float) drawable.getIntrinsicWidth());
            girlModel.width = width;
            girlModel.height = height;
            setMeasuredDimension(width, height);
            return;
        }
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }


    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isShowImage()) {
            canvas.save();
            canvas.clipRect(getPaddingLeft(),
                    getPaddingTop(),
                    getWidth() - getPaddingRight(),
                    getHeight() - getPaddingBottom());
            labelBackground.draw(canvas);
            canvas.drawText(label,
                    getPaddingLeft() + labelTextPadding,
                    getHeight() - labelTextPadding - getPaddingBottom(),
                    labelPaint);
            canvas.restore();
        }
    }


    @Override public boolean isOpaque() {
        return true;
    }


    private ColorDrawable getPlaceHolder() {
        ColorDrawable drawable = placeholder != null ? placeholder.get() : null;
        if (drawable == null) {
            drawable = new ColorDrawable(DEFAULT_PLACE_HOLDER_COLOR);
            this.placeholder = new WeakReference<>(drawable);
        }
        return drawable;
    }


    public void setText(String text) {
        this.label = TextUtils.isEmpty(text) ? EMPTY_STRING : text;
    }


    public void reset() {
        this.imageHeight = DEFAULT_SIZE;
        this.imageWidth = DEFAULT_SIZE;
        this.label = EMPTY_STRING;
    }


    public void resize(int width, int height) {
        this.imageHeight = height;
        this.imageWidth = width;
    }


    public void setPlaceholderColor(int color) {
        getPlaceHolder().setColor(color);
    }


    public void bind(final GirlModel model) {
        this.girlModel = model;
        this.resize(girlModel.width, girlModel.height);
        this.setText(girlModel.label);
        this.setPlaceholderColor(girlModel.placeHolderColor);
        Glide.with(((Activity) this.getContext()))
             .load(girlModel.url)
             .crossFade()
             .placeholder(this.getPlaceHolder())
             .diskCacheStrategy(DiskCacheStrategy.SOURCE)
             .fitCenter()
             .into(this);
    }
}
