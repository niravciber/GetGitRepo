package com.git.repo.network;

import com.git.repo.model.LoginResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubApi {

    @POST("login/oauth/access_token")
    Call<LoginResponse> getAccessToken(@Query("client_id") String client_id,@Query("code") String code,@Query("client_secret") String client_secret);
}
