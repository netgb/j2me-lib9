set WORK_DIR=%CD%
set TOOLS_DIR=%WORK_DIR%\tools
set JDK_DIR=C:\j2sdk1.4.2_04
set WTK_DIR=D:\software\WTK2.5.2

rem build process
set process_Dir=_process
set Jar_Dir=_build

set J2MEAPI=%WTK_DIR%\lib\cldcapi11.jar;%WTK_DIR%\lib\midpapi20.jar;%WTK_DIR%\lib\wma20.jar;%WTK_DIR%\lib\jsr75.jar;%WTK_DIR%\lib\mmapi.jar

rem set J2MEAPI=%TOOLS_DIR%\hmidp80.zip

set PATH=%TOOLS_DIR%;%PATH%

set PROJ_NAME=lib9

set MAIN_CLASS=CSmsAPI
set GAME_NAME=9Lib

set VENDOR=WareLoft


set Obfusated=1

set Preverify=1

set Use_Kzip=1





