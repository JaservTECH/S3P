package com.jafar.jaservtech.system3pjagung;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jafar.jaservtech.system3pjagung.dummy.DBHandler;

public class HomeOfSystem extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    protected RelativeLayout home,progress,history,help,addProg;
    protected FloatingActionButton fab;
    protected ImageView createNew, createNext, backCreate;
    protected Button h,ll;
    protected LinearLayout j;
    protected LinearLayout viewProg;
    protected DBHandler db;
    protected void initial(){
        viewProg = (LinearLayout)findViewById(R.id.viewerProgress);
        home = (RelativeLayout)findViewById(R.id.home_layout);
        progress = (RelativeLayout)findViewById(R.id.progress_layout);
        history = (RelativeLayout)findViewById(R.id.history_layout);
        help = (RelativeLayout)findViewById(R.id.help_layout);
        db = new DBHandler(HomeOfSystem.this);
        //h = (Button)findViewById(R.id.getLayout);
        //ll = (Button)findViewById(R.id.getGoneLayout);
        //j = (LinearLayout)findViewById(R.id.contentLayout);
        createNew = (ImageView)findViewById(R.id.newProgress);
        createNext = (ImageView)findViewById(R.id.nextProgress);
        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeOfSystem.this, CreateProgressDefault.class));
                addProgHide();
                fab.show();
            }
        });
        createNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeOfSystem.this, CreateProgressNext.class));
                addProgHide();
                fab.show();
            }
        });
        backCreate = (ImageView)findViewById(R.id.outProgress);
        addProg = (RelativeLayout)findViewById(R.id.optionCreateProgress);
        backCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProgHide();
                fab.show();
            }
        });
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View jojo, kokos;
        LinearLayout kokol;
        for(int z = 0;z < 10;z++ ){
            final int kei = z;
            jojo = inflater.inflate(R.layout.button_show_list,null);
            jojo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(HomeOfSystem.this,"show : "+ kei,Toast.LENGTH_LONG).show();
                }
            });
            kokos = inflater.inflate(R.layout.button_delete_list,null);
            kokos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewProg.removeViewAt(kei);
                    Toast.makeText(HomeOfSystem.this, "delete  : " + kei, Toast.LENGTH_LONG).show();
                }
            });
            kokol = (LinearLayout)inflater.inflate(R.layout.content_view_list,null);
            kokol.addView(jojo);
            kokol.addView(kokos);
            kokol.setMinimumWidth(100);
            viewProg.addView(kokol);
        }
    }
    protected void showLayout(RelativeLayout temp){
        home.setVisibility(View.GONE);
        progress.setVisibility(View.GONE);
        history.setVisibility(View.GONE);
        help.setVisibility(View.GONE);
        temp.setVisibility(View.VISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_of_system);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.hide();
                addProgShow();
            }
        });
        fab.hide();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initial();
        refreshHistoryFastInfo();
        refreshProgresFastInfo();
        ImageView a,b;
        a=(ImageView)findViewById(R.id.refreshHistoryFastInfo);
        b=(ImageView)findViewById(R.id.refreshProgressFastInfo);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshHistoryFastInfo();
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshProgresFastInfo();
            }
        });
        Toast.makeText(HomeOfSystem.this,"Tap Image to refresh info fast from progress and history", Toast.LENGTH_LONG).show();
    }
    protected void refreshProgresFastInfo(){
        int hkh = db.getProgresssHisCount(0);
        TextView ko = (TextView)findViewById(R.id.progressTotal);
        ko.setText("" + hkh + "");
    }
    protected void refreshHistoryFastInfo(){
        int hkj = db.getProgresssHisCount(1);
        TextView jo = (TextView)findViewById(R.id.historyTotal);
        jo.setText("" + hkj + "");
    }
    public void addProgShow(){
        addProg.setVisibility(View.VISIBLE);
        addProg.animate().alpha(1.0f).setDuration(300).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                addProg.setVisibility(View.VISIBLE);
            }
        });
    }
    public void addProgHide(){
        addProg.animate().alpha(0.0f).setDuration(300).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                addProg.setVisibility(View.GONE);
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
/*
*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_of_system, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
* */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            showLayout(home);
            fab.hide();
            Toast.makeText(HomeOfSystem.this,"Tap Image to refresh info fast from progress and history", Toast.LENGTH_LONG).show();
        } else if (id == R.id.history) {
            showLayout(history);
            fab.hide();
        } else if (id == R.id.progress) {
            showLayout(progress);
            fab.show();
        } else if (id == R.id.help) {
            showLayout(help);
            fab.hide();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    protected void homeSession(){

    }
}
