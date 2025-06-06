package com.example.deliverify.utils;

// com.example.deliverify.util.DistanceUtil.java

import com.example.deliverify.entity.Location;

public class DistanceUtil {
    public static double calculateDistance(Location loc1, Location loc2) {
        final int EARTH_RADIUS = 6371;

        double latDistance = Math.toRadians(loc2.getLatitude() - loc1.getLatitude());
        double lonDistance = Math.toRadians(loc2.getLongitude() - loc1.getLongitude());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(loc1.getLatitude())) * Math.cos(Math.toRadians(loc2.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }
}
