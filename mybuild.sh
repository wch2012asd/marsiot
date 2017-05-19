#!/bin/sh

find src -name \*.java >javaFiles.list
javac -d bin -cp .:./libs/*  @./javaFiles.list
cd ./bin
jar -cvf marsiot-pi-sdk.jar ./com

