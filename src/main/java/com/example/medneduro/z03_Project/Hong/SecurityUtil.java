package com.example.medneduro.z03_Project.Hong;

public class SecurityUtil {
    public static String maskIp(String ip) {
        if (ip == null || ip.isBlank()) return "Unknown";

        // 1. IPv4 마스킹 (192.168.0.15 -> 192.168.0.***)
        // lastIndexOf 방식도 깔끔하지만, IPv4에는 split을 사용하면 각 마디의 유효성 검사까지 할 수 있음
        if (ip.contains(".")) {
            String[] parts = ip.split("\\.");
            if (parts.length == 4) {
                return parts[0] + "." + parts[1] + "." + parts[2] + ".***";
            }
        }

        // 2. IPv6 마스킹 (2001:db8::ff00:42:8329 -> 2001:db8::ff00:42:****)
        if (ip.contains(":")) {
            int lastColonIndex = ip.lastIndexOf(":");
            return ip.substring(0, lastColonIndex + 1) + "****";
        }

        return ip;
    }
}