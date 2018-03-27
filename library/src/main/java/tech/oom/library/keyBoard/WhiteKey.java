package tech.oom.library.keyBoard;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.PointF;
import android.text.TextPaint;


import java.util.ArrayList;
import java.util.List;

import tech.oom.library.Const;

/**
 * 白键
 */

public class WhiteKey extends Key {

    private TextPaint textPaint;
    private Bitmap unPressedBitmap;
    private Bitmap pressedBitmap;
    private PointF pointF;
    private String textToDraw;

    public WhiteKey(float left, float top, float right, float bottom) {
        super(left, top, right, bottom);
    }

    public static List<WhiteKey> generatorWhiteKey(float whiteKeyWidth, float whiteKeyHeight, Bitmap unPressedBitmap, Bitmap pressedBitmap, TextPaint whiteTextPaint, float textYCoordinate) {
        ArrayList<WhiteKey> list = new ArrayList<>();
        Paint whitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        whitePaint.setFilterBitmap(true);
        whitePaint.setDither(true);

        for (int i = 0; i < Const.RANGE.length; i++) {
            WhiteKey whiteKey = new WhiteKey(i * whiteKeyWidth, 0, (i + 1) * whiteKeyWidth, whiteKeyHeight);
            whiteKey.setUnPressedBitmap(unPressedBitmap);
            whiteKey.setPressedBitmap(pressedBitmap);
            whiteKey.setTextPaint(whiteTextPaint);
            whiteKey.setDrawTextCoordinate(i * whiteKeyWidth + whiteKeyWidth / 2, textYCoordinate);
            whiteKey.setTextToDraw(Const.RANGE[i]);
            whiteKey.setKeyCode(Const.WHITEKEY_CODE[i]);
            list.add(whiteKey);
        }
        return list;
    }


    @Override
    protected Paint getKeyTextPaint() {
        return textPaint;
    }

    @Override
    protected Bitmap getUnPressedBitmap() {
        return unPressedBitmap;
    }

    public void setUnPressedBitmap(Bitmap unPressedBitmap) {
        this.unPressedBitmap = unPressedBitmap;
    }

    @Override
    protected Bitmap getPressedBitmap() {
        return pressedBitmap;
    }

    public void setPressedBitmap(Bitmap pressedBitmap) {
        this.pressedBitmap = pressedBitmap;
    }

    @Override
    protected String getTextToDraw() {
        return textToDraw;
    }

    public void setTextToDraw(String textToDraw) {
        this.textToDraw = textToDraw;
    }

    @Override
    protected PointF getTextPoint() {
        return pointF;
    }

    public void setDrawTextCoordinate(float x, float y) {
        this.pointF = new PointF(x, y);
    }


    public void setTextPaint(TextPaint textPaint) {
        this.textPaint = textPaint;
    }
}
