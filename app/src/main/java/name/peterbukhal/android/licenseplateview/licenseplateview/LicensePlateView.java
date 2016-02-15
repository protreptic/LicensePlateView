package name.peterbukhal.android.licenseplateview.licenseplateview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created on 15/02/16 17:50 by
 *
 * @author Peter Bukhal (petr@taxik.ru)
 */
public class LicensePlateView extends View {

    public LicensePlateView(Context context) {
        super(context);

        init();
    }

    public LicensePlateView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initAttrs(attrs, 0);
        init();
    }

    public LicensePlateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(attrs, defStyleAttr);
        init();
    }

    private void initAttrs(AttributeSet attrs, int defStyleAttr) {

    }

    private void init() {
        typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/RoadNumbers2.0.ttf");

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1.0f);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
    }

    private static final float RELATION = 0.2153f;

    private Paint paint = new Paint();
    private RectF outerBounds = new RectF();
    private RectF innerBounds = new RectF();
    private RectF innerBounds2 = new RectF();

    private float mWidth;
    private float mHeight;

    private Typeface typeface;

    @Override
    protected void onDraw(Canvas canvas) {
        float w, h, textSize1, textSize2, textSize3;

        w = getWidth();
        h = w * RELATION;

        mWidth = w;
        mHeight = h;

        drawFrame(canvas);

        // holes
        drawHoles(canvas);

        // text
        textSize1 = 75.0f;
        textSize2 = mHeight * 0.9f;
        //textSize3 = 35.0f * scale;

        paint.setTextSize(textSize2);
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(typeface);
        canvas.drawText("м976мм", (mHeight * 0.247142f), (mHeight - (mHeight * 0.19f)), paint);

        //
        //paint.setTextSize(textSize1);
        //paint.setStyle(Paint.Style.FILL);

        //canvas.drawText("34", ((400.0f + 10.0f)), (textSize1 + innerBoundsPadding + innerBoundsStrokeWidth + 28.0f), paint);

        //
        //paint.setTextSize(textSize3);
        //paint.setStyle(Paint.Style.FILL);

        //canvas.drawText("RUS", ((400.0f + 20.0f) * scale), (h - innerBoundsPadding - innerBoundsStrokeWidth - (1.0f * scale)), paint);

        // draw flag
        //drawFlag(canvas);
    }

    private void drawFrame(Canvas canvas) {
        float cornerRadius, outerBoundsPadding, innerBoundsPadding, innerBoundsStrokeWidth;

        cornerRadius = mHeight * 0.107142f;
        innerBoundsStrokeWidth = mHeight * 0.034f;

        // outer rect
        outerBoundsPadding = 0.0f;
        outerBounds.set(outerBoundsPadding, outerBoundsPadding, (mWidth - outerBoundsPadding), (mHeight - outerBoundsPadding));

        paint.setStrokeWidth(0.0f);
        canvas.drawRoundRect(outerBounds, cornerRadius, cornerRadius, paint);

        // inner rect
        innerBoundsPadding = mHeight * 0.05642f ;
        innerBounds.set(innerBoundsPadding, innerBoundsPadding, (mWidth - innerBoundsPadding), (mHeight - innerBoundsPadding));

        paint.setStrokeWidth(innerBoundsStrokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(innerBounds, cornerRadius, cornerRadius, paint);

        //
        innerBounds2.set(mWidth - (mWidth * 0.25f), innerBoundsPadding, (mWidth - innerBoundsPadding), (mHeight - innerBoundsPadding));

        paint.setStrokeWidth(innerBoundsStrokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(innerBounds2, cornerRadius, cornerRadius, paint);

        innerBounds2.set(innerBoundsPadding, innerBoundsPadding, mWidth - (mWidth * 0.25f), (mHeight - innerBoundsPadding));
        canvas.drawRoundRect(innerBounds2, cornerRadius, cornerRadius, paint);
    }

    private void drawHoles(Canvas canvas) {
        float radius, padding;

        radius = mHeight * 0.0625f / 2;
        padding = mWidth * 0.038461f;

        paint.setStrokeWidth(1.0f);
        canvas.drawCircle(padding, (mHeight / 2.0f), radius, paint);
        canvas.drawCircle((mWidth - padding), (mHeight / 2.0f), radius, paint);
    }

    private void drawFlag(Canvas canvas) {

    }

}
