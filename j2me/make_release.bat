set PHONE=clib

set DEFINES=-DDEBUG
set DEFINES=%DEFINES% -D%PHONE%

call config.bat
call make_code.bat

pause