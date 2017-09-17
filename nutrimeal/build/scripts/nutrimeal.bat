@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  nutrimeal startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and NUTRIMEAL_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\nutrimeal-0.1.jar;%APP_HOME%\lib\jetty-server-9.4.1.v20170120.jar;%APP_HOME%\lib\jetty-webapp-9.4.1.v20170120.jar;%APP_HOME%\lib\jetty-plus-9.4.1.v20170120.jar;%APP_HOME%\lib\jetty-jaas-9.4.1.v20170120.jar;%APP_HOME%\lib\jetty-servlet-9.4.1.v20170120.jar;%APP_HOME%\lib\jetty-annotations-9.4.1.v20170120.jar;%APP_HOME%\lib\jersey-container-servlet-2.26-b01.jar;%APP_HOME%\lib\derby-10.13.1.1.jar;%APP_HOME%\lib\derbyclient-10.13.1.1.jar;%APP_HOME%\lib\eclipselink-2.6.1.jar;%APP_HOME%\lib\mail-1.4.1.jar;%APP_HOME%\lib\commons-fileupload-1.3.2.jar;%APP_HOME%\lib\poi-ooxml-3.16-beta1.jar;%APP_HOME%\lib\itextpdf-5.5.6.jar;%APP_HOME%\lib\jackson-core-2.8.6.jar;%APP_HOME%\lib\javax.servlet-api-3.1.0.jar;%APP_HOME%\lib\javax.websocket-api-1.0.jar;%APP_HOME%\lib\angularjs-1.6.1.jar;%APP_HOME%\lib\bootstrap-4.0.0-alpha.6.jar;%APP_HOME%\lib\jersey-media-json-jackson-2.26-b02.jar;%APP_HOME%\lib\jetty-http-9.4.1.v20170120.jar;%APP_HOME%\lib\jetty-io-9.4.1.v20170120.jar;%APP_HOME%\lib\jetty-xml-9.4.1.v20170120.jar;%APP_HOME%\lib\jetty-jndi-9.4.1.v20170120.jar;%APP_HOME%\lib\jetty-security-9.4.1.v20170120.jar;%APP_HOME%\lib\javax.annotation-api-1.2.jar;%APP_HOME%\lib\asm-5.1.jar;%APP_HOME%\lib\asm-commons-5.1.jar;%APP_HOME%\lib\jersey-container-servlet-core-2.26-b01.jar;%APP_HOME%\lib\jersey-server-2.26-b01.jar;%APP_HOME%\lib\javax.persistence-2.1.0.jar;%APP_HOME%\lib\commonj.sdo-2.1.1.jar;%APP_HOME%\lib\validation-api-1.1.0.Final.jar;%APP_HOME%\lib\javax.json-1.0.4.jar;%APP_HOME%\lib\activation-1.1.1.jar;%APP_HOME%\lib\commons-io-2.2.jar;%APP_HOME%\lib\poi-3.16-beta1.jar;%APP_HOME%\lib\poi-ooxml-schemas-3.16-beta1.jar;%APP_HOME%\lib\curvesapi-1.04.jar;%APP_HOME%\lib\jquery-1.11.1.jar;%APP_HOME%\lib\jersey-entity-filtering-2.26-b02.jar;%APP_HOME%\lib\jackson-jaxrs-base-2.8.4.jar;%APP_HOME%\lib\jackson-jaxrs-json-provider-2.8.4.jar;%APP_HOME%\lib\jackson-annotations-2.8.4.jar;%APP_HOME%\lib\jetty-util-9.4.1.v20170120.jar;%APP_HOME%\lib\asm-tree-5.1.jar;%APP_HOME%\lib\jersey-client-2.26-b01.jar;%APP_HOME%\lib\jersey-media-jaxb-2.26-b01.jar;%APP_HOME%\lib\commons-codec-1.10.jar;%APP_HOME%\lib\commons-collections4-4.1.jar;%APP_HOME%\lib\xmlbeans-2.6.0.jar;%APP_HOME%\lib\jackson-databind-2.8.4.jar;%APP_HOME%\lib\jackson-module-jaxb-annotations-2.8.4.jar;%APP_HOME%\lib\osgi-resource-locator-1.0.1.jar;%APP_HOME%\lib\javassist-3.20.0-GA.jar;%APP_HOME%\lib\stax-api-1.0.1.jar;%APP_HOME%\lib\javax.inject-1.jar;%APP_HOME%\lib\jersey-common-2.26-b02.jar;%APP_HOME%\lib\jersey-guava-2.26-b02.jar;%APP_HOME%\lib\javax.ws.rs-api-2.1-m03.jar;%APP_HOME%\lib\hk2-api-2.5.0-b32.jar;%APP_HOME%\lib\javax.inject-2.5.0-b32.jar;%APP_HOME%\lib\hk2-locator-2.5.0-b32.jar;%APP_HOME%\lib\hk2-utils-2.5.0-b32.jar;%APP_HOME%\lib\aopalliance-repackaged-2.5.0-b32.jar

@rem Execute nutrimeal
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %NUTRIMEAL_OPTS%  -classpath "%CLASSPATH%" pt.ipb.nutrimeal.App %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable NUTRIMEAL_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%NUTRIMEAL_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
