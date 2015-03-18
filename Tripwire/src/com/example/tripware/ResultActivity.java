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

import java.io.*;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.StringTokenizer;


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


                    System.out.println(SummaryActivity.getMd5OfFile(file_master));
                System.out.println(SummaryActivity.getMd5OfFile(file_time));
                if(SummaryActivity.getMd5OfFile(file_master).equals(SummaryActivity.getMd5OfFile(file_time))){
                    Log.d("Files", "Files are equal");
                    System.out.println("eşit");


                }
                else{
                    Log.d("Files", "Files are different");
                    System.out.println("eşit değil");


                }



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



    public static boolean CompareFilesbyByte(String file1,String file2) throws IOException
    {
        File f1=new File(file1);
        File f2=new File(file2);
        FileInputStream fis1 = new FileInputStream (f1);
        FileInputStream fis2 = new FileInputStream (f2);
        if (f1.length()==f2.length())
        {
            int n=0;
            byte[] b1;
            byte[] b2;
            while ((n = fis1.available()) > 0) {
                if (n>80) n=80;
                b1 = new byte[n];
                b2 = new byte[n];
                int res1 = fis1.read(b1);
                int res2 = fis2.read(b2);

                if (Arrays.equals(b1, b2)==false)
                {
                    System.out.println(file1 + " :\n\n " + new String(b1));
                    System.out.println();
                    System.out.println(file2 + " : \n\n" + new String(b2));
                    return false;
                }
                else {
                    System.out.println("Eşit değil");
                }
            }
        }
        else return false; // length is not matched.
        return true;
    }
    public static String MD5HashFile(String filename) throws Exception {
        byte[] buf = ChecksumFile(filename);
        String res = "";
        for (int i = 0; i < buf.length; i++) {
            res+= Integer.toString((buf[i] & 0xff) + 0x100, 16).substring(1);
        }
        return res;
    }
    public static byte[] ChecksumFile(String filename) throws Exception {
        InputStream fis = new FileInputStream(filename);
        byte[] buf = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("MD5");
        int n;
        do {
            n= fis.read(buf);
            if (n > 0) {
                complete.update(buf, 0, n);
            }
        } while (n != -1);
        fis.close();
        return complete.digest();
    }

    private void compareFiles() throws Exception,FileNotFoundException {
        String s1 = "";
        String s2 = "", s3 = "", s4 = "";
        String y = "", z = "";
        // Reading the contents of the files
        BufferedReader br = new BufferedReader(new InputStreamReader(
                getAssets().open(path1)));
        BufferedReader br1 = new BufferedReader(new InputStreamReader(
                getAssets().open(path2)));
        while ((z = br1.readLine()) != null) {
            s3 += z;
            s3 += System.getProperty("line.separator");
        }
        while ((y = br.readLine()) != null) {
            s1 += y;
            s1 += System.getProperty("line.separator");
        }
        // String tokenizing
        StringTokenizer st = new StringTokenizer(s1);
        String[] a = new String[10000];
        for (int l = 0; l < 10000; l++) {
            a[l] = "";
        }
        int i = 0;
        while (st.hasMoreTokens()) {
            s2 = st.nextToken();
            a[i] = s2;
            i++;
        }
        StringTokenizer st1 = new StringTokenizer(s3);
        String[] b = new String[10000];
        for (int k = 0; k < 10000; k++) {
            b[k] = "";
        }
        int j = 0;
        while (st1.hasMoreTokens()) {
            s4 = st1.nextToken();
            b[j] = s4;
            j++;
        }
        // comparing the contents of the files and printing the differences, if
        // any.
        int x = 0;
        for (int m = 0; m < a.length; m++) {
            if (a[m].equals(b[m])) {
            } else {
                x++;
                Log.d("Home", a[m] + " -- " + b[m]);
            }
        }
        Log.d("Home", "No. of differences : " + x);
        if (x > 0) {
            Log.d("Home", "Files are not equal");
        } else {
            Log.d("Home", "Files are equal. No difference found");
        }
    }


    







}
