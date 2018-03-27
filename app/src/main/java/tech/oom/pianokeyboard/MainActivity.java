package tech.oom.pianokeyboard;

import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import tech.oom.library.keyBoard.Key;
import tech.oom.library.keyBoard.PianoKeyBoard;
import tech.oom.library.sound.SoundPlayUtils;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private PianoKeyBoard keyBoard;
    /**
     * 按键的listener
     */
    PianoKeyBoard.KeyListener listener = new PianoKeyBoard.KeyListener() {
        @Override
        public void onKeyPressed(Key key) {
//某个键被按下的回调
        }

        @Override
        public void onKeyUp(Key key) {
//某个键被松开的回调
        }

        @Override
        public void currentFirstKeyPosition(int position) {
//            键盘显示的第一个键的index/position更新回调
            seekBar.setMax(keyBoard.getMaxMovePosition());
            seekBar.setProgress(position);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SoundPlayUtils.init(this);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        seekBar = (SeekBar) findViewById(R.id.activity_play_seek_bar);
        keyBoard = (PianoKeyBoard) findViewById(R.id.keyboard);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                keyBoard.moveToPosition(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBar.setMax(keyBoard.getMaxMovePosition());

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//        keyBoard.setKeyCount(8);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyBoard.showPrevious();
                keyBoard.setPronuncTextDimension(12 * getResources().getDisplayMetrics().scaledDensity);
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyBoard.showNext();
            }
        });

        keyBoard.setKeyListener(listener);

    }
}
