package com.example.tripware;


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.io.*;
import java.util.ArrayList;

public class FileOperations extends Activity {
    String filename = "özet.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.file);



        FileOperations file=new FileOperations();
        Button button_geri2 = (Button) findViewById(R.id.button_geri2);
        Button button_create = (Button) findViewById(R.id.create_button);
        Button summary_create = (Button) findViewById(R.id.summary_file);


       summary_create.setOnClickListener(new Button.OnClickListener() {


                                             public void onClick(View v) {



                                                 Intent intent = new Intent(getApplicationContext(),
                                                       SummaryActivity.class);

                                                 startActivity(intent);


                                             }

                                         });


               button_geri2.setOnClickListener(new Button.OnClickListener() {


                   public void onClick(View v) {


                       Intent intent = new Intent(getApplicationContext(),
                               ResultActivity.class);

                       startActivity(intent);
                   }
               });
        button_create.setOnClickListener(new Button.OnClickListener() {


            public void onClick(View v) {
                File_create(filename);

                Context context = getApplicationContext();
                CharSequence text = "Dosya /sdcard içine oluşturuldu";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();


                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);
            }
        });









        //SDCARD ın içinde özet.txt adında bir dosya oluşturuyorum



    }
public void File_create(String filename){
    File file = new File(Environment.getExternalStorageDirectory(), filename);
    FileOutputStream fos;
    byte[] data = new String("data to write to file").getBytes();
    try {
        fos = new FileOutputStream(file);
        fos.write(data);
        fos.flush();
        fos.close();
    } catch (FileNotFoundException e) {
        // handle exception
    } catch (IOException e) {
        // handle exception
    }



}




}