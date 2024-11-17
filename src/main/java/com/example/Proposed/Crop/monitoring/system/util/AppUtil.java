package com.example.Proposed.Crop.monitoring.system.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String fieldImageToBase64(byte[] bytesFieldImage01) {
        return Base64.getEncoder().encodeToString(bytesFieldImage01);
    }

    public static String cropImageToBase64(byte[] bytesCropImage01) {
        return Base64.getEncoder().encodeToString(bytesCropImage01);
    }
    public static String generateVehicleId(){
        return "VEHICLE-" + UUID.randomUUID();
    }

    public static String observedImageOneToBase64(byte[] byteObservedImage) {
        return Base64.getEncoder().encodeToString(byteObservedImage);
    }

    public static String generateLogId() {
        return "Log Code-" + UUID.randomUUID();
    }
}
