package tech.oom.library.sound;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import tech.oom.library.R;


/**
 *
 */
public class SoundPlayUtils {
    // SoundPool对象
    public static SoundPool mSoundPlayer = new SoundPool(10,
            AudioManager.STREAM_MUSIC, 5);
    public static SoundPlayUtils soundPlayUtils;
    // 上下文
    static Context mContext;

    /**
     * 初始化
     *
     * @param context
     */
    public static SoundPlayUtils init(Context context) {
        if (soundPlayUtils == null) {
            soundPlayUtils = new SoundPlayUtils();
        }

        // 初始化声音
        mContext = context;
        mSoundPlayer.load(mContext, R.raw.p01, 1);
        mSoundPlayer.load(mContext, R.raw.p02, 1);
        mSoundPlayer.load(mContext, R.raw.p03, 1);
        mSoundPlayer.load(mContext, R.raw.p04, 1);
        mSoundPlayer.load(mContext, R.raw.p05, 1);
        mSoundPlayer.load(mContext, R.raw.p06, 1);
        mSoundPlayer.load(mContext, R.raw.p07, 1);
        mSoundPlayer.load(mContext, R.raw.p08, 1);
        mSoundPlayer.load(mContext, R.raw.p09, 1);
        mSoundPlayer.load(mContext, R.raw.p10, 1);
        mSoundPlayer.load(mContext, R.raw.p11, 1);
        mSoundPlayer.load(mContext, R.raw.p12, 1);
        mSoundPlayer.load(mContext, R.raw.p13, 1);
        mSoundPlayer.load(mContext, R.raw.p14, 1);
        mSoundPlayer.load(mContext, R.raw.p15, 1);
        mSoundPlayer.load(mContext, R.raw.p16, 1);
        mSoundPlayer.load(mContext, R.raw.p17, 1);
        mSoundPlayer.load(mContext, R.raw.p18, 1);
        mSoundPlayer.load(mContext, R.raw.p19, 1);
        mSoundPlayer.load(mContext, R.raw.p20, 1);
        mSoundPlayer.load(mContext, R.raw.p21, 1);
        mSoundPlayer.load(mContext, R.raw.p22, 1);
        mSoundPlayer.load(mContext, R.raw.p23, 1);
        mSoundPlayer.load(mContext, R.raw.p24, 1);
        mSoundPlayer.load(mContext, R.raw.p25, 1);
        mSoundPlayer.load(mContext, R.raw.p26, 1);
        mSoundPlayer.load(mContext, R.raw.p27, 1);
        mSoundPlayer.load(mContext, R.raw.p28, 1);
        mSoundPlayer.load(mContext, R.raw.p29, 1);
        mSoundPlayer.load(mContext, R.raw.p30, 1);
        mSoundPlayer.load(mContext, R.raw.p31, 1);
        mSoundPlayer.load(mContext, R.raw.p32, 1);
        mSoundPlayer.load(mContext, R.raw.p33, 1);
        mSoundPlayer.load(mContext, R.raw.p34, 1);
        mSoundPlayer.load(mContext, R.raw.p35, 1);
        mSoundPlayer.load(mContext, R.raw.p36, 1);
        mSoundPlayer.load(mContext, R.raw.p37, 1);
        mSoundPlayer.load(mContext, R.raw.p38, 1);
        mSoundPlayer.load(mContext, R.raw.p39, 1);
        mSoundPlayer.load(mContext, R.raw.p40, 1);
        mSoundPlayer.load(mContext, R.raw.p41, 1);
        mSoundPlayer.load(mContext, R.raw.p42, 1);
        mSoundPlayer.load(mContext, R.raw.p43, 1);
        mSoundPlayer.load(mContext, R.raw.p44, 1);
        mSoundPlayer.load(mContext, R.raw.p45, 1);
        mSoundPlayer.load(mContext, R.raw.p46, 1);
        mSoundPlayer.load(mContext, R.raw.p47, 1);
        mSoundPlayer.load(mContext, R.raw.p48, 1);
        mSoundPlayer.load(mContext, R.raw.p49, 1);
        mSoundPlayer.load(mContext, R.raw.p50, 1);
        mSoundPlayer.load(mContext, R.raw.p51, 1);
        mSoundPlayer.load(mContext, R.raw.p52, 1);
        mSoundPlayer.load(mContext, R.raw.p53, 1);
        mSoundPlayer.load(mContext, R.raw.p54, 1);
        mSoundPlayer.load(mContext, R.raw.p55, 1);
        mSoundPlayer.load(mContext, R.raw.p56, 1);
        mSoundPlayer.load(mContext, R.raw.p57, 1);
        mSoundPlayer.load(mContext, R.raw.p58, 1);
        mSoundPlayer.load(mContext, R.raw.p59, 1);
        mSoundPlayer.load(mContext, R.raw.p60, 1);
        mSoundPlayer.load(mContext, R.raw.p61, 1);
        mSoundPlayer.load(mContext, R.raw.p62, 1);
        mSoundPlayer.load(mContext, R.raw.p63, 1);
        mSoundPlayer.load(mContext, R.raw.p64, 1);
        mSoundPlayer.load(mContext, R.raw.p65, 1);
        mSoundPlayer.load(mContext, R.raw.p66, 1);
        mSoundPlayer.load(mContext, R.raw.p67, 1);
        mSoundPlayer.load(mContext, R.raw.p68, 1);
        mSoundPlayer.load(mContext, R.raw.p69, 1);
        mSoundPlayer.load(mContext, R.raw.p70, 1);
        mSoundPlayer.load(mContext, R.raw.p71, 1);
        mSoundPlayer.load(mContext, R.raw.p72, 1);
        mSoundPlayer.load(mContext, R.raw.p73, 1);
        mSoundPlayer.load(mContext, R.raw.p74, 1);
        mSoundPlayer.load(mContext, R.raw.p75, 1);
        mSoundPlayer.load(mContext, R.raw.p76, 1);
        mSoundPlayer.load(mContext, R.raw.p77, 1);
        mSoundPlayer.load(mContext, R.raw.p78, 1);
        mSoundPlayer.load(mContext, R.raw.p79, 1);
        mSoundPlayer.load(mContext, R.raw.p80, 1);
        mSoundPlayer.load(mContext, R.raw.p81, 1);
        mSoundPlayer.load(mContext, R.raw.p82, 1);
        mSoundPlayer.load(mContext, R.raw.p83, 1);
        mSoundPlayer.load(mContext, R.raw.p84, 1);
        mSoundPlayer.load(mContext, R.raw.p85, 1);
        mSoundPlayer.load(mContext, R.raw.p86, 1);
        mSoundPlayer.load(mContext, R.raw.p87, 1);
        mSoundPlayer.load(mContext, R.raw.p88, 1);

        return soundPlayUtils;
    }

    /**
     * 播放声音
     *
     * @param soundID
     */
    public static void play(int soundID) {
        mSoundPlayer.play(soundID, 1, 1, 0, 0, 1);
    }

    public static void stop(int soundId) {
        mSoundPlayer.stop(soundId);
    }

}