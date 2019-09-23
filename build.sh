#!/bin/bash

# Download Dependancies
mkdir -p lib
spigot='spigot-1.14.4.jar'
if test ! -f lib/$spigot; then
    echo "Downloading Spigot"
    curl "https://cdn.getbukkit.org/spigot/$spigot" --output lib/$spigot
fi

# Compile plugin
echo "Compiling plugin"
rm -rf bin
mkdir bin
javac \
    -classpath lib/$spigot \
    -source 8 \
    -target 8 \
    -d bin \
    $(find src -name "*.java")
if [ $? -eq 1 ]; then
    exit 1
fi

# Archive plugin
echo "Packaging plugin"
jar -cvf Welcome.jar \
    -C res . \
    -C bin .
