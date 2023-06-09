package com.android.util;

import java.nio.charset.StandardCharsets;

public class StringFormat {
    public static String trans(String s) {
        return new String(s.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }
}
