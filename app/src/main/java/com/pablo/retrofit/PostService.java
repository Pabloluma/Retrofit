package com.pablo.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostService {
    String API_ROUTE = "/films/-Mk8-Ge1Bs8EgrWMM4uV.json";

    @GET(API_ROUTE)
    Call<List<Post>> getPost();
}
