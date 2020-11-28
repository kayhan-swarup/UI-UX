package com.kayhan.real_ui.models;

public class MyTime {


    public static long millis(int n){
        return (long) n*1;
    }
    public static long seconds(int n){
        return (long) n*millis(1000);
    }

    public static long minutes(int n){
        return (long) n*seconds(60);
    }
    public static long hours(int n){
        return (long) n*minutes(60);
    }
    public static long days(int n){
        return (long) n*hours(24);
    }

    public static long months(int n){
        return (long) n*days(30);
    }
    public static long year(int n){
        return (long) n*days(365);
    }



}
