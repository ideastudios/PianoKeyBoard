package tech.oom.library.keyBoard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import tech.oom.library.Const;
import tech.oom.library.R;


/**
 * 自定义的钢琴键盘  88个键  支持多指滑动
 */
public class PianoKeyBoard extends View {

    private boolean isPlaySound = true;
    private ArrayList<Key> list = new ArrayList<>();
    private ArrayList<Key> reverseList = new ArrayList<>();
    private HashMap<Integer, Key> keyMap = new HashMap<>();
    private float keyBoardWidth;
    private float keyBoardHeight;
    private float whiteKeyWidth;
    private float whiteKeyHeight;
    private float blackKeyWidth;
    private float blackKeyHeight;
    private int keyCount = 12;
    private int pronuncTextColor = Color.GRAY;
    private float pronuncTextDimension = 20;
    private BitmapDrawable whiteKeyDrawable;
    private BitmapDrawable whiteKeyPressedDrawable;
    private BitmapDrawable blackKeyDrawable;
    private BitmapDrawable blackKeyPressedDrawable;
    private TextPaint pronuncTextPaint;
    private float blackKeyHeightRatio = 0.5f;
    private float pronuncTextYRatio = 0.9f;
    private int min_size = 700;
    private float blackKeyWidthRatioToWhiteKeyWidth = 0.8f;
    private KeyListener keyListener;
    private float keyBoardContentWidth;
    private float xOffset;

    public PianoKeyBoard(Context context) {
        super(context);
        init(null, 0);
    }

