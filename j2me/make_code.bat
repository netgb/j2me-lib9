@echo off

rd %process_Dir% /s /q
if not exist %process_Dir% mkdir %process_Dir%
rd %Jar_Dir% /s /q
if not exist %Jar_Dir% mkdir %Jar_Dir%


@echo Preprocessing...
copy src\lib9\*.java %process_Dir%\
copy src\res %process_Dir%\
cd %process_Dir%
for %%i in (*.java) do (
		%TOOLS_DIR%\cpp\cpp.exe -C -P %DEFINES% %%i %%i
)
rem cd %CURRENT_DIR%
dir *.java /s /b > sources.txt

rem %JDK_DIR%\bin\javac -target 1.1 -O -g:none -classpath "%J2MEAPI%" -bootclasspath "%J2MEAPI%" -d %Compiled_Dir% @sources.txt
%JDK_DIR%\bin\javac -target 1.1 -O -g:none -bootclasspath "%J2MEAPI%" -d . @sources.txt
rem del sources.txt

echo jar
%JDK_DIR%\bin\jar -cvf %WORK_DIR%\%process_Dir%\_%PROJ_NAME%.jar lib9 res

echo Obfuscating...

if "%Obfusated%" == "1" (
%JDK_DIR%\bin\java -jar %TOOLS_DIR%\proguard.jar @%WORK_DIR%\proguard.pro
) else (
copy /y _%PROJ_NAME%.jar %PROJ_NAME%.jar
)

del _%PROJ_NAME%.jar

echo Preverifying...
if "%Preverify%" == "1" (
%WTK_DIR%\bin\preverify1.1.exe -classpath "%J2MEAPI%" -d . %PROJ_NAME%.jar
) 

if "%Use_Kzip%" == "1" (
	mkdir tmp
	copy %PROJ_NAME%.jar tmp\
	cd tmp
	%JDK_DIR%\bin\jar -xvf %PROJ_NAME%.jar
	del %PROJ_NAME%.jar
	%TOOLS_DIR%\kzip.exe /r /y %PROJ_NAME%.jar *.*
	copy %PROJ_NAME%.jar ..\
)
copy %WORK_DIR%\%process_Dir%\%PROJ_NAME%.jar %WORK_DIR%\%Jar_Dir%\
cd %WORK_DIR%\%Jar_Dir%

:MakeJad
echo Make Jad...

	echo MIDlet-1: %GAME_NAME%, /i.png, %MAIN_CLASS%>  %PROJ_NAME%.jad
	echo MIDlet-Name: %GAME_NAME%>>                             %PROJ_NAME%.jad
	echo MIDlet-Vendor: %VENDOR%>>                            %PROJ_NAME%.jad
	echo MIDlet-Version: %VERSION_STR%>>                          %PROJ_NAME%.jad
	echo MIDlet-Icon: /i.png>>                                %PROJ_NAME%.jad
	echo MIDlet-Description: this is a sms app!>>                  %PROJ_NAME%.jad
	echo MIDlet-Data-Size: 4096>>                 %PROJ_NAME%.jad
	echo MIDlet-Jar-Size: >>                                  %PROJ_NAME%.jad
	echo MIDlet-Jar-URL: %PROJ_NAME%.jar>>                 %PROJ_NAME%.jad
	echo MicroEdition-Configuration: CLDC-1.0>> %PROJ_NAME%.jad
	echo MicroEdition-Profile: MIDP-2.0>>              %PROJ_NAME%.jad
	if "%Nokia_Phone%" == "1" (
		echo Nokia-MIDlet-Category: Game>> 						%PROJ_NAME%.jad
	)
rem 这个属性造成游戏不能安装	
rem	if "%Orientation%" == "1" (
rem		echo Nokia-MIDlet-App-Orientation：portrait>> 						%PROJ_NAME%.jad
rem	)
	
	rem echo MIDlet-Permissions: javax.microedition.pim.ContactList.read,javax.microedition.pim.ContactList.write>> %PROJ_NAME%.jad
	
	
	copy /y %TOOLS_DIR%\update.class %WORK_DIR%\%Jar_Dir%\ 
	%JDK_DIR%\bin\java.exe update s %PROJ_NAME%.jad %PROJ_NAME%.jar MIDlet-Jar-Size

	del update.class
	
	
	TYPE %PROJ_NAME%.jad

cd %WORK_DIR%

del /q %Compiled_Dir%\*.*
rd %Compiled_Dir%




:exit
