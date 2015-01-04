package com.example.tripware;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class ResultActivity extends Activity {
    String summary="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);


        Bundle b = getIntent().getExtras();
     final ArrayList resultArr = b.getStringArrayList("");
        ListView lv = (ListView) findViewById(R.id.outputList);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, resultArr);


        lv.setAdapter(adapter);

        Button btn_file = (Button) findViewById(R.id.button_i);
        Button btn_geri = (Button) findViewById(R.id.button_geri);




        btn_file.setOnClickListener(new Button.OnClickListener() {


                                        public void onClick(View v) {




                                            Intent intent = new Intent(getApplicationContext(),
                                                    FileOperations.class);

                                            // Create a bundle object
                                            Bundle b = new Bundle();
                                            final ArrayList fileArr = b.getStringArrayList("");


                                            // Add the bundle to the intent.
                                            intent.putExtras(b);

                                            // start the ResultActivity
                                            startActivity(intent);



                                            startActivity(intent);




                                        }
                                    });


                btn_geri.setOnClickListener(new Button.OnClickListener() {


                    public void onClick(View v) {


                        Intent intent = new Intent(getApplicationContext(),
                                MainActivity.class);

                        startActivity(intent);


                    }
                });


            }


        }
