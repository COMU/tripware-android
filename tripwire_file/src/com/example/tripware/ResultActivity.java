package com.example.tripware;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class ResultActivity extends Activity {

    FileOperations file;
    String filename;
    String dosyasum;
    SummaryActivity summary;
    File parent = null;
    File file_master;
     File file_time;
    String master="master.txt";
    String bosluk="   ";
    String time="tarih.txt";



    String new_path="/sdcard";
    String time_path="/sdcard/Tripwire";
    String path1="/sdcard/Tripwire/master.txt";
    String path2="/sdcard/Tripwire/2015-03-18 01-18-32.txt";
    Scanner sc2;
    Scanner sc1;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        Bundle b = getIntent().getExtras();
        filename = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss'.txt'").format(new Date());
        File folder = new File(new_path + "/Tripwire");
        boolean success = true;
        if (!folder.exists()) {
            //Toast.makeText(MainActivity.this, "Directory Does Not Exist, Create It", Toast.LENGTH_SHORT).show();
            success = folder.mkdir();
        }
        if (success) {
            //Toast.makeText(MainActivity.this, "Directory Created", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(MainActivity.this, "Failed - Error", Toast.LENGTH_SHORT).show();
        }

        file_time = new File(time_path, this.time);
        file_master = new File(time_path, this.master);





        final String[] resultArr = b.getStringArray("selectedItems");


        ListView lv = (ListView) findViewById(R.id.outputList);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, resultArr);
        lv.setAdapter(adapter);

        Button btn_file = (Button) findViewById(R.id.button_save);
        final Button btn_geri = (Button) findViewById(R.id.button_geri);
        Button btn_reboot = (Button) findViewById(R.id.button_reboot);
        Button btn_master = (Button) findViewById(R.id.button_master);
        Button btn_diff = (Button) findViewById(R.id.button_differnt);

        btn_file.setOnClickListener(new Button.OnClickListener() {


            public void onClick(View v) {


                for (String value : resultArr) {
                    File f = new File(value);


                    try {
                        parent = f.getCanonicalFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {

                        dosyasum = summary.getMd5OfFile(parent.getCanonicalFile());
                        System.out.println(dosyasum);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    FileOutputStream fos;

                    byte[] filename = value.getBytes();
                    byte[] bos = bosluk.getBytes();
                    byte[] sum = dosyasum.getBytes();


                    try {
                        fos = new FileOutputStream(file_time);
                        fos.write(filename);
                        fos.write(bos);
                        fos.write(sum);

                        fos.flush();
                        fos.close();
                    } catch (FileNotFoundException e) {
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


        btn_master.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {


                for (String value : resultArr) {
                    File f = new File(value);


                    try {
                        parent = f.getCanonicalFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {

                        dosyasum = summary.getMd5OfFile(parent.getCanonicalFile());
                        System.out.println(dosyasum);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    FileOutputStream fos;

                    byte[] filename = value.getBytes();
                    byte[] bos = bosluk.getBytes();

                    byte[] sum = dosyasum.getBytes();


                    try {
                        fos = new FileOutputStream(file_master);
                        fos.write(filename);
                        fos.write(bos);
                        fos.write(sum);

                        fos.flush();
                        fos.close();
                    } catch (FileNotFoundException e) {
                        // handle exception
                    } catch (IOException e) {
                        // handle exception
                    }


                }
            }
        });


        btn_reboot.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                askReboot();
            }
        });


        btn_diff.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {


               
                try {
                     sc1 = new Scanner(file_master);
                     sc2=new Scanner(file_time);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                compareTextFiles(sc1,sc2);



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




    public void compareTextFiles(Scanner s1, Scanner s2) {

        int count1 = 0;
        int count2 = 0;

        // while we have lines left in both files, compare and
        // print the lines that don't match
        while (s1.hasNextLine() && s2.hasNextLine()) {
            String line1 = s1.nextLine().trim(); count1++;
            String line2 = s2.nextLine().trim(); count2++;
            if (!line1.equalsIgnoreCase(line2)) {
                System.out.println("Line " + count1 + " differs.");
                System.out.println("< " + line1);
                System.out.println("> " + line2);
                Toast.makeText(getApplicationContext(), "UYARI MESAJI:Satır " + count1 + " farklı. < "+ line1+ ">"+ line2+"", Toast.LENGTH_LONG).show();
            }
            else {
                System.out.println("Line " + count1 + " matches.");
                Toast.makeText(getApplicationContext(), "UYARI MESAJI:Satır " + count1 +"eşleşti ", Toast.LENGTH_LONG).show();
            }
        }

        // any leftover lines in file1 count as differences
        while (s1.hasNextLine()) {
            String line1 = s1.nextLine(); count1++;
            System.out.println("Line " + count1 + " differs.");
            System.out.println("< " + line1);
            System.out.println("> " );
            Toast.makeText(getApplicationContext(), "UYARI MESAJI:Satır " + count1 + " farklı. < "+ line1+ ">", Toast.LENGTH_LONG).show();
        }

        // any leftover lines in file2 count as differences
        while (s2.hasNextLine()) {
            String line2 = s2.nextLine(); count2++;
            System.out.println("Line " + count2 + " differs.");
            System.out.println("< ");
            System.out.println("> " + line2);
            Toast.makeText(getApplicationContext(), "UYARI MESAJI:Satır " + count2 + " farklı. < >"+ line2+"", Toast.LENGTH_LONG).show();

        }

    }















}
