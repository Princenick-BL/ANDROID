package com.example.tp1;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieServices {

    public static final String ENDPOINT = "https://api.themoviedb.org/";
    static String api_key = "3c66aaf4a3332dc5308306eb61003e72";

    @GET("/3/movie/popular?api_key="+api_key)
    Call<Movies> getPopularMovies(@Query("language") String language,@Query("page") int page);

    @GET("/3/movie/upcoming?api_key="+api_key)
    Call<Movie>  getUpcomingMovies(@Query("language") String language,@Query("page") int page);

    @GET("/3/search/movie?api_key="+api_key+"&include_adult=false")
    Call<Movie> searchMovie(@Query("language") String language,@Query("query") String query,@Query("page") int page);


}
