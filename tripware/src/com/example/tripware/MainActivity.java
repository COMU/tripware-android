package com.example.tripware;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.*;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;





/**
 * Created by ulusoy on 22.12.2014.
 */
public class MainActivity extends ListActivity {
    private ArrayAdapter adapter;
    public ArrayList<String> selectedItems;

    String[] outputStrArr;
    String[] filenames;





    private String path;
    private String com = "cd /data";
    private static final String TAG = MainActivity.class.getName();

    String liste;

    Context mContext;

FileOperations FİLE_NAME;
    SummaryActivity summary;
    String filePath;
    ResultActivity result;

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);





















        /* dizinlerin başlıklarını ekliyoruz pathe */

            path = "/";

            if (getIntent().hasExtra("path")) {
                path = getIntent().getStringExtra("path");

            }



            setTitle(path);

            // Read all files sorted into the values-array
            final List values = new ArrayList();
            final File dir = new File(path);


            if (!dir.canRead()) {
                try {
                    Process p = Runtime.getRuntime().exec("su");

                    runAsRoot(com);



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            final String[] list = dir.list();


            if (list != null) {
                for (String file : list) {
                    System.out.println(file);

                    if (!file.startsWith(".")) {
                        values.add(file);

                    }

                }
            }


            Collections.sort(values);


            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, values);


            setListAdapter(adapter);
            TextView selection = (TextView) findViewById(R.id.selection);
            final ListView listView = getListView();
            listView.setItemsCanFocus(false);
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


            Button btn_kaydet = (Button) findViewById(R.id.button);
            final FileOperations file_o = new FileOperations();

            btn_kaydet.setOnClickListener(new Button.OnClickListener() {


                public void onClick(View v) {




                    SparseBooleanArray checked = listView.getCheckedItemPositions();

                    selectedItems = new ArrayList<String>();


                    for (int i = 0; i < checked.size(); i++) {
// Item position in adapter
                        int position = checked.keyAt(i);
// Add sport if it is checked i.e.) == TRUE!
                        if (checked.valueAt(i))
                            selectedItems.add((String) adapter.getItem(position));
                    }

                    liste = selectedItems.toString();


                    outputStrArr = new String[selectedItems.size()];


                    for (int i = 0; i < selectedItems.size(); i++) {
                        outputStrArr[i] = selectedItems.get(i);

                    }


                    Intent intent = new Intent(getApplicationContext(),
                            ResultActivity.class);


// Create a bundle object
                    Bundle b = new Bundle();
                    b.putStringArray("selectedItems", outputStrArr);
// Add the bundle to the intent.
                    intent.putExtras(b);
// start the ResultActivity
                    startActivity(intent);

                }
            });









    /*uygulamanın içinden root isteği*/
            Process p;
            try {
                // Preform su to get root privledges
                p = Runtime.getRuntime().exec("su");

                // Attempt to write a file to a root-only
                DataOutputStream os = new DataOutputStream(p.getOutputStream());
                os.writeBytes("echo \"Do I have root?\" >/system/sd/temporary.txt\n");

                // Close the terminal
                os.writeBytes("exit\n");
                os.flush();
                try {
                    p.waitFor();
                    if (p.exitValue() != 255) {
                        // TODO Code to run on success
                        toastMessage("root");
                    } else {
                        // TODO Code to run on unsuccessful
                        toastMessage("not root");
                    }
                } catch (InterruptedException e) {
                    // TODO Code to run in interrupted exception
                    toastMessage("not root");
                }
            } catch (IOException e) {
                // TODO Code to run in input/output exception
                toastMessage("not root");
            }
        }




    private void toastMessage(String s) {
    }
    /*uygulamanın içinde terminal emilatör gibi kod çalıştırma*/

    public void runAsRoot(String command) throws IOException {
        Process p = Runtime.getRuntime().exec("su");


        DataOutputStream os = new DataOutputStream(p.getOutputStream());
        os.writeBytes(command + "\n");
        os.writeBytes("exit\n");
        os.flush();


    }


/*filename seçilen dosyanın yolu var */

    @Override

    protected void onListItemClick(ListView l, View v, int position, long id) {

        String filename = (String) getListAdapter().getItem(position);

        if (path.endsWith(File.separator)) {
            filename = path + filename;

        } else {
            filename = path + File.separator + filename;
        }










        if (new File(filename).isDirectory()) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("path", filename);
            startActivity(intent);
            Toast.makeText(this, filename + "Bu bir dizindir seçilemez ", Toast.LENGTH_LONG).show();

        } else {
            System.out.println(filename);
            System.out.println(path.endsWith(File.separator));
            System.out.println(path);





        }



    }


}







