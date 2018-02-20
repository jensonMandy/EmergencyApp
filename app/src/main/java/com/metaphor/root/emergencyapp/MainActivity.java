package com.metaphor.root.emergencyapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.android.gms.common.api.Response;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView textView;
    private LocationManager locationManager;
    private LocationListener locationListener;
    double latitude, longitude;
    public String loc = new String(  );
//    public String desc = new String(  );

//    Spinner spinner;
//    ArrayAdapter<CharSequence> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        button = (Button) findViewById( R.id.locationControllerGPS );
        textView = (TextView) findViewById( R.id.titleTextGPS );

        locationManager = (LocationManager) this.getSystemService( LOCATION_SERVICE );
//
//        spinner = (Spinner) findViewById(R.id.select_emergency);
//        adapter = ArrayAdapter.createFromResource(this, R.array.select_emergency, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
//                desc = (String)parent.getItemAtPosition(i);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION )
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager
                .PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions( new String[] {
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission
                        .ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET}, 10);
            }
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        } else {
            configureButton();
        }
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                longitude = location.getLongitude();
                latitude = location.getLatitude();
                loc = "Latitude = "+latitude+"\n Longitude = "+longitude;
                textView.setText( loc );
//                textView.append( "\n"+location.getLatitude()+ " "+ location.getLongitude() );
//                ourLocation = location;
//                textView.append( "\n"+ourLocation.getLatitude()+ " "+ ourLocation.getLongitude() );
                Intent intent;
                intent = new Intent(MainActivity.this, FormActivity.class);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                startActivity(intent);

//                senddata();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent intent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS );
                startActivity( intent );
            }
        };


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
        switch (requestCode){
            case 10:
                if(grantResults.length>0&&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    configureButton();;
                }
                return;
        }
    }

//    @Override
//    public void onRe(int requestCode, String [] permissions, int
//            []grantResults ){
////        super.onRequestPermissionsResult( requestCode, permissions, grantResults);
//
//    }

    private void configureButton() {
        button.setOnClickListener( new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                Criteria criteria = new Criteria();
//                criteria.setAccuracy(Criteria.ACCURACY_FINE);
//                criteria.setAltitudeRequired(false);
//                criteria.setBearingRequired(false);
//                criteria.setCostAllowed(true);
//                criteria.setPowerRequirement(Criteria.POWER_LOW);
                locationManager.requestLocationUpdates( locationManager.NETWORK_PROVIDER, 5000, 1,
                        locationListener );
//                ourLocation = new Location( "gps" );
////                Location location = new Location( LOCATION_SERVICE );
//                textView = (TextView)findViewById( R.id.titleTextGPS );
//                textView.append( "\n"+ourLocation.getLatitude()+ " "+ ourLocation.getLongitude() );
            }
        } );
    }

//    public void toggleGPSUpdates(View view) {
//    }



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





}
