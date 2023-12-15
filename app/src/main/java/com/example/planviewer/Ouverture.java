package com.example.planviewer;

import androidx.annotation.NonNull;

public class Ouverture {

    private Mur mur1;
    private Mur mur2;

    public Ouverture(){

    }

    public void addMur(Mur mur){
        if(mur1 == null){
            this.mur1 = mur;
            mur1.addOuverture(this);
        }else if(mur2 == null){
            this.mur2 = mur;
            mur2.addOuverture(this);
        }
    }

    public Mur quelMur1(){
        return mur1;
    }

    public Mur quelMur2(){
        return mur2;
    }

    @NonNull
    @Override
    public String toString(){
        return "ouverture de "+mur1.quelPiece().toString() +" a "+mur2.quelPiece().toString();
    }
}
