package com.example.tripware;


import android.app.Activity;
import android.os.Bundle;

import java.io.*;

public class FileOperations extends Activity {

    String files_name="/sdcard/Tripwire";
    String save="filesname.txt";
    File files_save;
    static String path="/sdcard/Tripwire/save.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.file);



        files_save = new File(files_name, this.save);











    }








    public static void DosyadanOku() throws IOException {

        FileReader file = new FileReader(path);
        BufferedReader reader = new BufferedReader(file);
        String metin = reader.readLine();
        reader.close();
        System.out.println("Dosya Metni : " + metin);
    }


    public static void DosyadanTumSatirlariOku() throws IOException{

        FileReader file = new FileReader(path);
        BufferedReader reader = new BufferedReader(file);
        String satir;
        String metin = "";
        while((satir = reader.readLine()) != null){
            metin += satir + "\n";
        }
        System.out.println(metin);
    }

}










