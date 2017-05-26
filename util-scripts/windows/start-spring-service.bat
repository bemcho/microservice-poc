@echo off

REM Repackage the service
cd %dp0/../../..
call gradlew :%1%:bootRepackage
cd %1%\build\libs

REM set the name of the JAR file to be run
For /R %%# in (*.jar) Do (
	set JAR_NAME=%%~nx#
)

echo We chose to execute: %JAR_NAME%

java -jar -Xmx256m %JAR_NAME%