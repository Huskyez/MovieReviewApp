package com.huskyez.test.model.stats;


public class UserStats {

    private MovieStats movies;
    private ShowStats shows;
    private SeasonStats seasons;
    private EpisodeStats episodes;
    private NetworkStats network;
    private RatingStats ratings;

    public UserStats() {
    }

    public UserStats(MovieStats movies, ShowStats shows, SeasonStats seasons, EpisodeStats episodes, NetworkStats network, RatingStats ratings) {
        this.movies = movies;
        this.shows = shows;
        this.seasons = seasons;
        this.episodes = episodes;
        this.network = network;
        this.ratings = ratings;
    }

    public MovieStats getMovies() {
        return movies;
    }

    public void setMovies(MovieStats movies) {
        this.movies = movies;
    }

    public ShowStats getShows() {
        return shows;
    }

    public void setShows(ShowStats shows) {
        this.shows = shows;
    }

    public SeasonStats getSeasons() {
        return seasons;
    }

    public void setSeasons(SeasonStats seasons) {
        this.seasons = seasons;
    }

    public EpisodeStats getEpisodes() {
        return episodes;
    }

    public void setEpisodes(EpisodeStats episodes) {
        this.episodes = episodes;
    }

    public NetworkStats getNetwork() {
        return network;
    }

    public void setNetwork(NetworkStats network) {
        this.network = network;
    }

    public RatingStats getRatings() {
        return ratings;
    }

    public void setRatings(RatingStats ratings) {
        this.ratings = ratings;
    }
}
