package com.jafar.jaservtech.system3pjagung;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jafar.jaservtech.system3pjagung.dummy.DBHandler;
import com.jafar.jaservtech.system3pjagung.dummy.JaservTechFilter;
import com.jafar.jaservtech.system3pjagung.dummy.Progress;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A login screen that offers login via email/password.
 */
public class CreateProgressNext extends AppCompatActivity {


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView,startProg;
    private EditText nama, alamat,informer;
    RelativeLayout showCalendar;
    private View mProgressView;
    private View mLoginFormView;
    private DatePicker datePick;
    private Button dateTimePick,input;
    private void initializing(){
        this.mProgressView = (View)findViewById(R.id.login_progress);
        this.mLoginFormView = (View)findViewById(R.id.login_form);
        this.input = (Button)findViewById(R.id.email_sign_in_button);
        this.input.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
        this.datePick = (DatePicker)findViewById(R.id.datePicker);
        this.datePick.setSpinnersShown(true);
        this.datePick.setCalendarViewShown(false);
        this.dateTimePick = (Button)findViewById(R.id.pilihDateTimePicker);
        this.dateTimePick.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Date temp = new Date();
                SimpleDateFormat hY = new SimpleDateFormat("yyyy");
                SimpleDateFormat hM = new SimpleDateFormat("MM");
                SimpleDateFormat hD = new SimpleDateFormat("dd");
                int Y = Integer.parseInt(hY.format(temp));
                int m = Integer.parseInt(hM.format(temp));
                int d = Integer.parseInt(hD.format(temp));
                int error = 0;
                int Ys = datePick.getYear();
                int ms = datePick.getMonth()+1;
                int ds = datePick.getDayOfMonth();
                if(Ys > Y){
                    error++;
                }else if(Ys == Y){
                    if(ms > m){
                        error++;
                    }else if(ms == m){
                        if(ds > d)
                            error++;
                        else if(ds == d){
                            error++;
                        }
                    }
                }
                if(error > 0){
                    Toast.makeText(CreateProgressNext.this,"Tanggal anda tidak sesuai dengan kriteria masa lampau",Toast.LENGTH_LONG).show();
                }else{
                    startProg.setText(Ys+"-"+ms+"-"+ds);
                    Toast.makeText(CreateProgressNext.this,"Tanggal berhasil dimasukan",Toast.LENGTH_LONG).show();
                    showCalendar.animate().alpha(0).setDuration(600).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            showCalendar.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });
        startProg = (EditText)findViewById(R.id.startProgress);
        showCalendar = (RelativeLayout)findViewById(R.id.showCalendar);
        startProg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendar.setVisibility(View.VISIBLE);
                showCalendar.animate().alpha(1f).setDuration(600).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        showCalendar.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        nama = (EditText)findViewById(R.id.namaProgress);
        alamat = (EditText)findViewById(R.id.alamatProgress);
        informer = (EditText)findViewById(R.id.informerProgress);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_progress_next);
        initializing();
    }

    /**
     * Callback received when a permissions request has been completed.
     */

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        boolean cancel = false;
        View focusView = null;
        nama.setError(null);
        alamat.setError(null);
        informer.setError(null);
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
        startProg.setError(null);
        String startProgS = startProg.getText().toString();
        if(JaservTechFilter.isEmpty(startProgS)){
            startProg.setError("Tanggal start belum dimasukan");
            cancel = true;
            if (focusView == null){}
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
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
            s.setCat(2);
            s.setPercentage(0);
            s.setFinish(0);
            s.setStartDate(startProgS);
            s.setEndDate("");
            DBHandler hjk = new DBHandler(CreateProgressNext.this);
            if(hjk.addProgress(s)){
                try{
                    Toast.makeText(CreateProgressNext.this, "Data berhasil dimasukan", Toast.LENGTH_SHORT);
                    Thread.sleep(3000);
                }catch(InterruptedException e){

                }finally {
                    finish();
                }
            }

        }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home :
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

