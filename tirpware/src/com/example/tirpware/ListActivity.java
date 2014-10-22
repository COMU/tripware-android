package com.example.tirpware;

/**
 * Created by ulusoy on 22.10.2014.
 */


import java.util.ArrayList;

        import android.app.Activity;
        import android.content.Context;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.View.OnClickListener;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;
        import android.widget.AdapterView.OnItemClickListener;

public class ListActivity extends Activity {

    MyCustomAdapter dataAdapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen);

        //Generate list View from ArrayList
        displayListView();

        checkButtonClick();

    }

    private void displayListView() {

        //Array list of countries
        ArrayList<Dizin> diziList = new ArrayList<Dizin>();
        Dizin dizin = new Dizin("***", "/etc", false);
        diziList.add(dizin);
        dizin = new Dizin("***", "/dev" ,false);
        diziList.add(dizin);
        dizin = new Dizin("***", "/system" ,false);
        diziList.add(dizin);
        dizin = new Dizin("***", "/root", false);
        diziList.add(dizin);
        dizin = new Dizin("**", "/mnt", false);
        diziList.add(dizin);
        dizin = new Dizin("**", "/proc", false);
        diziList.add(dizin);
        dizin = new Dizin("**", "/sys", false);

        diziList.add(dizin);
        dizin = new Dizin("***", "/sbin", false);

        diziList.add(dizin);
        dizin = new Dizin("*", "/tmp-mksh", false);

        diziList.add(dizin);
        dizin = new Dizin("**", "/cache", false);

        diziList.add(dizin);

        //create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(this,
                R.layout.dizin, diziList);
        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new OnItemClickListener( ) {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Dizin dizin = (Dizin) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Clicked on Row: " + dizin.getName(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    private class MyCustomAdapter extends ArrayAdapter<Dizin> {

        private ArrayList<Dizin> dizinArrayList;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<Dizin> dizinArrayList) {
            super(context, textViewResourceId, dizinArrayList);
            this.dizinArrayList = new ArrayList<Dizin>();
            this.dizinArrayList.addAll(dizinArrayList);
        }

        private class ViewHolder {
            TextView code;
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.dizin, null);

                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);

                holder.name.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        Dizin dizin = (Dizin) cb.getTag();
                        Toast.makeText(getApplicationContext(),
                                "Clicked on directory: " + cb.getText() +
                                        " is " + cb.isChecked(),
                                Toast.LENGTH_LONG).show();
                        dizin.setSelected(cb.isChecked());
                    }
                });
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Dizin dizin = dizinArrayList.get(position);
            holder.code.setText(" (" + dizin.getCode() + ")");
            holder.name.setText(dizin.getName());
            holder.name.setChecked(dizin.isSelected());
            holder.name.setTag(dizin);

            return convertView;

        }

    }

    private void checkButtonClick() {


        Button myButton = (Button) findViewById(R.id.findSelected);
        myButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("The following were selected...\n");

                ArrayList<Dizin> dizinArrayList = dataAdapter.dizinArrayList;
                for (int i = 0; i < dizinArrayList.size(); i++) {
                    Dizin dizin = dizinArrayList.get(i);
                    if (dizin.isSelected()) {
                        responseText.append("\n" + dizin.getName());
                    }
                }

                Toast.makeText(getApplicationContext(),
                        responseText, Toast.LENGTH_LONG).show();

            }
        });

    }
}