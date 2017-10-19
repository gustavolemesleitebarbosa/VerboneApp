package com.example.gustavo.coacroch;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ptMain extends AppCompatActivity {

    String time;
    String person;
    String verboConjugado;
    String verboInfinitivo;
    List<String> presentedVerbsList;
    int verbIndex;
    int verbTime;
    int verbPerson;
    Intent intent;
    SQLiteDatabase verbosDB;
    int roundsCount;
    int scoreCount;
    TextView score;
    TextView rounds;
    TextView timeText;
    TextView personText;
    TextView verbNameText;
    TextView timeLeft;
    CheckBox checkBox;
    String hint;
    String verbHint;
    TextView verbHintTxt ;
    TextView conjugationHinTxt ;
    EditText test;

    public void test(View view) {


    }

    public void round() {
        Random rnd = new Random();
        String type;
        int regOrIrrg;

        if (DefineParams.regularityList.size() == 1) {
            regOrIrrg = DefineParams.regularityList.get(0);
        } else {
            regOrIrrg = rnd.nextInt(2);
        }

        String radical;

        if (EntryPT.timeList.size() > 1) {
            int verbTimeIndex = rnd.nextInt(EntryPT.timeList.size());
            verbTime = EntryPT.timeList.get(verbTimeIndex);
        } else {
            verbTime = EntryPT.timeList.get(0);
        }
        //PARTE DOS regulares Under work!, o if.
        if (regOrIrrg == 0) {
            type = "regular";

            verbPerson = rnd.nextInt(6);
            verbIndex = rnd.nextInt(3820);

            if (DefineParams.levelList.size() == 1) {
                Cursor c1 = verbosDB.rawQuery("SELECT * FROM regularPt WHERE difficulty = " + "'" + DefineParams.levelList.get(0) + "'" + " ORDER BY RANDOM()", null);
                c1.moveToFirst();
                verboInfinitivo = c1.getString(0);
                presentedVerbsList.add(verboInfinitivo);
                verbNameText.setText(verboInfinitivo);
            } else if (DefineParams.levelList.size() == 2) {
                Cursor c1 = verbosDB.rawQuery("SELECT * FROM regularPt WHERE difficulty = " + "'" + DefineParams.levelList.get(0) + "'" + "OR difficulty = " + "'" + DefineParams.levelList.get(1) + "'" + "ORDER BY RANDOM()", null);
                c1.moveToFirst();
                verboInfinitivo = c1.getString(0);
                presentedVerbsList.add(verboInfinitivo);
                verbNameText.setText(verboInfinitivo);
            } else if (DefineParams.levelList.size() == 3) {
                Cursor c1 = verbosDB.rawQuery("SELECT * FROM regularPt WHERE difficulty = " + "'" + DefineParams.levelList.get(0) + "'" + "OR difficulty = " + "'" + DefineParams.levelList.get(1) + "'" + "OR difficulty = " + "'" + DefineParams.levelList.get(2) + "'" + " ORDER BY RANDOM()", null);
                c1.moveToFirst();
                verboInfinitivo = c1.getString(0);
                presentedVerbsList.add(verboInfinitivo);
                verbNameText.setText(verboInfinitivo);
            } else {
                Cursor c1 = verbosDB.rawQuery("SELECT * FROM regularPt  ORDER BY RANDOM()", null);
                c1.moveToFirst();
                verboInfinitivo = c1.getString(0);
                presentedVerbsList.add(verboInfinitivo);
                verbNameText.setText(verboInfinitivo);
            }

            radical = verboInfinitivo.substring(0, verboInfinitivo.length() - 2);
            String termination = verboInfinitivo.substring(verboInfinitivo.length() - 2);
            switch (verbTime) {
                case 0: {
                    time = "Presente do indicativo";
                    timeText.setText(time);

                    switch (verbPerson) {
                        case 0: {
                            person = "1 pessoa singular";
                            personText.setText(person);
                            verboConjugado = radical + "o";
                            verbHint = "comer";
                            hint = "como";
                        }
                        break;

                        case 1: {
                            person = "2 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "as";
                                verbHint = "falar";
                                hint = "falas";
                            } else {
                                verboConjugado = radical + "es";
                                verbHint = "comer";
                                hint = "comes";
                            }
                        }
                        break;
                        case 2: {
                            person = "3 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "a";
                                verbHint = "falar";
                                hint = "fala";
                            } else {
                                verboConjugado = radical + "e";
                                verbHint = "comer";
                                hint = "come";
                            }
                        }
                        break;

                        case 3: {
                            person = "1 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "amos";
                                verbHint = "falar";
                                hint = "falamos";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "emos";
                                verbHint = "comer";
                                hint = "comemos";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "imos";
                                verbHint = "partir";
                                hint = "partimos";

                            }
                        }
                        break;

                        case 4: {
                            person = "2 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "ais";
                                verbHint = "falar";
                                hint = "falais";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "eis";
                                verbHint = "comer";
                                hint = "comeis";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "is";
                                verbHint = "partir";
                                hint = "partis";
                            }
                        }
                        break;
                        case 5: {
                            person = "3 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "am";
                                verbHint = "falar";
                                hint = "falam";
                            } else {
                                verboConjugado = radical + "em";
                                verbHint = "comer";
                                hint = "comem";

                            }
                        }
                        break;
                    }
                }
                break;

                case 1: {
                    time = "Pretérito imperfeito do indicativo";
                    timeText.setText(time);
                    switch (verbPerson) {
                        case 0: {
                            person = "1 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "ava";
                                verbHint = "falar";
                                hint = "falava";
                            } else {
                                verboConjugado = radical + "ia";
                                verbHint = "comer";
                                hint = "comia";
                            }
                        }
                        break;

                        case 1: {
                            person = "2 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "avas";
                                verbHint = "falar";
                                hint = "falavas";
                            } else {
                                verboConjugado = radical + "ias";
                                verbHint = "comer";
                                hint = "comias";
                            }
                        }
                        break;
                        case 2: {
                            person = "3 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "ava";
                                verbHint = "falar";
                                hint = "falava";
                            } else {
                                verboConjugado = radical + "ia";
                                verbHint = "comer";
                                hint = "comia";
                            }
                        }
                        break;

                        case 3: {
                            person = "1 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "ávamos";
                                verbHint = "falar";
                                hint = "falávamos";
                            } else {
                                verboConjugado = radical + "íamos";
                                verbHint = "comer";
                                hint = "comíamos";
                            }
                        }
                        break;

                        case 4: {
                            person = "2 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "áveis";
                                verbHint = "falar";
                                hint = "faláveis";
                            } else {
                                verboConjugado = radical + "íeis";
                                verbHint = "comer";
                                hint = "comíeis";

                            }
                        }
                        break;
                        case 5: {
                            person = "3 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "avam";
                                verbHint = "falar";
                                hint = "falavam";
                            } else {
                                verboConjugado = radical + "iam";
                                verbHint = "comer";
                                hint = "comiam";

                            }
                        }
                        break;
                    }
                }
                break;

                case 2: {
                    time = "Pretérito perfeito do indicativo";
                    timeText.setText(time);
                    switch (verbPerson) {
                        case 0: {
                            person = "1 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "ei";
                                verbHint = "falar";
                                hint = "falei";
                            } else {
                                verboConjugado = radical + "i";
                                verbHint = "comer";
                                hint = "comi";
                            }
                        }
                        break;

                        case 1: {

                            person = "2 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "aste";
                                verbHint = "falar";
                                hint = "falaste";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "este";
                                verbHint = "comer";
                                hint = "comeste";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "iste";
                                verbHint = "partir";
                                hint = "partiste";
                            }

                        }
                        break;
                        case 2: {
                            person = "3 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "ais";
                                verbHint = "falar";
                                hint = "falais";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "eis";
                                verbHint = "comer";
                                hint = "comeu";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "iu";
                                verbHint = "partir";
                                hint = "partiu";
                            }

                        }
                        break;

                        case 3: {
                            person = "1 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "amos";
                                verbHint = "falar";
                                hint = "falamos";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "emos";
                                verbHint = "comer";
                                hint = "comemos";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "imos";
                                verbHint = "partir";
                                hint = "partimos";
                            }
                        }
                        break;

                        case 4: {
                            person = "2 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "astes";
                                verbHint = "falar";
                                hint = "falastes";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "estes";
                                verbHint = "comer";
                                hint = "comestes";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "istes";
                                verbHint = "partir";
                                hint = "partistes";
                            }

                        }
                        break;
                        case 5: {
                            person = "3 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "aram";
                                verbHint = "falar";
                                hint = "falaram";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "eram";
                                verbHint = "comer";
                                hint = "comeram";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "iram";
                                verbHint = "partir";
                                hint = "partiram";
                            }

                        }
                        break;
                    }
                }
                break;


                case 3: {
                    time = "Pretérito mais-que-perfeito do indicativo";
                    timeText.setText(time);
                    switch (verbPerson) {
                        case 0: {
                            person = "1 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "ara";
                                verbHint = "falar";
                                hint = "falara";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "era";
                                verbHint = "comer";
                                hint = "comera";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "ira";
                                verbHint = "partir";
                                hint = "partira";
                            }

                        }
                        break;

                        case 1: {
                            person = "2 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "aras";
                                verbHint = "falar";
                                hint = "falaras";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "eras";
                                verbHint = "comer";
                                hint = "comeras";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "iras";
                                verbHint = "partir";
                                hint = "partiras";
                            }

                        }
                        break;
                        case 2: {
                            person = "3 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "ara";
                                verbHint = "falar";
                                hint = "falara";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "era";
                                verbHint = "comer";
                                hint = "comera";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "ira";
                                verbHint = "partir";
                                hint = "partira";
                            }

                        }
                        break;

                        case 3: {
                            person = "1 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "áramos";
                                verbHint = "falar";
                                hint = "faláramos";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "êramos";
                                verbHint = "comer";
                                hint = "comêramos";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "íramos";
                                verbHint = "partir";
                                hint = "partíramos";
                            }
                        }
                        break;

                        case 4: {
                            person = "2 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "áreis";
                                verbHint = "falar";
                                hint = "faláreis";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "êreis";
                                verbHint = "comer";
                                hint = "comêreis";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "íreis";
                                verbHint = "partir";
                                hint = "partíreis";
                            }

                        }
                        break;
                        case 5: {
                            person = "3 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "aram";
                                verbHint = "falar";
                                hint = "falaram";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "eram";
                                verbHint = "comer";
                                hint = "comeram";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "iram";
                                verbHint = "partir";
                                hint = "partiram";
                            }
                        }
                        break;
                    }
                }
                break;

                case 4: {
                    time = "Futuro do presente do indicativo";
                    timeText.setText(time);
                    switch (verbPerson) {
                        case 0: {
                            person = "1 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "arei";
                                verbHint = "falar";
                                hint = "falarei";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "erei";
                                verbHint = "comer";
                                hint = "comerei";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "irei";
                                verbHint = "partir";
                                hint = "partirei";
                            }

                        }
                        break;

                        case 1: {
                            person = "2 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "arás";
                                verbHint = "falar";
                                hint = "falarás";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "erás";
                                verbHint = "comer";
                                hint = "comerás";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "irás";
                                verbHint = "partir";
                                hint = "partirás";
                            }
                        }
                        break;
                        case 2: {
                            person = "3 pessoa singular";
                            personText.setText(person);

                            if (termination.equals("ar")) {
                                verboConjugado = radical + "ará";
                                verbHint = "falar";
                                hint = "falará";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "erá";
                                verbHint = "comer";
                                hint = "comerá";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "irá";
                                verbHint = "partir";
                                hint = "partirá";
                            }
                        }
                        break;

                        case 3: {
                            person = "1 pessoa plural";
                            personText.setText(person);

                            if (termination.equals("ar")) {
                                verboConjugado = radical + "aremos";
                                verbHint = "falar";
                                hint = "falaremos";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "eremos";
                                verbHint = "comer";
                                hint = "comeremos";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "iremos";
                                verbHint = "partir";
                                hint = "partiremos";
                            }

                        }
                        break;

                        case 4: {
                            person = "2 pessoa plural";
                            personText.setText(person);

                            if (termination.equals("ar")) {
                                verboConjugado = radical + "areis";
                                verbHint = "falar";
                                hint = "falareis";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "ereis";
                                verbHint = "comer";
                                hint = "comereis";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "ireis";
                                verbHint = "partir";
                                hint = "partireis";
                            }

                        }
                        break;
                        case 5: {
                            person = "3 pessoa plural";
                            personText.setText(person);

                            if (termination.equals("ar")) {
                                verboConjugado = radical + "arão";
                                verbHint = "falar";
                                hint = "falarão";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "erão";
                                verbHint = "comer";
                                hint = "comerão";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "irão";
                                verbHint = "partir";
                                hint = "partirão";
                            }


                        }
                        break;
                    }
                }
                break;


                case 5: {
                    time = "Futuro do pretérito do indicativo";
                    timeText.setText(time);
                    switch (verbPerson) {
                        case 0: {
                            person = "1 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "aria";
                                verbHint = "falar";
                                hint = "falaria";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "eria";
                                verbHint = "comer";
                                hint = "comeria";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "iria";
                                verbHint = "partir";
                                hint = "partiria";
                            }
                        }
                        break;

                        case 1: {
                            person = "2 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "arias";
                                verbHint = "falar";
                                hint = "falarias";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "erias";
                                verbHint = "comer";
                                hint = "comerias";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "irias";
                                verbHint = "partir";
                                hint = "partirias";
                            }
                        }
                        break;
                        case 2: {
                            person = "3 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "aria";
                                verbHint = "falar";
                                hint = "falaria";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "eria";
                                verbHint = "comer";
                                hint = "comeria";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "iria";
                                verbHint = "partir";
                                hint = "partiria";
                            }
                        }
                        break;

                        case 3: {
                            person = "1 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "aríamos";
                                verbHint = "falar";
                                hint = "falaríamos";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "eríamos";
                                verbHint = "comer";
                                hint = "comeríamos";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "iríamos";
                                verbHint = "partir";
                                hint = "partiríamos";
                            }
                        }
                        break;

                        case 4: {
                            person = "2 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "aríeis";
                                verbHint = "falar";
                                hint = "falaríeis";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "eríeis";
                                verbHint = "comer";
                                hint = "comeríeis";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "iríeis";
                                verbHint = "partir";
                                hint = "partiríeis";
                            }
                        }
                        break;
                        case 5: {
                            person = "3 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "ariam";
                                verbHint = "falar";
                                hint = "falariam";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "eriam";
                                verbHint = "comer";
                                hint = "comeriam";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "iriam";
                                verbHint = "partir";
                                hint = "partiriam";
                            }
                        }
                        break;
                    }
                }
                break;


                case 6: {
                    time = "Presente do subjuntivo";
                    timeText.setText(time);
                    switch (verbPerson) {
                        case 0: {
                            person = "1 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "e";
                                verbHint = "falar";
                                hint = "fale";
                            } else {
                                verboConjugado = radical + "a";
                                verbHint = "comer";
                                hint = "coma";
                            }

                        }
                        break;

                        case 1: {
                            person = "2 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "es";
                                verbHint = "falar";
                                hint = "fales";
                            } else {
                                verboConjugado = radical + "as";
                                verbHint = "comer";
                                hint = "comas";
                            }
                        }
                        break;
                        case 2: {
                            person = "3 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "e";
                                verbHint = "falar";
                                hint = "fale";
                            } else {
                                verboConjugado = radical + "a";
                                verbHint = "comer";
                                hint = "coma";
                            }
                        }
                        break;

                        case 3: {
                            person = "1 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "emos";
                                verbHint = "falar";
                                hint = "falemos";
                            } else {
                                verboConjugado = radical + "amos";
                                verbHint = "comer";
                                hint = "comamos";

                            }

                        }
                        break;

                        case 4: {
                            person = "2 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "eis";
                                verbHint = "falar";
                                hint = "faleis";
                            } else {
                                verboConjugado = radical + "ais";
                                verbHint = "comer";
                                hint = "comais";
                            }
                        }
                        break;
                        case 5: {
                            person = "3 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "em";
                                verbHint = "falar";
                                hint = "falem";
                            } else {
                                verboConjugado = radical + "am";
                                verbHint = "comer";
                                hint = "comam";
                            }
                        }
                        break;
                    }
                }
                break;


                case 7: {
                    time = "Pretérito imperfeito do subjuntivo";
                    timeText.setText(time);
                    switch (verbPerson) {
                        case 0: {
                            person = "1 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "asse";
                                verbHint = "falar";
                                hint = "falasse";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "esse";
                                verbHint = "comer";
                                hint = "comesse";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "isse";
                                verbHint = "partir";
                                hint = "partisse";
                            }
                        }
                        break;

                        case 1: {
                            person = "2 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "asses";
                                verbHint = "falar";
                                hint = "falasses";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "esses";
                                verbHint = "comer";
                                hint = "comesses";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "isses";
                                verbHint = "partir";
                                hint = "partisses";
                            }
                        }
                        break;
                        case 2: {
                            person = "3 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "asse";
                                verbHint = "falar";
                                hint = "falasse";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "esse";
                                verbHint = "comer";
                                hint = "comesse";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "isse";
                                verbHint = "partir";
                                hint = "partisse";
                            }
                        }
                        break;

                        case 3: {
                            person = "1 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "ássemos";
                                verbHint = "falar";
                                hint = "falássemos";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "êssemos";
                                verbHint = "comer";
                                hint = "comêssemos";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "íssemos";
                                verbHint = "partir";
                                hint = "partíssemos";
                            }
                        }
                        break;

                        case 4: {
                            person = "2 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "ásseis";
                                verbHint = "falar";
                                hint = "falásseis";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "êsseis";
                                verbHint = "comer";
                                hint = "comêsseis";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "ísseis";
                                verbHint = "partir";
                                hint = "partísseis";
                            }
                        }
                        break;
                        case 5: {
                            person = "3 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "assem";
                                verbHint = "falar";
                                hint = "falassem";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "essem";
                                verbHint = "comer";
                                hint = "comessem";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "issem";
                                verbHint = "partir";
                                hint = "partissem";
                            }
                        }
                        break;
                    }
                }
                break;


                case 8: {
                    time = "Futuro do subjuntivo";
                    timeText.setText(time);
                    switch (verbPerson) {
                        case 0: {
                            person = "1 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "ar";
                                verbHint = "falar";
                                hint = "falar";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "er";
                                verbHint = "comer";
                                hint = "comer";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "ir";
                                verbHint = "partir";
                                hint = "partir";
                            }
                        }
                        break;

                        case 1: {
                            person = "2 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "ares";
                                verbHint = "falar";
                                hint = "falares";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "eres";
                                verbHint = "comer";
                                hint = "comeres";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "ires";
                                verbHint = "partir";
                                hint = "partires";
                            }
                        }
                        break;
                        case 2: {
                            person = "3 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "ar";
                                verbHint = "falar";
                                hint = "falar";

                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "er";
                                verbHint = "comer";
                                hint = "comer";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "ir";
                                verbHint = "partir";
                                hint = "partir";
                            }
                        }
                        break;

                        case 3: {
                            person = "1 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "armos";
                                verbHint = "falar";
                                hint = "falarmos";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "ermos";
                                verbHint = "comer";
                                hint = "comermos";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "irmos";
                                verbHint = "partir";
                                hint = "partirmos";
                            }
                        }
                        break;

                        case 4: {
                            person = "2 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "ardes";
                                verbHint = "falar";
                                hint = "falardes";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "erdes";
                                verbHint = "comer";
                                hint = "comerdes";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "irdes";
                                verbHint = "partir";
                                hint = "partirdes";
                            }
                        }
                        break;
                        case 5: {
                            person = "3 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "arem";
                                verbHint = "falar";
                                hint = "falarem";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "erem";
                                verbHint = "comer";
                                hint = "comerem";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "irem";
                                verbHint = "partir";
                                hint = "partirem";
                            }


                        }
                        break;
                    }
                }
                break;


                case 9: {
                    time = "Imperativo afirmativo";
                    timeText.setText(time);
                    switch (verbPerson) {
                        case 0: {
                            round();
                            return;
                        }


                        case 1: {
                            person = "2 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "a";
                                verbHint = "falar";
                                hint = "fala";
                            } else {
                                verboConjugado = radical + "e";
                                verbHint = "comer";
                                hint = "come";
                            }
                        }
                        break;
                        case 2: {
                            person = "3 pessoa singular";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "e";
                                verbHint = "falar";
                                hint = "fale";
                            } else {
                                verboConjugado = radical + "a";
                                verbHint = "comer";
                                hint = "coma";
                            }
                        }
                        break;

                        case 3: {
                            person = "1 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "emos";
                                verbHint = "falar";
                                hint = "falemos";
                            } else {
                                verboConjugado = radical + "amos";
                                verbHint = "comer";
                                hint = "comamos";
                            }
                        }
                        break;

                        case 4: {
                            person = "2 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "ai";
                                verbHint = "falar";
                                hint = "falai";
                            } else if (termination.equals("er")) {
                                verboConjugado = radical + "ei";
                                verbHint = "comer";
                                hint = "comei";
                            } else if (termination.equals("ir")) {
                                verboConjugado = radical + "i";
                            }


                        }
                        break;
                        case 5: {
                            person = "3 pessoa plural";
                            personText.setText(person);
                            if (termination.equals("ar")) {
                                verboConjugado = radical + "em";
                                verbHint = "falar";
                                hint = "falem";
                            } else {
                                verboConjugado = radical + "am";
                                verbHint = "comer";
                                hint = "comam";
                            }
                        }
                        break;
                    }
                }

                break;


            }

        } else {

            Random rnd6 = new Random();


            if (DefineParams.levelList.size() == 1) {
                Cursor c1 = verbosDB.rawQuery("SELECT * FROM irregularPt WHERE difficulty = " + "'" + DefineParams.levelList.get(0) + "'" + " ORDER BY RANDOM()", null);
                c1.moveToFirst();
                verboInfinitivo = c1.getString(0);
                verbNameText.setText(verboInfinitivo);
                presentedVerbsList.add(verboInfinitivo);
                verbIndex = c1.getInt(2);
            } else if (DefineParams.levelList.size() == 2) {
                Cursor c1 = verbosDB.rawQuery("SELECT * FROM irregularPt WHERE difficulty = " + "'" + DefineParams.levelList.get(0) + "'" + "OR difficulty = " + "'" + DefineParams.levelList.get(1) + "'" + "ORDER BY RANDOM()", null);
                c1.moveToFirst();
                verboInfinitivo = c1.getString(0);
                verbNameText.setText(verboInfinitivo);
                presentedVerbsList.add(verboInfinitivo);
                verbIndex = c1.getInt(2);
            } else if (DefineParams.levelList.size() == 3) {
                Cursor c1 = verbosDB.rawQuery("SELECT * FROM irregularPt WHERE difficulty = " + "'" + DefineParams.levelList.get(0) + "'" + "OR difficulty = " + "'" + DefineParams.levelList.get(1) + "'" + "OR difficulty = " + "'" + DefineParams.levelList.get(2) + "'" + " ORDER BY RANDOM()", null);
                c1.moveToFirst();
                verboInfinitivo = c1.getString(0);
                verbNameText.setText(verboInfinitivo);
                presentedVerbsList.add(verboInfinitivo);
                verbIndex = c1.getInt(2);
            } else {
                Cursor c1 = verbosDB.rawQuery("SELECT * FROM irregularPt  ORDER BY RANDOM()", null);
                c1.moveToFirst();
                verboInfinitivo = c1.getString(0);
                verbNameText.setText(verboInfinitivo);
                presentedVerbsList.add(verboInfinitivo);
                verbIndex = c1.getInt(2);

            }


            int verbPerson = rnd6.nextInt(5);
            type = "irregular";
            switch (verbTime) {
                case 0: {

                    time = "Presente do indicativo";
                    timeText.setText(time);
                    int value = (verbIndex - 1) * 6 + verbPerson + 1;

                    Cursor c1 = verbosDB.rawQuery("SELECT * FROM indiPresente WHERE id = " + value, null);
                    c1.moveToFirst();
                    int columnIndex = c1.getColumnIndex("conjugation");
                    verboConjugado = c1.getString(0);

                    switch (verbPerson) {
                        case 0: {
                            person = "1 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "como";
                        }
                        break;

                        case 1: {
                            person = "2 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comes";
                        }
                        break;
                        case 2: {
                            person = "3 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "come";
                        }
                        break;

                        case 3: {
                            person = "1 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comemos";
                        }
                        break;

                        case 4: {
                            person = "2 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comeis";
                        }
                        break;
                        case 5: {
                            person = "3 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comem";
                        }
                        break;
                    }
                }
                break;
                case 1: {
                    time = "Pretérito imperfeito do indicativo";
                    timeText.setText(time);
                    int value = (verbIndex - 1) * 6 + verbPerson + 1;

                    Cursor c1 = verbosDB.rawQuery("SELECT * FROM indiPreteritoImperfeito WHERE id = " + value, null);
                    c1.moveToFirst();
                    int columnIndex = c1.getColumnIndex("conjugation");
                    verboConjugado = c1.getString(0);

                    switch (verbPerson) {
                        case 0: {
                            person = "1 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comia";
                        }
                        break;

                        case 1: {
                            person = "2 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comias";
                        }
                        break;
                        case 2: {
                            person = "3 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comia";
                        }
                        break;

                        case 3: {
                            person = "1 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comíamos";
                        }
                        break;

                        case 4: {
                            person = "2 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comíeis";
                        }
                        break;
                        case 5: {
                            person = "3 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comiam";
                        }
                        break;
                    }
                }
                break;
                case 2: {
                    time = "Pretérito perfeito do indicativo";
                    timeText.setText(time);
                    int value = (verbIndex - 1) * 6 + verbPerson + 1;

                    Cursor c1 = verbosDB.rawQuery("SELECT * FROM indiPreteritoPerfeito WHERE id = " + value, null);
                    c1.moveToFirst();
                    int columnIndex = c1.getColumnIndex("conjugation");
                    verboConjugado = c1.getString(0);

                    switch (verbPerson) {
                        case 0: {
                            person = "1 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comi";
                        }
                        break;

                        case 1: {
                            person = "2 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comeste";
                        }
                        break;
                        case 2: {
                            person = "3 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comeu";
                        }
                        break;

                        case 3: {
                            person = "1 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comemos";
                        }
                        break;

                        case 4: {
                            person = "2 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comestes";
                        }
                        break;
                        case 5: {
                            person = "3 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comeram";
                        }
                        break;
                    }
                }
                break;

                case 3: {
                    time = "Pretérito mais-que-perfeito do indicativo";
                    timeText.setText(time);
                    int value = (verbIndex - 1) * 6 + verbPerson + 1;

                    Cursor c1 = verbosDB.rawQuery("SELECT * FROM indiPreteritoMqf WHERE id = " + value, null);
                    c1.moveToFirst();
                    int columnIndex = c1.getColumnIndex("conjugation");
                    verboConjugado = c1.getString(0);


                    switch (verbPerson) {
                        case 0: {
                            person = "1 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comera";
                        }
                        break;

                        case 1: {
                            person = "2 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comeras";

                        }
                        break;
                        case 2: {
                            person = "3 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comera";
                        }
                        break;

                        case 3: {
                            person = "1 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comêramos";
                        }
                        break;

                        case 4: {
                            person = "2 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comêreis";
                        }
                        break;
                        case 5: {
                            person = "3 pessoa plural";
                            verbHint = "comer";
                            hint = "comeram";
                        }
                        break;
                    }
                }
                break;

                case 4: {
                    time = "Futuro do presente do indicativo";
                    timeText.setText(time);
                    int value = (verbIndex - 1) * 6 + verbPerson + 1;
                    Cursor c1 = verbosDB.rawQuery("SELECT * FROM indiFuturoPresente WHERE id = " + value, null);
                    c1.moveToFirst();
                    int columnIndex = c1.getColumnIndex("conjugation");
                    verboConjugado = c1.getString(0);

                    switch (verbPerson) {
                        case 0: {
                            person = "1 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comerei";
                        }
                        break;

                        case 1: {
                            person = "2 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comerás";
                        }
                        break;
                        case 2: {
                            person = "3 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comerá";
                        }
                        break;

                        case 3: {
                            person = "1 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comeremos";
                        }
                        break;

                        case 4: {
                            person = "2 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comereis";
                        }
                        break;
                        case 5: {
                            person = "3 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comerão";
                        }
                        break;
                    }
                }
                break;
                case 5: {


                    time = "Futuro do pretérito do indicativo";
                    timeText.setText(time);
                    int value = (verbIndex - 1) * 6 + verbPerson + 1;
                    Cursor c1 = verbosDB.rawQuery("SELECT * FROM indiFuturoPreterito WHERE id = " + value, null);
                    c1.moveToFirst();
                    int columnIndex = c1.getColumnIndex("0");
                    verboConjugado = c1.getString(0);

                    switch (verbPerson) {
                        case 0: {
                            person = "1 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comeria";
                        }
                        break;

                        case 1: {
                            person = "2 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comerias";
                        }
                        break;
                        case 2: {
                            person = "3 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comeria";
                        }
                        break;

                        case 3: {
                            person = "1 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comeríamos";
                        }
                        break;

                        case 4: {
                            person = "2 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comeríeis";
                        }
                        break;
                        case 5: {
                            person = "3 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comereiam";
                        }
                        break;
                    }
                }
                break;

                case 6: {
                    time = "Presente do subjuntivo";

                    timeText.setText(time);
                    int value = (verbIndex - 1) * 6 + verbPerson + 1;

                    Cursor c1 = verbosDB.rawQuery("SELECT * FROM subPresente WHERE id = " + value, null);
                    c1.moveToFirst();
                    int columnIndex = c1.getColumnIndex("conjugation");
                    verboConjugado = c1.getString(0);

                    switch (verbPerson) {
                        case 0: {
                            person = "1 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "coma";
                        }
                        break;

                        case 1: {
                            person = "2 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comas";
                        }
                        break;
                        case 2: {
                            person = "3 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "coma";
                        }
                        break;

                        case 3: {
                            person = "1 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comamos";
                        }
                        break;

                        case 4: {
                            person = "2 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comais";
                        }
                        break;
                        case 5: {
                            person = "3 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comam";
                        }
                        break;
                    }
                }
                break;
                case 7: {
                    time = "Pretérito imperfeito do subjuntivo ";
                    timeText.setText(time);

                    int value = (verbIndex - 1) * 6 + verbPerson + 1;

                    Cursor c1 = verbosDB.rawQuery("SELECT * FROM subPreteritoImperfeito WHERE id = " + value, null);
                    c1.moveToFirst();
                    int columnIndex = c1.getColumnIndex("conjugation");
                    verboConjugado = c1.getString(0);

                    switch (verbPerson) {
                        case 0: {
                            person = "1 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comesse";
                        }
                        break;

                        case 1: {
                            person = "2 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comesses";
                        }
                        break;
                        case 2: {
                            person = "3 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comesse";
                        }
                        break;

                        case 3: {
                            person = "1 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comêssemos";
                        }
                        break;

                        case 4: {
                            person = "2 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comêsseis";
                        }
                        break;
                        case 5: {
                            person = "3 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comessem";
                        }
                        break;
                    }
                }
                break;

                case 8: {
                    time = "Futuro do subjuntivo";
                    timeText.setText(time);

                    int value = (verbIndex - 1) * 6 + verbPerson + 1;

                    Cursor c1 = verbosDB.rawQuery("SELECT * FROM subFuturo WHERE  id = " + value, null);
                    c1.moveToFirst();
                    int columnIndex = c1.getColumnIndex("conjugation");
                    verboConjugado = c1.getString(0);

                    switch (verbPerson) {
                        case 0: {
                            person = "1 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comer";
                        }
                        break;

                        case 1: {
                            person = "2 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comeres";
                        }
                        break;
                        case 2: {
                            person = "3 pessoa singular";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comer";
                        }
                        break;

                        case 3: {
                            person = "1 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comermos";
                        }
                        break;

                        case 4: {
                            person = "2 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comerdes";
                        }
                        break;
                        case 5: {
                            person = "3 pessoa plural";
                            personText.setText(person);
                            verbHint = "comer";
                            hint = "comerem";
                        }
                        break;
                    }
                }
                break;


                case 9: {
                    time = "imperativo afirmativo";
                    timeText.setText(time);

                    int value = (verbIndex - 1) * 6 + verbPerson + 1;

                    Cursor c1 = verbosDB.rawQuery("SELECT * FROM impAfirmativo WHERE id = " + value, null);
                    c1.moveToFirst();
                    int columnIndex = c1.getColumnIndex("conjugation");
                    verboConjugado = c1.getString(0);

                    switch (verbPerson) {
                        case 0: {
                            round();
                            return;
                        }


                        case 1: {
                            person = "2 pessoa singular";
                            personText.setText(person);
                            verbHint="comer";
                            hint="come";

                        }
                        break;
                        case 2: {
                            person = "3 pessoa singular";
                            personText.setText(person);
                            verbHint="comer";
                            hint="coma";

                        }
                        break;

                        case 3: {
                            person = "1 pessoa plural";
                            personText.setText(person);
                            verbHint="comer";
                            hint="comamos";

                        }
                        break;

                        case 4: {
                            person = "2 pessoa plural";
                            personText.setText(person);
                            verbHint="comer";
                            hint="comei";
                        }
                        break;
                        case 5: {
                            person = "3 pessoa plural";
                            personText.setText(person);
                            verbHint="comer";
                            hint="comam";
                        }
                        break;
                    }
                }
                break;
            }
        }

        verbHintTxt.setText(verbHint.toString());
        conjugationHinTxt.setText(hint);

        Toast.makeText(this, verboConjugado + " " + time + " " + person, Toast.LENGTH_SHORT).show();
        Log.i("O que rolou ", Integer.toString(verbIndex) + Integer.toString(verbTime) + " " + Integer.toString(verbPerson) + " " + verboInfinitivo + " " + verboConjugado + " " + time + " " + person);
    }



    public boolean onKey(EditText  view, int i, KeyEvent keyEvent) {
        if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            roundsCount++;

            if (verboConjugado.equals(view.getText().toString())) {
                scoreCount++;
                score.setText(Integer.toString(scoreCount));
                rounds.setText(Integer.toString(roundsCount));
                checkBox.setChecked(false);
                round();

            } else {
                round();
                checkBox.setChecked(false);
                rounds.setText(Integer.toString(roundsCount));
            }
        }

        return false;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pt_main);

        roundsCount = 0;
        scoreCount = 0;
        presentedVerbsList = new ArrayList<String>();
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, R.id.showedVerbs);
        intent = new Intent(this, DefineParams.class);
        verbosDB = EntryPT.verbosDB;
        timeText = (TextView) findViewById(R.id.verbTime);
        personText = (TextView) findViewById(R.id.verbPerson);
        verbNameText = (TextView) findViewById(R.id.verbName);
        score = (TextView) findViewById(R.id.score);
        rounds = (TextView) findViewById(R.id.rounds);
        timeLeft = (TextView) findViewById(R.id.timeLeft);
        verbHintTxt = (TextView) findViewById(R.id.selectedVerbHint);
        conjugationHinTxt = (TextView) findViewById(R.id.selectedConjugationHint);
        checkBox = (CheckBox) findViewById(R.id.checkBox2);
        test = (EditText) findViewById(R.id.conjugationAttempt);
        Button playAgain = (Button) findViewById(R.id.playAgain);
        Button backToSelection = (Button) findViewById(R.id.backToSelection);
        score.setText(Integer.toString(scoreCount));
        rounds.setText(Integer.toString(roundsCount));
        round();


        test.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                EditText v1 = (EditText) v;
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    roundsCount++;

                    if (verboConjugado.equals(v1.getText().toString())) {
                        scoreCount++;
                        score.setText(Integer.toString(scoreCount));
                        rounds.setText(Integer.toString(roundsCount));
                        checkBox.setChecked(false);
                        round();

                    } else {
                        round();
                        checkBox.setChecked(false);
                        rounds.setText(Integer.toString(roundsCount));
                    }
                }

                return false;
            }
        });


        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent restartIntent = getIntent();
                finish();
                startActivity(restartIntent);
            }
        });

        backToSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });






        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundsCount++;

                if (verboConjugado.equals(test.getText().toString())) {
                    scoreCount++;
                    score.setText(Integer.toString(scoreCount));
                    rounds.setText(Integer.toString(roundsCount));
                    checkBox.setChecked(false);
                    round();

                } else {
                    round();
                    checkBox.setChecked(false);
                    rounds.setText(Integer.toString(roundsCount));
                }
            }
        });


        new CountDownTimer(DefineParams.secondsLeft * 1000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {

                long seconds = millisUntilFinished / 1000;

                String displayedText = "";
                Double result = Math.floor(seconds / 60);
                int intResult = result.intValue();
                displayedText = Integer.toString(intResult);
                long result2 = seconds % 60;

                String displayedText2 = Long.toString(result2);
                if (result2 < 10) {
                    displayedText2 = "0" + displayedText2;
                }
                displayedText = displayedText + ":" + displayedText2;

                timeLeft.setText(displayedText);
            }

            @Override
            public void onFinish() {
                roundsCount = 0;
                scoreCount = 0;
                score.setText(Integer.toString(scoreCount));
                rounds.setText(Integer.toString(roundsCount));
                return;

                // mediaPlayer.start();
            }
        }.start();


    }

    @Override
    public void onBackPressed() {

        startActivity(intent);

    }
}
