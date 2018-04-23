package com.nypyme.nypyme.view.result;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.nypyme.nypyme.R;
import com.nypyme.nypyme.util.Constants;

/*
 * Created by Acer on 16/04/2018.
 */

public class ResultActivity extends AppCompatActivity {

  private TextView cpf;
  private TextView pointsNow;
  private TextView pointsReceived;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_result);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle("Pontuação do participante");

    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });

    pointsNow = findViewById(R.id.result_points);
    pointsReceived = findViewById(R.id.result_received_points);
    cpf = findViewById(R.id.result_cpf);

    String cpfString = getIntent().getExtras().getString(Constants.RESULT_CPF);
    String amount = getIntent().getExtras().getString(Constants.RESULT_POINTS_NOW);
    String pointsBefore = getIntent().getExtras().getString(Constants.RESULT_POINTS_BEFORE);

    Log.i("amount", amount);
    Log.i("pointsBefore", pointsBefore);
    int intAmount = Integer.valueOf(amount);
    int intPointsBefore = Integer.valueOf(pointsBefore);
    int pointsReceivedInTransaction = intAmount - intPointsBefore;

    cpf.setText(cpfString);
    pointsNow.setText(amount + " pts");
    pointsReceived.setText(Integer.toString(pointsReceivedInTransaction) + " pts");
  }
}
