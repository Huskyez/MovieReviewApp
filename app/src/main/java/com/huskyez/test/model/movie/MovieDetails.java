package com.huskyez.test.model.movie;

import com.huskyez.test.model.common.Ids;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetails {

    private String title;
    private Integer year;
    private Ids ids;

    private String tagline;
    private String overview;
    private String released;
    private Integer runtime;
    private String country;
    private String trailer;
    private String homepage;
    private String status;
    private Double rating;
    private Integer votes;
    @SerializedName("comment_count")
    private Integer comments;
    private String updated_at;
    private String language;
    private List<String> available_translations;
    private List<String> genres;
//    private String certification;

    public MovieDetails() {
    }

    public MovieDetails(String title, Integer year, Ids ids, String tagline, String overview, String released, Integer runtime, String country, String trailer, String homepage, String status, Double rating, Integer votes, Integer comments, String updated_at, String language, List<String> available_translations, List<String> genres, String certification) {
        this.title = title;
        this.year = year;
        this.ids = ids;
        this.tagline = tagline;
        this.overview = overview;
        this.released = released;
        this.runtime = runtime;
        this.country = country;
        this.trailer = trailer;
        this.homepage = homepage;
        this.status = status;
        this.rating = rating;
        this.votes = votes;
        this.comments = comments;
        this.updated_at = updated_at;
        this.language = language;
        this.available_translations = available_translations;
        this.genres = genres;
//        this.certification = certification;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public List<String> getAvailable_translations() {
        return available_translations;
    }

    public void setAvailable_translations(List<String> available_translations) {
        this.available_translations = available_translations;
    }

//    public String getCertification() {
//        return certification;
//    }
//
//    public void setCertification(String certification) {
//        this.certification = certification;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Ids getIds() {
        return ids;
    }

    public void setIds(Ids ids) {
        this.ids = ids;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
