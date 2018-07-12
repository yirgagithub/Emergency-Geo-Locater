package com.the.example.amorageolocater;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.the.example.amorageolocater.health.HealthRecyclerViewAdapter;
import com.the.example.amorageolocater.police.PoliceRecyclerViewAdapter;
import com.the.example.amorageolocater.traffic.TrafficRecyclerViewAdapter;

/**
 * Created by what on 7/24/2017.
 */

public class LocationProviderClass extends AppCompatActivity implements  GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {



    private static final int FINE_LOCATION_REQUEST = 100;
    private static final int SMS_REQUEST_CODE = 101;
    private static final int LOCATION_REQUESTCODE =102 ;
    private static final String RUNTIME_PERMISSION_ERROR ="run time error" ;

    private LocationRequest locationRequest;


    private ProgressBar progressBar;
    LinearLayout mainLayout;



    private static final String LOCATION_TAG = "location provider tag";
    private static final int STATUS_INT = 103;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 0;
    private static final String TAG = "connection failed tag";


    String from = "";


    //information to send
    public String[] helpCenterContact;
    private String category;
    private String accidentType;
    private String message;
    private SendMessage sendMessage;


    private GoogleApiClient mGoogleApiClient;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Location mLocation;
    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_provider_layout);
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        progressBar = (ProgressBar) findViewById(R.id.location_progress_id);
        Intent intent = getIntent();
        category = intent.getStringExtra(PoliceRecyclerViewAdapter.CATEGORY);
        accidentType = intent.getStringExtra(PoliceRecyclerViewAdapter.ACCIDENT_TYPE);
        if (category == null && accidentType == null) {
            category = intent.getStringExtra(HealthRecyclerViewAdapter.CATEGORY);
            accidentType = intent.getStringExtra(PoliceRecyclerViewAdapter.ACCIDENT_TYPE);
            if (category == null && accidentType == null) {
                category = intent.getStringExtra(TrafficRecyclerViewAdapter.CATEGORY);
                accidentType = intent.getStringExtra(TrafficRecyclerViewAdapter.ACCIDENT_TYPE);
            }
        }
        checkPlayServices();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        if(mGoogleApiClient!=null)
        mGoogleApiClient.connect();


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
            checkPermissions();


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (!mGoogleApiClient.isConnected()) {
            Toast.makeText(getApplicationContext(), "Google Api client is not connected", Toast.LENGTH_LONG).show();
            return;
        }
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationRequest = new LocationRequest();

        if (checkProvider(LocationManager.NETWORK_PROVIDER)) {
            locationRequest = new LocationRequest();
            locationRequest.setPriority(LocationRequest.PRIORITY_LOW_POWER);
            locationRequest.setInterval(5000);
            locationRequest.setFastestInterval(2000);
        } else if(checkProvider(LocationManager.GPS_PROVIDER)) {
            locationRequest = new LocationRequest();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(7000);
            locationRequest.setFastestInterval(2000);
        }
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                final Status status = locationSettingsResult.getStatus();
                switch (status.getStatusCode()) {

                    case LocationSettingsStatusCodes.SUCCESS:
                        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                            boolean isLocationOn = ActivityCompat.checkSelfPermission(LocationProviderClass.this, Manifest.permission_group.LOCATION) != PackageManager.PERMISSION_GRANTED;

                            if (!(isLocationOn)) {
                                String [] runTimePermission=new String[]{Manifest.permission_group.SMS};
                                requestPermissions(runTimePermission, LOCATION_REQUESTCODE);
                            }
                            else
                                computeLocation();
                        } else

                            computeLocation();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            status.startResolutionForResult(LocationProviderClass.this, STATUS_INT);
                        } catch (IntentSender.SendIntentException e) {
                            Log.d(LOCATION_TAG, "error in startResolutionForResult");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.d(LOCATION_TAG, "Location setting is canceled");
                        break;
                    default:
                        Log.d(LOCATION_TAG,"Location setting enters into default");

                }
            }
        });

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }




    private boolean checkProvider(String networkProvider) {
        return locationManager.isProviderEnabled(networkProvider);
    }

    private void computeLocation() {

        if(!(checkProvider(LocationManager.GPS_PROVIDER)||checkProvider(LocationManager.NETWORK_PROVIDER))) {
            createWaringDialog();
            progressBar.setVisibility(View.GONE);
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission_group.LOCATION}, SMS_REQUEST_CODE);
            }
            return;
        }
        //single request
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                // GPS location can be null if GPS is switched off
                if (location != null) {
                    onLocationChanged(location);
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("MapDemoActivity", "Error trying to get last GPS location");
                        e.printStackTrace();
                    }
                });

