package com.nypyme.nypyme.model;

import com.squareup.moshi.Json;

/**
 * Created by Acer on 23/03/2018.
 */

public class LoginAccount {
  @Json(name = "client_id") private int clientId;
  @Json(name = "client_secret") private  String clientSecret;
  @Json(name = "grant_type") private String grantType;
  @Json(name = "username") private String userName;
  @Json(name = "password") private String password;
  @Json(name = "scope") private String scope;
  @Json(name = "access_token") private String token;

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public String getGrantType() {
    return grantType;
  }

  public void setGrantType(String grantType) {
    this.grantType = grantType;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
