#!/bin/bash
ant clean
ant build
cd bin
echo
echo
echo
java Sorter
cd ..
echo
echo
