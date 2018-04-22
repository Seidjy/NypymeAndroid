package com.nypyme.nypyme.rest;

import com.nypyme.nypyme.rest.service.NypymeService;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Acer on 22/03/2018.
 */

public class NypymeRest {

  public static NypymeService nypymeRest(){
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://nypyme.herokuapp.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();
    return retrofit.create(NypymeService.class);
  }
}
