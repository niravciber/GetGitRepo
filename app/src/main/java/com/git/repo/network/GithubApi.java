package com.git.repo.network;

import com.git.repo.model.LoginResponse;
import com.git.repo.model.Repo;
import com.git.repo.model.RepoResponse;

import java.util.List;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubApi {

    @Headers({"Accept: application/json"})
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    Call<LoginResponse> getAccessToken(@Field("client_id") String client_id, @Field("code") String code, @Field("client_secret") String client_secret);

    @GET("user/repos")
    Single<List<Repo>> getDefaultRepos(@Header("Authorization") String header);

    @GET("search/repositories")
    Single<RepoResponse> searchRepos(@Header("Authorization") String header, @Query("q") String query);

}
