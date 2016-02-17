package name.peterbukhal.android.licenseplateview.licenseplateview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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

    private Paint paint = new Paint();
    private RectF outerBounds = new RectF();
    private RectF innerBounds = new RectF();
    private RectF innerBounds2 = new RectF();

    private float mWidth;
    private float mHeight;

    private Typeface typeface;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), (int) (MeasureSpec.getSize(widthMeasureSpec) * 0.2153f));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float textSize1, textSize2, textSize3;

        mWidth = getWidth();
        mHeight = mWidth * 0.2153f;

        drawFrame(canvas);
        drawHoles(canvas);

        // text
        textSize1 = mHeight * 0.75f;
        textSize2 = mHeight * 1f;
        textSize3 = mHeight * 0.22f;

        paint.setTextSize(textSize3);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("RUS", (mWidth - (mWidth * 0.265f)), (mHeight - (mHeight * 0.13f)), paint);

        paint.setTextSize(textSize2);
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(typeface);
        canvas.drawText("x666xx", (mHeight * 0.4f), (mHeight - (mHeight * 0.19f)), paint);

        //
        paint.setTextSize(textSize1);
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(typeface);
        canvas.drawText("666", (mWidth - (mWidth * 0.28f)), (mHeight - (mHeight * 0.365f)), paint);

        drawFlag(canvas);
    }

    private void drawFrame(Canvas canvas) {
        float cornerRadius, outerBoundsPadding, innerBoundsPadding, innerBoundsStrokeWidth;

        cornerRadius = mHeight * 0.107142f;
        innerBoundsStrokeWidth = mHeight * 0.034f;

        // outer rect
        outerBoundsPadding = 0.0f;
        outerBounds.set(outerBoundsPadding, outerBoundsPadding, (mWidth - outerBoundsPadding), (mHeight - outerBoundsPadding));

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(0);
        canvas.drawRoundRect(outerBounds, cornerRadius, cornerRadius, paint);

        // inner rect
        innerBoundsPadding = mHeight * 0.04f ;
        innerBounds.set(innerBoundsPadding, innerBoundsPadding, (mWidth - innerBoundsPadding), (mHeight - innerBoundsPadding));

        paint.setStrokeWidth(innerBoundsStrokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawRoundRect(innerBounds, cornerRadius, cornerRadius, paint);

        //
        innerBounds2.set(mWidth - (mWidth * 0.3f), innerBoundsPadding, (mWidth - innerBoundsPadding), (mHeight - innerBoundsPadding));

        paint.setStrokeWidth(innerBoundsStrokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(innerBounds2, cornerRadius, cornerRadius, paint);

        innerBounds2.set(innerBoundsPadding, innerBoundsPadding, mWidth - (mWidth * 0.3f), (mHeight - innerBoundsPadding));
        canvas.drawRoundRect(innerBounds2, cornerRadius, cornerRadius, paint);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mWidth - (mWidth * 0.3f), (innerBoundsPadding * 1.75f), innerBoundsPadding, paint);
        canvas.drawCircle(mWidth - (mWidth * 0.3f), (mHeight - (innerBoundsPadding * 1.75f)), innerBoundsPadding, paint);
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
        paint.setStyle(Paint.Style.STROKE);

        float x1, y1, x2, y2;

        x1 = (mWidth - (mWidth * 0.165f));
        y1 = (mHeight - (mHeight * 0.3f));
        x2 = (mWidth - (mWidth * 0.1f));
        y2 = (mHeight - (mHeight * 0.12f));

        RectF rectF = new RectF();
        rectF.set(x1, y1, x2, y2);

        float radius = mHeight * 0.02f;

        canvas.drawRoundRect(rectF, radius, radius, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        rectF.set(x1, y1, x2, y2);
        canvas.drawRoundRect(rectF, radius, radius, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        rectF.set(x1, y1, x2, y2 - (mHeight * 0.055f));
        canvas.drawRoundRect(rectF, radius, radius, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        rectF.set(x1, y1, x2, y2 - (mHeight * 0.115f));
        canvas.drawRect(rectF, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(mHeight * 0.0085f);
        rectF.set(x1, y1, x2, y2);
        canvas.drawRoundRect(rectF, radius, radius, paint);
    }

}
