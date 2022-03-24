package com.qminder.borger.utils;

import java.io.Closeable;
import java.io.IOException;

public class Utils {

    public static <T extends Closeable> void close(T is) {
        if (is == null) {
            return;
        }

        try {
            is.close();
        } catch (IOException ignore) {}
    }
}
