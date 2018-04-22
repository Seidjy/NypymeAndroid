package com.nypyme.nypyme.rest.service;

import com.nypyme.nypyme.model.LoginAccount;
import com.nypyme.nypyme.model.Participant;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

/*
 * Created by Acer on 22/03/2018.
 */

public interface NypymeService {

  @POST("oauth/token") Call<LoginAccount> login(@Body LoginAccount account);
  //@Header()
  @FormUrlEncoded
  @POST("api/deals")
  Call<Participant> transaction(@Header("Authorization") String token,
      @Field("cpf") String cpf,
      @Field("amount") String amount);

}
