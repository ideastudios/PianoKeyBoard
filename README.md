# PianoKeyBoard
自定义的钢琴键盘 从A0 - C8 一共88个键 支持多点触控和多点滑动(如果与系统手势快捷方式冲突则不支持 比如miui (三指下拉截屏) 不支持三指及以上滑动) 支持键盘移动

# 效果图
<img src="/screenshots/screenshot.gif"/>

# 属性

```
               <!--设置白键被按下去时的BitmapDrawable-->
               <attr name="whiteKeyPressedDrawable" format="reference" />

               <!--白键默认的Drawable-->
               <attr name="whiteKeyDrawable" format="reference" />

               <!--设置黑键被按下去时的BitmapDrawable-->
               <attr name="blackKeyPressedDrawable" format="reference" />

               <!--黑键默认的BitmapDrawable-->
               <attr name="blackKeyDrawable" format="reference" />

               <!--设置一屏/一页 展示的白键的个数-->
               <attr name="keyCount" format="integer" />

               <!--黑键占整个键盘高度的比-->
               <attr name="blackKeyHeightRatio" format="float" />

               <!--黑键的宽度 黑键宽度和白键宽度的比-->
               <attr name="blackKeyWidthRatioToWhiteKeyWidth" format="float" />

               <!--设置白键上字体的高度占白键高度的比-->
               <attr name="pronuncTextYRatio" format="float" />

               <!--设置白键上字体的大小-->
               <attr name="pronuncTextSize" format="dimension" />

               <!--设置白键上字体的颜色-->
               <attr name="pronuncTextColor" format="color" />
```
# 声明-布局中
```java
 <tech.oom.library.keyBoard.PianoKeyBoard
        android:id="@+id/keyboard"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#ccc"
        app:blackKeyDrawable="@drawable/black_up"
        app:blackKeyPressedDrawable="@drawable/black_down"
        app:keyCount="12"
        app:pronuncTextColor="#000000"
        app:pronuncTextSize="12sp"
        app:pronuncTextYRatio="0.9"
        app:whiteKeyDrawable="@drawable/white_up"
        app:whiteKeyPressedDrawable="@drawable/white_down" />
```

# 回调
```java
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

```

# 代码配置
```java
    /**
     * 设置是否播放音效
     */
    public void setIsPlaySound(boolean isPlaySound) {

    }

    /**
     * 根据黑/白键的code 获取黑/白键 code从21-108 和midi键盘对应
     */
    public Key getKeyByKeycode(int code) {

    }

    /**
     * 移动键盘到当前位置 即键盘显示的第一个白键为当前位置 默认为0 即第一个显示的白键为A0
     */
    public void moveToPosition(int position) {
    }

    /**
     * 以当前显示位置为基准，显示键盘的下一页/屏键位
     */
    public void showNext() {

    }

    /**
     * 以当前显示位置为基准，显示键盘的上一页/屏键位
     */
    public void showPrevious() {

    }

    /**还有各种设置xml中属性的方法set×××() ……*/

```

# 键盘音效
键盘音效是以手机的音乐音量大小播放的(AudioManager.STREAM_MUSIC)，如果需要播放音效，需要在Activity 创建时调用 SoundPlayUtils.init(context);



# Gradle
[![](https://jitpack.io/v/ideastudios/PianoKeyBoard.svg)](https://jitpack.io/#ideastudios/PianoKeyBoard)


1. Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
2. Add the dependency
```
	dependencies {
	         compile 'com.github.ideastudios:PianoKeyBoard:1.1.1'
	}

```



# 说明
* 此工程中的钢琴按键音音频文件和黑白键的背景图片来自互联网