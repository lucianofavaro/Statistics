package com.codechallenge.utils;

public class UnixTimestampUtils {
    public static long getUnixTimeStampInSeconds(long timestampInMilliseconds) {
        return timestampInMilliseconds / 1000L;
    }
}
