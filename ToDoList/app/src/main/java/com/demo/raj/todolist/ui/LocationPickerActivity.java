package com.demo.raj.todolist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.demo.raj.todolist.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;

/**
 * Created by raj on 2/15/2016.
 */
public class LocationPickerActivity extends AppCompatActivity {

    // log tag for debugging
    private static final String LOG_TAG = LocationPickerActivity.class.getSimpleName();

    // GoogleApiClient instance
    private GoogleApiClient mGoogleApiClient;

    // request code for PlacePicker
    public static int PLACE_PICKER_REQUEST = 1;

    public static final String EXTRAS_LOCATION = "location";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_picker);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .build();

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {

            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        }catch (GooglePlayServicesNotAvailableException e){

            e.printStackTrace();

        }catch (GooglePlayServicesRepairableException e){

            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK){

            if(data != null) {

                Place place = PlacePicker.getPlace(this, data);
                String name = place.getName().toString();
//                Log.v(LOG_TAG, name);

                // send result back to the starting activity
                Intent intent = new Intent();
                intent.putExtra(EXTRAS_LOCATION, name);
                setResult(RESULT_OK, intent);
            }else{

                Toast.makeText(this, "User didn't select any location.", Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }
}
