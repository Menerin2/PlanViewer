/*package com.example.planviewer.test;

import com.example.planviewer.Mur;
import com.example.planviewer.Ouverture;
import com.example.planviewer.Piece;
import com.example.planviewer.Plan;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

public class testJson{

    @Test
    public void testdeJson() throws JSONException, IOException {
        //Plan plan = creaPlan();
        JSONObject json = new JSONObject();
        //FileWriter fich = new FileWriter("/res/drawale/plan.json");
        for(Piece p : plan.getPieces()){
            for(Mur mur : p.getMurs()){
                if(mur!=null) {
                    if(mur.getOuverture()!=null) {
                        json.put("ouverture", mur.getOuverture().quelMur2().toString());
                    }
                    json.put("mur", mur.toString());
                }
            }
            json.put("piece", p.toString());
        }
        fich.write(json.toString());
        System.out.println(json);
        fich.flush();
        fich.close();
    }

    public Plan creaPlan(){
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

        return plan;
    }

}*/
