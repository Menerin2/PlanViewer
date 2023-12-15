package com.example.planviewer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainCrea extends AppCompatActivity {

    private Plan plan;
    int cpt = 0;
    Piece pieceVisu;
    int murSelect = -1;

    private String pieceNom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_main);
        plan = creaPlan();
        creaListe();
    }

    public void addRoom(View view){
        AlertDialog.Builder constr = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        constr.setView(input);
        constr.setPositiveButton("Creer", (dialog, which) -> {
            pieceNom = input.getText().toString();
            new Piece(plan, pieceNom);
            creaListe();

        });
        constr.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        constr.show();
    }

    public void creaListe(){
        String[] piecesNom = new String[plan.getPieces().size()];
        for(int i=0; i<plan.getPieces().size(); i++){
            piecesNom[i]=plan.getPieces().get(i).getNom();
        }
        @SuppressLint("ResourceType") ArrayAdapter<String> pieces = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, piecesNom);
        ListView l = (ListView) findViewById(R.id.listMurs);
        l.setOnItemClickListener((parent, view, position, id) -> selectionPiece(position));
        l.setAdapter(pieces);
    }

    public void selectionPiece(int position){
        pieceVisu = plan.getPieces().get(position);
        setContentView(R.layout.liste_piece);
        TextView txt = findViewById(R.id.pieceModif);
        txt.setText(pieceVisu.getNom());
        creaListeMur();
    }

    public void creaListeMur(){
        String[] murNom = new String[4];
        for(int i=0; i<4; i++){
            if(pieceVisu.getMurs()[i]==null){
                murNom[i] = "vide";
            }else{
                murNom[i] = pieceVisu.getNom();
            }
        }
        @SuppressLint("ResourceType") ArrayAdapter<String> murs = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, murNom);
        ListView l = findViewById(R.id.listMurs);
        l.setOnItemClickListener((parent, view, position, id) -> selectionMur(position));
        l.setAdapter(murs);
    }

    public void selectionMur(int position){
        murSelect = position;
    }

    public void supprMur(View view){
        if(murSelect != -1){
            pieceVisu.getMurs()[murSelect]=null;
        }else{
            Toast.makeText(this, "Aucun mur selectionné", Toast.LENGTH_SHORT).show();
        }
        creaListeMur();
    }

    public void addMur(View view){
        if(murSelect != -1){
            if(pieceVisu.getMurs()[murSelect]==null){
                pieceVisu.getMurs()[murSelect]=new Mur();
            }else{
                Toast.makeText(this, "Selectionnez un emplacement vide", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Selectionnez un emplacement", Toast.LENGTH_SHORT).show();
        }
        creaListeMur();
    }

    public int cptMurNoOuv(){
        cpt = 0;
        for (Piece p : plan.getPieces()){
            for(int i=0;i<4;i++){
                if(p.getMurs()[i]!=null){
                    if(p.getMurs()[i].getOuverture()!=null){
                        cpt++;
                    }
                }
            }
        }
        return cpt;
    }

    public void creaListeMurOuv(){
        int nbMur = cptMurNoOuv();
        String orient = null;
        String piece;
        int cpt=0;
        String[] mur = new String[nbMur];
        for (Piece p : plan.getPieces()){
            piece = p.getNom();
            for(int i=0;i<4;i++){
                if(p.getMurs()[i]!=null){
                    switch (i){
                        case 0:
                            orient="N";
                            break;
                        case 1:
                            orient="E";
                            break;
                        case 2:
                            orient="S";
                            break;
                        case 3:
                            orient="O";
                            break;
                    }
                    //piece = piece.concat(orient);
                    mur[cpt]=piece;
                    cpt++;
                }
            }
        }
        @SuppressLint("ResourceType") ArrayAdapter<String> pieces = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mur);
        ListView l = (ListView) findViewById(R.id.listMurs);
        l.setOnItemClickListener((parent, view, position, id) -> selectionMur(position));
        l.setAdapter(pieces);
    }

    public void selectMurOuv(){
        setContentView(R.layout.liste_mur_ouv);
        creaListeMurOuv();
    }

    public void addOuv(View view){
        if(murSelect != -1){
            if(pieceVisu.getMurs()[murSelect]!=null){
                if(pieceVisu.getMurs()[murSelect].getOuverture()!=null){
                    selectMurOuv();
                }else{
                    Toast.makeText(this, "Ce mur a déjà une ouverture", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Selectionnez un emplacement non vide", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Selectionnez un emplacement", Toast.LENGTH_SHORT).show();
        }
        creaListeMur();
    }

    public void supprOuv(View view){
        if(murSelect != -1){
            if(pieceVisu.getMurs()[murSelect]!=null){
                if(pieceVisu.getMurs()[murSelect].getOuverture()!=null){
                    pieceVisu.getMurs()[murSelect].rmvOuverture();
                    Toast.makeText(this, "Ouverture supprime", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Ce mur ne possède pas d'ouverture", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Selectionnez un emplacement non vide", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Aucun mur selectionné", Toast.LENGTH_SHORT).show();
        }
        creaListeMur();
    }

    public void valider(View view){
        murSelect = -1;
        setContentView(R.layout.creation_main);
        creaListe();
    }

    public void visionner(View view){
        setContentView(R.layout.visual_main);
        pieceVisu = plan.getPieces().get(2);
        etatButton();
        ImageView imgMur = findViewById(R.id.imgMur);
        imgMur.setImageResource(pieceVisu.getMurs()[cpt].getImage());
        TextView nomPiece = findViewById(R.id.nomPiece);
        nomPiece.setText(pieceVisu.getNom());
    }

    public void returnCrea(View view){
        setContentView(R.layout.creation_main);
        creaListe();
    }

    public void etatButton(){
        Button button = findViewById(R.id.chgPiece);
        if(pieceVisu.getMurs()[cpt]!=null){
            button.setEnabled(pieceVisu.getMurs()[cpt].getOuverture() != null);
        }else{
            button.setEnabled(false);
        }
    }

    public void goDrt(View view){
        ImageView imgMur = (ImageView) findViewById(R.id.imgMur);
        if(cpt==3){
            cpt=0;
        }else{
            cpt++;
        }
        if(pieceVisu.getMurs()[cpt]==null){
            imgMur.setImageResource(R.drawable.murrandom);
        }else{
            imgMur.setImageResource(pieceVisu.getMurs()[cpt].getImage());
        }
        etatButton();
    }

    public void goGch(View view){
        ImageView imgMur = (ImageView) findViewById(R.id.imgMur);
        if(cpt==0){
            cpt=3;
        }else{
            cpt--;
        }
        imgMur.setImageResource(pieceVisu.getMurs()[cpt].getImage());
        etatButton();
    }

    public void chgPiece(View view){
        ImageView imgMur = (ImageView) findViewById(R.id.imgMur);
        TextView nomPiece = (TextView) findViewById(R.id.nomPiece);
        Ouverture ouv = pieceVisu.getMurs()[cpt].getOuverture();
        Mur mur;
        if(ouv.quelMur1()!=null && ouv.quelMur2()!=null){
            if(pieceVisu.getMurs()[cpt]==ouv.quelMur1()){
                mur = ouv.quelMur2();
            }else{
                mur = ouv.quelMur1();
            }
            for(Piece p : plan.getPieces()){
                for(int i = 0; i<4; i++){
                    if(p.getMurs()[i]==mur){
                        pieceVisu = p;
                        cpt = i;
                        imgMur.setImageResource(pieceVisu.getMurs()[cpt].getImage());
                        nomPiece.setText(pieceVisu.getNom());
                    }
                }
            }
        }else{
            Toast.makeText(this, "Cette ouverture ne mène nulle part", Toast.LENGTH_SHORT).show();
        }

    }

    @SuppressLint("ResourceType")
    public Plan creaPlan(){
        Plan plan = new Plan("phi");
        Piece salle = new Piece(plan, "salle");
        Piece reserve = new Piece(plan, "reserve");
        Piece bureau = new Piece(plan, "bureau");
        Mur bureau1 = new Mur();
        bureau1.setImage(R.drawable.bureaun);
        Mur bureau2 = new Mur();
        bureau2.setImage(R.drawable.bureaue);
        Mur bureau3 = new Mur();
        bureau3.setImage(R.drawable.bureaus);
        Mur bureau4 = new Mur();
        bureau4.setImage(R.drawable.bureauo);

        Mur reserve1 = new Mur();
        reserve1.setImage(R.drawable.reserven);
        Mur reserve2 = new Mur();
        reserve2.setImage(R.drawable.reservee);
        Mur reserve3 = new Mur();
        reserve3.setImage(R.drawable.reserves);
        Mur reserve4 = new Mur();
        reserve4.setImage(R.drawable.reserveo);

        Mur salle2 = new Mur();
        salle2.setImage(R.drawable.salleo);
        Mur salle3 = new Mur();
        salle3.setImage(R.drawable.salles);
        Mur salle4 = new Mur();
        salle4.setImage(R.drawable.sallee);

        Ouverture porte = new Ouverture();
        porte.addMur(bureau2);
        porte.addMur(reserve4);

        bureau1.setPiece(bureau, 1);
        bureau2.setPiece(bureau, 2);
        bureau3.setPiece(bureau, 3);
        bureau4.setPiece(bureau, 0);

        Ouverture port = new Ouverture();
        port.addMur(reserve1);
        port.addMur(salle3);

        reserve1.setPiece(reserve, 1);
        reserve2.setPiece(reserve, 2);
        reserve3.setPiece(reserve, 3);
        reserve4.setPiece(reserve, 0);

        Ouverture sortie = new Ouverture();
        sortie.addMur(salle2);

        salle2.setPiece(salle, 2);
        salle3.setPiece(salle, 3);
        salle4.setPiece(salle, 0);

        return plan;
    }
}