package com.app.equaker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Calendar;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FormActivity extends AppCompatActivity implements OnClickListener {

    //UI References
    private EditText fromDateEtxt;
    private EditText toDateEtxt;
    private DatePickerDialog fromDatePickerDialog;

    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    public static final String BASE_URL = "http://www.seismi.org/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        findViewsById();

        setDateTimeField();


        //Creating Rest Services

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestInterface service = retrofit.create(RestInterface.class);

        Call<KuakeLocation> call = service.getEarthquakes();

        //Executing Call
        call.enqueue(new Callback<KuakeLocation>() {
            @Override
            public void onResponse(Call<KuakeLocation> call, Response<KuakeLocation> response) {
                List<Earthquake> equakes = response.body().getEarthquakes();
                Log.d("CORRECTO", equakes.toString());
                for (Earthquake eq : equakes) {
                    Log.d("REGION", eq.getRegion());
                    Log.d("MAGNITUDE", eq.getMagnitude());
                }

            }

            @Override
            public void onFailure(Call<KuakeLocation> call, Throwable t) {

            }
        });
    }

    private void findViewsById() {
        fromDateEtxt = (EditText) findViewById(R.id.etxt_fromdate);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
        fromDateEtxt.requestFocus();

        toDateEtxt = (EditText) findViewById(R.id.etxt_todate);
        toDateEtxt.setInputType(InputType.TYPE_NULL);
    }

    private void setDateTimeField() {
        fromDateEtxt.setOnClickListener(this);
        toDateEtxt.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                toDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        if(view == fromDateEtxt) {
            fromDatePickerDialog.show();
        } else if(view == toDateEtxt) {
            toDatePickerDialog.show();
        }
    }
}
