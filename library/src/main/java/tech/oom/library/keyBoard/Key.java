package tech.oom.library.keyBoard;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.text.TextUtils;

import tech.oom.library.sound.SoundPlayUtils;


/**
 * Created by issuser on 2018/3/8 0008.
 */

public abstract class Key {

    private RectF rectF;
    private boolean isPressed;
    private Paint keyPaint;
    private int keyCode;


    public Key(float left, float top, float right, float bottom) {

        rectF = new RectF(left, top, right, bottom);
        keyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        keyPaint.setFilterBitmap(true);
        keyPaint.setDither(true);
    }

    public final void setPressed(boolean isPressed, boolean isPlaySound) {
        this.isPressed = isPressed;
        if (isPlaySound && isPressed) {
            SoundPlayUtils.play(keyCode - 20);
        } else {
            SoundPlayUtils.stop(keyCode - 20);
        }

    }

    public final void drawKey(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        Bitmap originalBitmap = null;
        if (isPressed) {
            originalBitmap = getPressedBitmap();
        } else {
            originalBitmap = getUnPressedBitmap();
        }
        Matrix matrix = new Matrix();
        Bitmap bitmap = Bitmap.createBitmap(originalBitmap, 0, 0, originalBitmap.getWidth(), originalBitmap.getHeight(), matrix, true);
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, null, rectF, keyPaint);
        }
        String textToDraw = getTextToDraw();
        PointF textPoint = getTextPoint();
        if (!TextUtils.isEmpty(textToDraw) && getKeyTextPaint() != null && textPoint != null) {
            canvas.drawText(textToDraw, textPoint.x, textPoint.y, getKeyTextPaint());
        }
    }

    protected abstract Paint getKeyTextPaint();

    protected abstract Bitmap getUnPressedBitmap();

    protected abstract Bitmap getPressedBitmap();

    protected abstract String getTextToDraw();

    protected abstract PointF getTextPoint();


    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public RectF getRectF() {
        return rectF;
    }

}
