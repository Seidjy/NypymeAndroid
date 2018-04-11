package com.nypyme.nypyme.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.nypyme.nypyme.R;
import com.nypyme.nypyme.model.LoginAccount;
import com.nypyme.nypyme.rest.NypymeRest;
import com.nypyme.nypyme.util.Constants;
import com.nypyme.nypyme.util.MessageHelper;
import com.nypyme.nypyme.util.PreferencesHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

  private EditText user;
  private EditText password;

  /*@Override protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);

  user = findViewById(R.id.user);
  password = findViewById(R.id.password);
  Button login = findViewById(R.id.login);
  login.setOnClickListener(new View.OnClickListener() {
  @Override public void onClick(View view) {
  tryToLogin(prepareAccount());
  }
  });
  }
   */

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    user = (EditText) findViewById(R.id.user);

    password = (EditText) findViewById(R.id.password);

    Button login = findViewById(R.id.login);
    login.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        if (verificaConexao()) {
          boolean canTryLogin = true;
          if (password.getText().toString().length() == 0) {
            password.setError("Digite a Senha");
            canTryLogin = false;
          }
          if (user.getText().toString().length() == 0) {
            user.setError("Digite Nome de Usuário!");
            canTryLogin = false;
          }
          if (canTryLogin) {
            tryToLogin(prepareAccount());
          }
        } else {
          MessageHelper.showToast(LoginActivity.this, "Verifique sua conexão.");
        }
      }
    });
  }

  public boolean verificaConexao() {
    boolean conectado;
    ConnectivityManager conectivtyManager =
        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    if (conectivtyManager.getActiveNetworkInfo() != null && conectivtyManager.getActiveNetworkInfo()
        .isAvailable() && conectivtyManager.getActiveNetworkInfo().isConnected()) {
      conectado = true;
    } else {
      conectado = false;
    }
    return conectado;
  }

  private LoginAccount prepareAccount() {
    LoginAccount loginAccount = new LoginAccount();
    loginAccount.setClientId(Constants.CLIENT_ID);
    loginAccount.setClientSecret(Constants.CLIENT_SECRET);
    loginAccount.setGrantType(Constants.GRANT_TYPE);
    loginAccount.setUserName(user.getText().toString());
    loginAccount.setPassword(password.getText().toString());
    loginAccount.setScope(Constants.SCOPE);
    return loginAccount;
  }

  private void tryToLogin(LoginAccount account) {
    Call<LoginAccount> call = NypymeRest.nypymeRest().login(account);
    call.enqueue(new Callback<LoginAccount>() {
      @Override public void onResponse(@NonNull Call<LoginAccount> call,
          @NonNull Response<LoginAccount> response) {
        if (response.errorBody() == null) {
          PreferencesHelper preferencesHelper = new PreferencesHelper(LoginActivity.this);
          preferencesHelper.saveToken(response.body().getToken());
          MessageHelper.showToast(LoginActivity.this, "Logado.");
        }else{
          MessageHelper.showToast(LoginActivity.this, "Dados incorretos.");
        }

      }

      @Override public void onFailure(@NonNull Call<LoginAccount> call, @NonNull Throwable t) {
        MessageHelper.showToast(LoginActivity.this, "Verifique sua conexão com a Internet.");
      }
    });
  }
}