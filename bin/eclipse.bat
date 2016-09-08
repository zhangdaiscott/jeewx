@echo off
echo.
pause
echo.

cd /d %~dp0
cd..

call mvn -Declipse.workspace=%cd% eclipse:clean eclipse:eclipse

cd bin
pause