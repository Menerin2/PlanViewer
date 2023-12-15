package com.example.planviewer;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;

public class Plan {

    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    String nom;
    public Plan(String nom){
        this.nom = nom;
    }

    public void addPiece(Piece piece){
        pieces.add(piece);
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void removePiece(Piece piece){
        pieces.remove(piece);
    }

    public Iterator<Piece> iterator(){
        return pieces.iterator();
    }

    @NonNull
    @Override
    public String toString(){
        return nom;
    }

}
