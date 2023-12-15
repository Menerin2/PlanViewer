package com.example.planviewer;

import androidx.annotation.NonNull;

public class Piece {

    String nom;
    Mur[] murs = new Mur[4];
    Plan plan;

    public Piece(Plan plan, String nom){
        this.plan = plan;
        this.plan.addPiece(this);
        this.nom = nom;
    }

    public void addMur(Mur mur, int position){
        if(position<=3){
            this.murs[position] = mur;
        }
    }

    public Mur[] getMurs(){
        return murs;
    }

    public String getNom(){
        return nom;
    }

    @NonNull
    @Override
    public String toString(){
        return nom;
    }

}
