package com.example.tripware;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileOperations extends Activity {

    private static final String TAG = "";
    String out;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.file);
        out= new SimpleDateFormat("yyyy-MM-dd hh-mm-ss'.txt'").format(new Date());

        isExternalStorageReadable();
        isExternalStorageWritable();
        MainActivity file_list = new MainActivity();


        Button button_create = (Button) findViewById(R.id.create_button);
        Button button_write = (Button) findViewById(R.id.write_button);
        File file[] = Environment.getExternalStorageDirectory().listFiles();


        for (File f : file)
        {
            if (f.isDirectory()) {

                Log.i("Name", f.getPath() + "");
            }
        }
        String dirName= "mnt/sdcard/";

        File dir = new File(dirName);

        File[] files = (new File(dirName)).listFiles();

        // This filter only returns directories
        FileFilter dirFilter = new FileFilter() {
            public boolean accept(File dir) {
                return dir.isDirectory();
            }
        };

        files = dir.listFiles(dirFilter);

        for (int i=0; i<files.length; i++) {
            if(files[i].getAbsolutePath().contains("tempfolder"))
                System.out.println("directory path : " + files[i].getAbsolutePath());
        }


        button_write.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {


            }
        });


        //SDCARD ın içinde özet.txt adında bir dosya oluşturuyorum


        button_create.setOnClickListener(new Button.OnClickListener() {



            public void onClick(View v) {

                File_create(out);
                Context context = getApplicationContext();
                CharSequence text = "Dosya /sdcard içine oluşturuldu";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();




                Intent intent = new Intent(getApplicationContext(), MainActivity.class);


                startActivity(intent);
            }
        });


    }


    public void File_create(String filename) {
        File file = new File(Environment.getExternalStorageDirectory(), this.out);
        String yeni="";

        FileOutputStream fos;
        byte[] data = yeni.getBytes();
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

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


}





