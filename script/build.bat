@echo off
setlocal

:: Navigate to the project root directory
cd /d "%~dp0.."

echo ======================================
echo     Starting Android Build Process    
echo ======================================

echo [1/6] Cleaning project...
call gradlew clean

echo [2/6] Running tests...
call gradlew test

echo [3/6] Building project...
call gradlew build

echo [4/6] Assembling Debug APK...
call gradlew assembleDebug

echo [5/6] Assembling Release APK...
call gradlew assemble

echo [6/6] Building Release App Bundle (AAB)...
call gradlew app:bundleRelease

echo ======================================
echo      Build completed successfully!    
echo ======================================
pause
