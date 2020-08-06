package com.huskyez.movieapp.model.sync;

public class PostResponseData {

    public Result added;
    public Result existing;

    public static class Result {
        public Integer movies;
        public Integer shows;
        public Integer seasons;
        public Integer episodes;
    }


}
