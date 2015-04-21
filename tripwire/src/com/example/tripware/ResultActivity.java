package com.example.tripware;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class ResultActivity extends Activity {

    FileOperations file;
    String filename;
    String dosyasum;
    SummaryActivity summary;
    File parent = null;
    File file_master;
    File file_time;
    String master = "Master.txt";
    String bosluk = "   ";
    String time = "Kontrol.txt";

    String new_path = "/sdcard";
    String time_path = "/sdcard/Tripwire";
   
    Scanner sc2;
    Scanner sc1;

    static String path = "/sdcard/Tripwire/save.txt";
    String files_name = "/sdcard/Tripwire";
    String save = "Save.txt";
    File files_save;
     ArrayList<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        Bundle b = getIntent().getExtras();


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
        files_save = new File(files_name, this.save);


        final String[] resultArr = b.getStringArray("selectedItems");

        final String save[] = new String[0];


        ListView lv = (ListView) findViewById(R.id.outputList);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, resultArr);
        lv.setAdapter(adapter);

        Button btn_kontrol = (Button) findViewById(R.id.button_save);
        final Button btn_geri = (Button) findViewById(R.id.button_geri);
        Button btn_reboot = (Button) findViewById(R.id.button_reboot);
        Button btn_master = (Button) findViewById(R.id.button_master);


        btn_kontrol.setOnClickListener(new Button.OnClickListener() {


            public void onClick(View v) {

                Scanner s = null;
                try {
                    s = new Scanner(new File("/sdcard/Tripwire/save.txt"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                          }
                list = new ArrayList<String>();
                while (s.hasNext()){
                    list.add(s.next());
                                    }
                s.close();
                System.out.println(list);

                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(file_time, true));
                } catch (IOException e) {
                    e.printStackTrace();
                }



                for (String value : list) {
                    System.out.print(value);

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

                    try {
                        writer.write(value);
                         writer.write(bosluk);
                        writer.write(dosyasum);
                        writer.newLine();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                try {
                    sc1 = new Scanner(file_master);
                    sc2 = new Scanner(file_time);
                    } catch (FileNotFoundException e) {
                          e.printStackTrace();
                        }
                        compareTextFiles(sc1, sc2);



            }

        });


        btn_geri.setOnClickListener(new Button.OnClickListener()

        {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(intent);

            }

        });


        btn_master.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                BufferedWriter writer1 = null;
                BufferedWriter writer2 = null;

                try {
                    writer1 = new BufferedWriter(new FileWriter(file_master, true));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try{
                    writer2=new BufferedWriter(new FileWriter(files_save,true)) ;
                }catch(IOException e){
                    e.printStackTrace();
                }



                for (String value : resultArr) {

                    File f = new File(value);

                    addElement(save, value);
                    System.out.println(save);


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

                    try {
                        writer1.write(value);
                         writer1.write(bosluk);
                        writer1.write(dosyasum);
                        writer1.newLine();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        writer2.write(value);
                        writer2.newLine();

                       } catch (IOException e) {
                          e.printStackTrace();}

                }
                try {
                    writer1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                      try {
                          writer2.close();
                      } catch (IOException e) {
                          e.printStackTrace();

                      }


                     }
                 });
        btn_reboot.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                askReboot();
            }
        });




    }


    protected void askReboot() {
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

    public boolean execCommandAsRoot(String path) {
        try {
            String[] cmd = {"su", "-c", path};
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
            String line1 = s1.nextLine().trim();
            count1++;
            String line2 = s2.nextLine().trim();
            count2++;

            if (!line1.equalsIgnoreCase(line2)) {
                System.out.println("Line " + count1 + " differs.");
                System.out.println("< " + line1);
                System.out.println("> " + line2);
                Toast.makeText(getApplicationContext(), "UYARI MESAJI::Satır " + count1 + " farklı.DOSYALAR: " + line1 + "><" + line2 + "", Toast.LENGTH_LONG).show();

            } else {
                System.out.println("Line " + count1 + " matches.");


                Toast.makeText(getApplicationContext(), "UYARI MESAJI::Satır " + count1 + "eşleşti ", Toast.LENGTH_LONG).show();
            }
        }

        // any leftover lines in file1 count as differences
        while (s1.hasNextLine()) {
            String line1 = s1.nextLine();
            count1++;
            System.out.println("Line " + count1 + " differs.");
            System.out.println("< " + line1);
            System.out.println("> ");
            Toast.makeText(getApplicationContext(), "UYARI MESAJI:Satır " + count1 + " özeti :< " + line1 + ">", Toast.LENGTH_LONG).show();
        }

        // any leftover lines in file2 count as differences
        while (s2.hasNextLine()) {
            String line2 = s2.nextLine();
            count2++;
            System.out.println("Line " + count2 + " differs.");
            System.out.println("< ");
            System.out.println("> " + line2);
            Toast.makeText(getApplicationContext(), "UYARI MESAJI:Satır " + count2 + "özeti : < >" + line2 + "", Toast.LENGTH_LONG).show();

        }

    }


    static <T> T[] append(T[] arr, T element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }


    String[] addElement(String[] org, String added) {
        String[] result = Arrays.copyOf(org, org.length + 1);
        result[org.length] = added;
        return result;
    }











    public static void DosyadanTumSatirlariOku() throws IOException {

        FileReader file = new FileReader(path);
        BufferedReader reader = new BufferedReader(file);
        String satir;
        String metin = "";
        while ((satir = reader.readLine()) != null) {
            metin += satir + "\n";
        }
        System.out.println(metin);
    }






}