    public PianoKeyBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public PianoKeyBoard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }


    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.PianoKeyBoard, defStyle, 0);

        pronuncTextColor = a.getColor(
                R.styleable.PianoKeyBoard_pronuncTextColor,
                pronuncTextColor);

        pronuncTextDimension = a.getDimension(
                R.styleable.PianoKeyBoard_pronuncTextSize,
                pronuncTextDimension);

        keyCount = a.getInt(R.styleable.PianoKeyBoard_keyCount, keyCount);

        blackKeyHeightRatio = a.getFloat(R.styleable.PianoKeyBoard_blackKeyHeightRatio, blackKeyHeightRatio);
        pronuncTextYRatio = a.getFloat(R.styleable.PianoKeyBoard_pronuncTextYRatio, pronuncTextYRatio);

        blackKeyWidthRatioToWhiteKeyWidth = a.getFloat(R.styleable.PianoKeyBoard_blackKeyWidthRatioToWhiteKeyWidth, blackKeyWidthRatioToWhiteKeyWidth);

        if (a.hasValue(R.styleable.PianoKeyBoard_whiteKeyDrawable)) {
            whiteKeyDrawable = (BitmapDrawable) a.getDrawable(
                    R.styleable.PianoKeyBoard_whiteKeyDrawable);
        }
        if (a.hasValue(R.styleable.PianoKeyBoard_whiteKeyPressedDrawable)) {
            whiteKeyPressedDrawable = (BitmapDrawable) a.getDrawable(
                    R.styleable.PianoKeyBoard_whiteKeyPressedDrawable);
        }
        if (a.hasValue(R.styleable.PianoKeyBoard_blackKeyDrawable)) {
            blackKeyDrawable = (BitmapDrawable) a.getDrawable(
                    R.styleable.PianoKeyBoard_blackKeyDrawable);
        }
        if (a.hasValue(R.styleable.PianoKeyBoard_blackKeyPressedDrawable)) {
            blackKeyPressedDrawable = (BitmapDrawable) a.getDrawable(
                    R.styleable.PianoKeyBoard_blackKeyPressedDrawable);
        }

        a.recycle();
        pronuncTextPaint = new TextPaint();
        pronuncTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        pronuncTextPaint.setTextAlign(Paint.Align.CENTER);
        invalidatePaintAndMeasurements();
    }

    private void invalidatePaintAndMeasurements() {
        pronuncTextPaint.setTextSize(pronuncTextDimension);
        pronuncTextPaint.setColor(pronuncTextColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(xOffset, 0);
        if (list != null) {
            for (Key key : list) {
                key.drawKey(canvas);
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        onNewTouchEvent(event);
        return true;
    }


    private void onNewTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        int pointerIndex = motionEvent.getActionIndex();
        int pointerId = motionEvent.getPointerId(pointerIndex);
        float x = motionEvent.getX(pointerIndex);
        float y = motionEvent.getY(pointerIndex);
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:

            case MotionEvent.ACTION_POINTER_DOWN:
                onFingerDown(pointerId, x, y);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                onFingerUp(pointerId, x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                int j = motionEvent.getPointerCount();
                for (int i = 0; i < j; i++) {
                    onFingerMove(motionEvent.getPointerId(i), motionEvent.getX(i), motionEvent.getY(i));
                }
                break;

            case MotionEvent.ACTION_CANCEL:
                onAllFingersUp();
                break;


        }
    }

    private void onFingerUp(int index, float x, float y) {
        Key key = this.keyMap.get(Integer.valueOf(index));
        if (key != null) {
            fireKeyUp(key);
            this.keyMap.remove(index);
        } else {
            Key key1 = pointerInWhichKey(x, y);
            fireKeyUp(key1);
        }
    }

    private void onFingerDown(int index, float x, float y) {
        Key key = pointerInWhichKey(x, y);
        fireKeyDown(key);
        this.keyMap.put(Integer.valueOf(index), key);

    }


    private void onAllFingersUp() {
        Iterator<Key> localIterator = this.keyMap.values().iterator();
        while (localIterator.hasNext()) {
            fireKeyUp(localIterator.next());
        }
        this.keyMap.clear();
    }

    private void onFingerMove(int index, float x, float y) {
        Key key = this.keyMap.get(Integer.valueOf(index));
        Key currentKey = pointerInWhichKey(x, y);
        if (key != null) {
            if ((currentKey != null) && (currentKey != key)) {
                fireKeyDown(currentKey);
                fireKeyUp(key);
                this.keyMap.put(index, currentKey);
            }
        }
    }


    private void fireKeyUp(Key key) {
        if (keyListener != null) {
            keyListener.onKeyUp(key);
        }
        key.setPressed(false, isPlaySound);
        invalidate();
    }

    private void fireKeyDown(Key key) {
        if (keyListener != null) {
            keyListener.onKeyPressed(key);
        }
        key.setPressed(true, isPlaySound);
        invalidate();
    }

    private Key pointerInWhichKey(float x, float y) {
        Key currentKey = null;
        for (Key key : reverseList) {
            if (key.getRectF().contains(x - xOffset, y)) {
                currentKey = key;
                break;
            }
        }
        return currentKey;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        keyBoardWidth = w;
        keyBoardHeight = h;

        initKeys();
    }

    private void initKeys() {
        list.clear();
        whiteKeyHeight = keyBoardHeight;
        whiteKeyWidth = keyBoardWidth / keyCount;
        blackKeyWidth = whiteKeyWidth * blackKeyWidthRatioToWhiteKeyWidth;
        blackKeyHeight = keyBoardHeight * blackKeyHeightRatio;
        keyBoardContentWidth = whiteKeyWidth * Const.PRONUNCIATION.length;
//        xOffset = -(keyBoardContentWidth - keyBoardWidth) / 2;
//        xOffset = -(keyBoardContentWidth - keyBoardWidth) / 2;
        list.addAll(WhiteKey.generatorWhiteKey(whiteKeyWidth, keyBoardHeight, whiteKeyDrawable.getBitmap(), whiteKeyPressedDrawable.getBitmap(), pronuncTextPaint, whiteKeyHeight * pronuncTextYRatio));
        list.addAll(BlackKey.generatorBlackKey(whiteKeyWidth, blackKeyWidth, blackKeyHeight, blackKeyDrawable.getBitmap(), blackKeyPressedDrawable.getBitmap()));
//        pointerInWhichKey(xOffset,0);
        reverseList.clear();
        reverseList.addAll(list);
        Collections.reverse(reverseList);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }

    private int measure(int measureSpec) {
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            return size;
        } else {

            result = min_size;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(size, result);
            }
        }
        return result;
    }


    public void setKeyListener(KeyListener listener) {
        this.keyListener = listener;
    }

    /**
     * 设置一屏/一页 展示的白键的个数
     *
     * @param keyCount 白键个数
     */
    public void setKeyCount(int keyCount) {
        this.keyCount = keyCount;
        initKeys();
    }


    /**
     * 设置是否播放音效
     *
     * @param isPlaySound 是否播放音效
     */
    public void setIsPlaySound(boolean isPlaySound) {
        this.isPlaySound = isPlaySound;
    }


    /**
     * 设置白键上字体的颜色
     *
     * @param pronuncTextColor 颜色的值
     */
    public void setPronuncTextColor(int pronuncTextColor) {
        this.pronuncTextColor = pronuncTextColor;
        invalidatePaintAndMeasurements();
        initKeys();
    }

    /**
     * 设置白键上字体的大小
     *
     * @param pronuncTextDimension 字体的像素大小
     */
    public void setPronuncTextDimension(float pronuncTextDimension) {
        this.pronuncTextDimension = pronuncTextDimension;
        invalidatePaintAndMeasurements();
        initKeys();
    }

    /**
     * 设置黑键占整个键盘高度的比例
     *
     * @param blackKeyHeightRatio 黑键占整个键盘高度的比例 默认为0.5f
     */
    public void setBlackKeyHeightRatio(float blackKeyHeightRatio) {
        this.blackKeyHeightRatio = blackKeyHeightRatio;
        initKeys();
    }

    /**
     * 设置黑键的宽度 黑键宽度和白键宽度的比 默认为0.8f 即 黑键的宽度 = 白键宽度*0.8f
     *
     * @param blackKeyWidthRatioToWhiteKeyWidth 黑键宽度和白键宽度的比例
     */
    public void setBlackKeyWidthRatioToWhiteKeyWidth(float blackKeyWidthRatioToWhiteKeyWidth) {
        this.blackKeyWidthRatioToWhiteKeyWidth = blackKeyWidthRatioToWhiteKeyWidth;
        initKeys();
    }

    /**
     * 设置白键默认的BitmapDrawable
     *
     * @param whiteKeyDrawable
     */
    public void setWhiteKeyDrawable(BitmapDrawable whiteKeyDrawable) {
        this.whiteKeyDrawable = whiteKeyDrawable;
        initKeys();
    }

    /**
     * 设置白键被按下去时的BitmapDrawable
     *
     * @param whiteKeyPressedDrawable
     */
    public void setWhiteKeyPressedDrawable(BitmapDrawable whiteKeyPressedDrawable) {
        this.whiteKeyPressedDrawable = whiteKeyPressedDrawable;
        initKeys();
    }

    /**
     * 设置黑键默认的BitmapDrawable
     *
     * @param blackKeyDrawable
     */
    public void setBlackKeyDrawable(BitmapDrawable blackKeyDrawable) {
        this.blackKeyDrawable = blackKeyDrawable;
        initKeys();
    }

    /**
     * 设置黑键被按下去时的BitmapDrawable
     *
     * @param blackKeyPressedDrawable
     */
    public void setBlackKeyPressedDrawable(BitmapDrawable blackKeyPressedDrawable) {
        this.blackKeyPressedDrawable = blackKeyPressedDrawable;
        initKeys();
    }

    /**
     * 根据黑/白键的code 获取黑/白键
     *
     * @param code 黑/白键的code code从21-108 和midi键盘对应
     * @return 黑/白键 may be null
     */
    public Key getKeyByKeycode(int code) {
        Key key = null;
        for (Key temp : list) {
            if (temp.getKeyCode() == code) {
                key = temp;
            }
        }
        return key;
    }


    /**
     * 移动键盘到当前位置 即键盘显示的第一个白键为当前位置 默认为0 即第一个显示的白键为A0
     *
     * @param position 键盘移动的位置
     */
    public void moveToPosition(int position) {

        if (position >= Const.WHITEKEY_CODE.length - keyCount) {
            position = Const.WHITEKEY_CODE.length - keyCount;
        }
        this.xOffset = -whiteKeyWidth * position;
        updateListenerPosition();
        postInvalidate();
    }

    /**
     * 获取键盘能向右移动的最大位置
     *
     * @return 键盘能向右移动的最大位置
     */
    public int getMaxMovePosition() {
        return Const.WHITEKEY_CODE.length - keyCount;
    }

    /**
     * 以当前显示位置为基准，显示键盘的下一页键位
     */
    public void showNext() {
        if (2 * keyBoardWidth - xOffset > keyBoardContentWidth) {
            this.xOffset = keyBoardWidth - keyBoardContentWidth;
        } else {
            this.xOffset = xOffset - keyBoardWidth;
        }
        updateListenerPosition();
        postInvalidate();
    }

    /**
     * 以当前显示位置为基准，显示键盘的上一页键位
     */
    public void showPrevious() {
        if (keyBoardWidth + xOffset >= 0) {
            this.xOffset = 0;
        } else {
            this.xOffset = xOffset + keyBoardWidth;
        }
        updateListenerPosition();
        postInvalidate();
    }

    private void updateListenerPosition() {
        if (whiteKeyWidth != 0) {
            int position = (int) (-xOffset / whiteKeyWidth);
            if (keyListener != null) {
                keyListener.currentFirstKeyPosition(position);
            }
        }

    }

    /**
     * 键盘的监听
     */
    public interface KeyListener {

        /**
         * 键盘被按下的回调
         *
         * @param key 被按下的键
         */
        void onKeyPressed(Key key);

        /**
         * 键盘被按松开的回调
         *
         * @param key 被松开的键
         */
        void onKeyUp(Key key);

        /**
         * 键盘显示的第一个键的index/position
         *
         * @param position 键盘显示的第一个键的index/position
         */
        void currentFirstKeyPosition(int position);
    }


}
