#!/bin/bash

# Navigate to the project root directory
cd "$(dirname "$0")/.."

echo "======================================"
echo "    Starting Android Build Process    "
echo "======================================"

# Make gradlew executable
chmod +x ./gradlew

echo "[1/6] Cleaning project..."
./gradlew clean

echo "[2/6] Running tests..."
./gradlew test

echo "[3/6] Building project..."
./gradlew build

echo "[4/6] Assembling Debug APK..."
./gradlew assembleDebug

echo "[5/6] Assembling Release APK..."
./gradlew assemble

echo "[6/6] Building Release App Bundle (AAB)..."
./gradlew app:bundleRelease

echo "======================================"
echo "     Build completed successfully!    "
echo "======================================"
