package com.jafar.jaservtech.system3pjagung;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jafar.jaservtech.system3pjagung.dummy.DBHandler;
import com.jafar.jaservtech.system3pjagung.dummy.JaservTechFilter;
import com.jafar.jaservtech.system3pjagung.dummy.Progress;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A login screen that offers login via email/password.
 */
public class CreateProgressDefault extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private EditText nama, alamat, informer;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_progress_default);
        nama = (EditText) findViewById(R.id.nama);
        alamat = (EditText) findViewById(R.id.alamat);
        informer = (EditText) findViewById(R.id.auth);
        submit = (Button) findViewById(R.id.email_sign_in_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
        mLoginFormView = (View)findViewById(R.id.login_form);
        mProgressView = (View)findViewById(R.id.login_progress);
    }


    /**
     * Callback received when a permissions request has been completed.
     */
    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Reset errors.
        nama.setError(null);
        alamat.setError(null);
        informer.setError(null);

        boolean cancel = false;
        View focusView = null;
        // Store values at the time of the login attempt.
        String namaS = nama.getText().toString();
        String alamatS = alamat.getText().toString();
        String informerS = informer.getText().toString();
        if(JaservTechFilter.isEmpty(namaS)){
            nama.setError("Nama progress Wajib diisi");
            cancel = true;
            focusView = nama;
        }else if(!JaservTechFilter.isNameFieldValid(namaS)){
            nama.setError("hanya : (A-Z a-z)");
            cancel = true;
            focusView = nama;
        }else  if(JaservTechFilter.isContainMoreSpace(namaS)){
            nama.setError("mengandung double spasi");
            focusView = nama;
            cancel = true;
        }
        if(JaservTechFilter.isEmpty(alamatS)){
            alamat.setError("Lokasi Ladang wajib diisi");
            cancel = true;
            if(focusView == null){
                focusView = alamat;
            }
        }else if(!JaservTechFilter.isAddressValid(alamatS)){
            alamat.setError("hanya : (A-Z,a-z.0-9)");
            cancel = true;
            if(focusView == null){
                focusView = alamat;
            }
        }else if(JaservTechFilter.isContainMoreSpace(alamatS)){
            alamat.setError("mengandung double spasi");
            cancel = true;
            if(focusView == null){
                focusView = alamat;
            }
        }

        if(JaservTechFilter.isEmpty(informerS)){
            informer.setError("Nama penanggung jawab progress wajib diisi");
            cancel = true;
            if(focusView == null){
                focusView = informer;
            }
        }else if(!JaservTechFilter.isNameValid(informerS)){
            informer.setError("hanya : (A-Z,a-z.0-9)");
            cancel = true;
            if(focusView == null){
                focusView = informer;
            }
        }else if(JaservTechFilter.isContainMoreSpace(informerS)){
            informer.setError("mengandung double spasi");
            cancel = true;
            if(focusView == null){
                focusView = informer;
            }
        }

        /*
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(nama) && !isPasswordValid(nama)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        */
        if (cancel) {
            focusView.requestFocus();
            // There was an error; don't attempt login and focus the first
            // form field with an error.
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            try{
                showProgress(true);
                Thread.sleep(3000);
            }catch (InterruptedException e){

            }
            Date h = new Date();
            SimpleDateFormat jj = new SimpleDateFormat("yyyy.MM.dd");
            Progress s = new Progress();
            s.setId(jj.format(h) + "" + informerS.charAt(0) + "" + alamatS.charAt(2) + "" + namaS.charAt(5));
            s.setName(namaS);
            s.setAddress(alamatS);
            s.setAuth(informerS);
            s.setCat(1);
            s.setPercentage(0);
            s.setFinish(0);
            s.setStartDate(jj.format(h));
            s.setEndDate("");
            DBHandler hjk = new DBHandler(CreateProgressDefault.this);
            if(hjk.addProgress(s)){
                try{
                    Toast.makeText(CreateProgressDefault.this, "Data berhasil dimasukan", Toast.LENGTH_SHORT);
                    Thread.sleep(3000);
                }catch(InterruptedException e){

                }finally {
                    finish();
                }
            }
            //  mAuthTask = new UserLoginTask(email, password);
            // mAuthTask.execute((Void) null);
        }
    }

    /*
    private boolean isEmailValid(String email) {
        TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
