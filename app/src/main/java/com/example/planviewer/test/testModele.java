package com.example.planviewer.test;

import com.example.planviewer.Mur;
import com.example.planviewer.Ouverture;
import com.example.planviewer.Piece;
import com.example.planviewer.Plan;

import org.junit.Test;

public class testModele {

    @Test
    public void test(){
        Plan plan = new Plan("phi");
        Piece salle = new Piece(plan, "salle");
        Piece reserve = new Piece(plan, "reserve");
        Piece bureau = new Piece(plan, "burreau");
        Mur derriere = new Mur();
        Mur cote = new Mur();
        Mur fond = new Mur();
        Mur devand = new Mur();

        derriere.setPiece(salle,2 );
        cote.setPiece(reserve, 0);
        fond.setPiece(reserve, 3);
        devand.setPiece(bureau, 1);

        Ouverture porte = new Ouverture();
        porte.addMur(cote);
        porte.addMur(derriere);
        Ouverture port = new Ouverture();
        port.addMur(fond);
        port.addMur(devand);

        System.out.println(plan);
        for(Piece p : plan.getPieces()){
            System.out.println(p.toString());
            for(Mur mur : p.getMurs()){
                if(mur!=null) {
                    System.out.println(mur);
                    if(mur.getOuverture()!=null) {
                        System.out.println(mur.getOuverture().toString());
                    }
                }
            }
        }
    }
}
