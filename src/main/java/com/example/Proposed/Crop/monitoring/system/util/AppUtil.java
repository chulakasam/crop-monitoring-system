package com.example.Proposed.Crop.monitoring.system.util;

import java.util.Base64;

public class AppUtil {
    public static String fieldImageToBase64(byte[] bytesFieldImage01) {
        return Base64.getEncoder().encodeToString(bytesFieldImage01);
    }

    public static String cropImageToBase64(byte[] bytesCropImage01) {
        return Base64.getEncoder().encodeToString(bytesCropImage01);
    }
}
