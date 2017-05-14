package com.me.movieticket.entity;

/**
 * Created by MANITO on 2017/4/2.
 */

public class CinemaInfo {
    private String cinema_name;
    private String cinema_grade;
    private String cinema_add;
    public CinemaInfo(String cinema_name,String cinema_grade,String cinema_add){
        this.cinema_name=cinema_name;
        this.cinema_grade=cinema_grade;
        this.cinema_add=cinema_add;
    }
    public String getCinema_name(){
        return cinema_name;
    }
    public String getCinema_grade(){
        return cinema_grade;
    }
    public String getCinema_add(){
        return cinema_add;
    }
}
