package com.nypyme.nypyme.rest.service;

import com.nypyme.nypyme.model.LoginAccount;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/*
 * Created by Acer on 22/03/2018.
 */

public interface NypymeService {

  @POST("oauth/token") Call<LoginAccount> login(@Body LoginAccount account);

}
