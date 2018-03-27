package tech.oom.library.keyBoard;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.PointF;


import java.util.ArrayList;
import java.util.List;

import tech.oom.library.Const;

/**
 * 黑键
 */

public class BlackKey extends Key {

    private Bitmap unPressedBitmap;
    private Bitmap pressedBitmap;

    public BlackKey(float left, float top, float right, float bottom) {

        super(left, top, right, bottom);
    }

    public static List<BlackKey> generatorBlackKey(float whiteKeyWidth, float blackKeyWidth, float blackKeyHeight, Bitmap unPressedBitmap, Bitmap pressedBitmap) {
        ArrayList<BlackKey> list = new ArrayList<>();
        for (int i = 0; i < Const.GAPS.length; i++) {
            int gap = Const.GAPS[i];
            BlackKey blackKey = new BlackKey((gap + 1) * whiteKeyWidth - blackKeyWidth / 2, 0, (gap + 1) * whiteKeyWidth + blackKeyWidth / 2, blackKeyHeight);
            blackKey.setUnPressedBitmap(unPressedBitmap);
            blackKey.setPressedBitmap(pressedBitmap);
            blackKey.setKeyCode(Const.BLACKKEY_CODE[i]);
            list.add(blackKey);
        }

        return list;
    }


    @Override
    protected Bitmap getUnPressedBitmap() {
        return unPressedBitmap;
    }

    public void setUnPressedBitmap(Bitmap bitmap) {
        this.unPressedBitmap = bitmap;
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
        return null;
    }

    @Override
    protected PointF getTextPoint() {
        return null;
    }

    @Override
    protected Paint getKeyTextPaint() {
        return null;
    }


}
