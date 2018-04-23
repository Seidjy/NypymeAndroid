package com.nypyme.nypyme.view.home.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.nypyme.nypyme.R;
import com.nypyme.nypyme.model.Participant;
import com.nypyme.nypyme.rest.NypymeRest;
import com.nypyme.nypyme.util.Constants;
import com.nypyme.nypyme.util.MaskEditUtil;
import com.nypyme.nypyme.util.MessageHelper;
import com.nypyme.nypyme.util.PreferencesHelper;
import com.nypyme.nypyme.view.login.LoginActivity;
import com.nypyme.nypyme.view.result.ResultActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Created by Acer on 15/04/2018.
 */

public class MakeTransactionFragment extends Fragment {
  private PreferencesHelper preferencesHelper;
  private EditText cpf;
  private EditText amount;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_transacao, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    preferencesHelper = new PreferencesHelper(getContext());

    cpf = view.findViewById(R.id.transacao_cpf);
    amount = view.findViewById(R.id.transacao_valor);

    cpf.addTextChangedListener(MaskEditUtil.mask(cpf,MaskEditUtil.FORMAT_CPF));

    Button confirmButton = view.findViewById(R.id.transacao_confirm);
    confirmButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        if (verificaConexao()) {
          boolean canTryLogin = true;
          if (cpf.getText().toString().length() < 14) {
            cpf.setError("Digite o CPF");
            canTryLogin = false;
          }
          if (amount.getText().toString().length() == 0) {
            amount.setError("Digite o valor!");
            canTryLogin = false;
          }
          if (canTryLogin) {
            sendDeal();
          }
        } else {
          MessageHelper.showToast(getContext(), Constants.MESSAGE_NO_CONNECTION);
        }
      }
    });
  }


  private void sendDeal() {
    NypymeRest.nypymeRest()
        .transaction("Bearer " + preferencesHelper.getToken(), MaskEditUtil.unmask(cpf.getText().toString()),
            amount.getText().toString())
        .enqueue(new Callback<Participant>() {
          @Override public void onResponse(@NonNull Call<Participant> call,
              @NonNull Response<Participant> response) {
            Log.i("resposta", response.message());
            if (response.errorBody() == null) {

              Intent intent = new Intent(getContext(), ResultActivity.class);
              intent.putExtra(Constants.RESULT_CPF, cpf.getText().toString());
              intent.putExtra(Constants.RESULT_POINTS_NOW, response.body().getPontos());
              intent.putExtra(Constants.RESULT_POINTS_BEFORE, response.body().getPontosRecebidos());
              startActivity(intent);
            }
          }

          @Override public void onFailure(@NonNull Call<Participant> call, @NonNull Throwable t) {
            MessageHelper.showToast(getContext(), Constants.MESSAGE_NO_CONNECTION);
          }
        });
  }
  public boolean verificaConexao() {
    boolean conectado;
    ConnectivityManager conectivtyManager =
        (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
    if (conectivtyManager.getActiveNetworkInfo() != null && conectivtyManager.getActiveNetworkInfo()
        .isAvailable() && conectivtyManager.getActiveNetworkInfo().isConnected()) {
      conectado = true;
    } else {
      conectado = false;
    }
    return conectado;
  }


}
