package com.example.test.model.show;

import com.example.test.model.Ids;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShowDetails {

    private String title;
    private Integer year;
    private Ids ids;
    private String overview;
    @SerializedName("first_aired")
    private String firstAired;
    private Airs airs;
    private Integer runtime;
    private String certification;
    private String network;
    private String country;
    private String updated_at;
    private String trailer;
    private String homepage;
    private String status;
    private Double rating;
    private Integer votes;
    private Integer comment_count;
    private String langauge;
    private List<String> availabe_translations;
    private List<String> genres;
    private Integer aired_episodes;

    public ShowDetails() {
    }

    public ShowDetails(String title, Integer year, Ids ids, String overview, String firstAired, Airs airs, Integer runtime, String certification, String network, String country, String updated_at, String trailer, String homepage, String status, Double rating, Integer votes, Integer comment_count, String langauge, List<String> availabe_translations, List<String> genres, Integer aired_episodes) {
        this.title = title;
        this.year = year;
        this.ids = ids;
        this.overview = overview;
        this.firstAired = firstAired;
        this.airs = airs;
        this.runtime = runtime;
        this.certification = certification;
        this.network = network;
        this.country = country;
        this.updated_at = updated_at;
        this.trailer = trailer;
        this.homepage = homepage;
        this.status = status;
        this.rating = rating;
        this.votes = votes;
        this.comment_count = comment_count;
        this.langauge = langauge;
        this.availabe_translations = availabe_translations;
        this.genres = genres;
        this.aired_episodes = aired_episodes;
    }

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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirstAired() {
        return firstAired;
    }

    public void setFirstAired(String firstAired) {
        this.firstAired = firstAired;
    }

    public Airs getAirs() {
        return airs;
    }

    public void setAirs(Airs airs) {
        this.airs = airs;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Integer getComment_count() {
        return comment_count;
    }

    public void setComment_count(Integer comment_count) {
        this.comment_count = comment_count;
    }

    public String getLangauge() {
        return langauge;
    }

    public void setLangauge(String langauge) {
        this.langauge = langauge;
    }

    public List<String> getAvailabe_translations() {
        return availabe_translations;
    }

    public void setAvailabe_translations(List<String> availabe_translations) {
        this.availabe_translations = availabe_translations;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Integer getAired_episodes() {
        return aired_episodes;
    }

    public void setAired_episodes(Integer aired_episodes) {
        this.aired_episodes = aired_episodes;
    }
}
