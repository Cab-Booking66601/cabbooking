package com.example.cabbooking;

import android.app.Application;

import java.util.ArrayList;

public class GlobalArraylist extends Application {

    ArrayList<cabItem> globalArraylist;
    int flag;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public ArrayList<cabItem> getGlobalArraylist() {
        return globalArraylist;
    }

    public void setGlobalArraylist(ArrayList<cabItem> globalArraylist) {
        this.globalArraylist = globalArraylist;
    }
}
