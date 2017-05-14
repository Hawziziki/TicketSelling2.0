package com.me.movieticket.entity;

/**
 * Created by MANITO on 2017/4/7.
 */

public class DiscoverItem {
    private String review_user_name;
    private String review_movie_name;
    private String review_score;
    private String review;
    public DiscoverItem(String review_user_name,String review_movie_name,String review_score,String review){
        this.review_user_name=review_user_name;
        this.review_movie_name=review_movie_name;
        this.review_score=review_score;
        this.review=review;
    }
    public String getReviewUserName(){
        return review_user_name;
    }
    public String getReviewMovieName(){
        return review_movie_name;
    }
    public String getReviewScore(){
        return review_score;
    }
    public String getReview(){
        return review;
    }
}
