#!/bin/bash

# --- Очистка ---
echo "Cleaning up..."
rm -rf build
rm -rf docs

# --- Компиляция ---
echo "Compiling..."
mkdir -p build
javac -d build -encoding UTF-8 $(find src/main/java -name "*.java")

echo "Generating Javadoc..."
mkdir -p docs
javadoc -d docs -sourcepath src/main/java -subpackages org.example -encoding UTF-8

echo "Creating JAR..."
jar -cvfe build/heapsort.jar org.example.HeapSort -C build .

# --- Запуск ---
echo "Running application..."
java -jar build/heapsort.jar