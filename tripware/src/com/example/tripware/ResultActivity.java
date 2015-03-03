package com.example.tripware;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ResultActivity extends Activity {

    FileOperations file;
    String out;
    String filename;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        Bundle b = getIntent().getExtras();
        filename= new SimpleDateFormat("yyyy-MM-dd hh-mm-ss'.txt'").format(new Date());

        final File file = new File(Environment.getExternalStorageDirectory(), this.filename);

        final String[] resultArr = b.getStringArray("selectedItems");



        ListView lv = (ListView) findViewById(R.id.outputList);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, resultArr);
        lv.setAdapter(adapter);

        Button btn_file = (Button) findViewById(R.id.button_save);
        final Button btn_geri = (Button) findViewById(R.id.button_geri);
         Button btn_reboot = (Button) findViewById(R.id.button_reboot);

        btn_file.setOnClickListener(new Button.OnClickListener() {




            public void onClick(View v) {



                for (String value : resultArr) {
                    String summary = SummaryActivity.md5(value).toString();



                    FileOutputStream fos;
                    FileOutputStream fos1;
                    byte[] filename = value.getBytes();
                    byte[] sum = summary.getBytes();


                    try {
                        fos = new FileOutputStream(file);
                        fos.write(filename);
                        fos.write(sum);

                        fos.flush();
                        fos.close();
                    }

                    catch (FileNotFoundException e) {
                        // handle exception
                    } catch (IOException e) {
                        // handle exception
                    }







                }


                }



        });


        btn_geri.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(intent);
            }
        });



        btn_reboot.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
               askReboot();
            }
        });




    }





    protected void askReboot()
    {
        /* Ask if user wants to reboot */
        new AlertDialog.Builder(this)
                .setMessage("Cihazınızı yeniden başlatmak istiyormusunuz?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        execCommandAsRoot("reboot");
                    }
                })
                .setNegativeButton("Hayır", null)
                .create().show();
    }
    public boolean execCommandAsRoot(String path)
    {
        try {
            String[] cmd = {"su","-c",path};
            Process p = Runtime.getRuntime().exec(cmd);
            int exitCode = p.waitFor();

            return (exitCode == 0);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }


}
