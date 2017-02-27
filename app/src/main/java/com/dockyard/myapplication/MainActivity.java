package com.dockyard.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;




import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;



import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;



public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,LocationListener
{
    static int c=0;
    static int d=0;
    TextView tv1, tv2, tv3, tv4,tv5,tv6,tv7,tv8,tv9,tv10;
    GoogleApiClient gac;
    Location L;
    LocationRequest LR;
    private Double lat,lon;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv6 = (TextView) findViewById(R.id.tv6);
        tv7 = (TextView) findViewById(R.id.tv7);
        tv8 = (TextView) findViewById(R.id.tv8);
        tv9 = (TextView) findViewById(R.id.tv9);
        tv10 = (TextView) findViewById(R.id.tv10);

        gac = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        LR=new LocationRequest();
        LR.setInterval(6000);
        LR.setFastestInterval(4000);
        LR.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    }

    protected void onStart()
    {
        super.onStart();
        gac.connect();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle)
    {
        c++;
        tv5.setText("5"+String.valueOf(c));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            tv1.setText("No permission last");
            tv2.setText("No permission last");
            tv3.setText("No permission last");
            tv4.setText("No permission last");
            return;
        }
        L = LocationServices.FusedLocationApi.getLastLocation(gac);
        if(L==null)
        {
            tv6.setText("6");
        }
        else
        {
            tv7.setText("7"+String.valueOf(c));
            request_location_updates();
        }
    }

    private void request_location_updates()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            tv1.setText("No permission updating");
            tv2.setText("No permission updating");
            tv3.setText("No permission updating");
            tv4.setText("No permission updating");
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(gac, LR, (LocationListener) this);
        tv8.setText("8");
    }

    @Override
    public void onConnectionSuspended(int i)
    {
        tv9.setText("9");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {
        tv10.setText("10");
    }



    @Override
    public void onLocationChanged(Location location)
    {
        d++;
        lat=location.getLatitude();
        lon=location.getLongitude();
        tv9.setText(String.valueOf(d)+"LocationChanged");
        tv2.setText(String.valueOf(lat));
        tv4.setText(String.valueOf(lon));
    }
}