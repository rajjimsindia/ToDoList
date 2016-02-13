package com.demo.raj.todolist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;

public class SignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener{

    // tag for debugging
    private static final String LOG_TAG = SignInActivity.class.getSimpleName();

    // sign-in request code
    private static final int SIGN_IN_REQUEST_CODE = 100;

    // GoogleApiClient
    GoogleApiClient mGoogleApiClient;

    // g+ sign-in button
    SignInButton mSignInButton;

    // sign-out button
    Button mSignOutButton;

    // progress dialog while sign-in
    ProgressDialog mProgressDlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        Scope scope = new Scope(Scopes.PLUS_LOGIN);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestScopes(scope, scope)
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mSignInButton = (SignInButton) findViewById(R.id.sign_in_btn);
        mSignInButton.setSize(SignInButton.SIZE_STANDARD);
        mSignInButton.setScopes(gso.getScopeArray());
        mSignInButton.setOnClickListener(this);

        mSignOutButton = (Button)findViewById(R.id.sign_out_btn);
    }

    // shows a progress dialog while signing-in
    private void showProgressDialog() {

        mProgressDlg = new ProgressDialog(this);
        mProgressDlg.setMessage("Signing-in...");
        mProgressDlg.setIndeterminate(true);
        mProgressDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDlg.show();
    }

    // hides the progress dialog if it's showing
    private void hideProgressDialog() {

        if (mProgressDlg.isShowing())
            mProgressDlg.hide();
    }

    // OnConnectionFailedListener callback
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        Log.v(LOG_TAG, "Connection failed with error code : " + connectionResult.getErrorCode());
    }

    // handles sign-in button's onClick
    @Override
    public void onClick(View v) {

        // sign-in request
        signIn();
        showProgressDialog();
    }

    // fire sign-in Intent
    private void signIn(){

        Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(intent, SIGN_IN_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){

            case SIGN_IN_REQUEST_CODE:

                if(resultCode == RESULT_OK){

                    GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                    handleSignInResult(result);

                }else{

                    Log.v(LOG_TAG, "Google sign-in cancelled!");
                }
                break;
        }
    }

    // handle the sign-in result
    private void handleSignInResult(GoogleSignInResult result){

        Log.v(LOG_TAG, " Was sign-in successful: " + result.isSuccess());

        if(result.isSuccess()){

            // successful login - show authenticated UI
            GoogleSignInAccount acc = result.getSignInAccount();
            Log.v(LOG_TAG, "Display-name: " + acc.getDisplayName());
            Log.v(LOG_TAG, "Email: " + acc.getEmail());
            Log.v(LOG_TAG, "Id: " + acc.getId());
            Log.v(LOG_TAG, "PhotoUrl: " + acc.getPhotoUrl());
            Log.v(LOG_TAG, "IdToken: " + acc.getIdToken());
            Log.v(LOG_TAG, "ServerAuthCode: " + acc.getServerAuthCode());

            mSignInButton.setVisibility(View.GONE);
            mSignOutButton.setVisibility(View.VISIBLE);
            // goto the to-do list activity

        }else{

            Toast.makeText(this, "Sign-in failed! Try again later.", Toast.LENGTH_SHORT).show();

            // failed attempt - show un-authenticated UI
            Log.v(LOG_TAG, "sign-in failed!");
        }
        hideProgressDialog();
    }
}
