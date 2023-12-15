package com.example.planviewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.provider.ContactsContract;
import android.widget.ImageView;

import androidx.annotation.NonNull;

public class Mur {

    private int image;
    private Ouverture ouverture;
    private Piece piece;

    public Mur(){
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void addOuverture(Ouverture ouverture){
        if(this.ouverture == null){
            this.ouverture = ouverture;
        }
    }

    public void rmvOuverture(){
        this.ouverture=null;
    }

    public void setPiece(Piece piece, int position){
        this.piece = piece;
        piece.addMur(this, position);
    }

    public Piece quelPiece(){
        return piece;
    }

    public Ouverture getOuverture(){
        return ouverture;
    }

    @NonNull
    @Override
    public String toString(){
        int cpt =0;
        for(int i =0; i<4; i++){
            if(this.piece.murs[i]==this){
                cpt = i;
            }
        }
        StringBuilder str = new StringBuilder("Mur ");
        switch (cpt){
            case 0:
                str.append("N");
                break;
            case 1:
                str.append("E");
                break;
            case 2:
                str.append("S");
                break;
            case 3:
                str.append("O");
                break;
        }
        return str.toString();
    }
}
