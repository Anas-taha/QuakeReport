package com.example.quakereport;

public class earthquake {

    private double mDegree;
    private String mCity;
    private String mUrl;
    private long mtimeInms;


    public String getmUrl() {
        return mUrl;
    }

    public earthquake(double degree, String city, long timeInms, String url) {
        mDegree = degree;
        mCity = city;
        mtimeInms = timeInms;
        mUrl     = url;

    }

    ;

    public double getmDegree() {
        return mDegree;
    }

    public String getmCity() {
        return mCity;
    }

    public long getMtimeInms() {
        return mtimeInms;
    }
}
