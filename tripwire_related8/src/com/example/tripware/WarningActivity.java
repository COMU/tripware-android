package com.example.tripware;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ulusoyy on 24.04.2015.
 */
public class WarningActivity extends ListActivity {
    ArrayAdapter adapter;
    String kritik="Kritik.txt";
    String kritik_path="/sdcard/Tripwire";
    String normal="Normal.txt";
    String normal_path="/sdcard/Tripwire";
    File file_kritik;
    File file_normal;
    int Position;
    BufferedWriter writer1 = null;
    String kelime;
    int konum1=0;
    int konum2=1;
    FileOutputStream fos;
    FileOutputStream fos2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.warning);


        file_kritik = new File(kritik_path,this.kritik);
        try {
            file_kritik.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        file_normal = new File(normal_path, this.normal);
        try {
            file_normal.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] liste = new String[]{"Kritik", "Normal"};


        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, liste);
        setListAdapter(adapter);
        TextView selection = (TextView) findViewById(R.id.selection);
        final ListView listView = getListView();
        listView.setItemsCanFocus(false);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        Button btn_kaydet = (Button) findViewById(R.id.button);

        btn_kaydet.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {

                if (Position == konum1) {
                    byte[] veri1 = kelime.getBytes();
                    try {
                        fos = new FileOutputStream(file_kritik);
                        fos.write(veri1);
                        fos.flush();
                        fos.close();
                    } catch (FileNotFoundException e) {
                    } catch (IOException e) {


                    }
                }
                else{


                        byte[] veri2 = kelime.getBytes();
                        try {
                            fos2 = new FileOutputStream(file_normal);
                            fos2.write(veri2);
                            fos2.flush();
                            fos2.close();
                        } catch (FileNotFoundException e) {
                        } catch (IOException e) {


                        }
                }



        }










        });
    }


    protected void onListItemClick(ListView listView, View view, int position, long id)
    {
        super.onListItemClick(listView, view, position, id);


        Object obj = this.getListAdapter().getItem(position);

         kelime = obj.toString();
        Position=position;
        Toast.makeText(this, "Seçiminiz : " + kelime, Toast.LENGTH_LONG).show();
        System.out.println(position);


        }

    }







