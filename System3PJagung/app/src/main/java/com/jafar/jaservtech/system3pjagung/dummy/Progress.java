package com.jafar.jaservtech.system3pjagung.dummy;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jafar on 4/5/2016.
 */
public class Progress {

    private String id; //id progress
    private String name; //nama progress
    private String address; //lokasi progress
    private int percentage; //persentase of progress
    private String startDate; //tanggal mulai progress format dd-mm-yy hh-a-s
    private String endDate; //tanggal mulai progress format dd-mm-yy hh-a-s
    private int finish; //status progress telah selesai 0 sedang berjalan, 1 telah selesai
    private int cat;//kategori dari awal atau melanjutkan
    private String auth; //penanggung jawab progress
    //private function
    private void generateIdProgress(){
        Date h = new Date();
        SimpleDateFormat j = new SimpleDateFormat("yyyyMMddhhmm");
       this.id = j.format(h);
    }
    private void initializing(){
        this.generateIdProgress();
        this.finish = 0;
        this.percentage = 0;
    }
    //constructor
    public Progress()
    {
        this.initializing();
    }
    public Progress(String nama,String add, int percen,String aut, String start){
        this.initializing();
        this.name = nama;
        this.address = add;
        this.percentage = percen;
        this.auth = aut;
        this.startDate = start;
    }
    //setter
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) { this.address = address; }
    public void setPercentage(int percen){ this.percentage = percen;}
    public void setStartDate(String start){this.startDate = start;}
    public void setEndDate(String end){this.endDate = end;}
    public void setFinish(int fins){ this.finish = fins;}
    public void setAuth(String aut){ this.auth = aut;}
    public void setCat(int s){this.cat = s;}

    //getter
    public String getId() { return id; }
    public String getAddress() {
        return address;
    }
    public String getName() {
        return name;
    }
    public int getPercentage(){
        return percentage;
    }
    public int getFinish(){
        return finish;
    }
    public String getStartDate(){
        return startDate;
    }
    public String getEndDate(){
        return endDate;
    }
    public String getAuth(){
        return auth;
    }
    public int getCat(){return cat;}
}