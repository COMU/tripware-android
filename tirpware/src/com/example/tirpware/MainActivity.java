package com.example.tirpware;

import android.app.Activity;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
/**
 * Created by ulusoy on 21.10.2014.
 */
public class MainActivity extends Activity {

    private CheckBox cb1, cb2, cb3, cb4, cb5, cb6,cb7,cb8,cb9,cb10,cb11;
    private Button b1;
    ArrayList<String> stringArray = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        cb1 = (CheckBox) findViewById(R.id.checkBox1);
        cb2 = (CheckBox) findViewById(R.id.checkBox2);
        cb3 = (CheckBox) findViewById(R.id.checkBox3);
        cb4 = (CheckBox) findViewById(R.id.checkBox4);
        cb5 = (CheckBox) findViewById(R.id.checkBox5);
        cb6 = (CheckBox) findViewById(R.id.checkBox6);
        cb7 = (CheckBox) findViewById(R.id.checkBox7);


        cb8 = (CheckBox) findViewById(R.id.checkBox8);
        cb9 = (CheckBox) findViewById(R.id.checkBox9);
        cb10 = (CheckBox) findViewById(R.id.checkBox10);
        cb11 = (CheckBox) findViewById(R.id.checkBox11);
        b1 = (Button) findViewById(R.id.button1);

        //cb1 listener-action
        //Seçildiğinde ve seçim iptal edildiğinde bunu toast'la ekranda göster
        cb1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (cb1.isChecked()) {
                    Toast.makeText(getApplicationContext(),
                            cb1.getText().toString() + " Seçildi", 0).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            cb1.getText().toString() + " İptal Edildi.", 0)
                            .show();

                }

            }
        });

        //cb2 listener-action
        cb2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (cb2.isChecked()) {
                    Toast.makeText(getApplicationContext(),
                            cb2.getText().toString() + " Seçildi", 0).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            cb2.getText().toString() + " İptal Edildi.", 0)
                            .show();

                }

            }
        });

        //cb3 listener-action
        cb3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (cb3.isChecked()) {
                    Toast.makeText(getApplicationContext(),
                            cb3.getText().toString() + " Seçildi", 0).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            cb3.getText().toString() + " İptal Edildi.", 0)
                            .show();

                }

            }
        });

        //cb4 listener-action
        cb4.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (cb4.isChecked()) {
                    Toast.makeText(getApplicationContext(),
                            cb4.getText().toString() + " Seçildi", 0).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            cb4.getText().toString() + " İptal Edildi.", 0)
                            .show();

                }

            }
        });

        //cb5 listener-action
        cb5.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (cb5.isChecked()) {
                    Toast.makeText(getApplicationContext(),
                            cb5.getText().toString() + " Seçildi", 0).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            cb5.getText().toString() + " İptal Edildi.", 0)
                            .show();

                }

            }
        });

        //cb6 listener-action
        cb6.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (cb6.isChecked()) {
                    Toast.makeText(getApplicationContext(),
                            cb6.getText().toString() + " Seçildi", 0).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            cb6.getText().toString() + " İptal Edildi.", 0)
                            .show();

                }

            }
        });

        cb6.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (cb7.isChecked()) {
                    Toast.makeText(getApplicationContext(),
                            cb7.getText().toString() + " Seçildi", 0).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            cb7.getText().toString() + " İptal Edildi.", 0)
                            .show();

                }

            }
        });
        cb8.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (cb8.isChecked()) {
                    Toast.makeText(getApplicationContext(),
                            cb8.getText().toString() + " Seçildi", 0).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            cb8.getText().toString() + " İptal Edildi.", 0)
                            .show();

                }

            }
        });
        cb9.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (cb9.isChecked()) {
                    Toast.makeText(getApplicationContext(),
                            cb9.getText().toString() + " Seçildi", 0).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            cb9.getText().toString() + " İptal Edildi.", 0)
                            .show();

                }

            }
        });
        cb10.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (cb10.isChecked()) {
                    Toast.makeText(getApplicationContext(),
                            cb10.getText().toString() + " Seçildi", 0).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            cb10.getText().toString() + " İptal Edildi.", 0)
                            .show();

                }

            }
        });
        cb11.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (cb11.isChecked()) {
                    Toast.makeText(getApplicationContext(),
                            cb11.getText().toString() + " Seçildi", 0).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            cb11.getText().toString() + " İptal Edildi.", 0)
                            .show();

                }

            }
        });





        b1.setOnClickListener(new OnClickListener() {
            @Override


            public void onClick(View v) {
                //Gönder buttonuna tıklandığında tüm seçili olan CheckBox textlerini alıp toast mesajında gösterelim
                String text = "";
                if (cb1.isChecked()) {
                    text = text +" "+ cb1.getText().toString() + "\n";
                }
                if (cb2.isChecked()) {
                    text = text +" "+ cb2.getText().toString()+ "\n";
                }
                if (cb3.isChecked()) {
                    text = text +" "+ cb3.getText().toString()+ "\n";
                }
                if (cb4.isChecked()) {
                    text = text +" "+ cb4.getText().toString()+ "\n";
                }
                if (cb5.isChecked()) {
                    text = text +" "+ cb5.getText().toString()+ "\n";
                }
                if (cb6.isChecked()) {
                    text = text +" "+ cb6.getText().toString()+ "\n";
                }
                if (cb7.isChecked()) {
                    text = text +" "+ cb7.getText().toString()+ "\n";
                }
                if (cb8.isChecked()) {
                    text = text +" "+ cb8.getText().toString()+ "\n";
                }
                if (cb9.isChecked()) {
                    text = text +" "+ cb9.getText().toString()+ "\n";
                }
                if (cb10.isChecked()) {
                    text = text +" "+ cb10.getText().toString()+ "\n";
                }
                if (cb11.isChecked()) {
                    text = text +" "+ cb11.getText().toString()+ "\n";
                }





                Toast.makeText(getApplicationContext(), "Saklanan dizinler: \n" + text ,Toast.LENGTH_SHORT).show();

            }
        });
    }
}
