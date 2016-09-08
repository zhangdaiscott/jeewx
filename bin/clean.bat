@echo off
echo.
pause
echo.

cd %~dp0
cd..

call mvn clean

cd bin
pause