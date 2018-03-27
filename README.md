# PianoKeyBoard

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


# Gradle
[![](https://jitpack.io/v/ideastudios/LuckView.svg)](https://jitpack.io/#ideastudios/LuckView)

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
	        compile 'com.github.ideastudios:LuckView:0.0.1'
	}

```

# 注意
* LuckView属性中，只有奖品图片的偏移量 和 文字的偏移量 是相对于圆盘半径的，其他的相关属性都是相对于圆盘的直径
* LuckView中奖项的数量大小应该设置为可以能被360整除的数，如果不能被360整除，则会出现相应bug
* LuckView draw不同奖项图片 draw不同奖项名称是通过canvas.rotate(sectorAnger)的方式实现的



# 感谢
该工程参考了[Nipuream/LuckPan](https://github.com/Nipuream/LuckPan) 的相关代码和UI,感谢这位小伙伴