/*
    //location update
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, new LocationCallback() {

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        //super.onLocationResult(locationResult);
                        if (locationResult != null) {
                            Location currentLocation=locationResult.getLastLocation();
                            if(currentLocation!=null)
                            {
                                fusedLocationProviderClient.removeLocationUpdates(new LocationCallback());
                            }
                            onLocationChanged(currentLocation);
                        }
                    }

                    @Override
                    public void onLocationAvailability(LocationAvailability locationAvailability) {
                        super.onLocationAvailability(locationAvailability);
                    }
                },
                Looper.myLooper());


*/

    }

    public void onLocationChanged(Location location) {

        String msg = "Updated Location: " +
                java.lang.Double.toString(location.getLatitude()) + "," +
                java.lang.Double.toString(location.getLongitude());
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        sendLocation(location);

    }




    private void sendLocation(Location location) {
        mLocation=location;
        Toast.makeText(getApplicationContext(), " pressed and found loc " + from + location.getLatitude() + " " + location.getLongitude(), Toast.LENGTH_LONG).show();
        helpCenterContact = NearByHelpCenterFinder.getNearByHelpCenter(getApplicationContext(), location.getLatitude(), location.getLongitude());
        //String phoneNumber = "0919044725";
        String myPhoneNumber = "0932044988";
        message = "lat=" + location.getLatitude() + "&" +
                "long=" + location.getLongitude() + "&" +
                "phone=" + myPhoneNumber + "&" +
                "cat=" + category + "&" +
                "type=" + accidentType;
        progressBar.setVisibility(View.GONE);


        final AlertDialog.Builder builder = new AlertDialog.Builder(
                LocationProviderClass.this);

        builder.setTitle("Report");
        builder.setMessage("You are Sending your location for " + accidentType + " emergency help ");
        builder.setCancelable(true);
        builder.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Toast.makeText(getApplicationContext(), "No is clicked", Toast.LENGTH_LONG).show();


                    }
                });
        builder.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Toast.makeText(getApplicationContext(), "Yes is clicked", Toast.LENGTH_LONG).show();
                        sendMessage = new SendMessage();

                        boolean isSMSSend = true;
                        boolean isHTTPSend = false;
                        if (isSMSSend) {
                            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission_group.SMS) != PackageManager.PERMISSION_GRANTED) {
                                    requestPermissions(new String[]{Manifest.permission_group.SMS}, SMS_REQUEST_CODE);
                                }
                            } else
                                sendMessage.sendSMS(getApplicationContext(), helpCenterContact[0], message);
                        } else if (isHTTPSend) {
                            String httpName="http://192.168.43.22/Req/";

                            sendMessage.sendHTTP(getApplicationContext(), httpName, message);
                        }
                    }
                });
        if(!(this).isFinishing())
        builder.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == STATUS_INT) {
            switch (resultCode) {
                case Activity.RESULT_OK: {
                    Toast.makeText(getApplicationContext(), "User enabled location permission", Toast.LENGTH_LONG).show();
                    computeLocation();
                    break;
                }
                case Activity.RESULT_CANCELED: {
                    computeLocation();
                    break;
                }
                default:
                    Toast.makeText(getApplication(), "Permission error", Toast.LENGTH_LONG).show();
            }

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {

        switch (requestCode) {
            case  LOCATION_REQUESTCODE:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    computeLocation();

                }
                else {

                    Log.d(RUNTIME_PERMISSION_ERROR,"Error in runtime error");
                    Toast.makeText(getApplication(),"The app need location permission", Toast.LENGTH_LONG).show();

                }
                break;

            case SMS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    sendMessage.sendSMS(getApplicationContext(), helpCenterContact[0], message);
                }
                else {

                    Log.d(RUNTIME_PERMISSION_ERROR,"Error in runtime error");
                    Toast.makeText(getApplication(),"The app need SMS permission", Toast.LENGTH_LONG).show();

                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        }
    }


    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestPermissions();
            return false;
        }
    }


    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                FINE_LOCATION_REQUEST);
    }


      @Override
    protected void onStop() {
        if (!mGoogleApiClient.isConnected())
            this.mGoogleApiClient.connect();
        super.onStop();
    }

    @Override
    protected void onPause() {
        if (mGoogleApiClient.isConnected() || mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        super.onPause();
    }

    public void createWaringDialog() {

        final AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle(R.string.locationDialog_title_string);
        builder.setMessage(R.string.location_permission_message);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                });
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.show();


    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else
                finish();

            return false;
        }
        return true;
    }
}
