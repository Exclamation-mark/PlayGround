package com.company.String;

import java.util.Arrays;

public class MyStringBuffer implements  IStringBuffer{
    private char[] value;
    private static int capacity = 19;
    private int currentCapacity;
    private int length = 0;
    private int currintPosition = 0;
    public MyStringBuffer(){
        this.value = new char[this.currentCapacity];
    }
    public MyStringBuffer(String str) {
        int length = str.length();
        this.currentCapacity =getNewCurrentCapacity(length);
        this.value = new char[this.currentCapacity];
        this.length+=length;
        this.currintPosition = length - 1;
        char[]  temporaryChatArray = str.toCharArray();
        for (int i = 0; i < length; i++) {
            value[i] = temporaryChatArray[i];
        }
    }

    @Override
    public void append(String str) {
        int strLength = str.length();
        extendArrayLength(strLength);
        char[] chars = str.toCharArray();
        for (int i = 0; i < strLength; i++) {
            value[currintPosition++] = chars[i];
        }
        length+=strLength;
    }

    @Override
    public void append(char c) {
        extendArrayLength(1);
        value[currintPosition++] = c;
        length++;
    }

    @Override
    public void insert(int pos, char b) {
        if (pos > length)
            return;
        extendArrayLength(1);
        char[] chars = new char[length-pos+1];
        for (int i = 0; i < length-pos+1 ; i++) {
            chars[i] = value[i+pos-1];
        }
        value[pos-1] = b;
        for (int i = 0; i < chars.length; i++) {
            value[pos+i] = chars[i];
        }
        length++;
        currintPosition++;
    }

    @Override
    public void insert(int pos, String b) {
        if (pos > length)
            return;
        int strLength = b.length();
        extendArrayLength(strLength);
        char[] chars = new char[length-pos+1+strLength];
        char[] chars1 = b.toCharArray();
        for (int i = 0; i < length-pos+1+strLength ; i++) {
            if (i < strLength){
                chars[i] = chars1[i];
            }else {
                chars[i] = value[i+pos-strLength-1];
            }
        }
        for (int i = 0; i < length-pos+1+strLength; i++) {
            value[pos+i-1] = chars[i];
        }
        length+=strLength;
        currintPosition+=strLength;
    }

    @Override
    public void delete(int start) {
//        for (int i = start; i < length; i++) {
//            value[i] = ' '
//        }
    }

    @Override
    public void delete(int start, int end) {

    }

    @Override
    public void reverse() {

    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public String toString() {
        return "MyStringBuffer{" +
                "value=" + Arrays.toString(value) +
                ", currentCapacity=" + currentCapacity +
                ", length=" + length +
                ", currintPosition=" + currintPosition +
                '}';
    }
    private void extendArrayLength(int increasedLength){
        int resultArrayLength = getNewCurrentCapacity(increasedLength);
        if (resultArrayLength != currentCapacity){
            char[] chars = value;
            value = new char[resultArrayLength];
            this.currentCapacity = resultArrayLength;
            for (int i = 0; i < this.currintPosition; i++) {
                value[i] = chars[i];
            }
        }
    }
    private int getNewCurrentCapacity(int num){
        return num + length >= currentCapacity ? (((num + length)/capacity)+1)* capacity : currentCapacity;
    }
}
