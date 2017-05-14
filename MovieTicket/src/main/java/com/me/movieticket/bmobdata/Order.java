package com.me.movieticket.bmobdata;

import cn.bmob.v3.BmobObject;

/**
 * Created by MANITO on 2017/4/28.
 */

public class Order extends BmobObject{
    private String order_num;
    private String movie_name;
    private String movie_time;
    private String movie_location;
    private String movie_seat;
    private String order_money;
    private String order_data;
    private Boolean isused;
    private Boolean ispayed;
    //订单号

    public String getOrder_num(){
        return order_num;
    }
    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    //电影名
    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    //电影时间
    public String getMovie_time() {
        return movie_time;
    }

    public void setMovie_time(String movie_time) {
        this.movie_time = movie_time;
    }

    //电影地址
    public String getMovie_location() {
        return movie_location;
    }

    public void setMovie_location(String movie_location) {
        this.movie_location = movie_location;
    }

    //电影座位
    public String getMovie_seat() {
        return movie_seat;
    }

    public void setmMovie_seat(String movie_seat) {
        this.movie_seat = movie_seat;
    }

    //订单价格
    public String getOrder_money() {
        return order_money;
    }

    public void setOrder_money(String order_money) {
        this.order_money = order_money;
    }
    //下单日期
    public String getOrder_data() {
        return order_data;
    }

    public void setOrder_data(String order_data) {
        this.order_data = order_data;
    }

    //是否使用
    public Boolean getIsused(){
        return isused;
    }
    public void setIsused(Boolean isused){
        this.isused=isused;
    }
    //是否支付
    public Boolean getIspayde(){
        return ispayed;
    }
    public void setIspayed(Boolean ispayed){
        this.ispayed=ispayed;
    }
}
