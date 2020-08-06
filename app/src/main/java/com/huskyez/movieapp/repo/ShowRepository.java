package com.huskyez.movieapp.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.huskyez.movieapp.api.ApiService;
import com.huskyez.movieapp.api.ApiServiceFactory;
import com.huskyez.movieapp.model.show.AnticipatedShow;
import com.huskyez.movieapp.model.show.Episode;
import com.huskyez.movieapp.model.show.RecommendedShow;
import com.huskyez.movieapp.model.show.Season;
import com.huskyez.movieapp.model.show.Show;
import com.huskyez.movieapp.model.show.ShowDetails;
import com.huskyez.movieapp.model.show.TrendingShow;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowRepository {

    private static ShowRepository instance = null;

    private ApiService apiService;

    private List<Show> popularShows = new ArrayList<>();
    private MutableLiveData<List<Show>> popularShowsLiveData = new MutableLiveData<>();

    private List<TrendingShow> trendingShows = new ArrayList<>();
    private MutableLiveData<List<TrendingShow>> trendingShowsLiveData = new MutableLiveData<>();

    private List<AnticipatedShow> anticipatedShows = new ArrayList<>();
    private MutableLiveData<List<AnticipatedShow>> anticipatedShowsLiveData = new MutableLiveData<>();

    private List<RecommendedShow> recommendedShows = new ArrayList<>();
    private MutableLiveData<List<RecommendedShow>> recommendedShowsLiveData = new MutableLiveData<>();

    private ShowDetails showDetails;
    private MutableLiveData<ShowDetails> showDetailsLiveData = new MutableLiveData<>();


    private List<Season> seasons = new ArrayList<>();
    private MutableLiveData<List<Season>> seasonsLiveData = new MutableLiveData<>();

    private List<List<Episode>> episodes = new ArrayList<>();
    private MutableLiveData<List<List<Episode>>> episodesLiveData = new MutableLiveData<>();


    private ShowRepository() {
        apiService = ApiServiceFactory.getService();
        trendingShowsLiveData.setValue(trendingShows);
        showDetailsLiveData.setValue(showDetails);
        anticipatedShowsLiveData.setValue(anticipatedShows);
        recommendedShowsLiveData.setValue(recommendedShows);

    }

    public static ShowRepository getInstance() {
        if (instance == null) {
            instance = new ShowRepository();
            return instance;
        }

        return instance;
    }


    public void searchPopularShows() {
        Call<List<Show>> call = apiService.getPopularShows();

        call.enqueue(new Callback<List<Show>>() {
            @Override
            public void onResponse(Call<List<Show>> call, Response<List<Show>> response) {
                if (!response.isSuccessful()) {
                    //TODO: do some error handling
                }
                assert response.body() != null;
                popularShows.clear();
                popularShows.addAll(response.body());
                popularShowsLiveData.setValue(popularShows);
            }

            @Override
            public void onFailure(Call<List<Show>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public LiveData<List<Show>> getPopularShows() {
        return popularShowsLiveData;
    }

    public LiveData<List<TrendingShow>> getTrendingShows() {
        return trendingShowsLiveData;
    }

    public void searchTrendingShows() {
        Call<List<TrendingShow>> call = apiService.getTrendingShows();

        call.enqueue(new Callback<List<TrendingShow>>() {
            @Override
            public void onResponse(Call<List<TrendingShow>> call, Response<List<TrendingShow>> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.errorBody());
                }
                assert response.body() != null;
                trendingShows.clear();
                trendingShows.addAll(response.body());
                trendingShowsLiveData.setValue(trendingShows);
//                moviesList.forEach(x -> searchImage(x.getShow().getIds().getTmdb(), "movie"));
//                response.body().forEach(x -> searchImage(x.getShow().getIds().getTmdb(), "movie"));
            }

            @Override
            public void onFailure(Call<List<TrendingShow>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public void searchShowDetails(String slug_id) {
        Call<ShowDetails> call = apiService.getShowDetails(slug_id);

        call.enqueue(new Callback<ShowDetails>() {
            @Override
            public void onResponse(Call<ShowDetails> call, Response<ShowDetails> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.errorBody());
                }
                showDetails = response.body();
                showDetailsLiveData.setValue(showDetails);
            }

            @Override
            public void onFailure(Call<ShowDetails> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public LiveData<ShowDetails> getShowDetails() {
        return showDetailsLiveData;
    }

    public void searchAnticipatedShows() {
        Call<List<AnticipatedShow>> call = apiService.getAnticipatedShows();

        call.enqueue(new Callback<List<AnticipatedShow>>() {
            @Override
            public void onResponse(Call<List<AnticipatedShow>> call, Response<List<AnticipatedShow>> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.errorBody());
                }
                assert response.body() != null;
                anticipatedShows.clear();
                anticipatedShows.addAll(response.body());
                anticipatedShowsLiveData.setValue(anticipatedShows);
            }

            @Override
            public void onFailure(Call<List<AnticipatedShow>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public LiveData<List<AnticipatedShow>> getAnticipatedShows() {
        return anticipatedShowsLiveData;
    }

    public void searchRecommendedShows(String period) {

        Call<List<RecommendedShow>> call = apiService.getRecommendedShows(period);

        call.enqueue(new Callback<List<RecommendedShow>>() {
            @Override
            public void onResponse(Call<List<RecommendedShow>> call, Response<List<RecommendedShow>> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.errorBody());
                }
                assert response.body() != null;
                recommendedShows.clear();
                recommendedShows.addAll(response.body());
                recommendedShowsLiveData.setValue(recommendedShows);
            }

            @Override
            public void onFailure(Call<List<RecommendedShow>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public LiveData<List<RecommendedShow>> getRecommendedShows() {
        return recommendedShowsLiveData;
    }

    public void searchSeason(String slug_id) {
        Call<List<Season>> call = apiService.getSeasons(slug_id);

        call.enqueue(new Callback<List<Season>>() {
            @Override
            public void onResponse(Call<List<Season>> call, Response<List<Season>> response) {
                assert response.body() != null;
                episodes.clear();

                seasons.clear();
                seasons.addAll(response.body());
                seasonsLiveData.setValue(seasons);
            }

            @Override
            public void onFailure(Call<List<Season>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    // assumes seasons has values
    public void searchEpisodes(String slug_id) {

        for (Season s : seasons) {
            Call<List<Episode>> call = apiService.getEpisodes(slug_id, s.getNumber());

            call.enqueue(new Callback<List<Episode>>() {
                @Override
                public void onResponse(Call<List<Episode>> call, Response<List<Episode>> response) {
                    episodes.add(response.body());
                    episodesLiveData.setValue(episodes);
                }

                @Override
                public void onFailure(Call<List<Episode>> call, Throwable t) {
                    t.getStackTrace();
                }
            });
        }
    }


    public LiveData<List<Season>> getSeasons() {
        return seasonsLiveData;
    }

    public LiveData<List<List<Episode>>> getEpisodes() {
        return episodesLiveData;
    }

    public void searchSeasonsAndEpisodes(String slug_id) {

        Call<List<Season>> call = apiService.getSeasonsWithEpisodes(slug_id);
        call.enqueue(new Callback<List<Season>>() {
            @Override
            public void onResponse(Call<List<Season>> call, Response<List<Season>> response) {
                assert response.body() != null;
                seasons.clear();
                seasons.addAll(response.body());
                seasonsLiveData.setValue(seasons);
            }

            @Override
            public void onFailure(Call<List<Season>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }


    public LiveData<List<Season>> getSeasonsAndEpisodes() {
        return seasonsLiveData;
    }
}
