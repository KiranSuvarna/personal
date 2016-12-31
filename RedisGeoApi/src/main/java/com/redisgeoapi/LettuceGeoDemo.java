package com.redisgeoapi;

import java.lang.reflect.Array;

import java.util.*;
import com.lambdaworks.redis.*;

public class LettuceGeoDemo {

    public static void main(String[] args) {

        RedisClient redisClient = new RedisClient("localhost",10001);
        RedisConnection<String, String> redis = redisClient.connect();
        String key = "offer_name";

        redis.geoadd(key, 12.8986218, 77.57089739999992, "J.P Nagar", 12.9259401, 77.58304190000001, "Jayanagar 4th Block", 12.9175999, 77.57286750000003, "Banashankari TTMC");
        
        Set<String> georadius = redis.georadius(key, 12.8986218, 77.57089739999992, 0.5, GeoArgs.Unit.km);
        System.out.println("Geo Radius: " + georadius);

         //georadius contains "Weinheim" and "Train station"

        Double distance = redis.geodist(key, "J.P Nagar", "Jayanagar 4th Block", GeoArgs.Unit.km);
        System.out.println("Distance: " + distance + " km");

        GeoArgs geoArgs = new GeoArgs().withHash().withCoordinates().withDistance().withCount(2).asc();

        List<GeoWithin<String>> georadiusWithArgs = redis.georadius(key, 12.9259401, 77.58304190000001, 5, GeoArgs.Unit.km, geoArgs);
//        // georadiusWithArgs contains "Weinheim" and "Train station"
//        // ordered descending by distance and containing distance/coordinates
        GeoWithin<String> jpNagar = georadiusWithArgs.get(0);
        System.out.println("Member: " + jpNagar.member);
        System.out.println("Geo hash: " + jpNagar.geohash);
        System.out.println("Distance: " + jpNagar.distance);
        System.out.println("Coordinates: " + jpNagar.coordinates.x + "/" + jpNagar.coordinates.y);
//
        List<GeoCoordinates> geopos = redis.geopos(key, "J.P Nagar", "Banashankari TTMC");
        GeoCoordinates jpNagarGeopos = geopos.get(0);
        System.out.println("Coordinates: " + jpNagarGeopos.x + "/" + jpNagarGeopos.y);

        redis.close();
        redisClient.shutdown();
    }
}
