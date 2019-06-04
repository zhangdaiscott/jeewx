echo 'Start to build project'
@echo off

cd jeecg-p3-biz-alipay-single
call mvn install package -Dmaven.test.skip=true

cd ..\jeecg-p3-biz-qywx
call mvn install package -Dmaven.test.skip=true

cd ..\jeewx
set MAVEN_OPTS=%MAVEN_OPTS% -Xms1024M -Xmx1024M -XX:PermSize=256M -XX:MaxPermSize=256M
call mvn tomcat7:run

@echo on
echo 'Build project successfully!'

pause