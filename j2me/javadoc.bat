call config.bat
set lib=%WORK_DIR%\src\lib9\j2me

%JDK_DIR%\bin\javadoc -d lib9J2meDoc -public  -classpath "%J2MEAPI%"  %lib%\L9Util.java %lib%\L9Sprite.java %lib%\L9InputStream.java %lib%\L9OutputStream.java %lib%\L9ResStr.java %lib%\L9Http.java %lib%\L9Animation.java %lib%\L9Str.java %lib%\Lib9.java %lib%\L9Rect.java %lib%\L9Config.java %lib%\L9Map.java %lib%\L9Store.java %lib%\L9Sound.java  %lib%\L9DateTime.java %lib%\L9IState.java %lib%\L9DialogMsg.java %lib%\L9DialogYesNo.java %lib%\L9DialogBackground.java
rem %JDK_DIR%\bin\javac  -classpath "%J2MEAPI%"  E:\work\app_sms\src\TUtil.java E:\work\app_sms\src\CSprite.java E:\work\app_sms\src\CInputStream.java E:\work\app_sms\src\COutputStream.java 
pause