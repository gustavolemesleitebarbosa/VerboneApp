package com.example.gustavo.coacroch;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EntryPT extends AppCompatActivity {

    boolean time;
    int timeCounter;
    int regularityCounter;
    int levelCounter;
    static List<Integer> timeList;

    static SQLiteDatabase verbosDB;


    public void addVerbs(Context context, String filename, String tableName)
            throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));

        // do reading, usually loop until end of file reading
        String sql0 = "DROP TABLE IF EXISTS " + tableName;
        String sql1 = "CREATE TABLE IF NOT EXISTS " + tableName + " (verb VARCHAR, difficulty VARCHAR, id INTEGER PRIMARY KEY)";
        verbosDB.execSQL(sql0);
        verbosDB.execSQL(sql1);

        String mLine = reader.readLine();
        String[] databaseItens;

        while (mLine != null) {
            databaseItens = mLine.split(";");
            String verb = databaseItens[0];
            String difficulty = databaseItens[1];
            String sql2 = "INSERT INTO " + tableName + "(verb,difficulty) VALUES (?,?)";
            SQLiteStatement statement2 = verbosDB.compileStatement(sql2);
            statement2.bindString(1, verb);
            statement2.bindString(2, difficulty);
            statement2.execute();
            mLine = reader.readLine();
        }
        reader.close();
    }

    public void addConjugations(Context context, String filename, String tableName)
            throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));

        // do reading, usually loop until end of file reading
        String sql0 = "DROP TABLE IF EXISTS " + tableName;
        String sql1 = "CREATE TABLE IF NOT EXISTS " + tableName + " (conjugation VARCHAR, id INTEGER PRIMARY KEY)";
        verbosDB.execSQL(sql0);
        verbosDB.execSQL(sql1);

        String mLine = reader.readLine();
        String[] databaseItens;

        while (mLine != null) {
            databaseItens = mLine.split(";");
            String conjugation = databaseItens[0];
            String sql2 = "INSERT INTO " + tableName + "(conjugation) VALUES (?)";
            SQLiteStatement statement2 = verbosDB.compileStatement(sql2);
            statement2.bindString(1, conjugation);
            statement2.execute();
            mLine = reader.readLine();
        }
        reader.close();
    }


    public void initializeDB() {

        verbosDB = this.openOrCreateDatabase("Verbos", MODE_PRIVATE, null);

        String text;

        try {
            addVerbs(getApplicationContext(), "ptRegular.txt", "regularPt");
           /* Cursor c1 = verbosDB.rawQuery("SELECT * FROM  regularPt ", null);
            int value = c1.getColumnIndex("verb");
            int value2 = c1.getColumnIndex("id");
            c1.moveToFirst();
            for (int i = 0; i < 4600; i++) {
                text = c1.getString(value);
                String difficulty = c1.getString(value);
                Log.i(difficulty, text);
                c1.moveToNext();

        }*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            addVerbs(getApplicationContext(), "ptIrregular.txt", "irregularPt");
          /*  Cursor c1 = verbosDB.rawQuery("SELECT * FROM  irregularPt", null);
            int value = c1.getColumnIndex("verb");
            int value2 = c1.getColumnIndex("id");
            c1.moveToFirst();
            for (int i = 0; i < 348; i++) {
                text = c1.getString(value);
                int id = c1.getInt(2);
                Log.i(Integer.toString(id), text);
                c1.moveToNext();
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            addConjugations(getApplicationContext(), "imperativoAfirmativo.txt", "impAfirmativo");
            /*Cursor c1 = verbosDB.rawQuery("SELECT * FROM  impAfirmativo ", null);
            int value = c1.getColumnIndex("conjugation");
            int value2 = c1.getColumnIndex("id");
            c1.moveToFirst();
            for (int i = 0; i < 2088; i++) {
                text = c1.getString(value);
                String difficulty = c1.getString(value2);
                Log.i(difficulty, text);
                c1.moveToNext();
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            addConjugations(getApplicationContext(), "indicativoFuturoDoPresente.txt", "indiFuturoPresente");
           /* Cursor c1 = verbosDB.rawQuery("SELECT * FROM  indiFuturoPresente ", null);
            int value = c1.getColumnIndex("conjugation");
            int value2 = c1.getColumnIndex("id");
            c1.moveToFirst();
            for (int i = 0; i < 2088; i++) {
                text = c1.getString(value);
                String difficulty = c1.getString(value2);
                Log.i(difficulty, text);
                c1.moveToNext();
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            addConjugations(getApplicationContext(), "IndicativofuturoDopretérito.txt", "indiFuturoPreterito");
            /*Cursor c1 = verbosDB.rawQuery("SELECT * FROM  indiFuturoPreterito ", null);
            int value = c1.getColumnIndex("conjugation");
            int value2 = c1.getColumnIndex("id");
            c1.moveToFirst();
            for (int i = 0; i < 2088; i++) {
                text = c1.getString(value);
               String difficulty = c1.getString(value2);
               Log.i(difficulty, text);
                c1.moveToNext();
            } */
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            addConjugations(getApplicationContext(), "indicativoPresente.txt", "indiPresente");
            /*Cursor c1 = verbosDB.rawQuery("SELECT * FROM  indiPresente", null);
            int value = c1.getColumnIndex("conjugation");
            int value2 = c1.getColumnIndex("id");
            c1.moveToFirst();
            for (int i = 0; i < 2088; i++) {
                text = c1.getString(value);
                String difficulty = c1.getString(value2);
                Log.i(difficulty, text);
                c1.moveToNext();
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            addConjugations(getApplicationContext(), "indicativoPreteritoImperfeito.txt", "indiPreteritoImperfeito");
/*          Cursor c1 = verbosDB.rawQuery("SELECT * FROM  indiPreteritoImperfeito ", null);
            int value = c1.getColumnIndex("conjugation");
            int value2 = c1.getColumnIndex("id");
            c1.moveToFirst();
            for (int i = 0; i < 2088; i++) {
              //  text = c1.getString(value);
            //    String difficulty = c1.getString(value2);
               // Log.i(difficulty, text);
                c1.moveToNext();
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            addConjugations(getApplicationContext(), "indicativoPreteritoMaisQueperfeito.txt", "indiPreteritoMqf");
           /* Cursor c1 = verbosDB.rawQuery("SELECT * FROM  indiPreteritoMqf", null);
            int value = c1.getColumnIndex("conjugation");
            int value2 = c1.getColumnIndex("id");
            c1.moveToFirst();
            for (int i = 0; i < 2088; i++) {
                text = c1.getString(value);
                String difficulty = c1.getString(value2);
             //   Log.i(difficulty, text);
                c1.moveToNext();
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            addConjugations(getApplicationContext(), "indicativoPreteritoperfeito.txt", "indiPreteritoPerfeito");
            /*Cursor c1 = verbosDB.rawQuery("SELECT * FROM  indiPreteritoPerfeito ", null);
            int value = c1.getColumnIndex("conjugation");
            int value2 = c1.getColumnIndex("id");
            c1.moveToFirst();
            for (int i = 0; i < 2088; i++) {
                text = c1.getString(value);
                String difficulty = c1.getString(value2);
               // Log.i(difficulty, text);
                c1.moveToNext();
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            addConjugations(getApplicationContext(), "SubjuntivoFuturo.txt", "subFuturo");
            /*Cursor c1 = verbosDB.rawQuery("SELECT * FROM  subFuturo ", null);
            int value = c1.getColumnIndex("conjugation");
            int value2 = c1.getColumnIndex("id");
            c1.moveToFirst();
            for (int i = 0; i < 2088; i++) {
                text = c1.getString(value);
                String difficulty = c1.getString(value2);
                //Log.i(difficulty, text);
                c1.moveToNext();
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            addConjugations(getApplicationContext(), "SubjuntivoPresente.txt", "subPresente");
            /*Cursor c1 = verbosDB.rawQuery("SELECT * FROM  subPresente ", null);
            int value = c1.getColumnIndex("conjugation");
            int value2 = c1.getColumnIndex("id");
            c1.moveToFirst();
            for (int i = 0; i < 2088; i++) {
                text = c1.getString(value);
                String difficulty = c1.getString(value2);
                //Log.i(difficulty, text);
                c1.moveToNext();
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            addConjugations(getApplicationContext(), "SubjuntivoPreteritoImperfeito.txt", "subPreteritoImperfeito");
            Cursor c1 = verbosDB.rawQuery("SELECT * FROM  subPreteritoImperfeito ", null);
            int value = c1.getColumnIndex("conjugation");
            int value2 = c1.getColumnIndex("id");
            c1.moveToFirst();
            for (int i = 0; i < 2088; i++) {
                text = c1.getString(value);
                String difficulty = c1.getString(value2);
                // Log.i(difficulty, text);
                c1.moveToNext();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    void toogleAlphaTime(Button b , int number) {
        float vibility;
        vibility = b.getAlpha();
        if (vibility == 0.4f) {
            timeCounter++;
            b.setAlpha(1f);
            timeList.add(number);
            return;
        } else {
            timeCounter--;
            b.setAlpha(0.4f);
            timeList.remove(timeList.indexOf(number));
        }
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_pt);

        timeList =new ArrayList<>();


        initializeDB();

        final Intent intent = new Intent(this,DefineParams.class);
        time = false;
        timeCounter=0;


        Button iPresent = (Button) findViewById(R.id.iPresent);
        iPresent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             toogleAlphaTime((Button) v,0);

            }
        });
        Button iPreeritoMQP = (Button) findViewById(R.id.iPretéritoMQP);
        iPreeritoMQP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogleAlphaTime((Button) v,1);
            }
        });
        Button iPreteritoPerfeito = (Button) findViewById(R.id.iPreteritoPerfeito);
        iPreteritoPerfeito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogleAlphaTime((Button) v,2);
            }
        });
        Button iFuturoPresente = (Button) findViewById(R.id.iFuturoDopresente);
        iFuturoPresente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogleAlphaTime((Button) v,3);
            }
        });
        Button iFuturoPreterito = (Button) findViewById(R.id.iFuturodoPretérito);
        iFuturoPreterito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogleAlphaTime((Button) v,4);
            }
        });
        Button iPreteritoimperfeito = (Button) findViewById(R.id.iPreteritoImper);
        iPreteritoimperfeito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogleAlphaTime((Button) v,5);
            }
        });
        Button sPresente = (Button) findViewById(R.id.sPresente);
        sPresente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogleAlphaTime((Button) v,6);
            }
        });
        Button sFuturo = (Button) findViewById(R.id.sFuturo);
        sFuturo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogleAlphaTime((Button) v,7);
            }
        });
        Button sPreteritoimperfeito = (Button) findViewById(R.id.sPreteritoImperfeito);
        sPreteritoimperfeito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogleAlphaTime((Button) v,8);
            }
        });
        Button iAffirmativo = (Button) findViewById(R.id.iAfirmativo);
        iAffirmativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogleAlphaTime((Button) v,9);
            }
        });

        final CheckBox checkBox =(CheckBox) findViewById(R.id.checkBox);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //||regularityCounter==0||levelCounter==0
                if(timeCounter==0){
                    checkBox.setChecked(false);
                    Toast.makeText(EntryPT.this, "At least one  verb time must be defined ", Toast.LENGTH_SHORT).show();
                }
                    else {
                        startActivity(intent);
                }
            }
        });

    }
}
