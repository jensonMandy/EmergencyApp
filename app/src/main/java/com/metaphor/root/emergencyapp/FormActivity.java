package com.metaphor.root.emergencyapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.appcompat.*;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class FormActivity extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    public String loc = new String(  );
    public String desc = new String(  );
    private Button button;
    private TextView textView;
    double lat, longi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
//        Intent intent = getIntent();
         lat = getIntent().getDoubleExtra("latitude", 0.00);
         longi = getIntent().getDoubleExtra("longitude", 0.000);
        loc = "Latitude = "+lat+"\n Longitude = "+longi;
        textView = findViewById(R.id.loc);
        textView.setText(loc);
        spinner = (Spinner) findViewById(R.id.select_emergency);
        adapter = ArrayAdapter.createFromResource(this, R.array.select_emergency, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                desc = (String)parent.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
//    public void senddata() {
//
//        String url = "http://flamboyant-bets.000webhostapp.com/test/emergency_request.php";
//
//        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
//
//        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.i("reach", "Reached here");
//                Context context = getApplicationContext();
//
//                Toast.makeText(context.getApplicationContext(), "Request sent successfully",
//                        Toast.LENGTH_LONG).show();
//            }
//        }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i("reach", "not here");
//                Context context = getApplicationContext();
//
//                Toast.makeText(context.getApplicationContext(), "Request not sent ",
//                        Toast.LENGTH_LONG).show();
//
//            }
//        }){
//
//            protected Map<String, String> getParams(){
//                Map<String, String> MyData = new HashMap<String, String>();
//                MyData.put("e_loc", loc);
//                MyData.put("e_desc", desc);
//                return MyData;
//            }
//
//        };
//
//        MyRequestQueue.add(MyStringRequest);
//
//    }

    public void senddata(View view) {
        String url = "http://flamboyant-bets.000webhostapp.com/test/emergency_request.php";

        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);

        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("reach", "Reached here");
                Context context = getApplicationContext();

                Toast.makeText(context.getApplicationContext(), "Request sent successfully",
                        Toast.LENGTH_LONG).show();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("reach", "not here");
                Context context = getApplicationContext();

                Toast.makeText(context.getApplicationContext(), "Request not sent ",
                        Toast.LENGTH_LONG).show();

            }
        }){

            protected Map<String, String> getParams(){
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("e_loc", loc);
                MyData.put("e_desc", desc);
                return MyData;
            }

        };

        MyRequestQueue.add(MyStringRequest);
    }
}
