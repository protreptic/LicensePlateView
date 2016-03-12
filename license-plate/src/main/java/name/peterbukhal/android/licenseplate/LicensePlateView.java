package name.peterbukhal.android.licenseplate;

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

    public enum PlateType {
        STANDART, YELLOW
    }

    private PlateType plateType;

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
        if (attrs != null)
            try {

            } finally {

            }
    }

    private void init() {
        typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/RoadNumbers2.0.ttf");

        plateType = PlateType.STANDART;
    }

    private static final Paint paint = new Paint();
    private static final RectF rectF = new RectF();

    private float mWidth;
    private float mHeight;

    private Typeface typeface;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), (int) (MeasureSpec.getSize(widthMeasureSpec) * X));
    }

    private static final float X = 0.235f;

    @Override
    protected void onDraw(Canvas canvas) {
        mWidth = getWidth();
        mHeight = (int) (mWidth * X);//0.2153f;

        drawFrame(canvas);
        drawHoles(canvas);
        drawText(canvas);

        if (plateType == PlateType.STANDART)
            drawFlag(canvas);
    }

    private void drawFrame(Canvas canvas) {
        float cornerRadius, outerBoundsPadding, innerBoundsPadding, innerBoundsStrokeWidth;

        cornerRadius = mHeight * 0.107142f;
        innerBoundsStrokeWidth = mHeight * 0.03f;

        // outer rect
        outerBoundsPadding = mHeight * 0.01f;
        rectF.set(outerBoundsPadding, outerBoundsPadding, (mWidth - outerBoundsPadding), (mHeight - outerBoundsPadding));

        paint.reset();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor((plateType == PlateType.STANDART) ? Color.WHITE : Color.YELLOW);
        paint.setStrokeWidth(0f);
        paint.setAntiAlias(true);
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint);

        // inner rect
        innerBoundsPadding = mHeight * 0.04f;
        rectF.set(innerBoundsPadding, innerBoundsPadding, (int) (mWidth - innerBoundsPadding), (int) (mHeight - innerBoundsPadding));

        paint.reset();
        paint.setStrokeWidth(innerBoundsStrokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint);

        paint.reset();
        paint.setStrokeWidth(innerBoundsStrokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);

        rectF.set(mWidth - (mWidth * 0.3f), innerBoundsPadding, (int) (mWidth - innerBoundsPadding), (int) (mHeight - innerBoundsPadding));
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint);

        rectF.set(innerBoundsPadding, innerBoundsPadding, (int) (mWidth - (mWidth * 0.3f)), (int) (mHeight - innerBoundsPadding));
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mWidth - (mWidth * 0.3f), (innerBoundsPadding * 1.75f), innerBoundsPadding, paint);
        canvas.drawCircle(mWidth - (mWidth * 0.3f), (mHeight - (innerBoundsPadding * 1.75f)), innerBoundsPadding, paint);
    }

    private void drawHoles(Canvas canvas) {
        float radius, padding;

        radius = mHeight * 0.0625f / 2;
        padding = mWidth * 0.038461f;

        paint.reset();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        canvas.drawCircle(padding, (mHeight / 2.0f), radius, paint);
        canvas.drawCircle((mWidth - padding), (mHeight / 2.0f), radius, paint);
    }

    private void drawText(Canvas canvas) {
        //
        paint.reset();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setTextSize(mHeight * 0.22f);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("RUS", (mWidth - (mWidth * 0.265f)), (mHeight - (mHeight * 0.13f)), paint);

        //
        paint.reset();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setTextSize(mHeight * 1f);
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(typeface);
        canvas.drawText("a460ca", (mHeight * 0.32f), (mHeight - (mHeight * 0.19f)), paint);

        //
        paint.reset();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setTextSize(mHeight * 0.75f);
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(typeface);
        canvas.drawText("134", (mWidth - (mWidth * 0.28f)), (mHeight - (mHeight * 0.365f)), paint);
    }

    private void drawFlag(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);

        float x1, y1, x2, y2;

        x1 = (mWidth - (mWidth * 0.165f));
        y1 = (mHeight - (mHeight * 0.3f));
        x2 = (mWidth - (mWidth * 0.1f));
        y2 = (mHeight - (mHeight * 0.12f));

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
