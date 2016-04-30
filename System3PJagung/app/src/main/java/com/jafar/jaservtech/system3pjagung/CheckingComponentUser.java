package com.jafar.jaservtech.system3pjagung;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.jafar.jaservtech.system3pjagung.dummy.DBHandler;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class CheckingComponentUser extends AppCompatActivity {
    protected TextView progressMessage;
    protected int codeNext;
    protected int SPLASH_TIME = 3000;

    @Override
    public void onCreate(Bundle savedInstancestate) {
        super.onCreate(savedInstancestate);
        setContentView(R.layout.activity_checking_component_user);
        this.progressMessage = (TextView) findViewById(R.id.progressMessage);

        new Handler().postDelayed(new Runnable() {

			/*
             * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                DBHandler test = new DBHandler(CheckingComponentUser.this);
                Intent i = new Intent(CheckingComponentUser.this, HomeOfSystem.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME);
    }
}
/*

        Thread threadMe = new Thread(){
            private int codeNext;
            public void run(){
                try{
                    sleep(1000);
                    progressMessage.setText("Checking table ...");
                    int j=0;
                    while(j<=10) {
                        sleep(100);
                        j++;
                    }

                   // SQLJaservTech testAccount = new SQLJaservTech(CheckingComponentUser.this,null,null,1);
                   // codeNext= testAccount.totalrow("SELECT * FROM user_active");



                }catch (Exception e){

                }finally {
                    Intent control = new Intent(CheckingComponentUser.this,HomeOfSystem.class);
                    startActivity(control);
                    finish();
/*
                    Intent control;
                    if(codeNext >= 1)
                        control = new Intent(MainActivity.this,ControlFirst.class);
                    else
                        control = new Intent(MainActivity.this,SignUpFirst.class);

                    startActivity(control);
                    finish();
                    *//*
                }
            }
        };
        threadMe.start();
    }
}
                */
