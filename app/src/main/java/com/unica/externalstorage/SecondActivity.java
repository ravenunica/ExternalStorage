package com.unica.externalstorage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class SecondActivity extends AppCompatActivity {

    Button Btn_readShared;
    Button Btn_readIntStor;
    Button Btn_readIntCache;
    Button Btn_readExtCache;
    Button Btn_readExtStor;
    Button Btn_readExtPubStor;
    Button Btn_Prev;
    TextView tvData;
    FileInputStream fis;
    BufferedReader br;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvData = (TextView) findViewById(R.id.tvData);
        Btn_readShared = (Button) findViewById(R.id.Btn_readSharedPref);
        Btn_readIntStor = (Button) findViewById(R.id.Btn_readInternalStor);
        Btn_readIntCache = (Button) findViewById(R.id.Btn_readInternalCache);
        Btn_readExtCache = (Button) findViewById(R.id.Btn_readExternalCache);
        Btn_readExtStor = (Button) findViewById(R.id.Btn_readExternalStor);
        Btn_readExtPubStor = (Button) findViewById(R.id.Btn_readExtPubStor);
        Btn_Prev = (Button) findViewById(R.id.Btn_Back);

    }

    public void backAct (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void readSharedPref(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());;
        String data = preferences.getString("data","");
        tvData.setText(data);
    }

    public void readInternalStorage (View view) throws IOException {
        String newline = "";
        String data = "";
        try{
            fis = openFileInput("storage.txt");
            br = new BufferedReader(new InputStreamReader(fis));
            if ((newline = br.readLine()) != null)
                data = newline;
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tvData.setText(data);
    }

    public void readInternalCache (View view) throws FileNotFoundException {
        String newline = "";
        String data = "";
        File folder = getCacheDir();
        File file = new File(folder, "storage.txt");
        fis = new FileInputStream(file);
        try{
            br = new BufferedReader(new InputStreamReader(fis));
            if ((newline = br.readLine()) != null)
                data = newline;
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tvData.setText(data);
    }

    public void readExternalCache (View view) throws FileNotFoundException {
        String newline = "";
        String data = "";
        File folder = getExternalCacheDir();
        File file = new File(folder, "storage.txt");
        fis = new FileInputStream(file);
        try{
            br = new BufferedReader(new InputStreamReader(fis));
            if ((newline = br.readLine()) != null)
                data = newline;
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tvData.setText(data);
    }

    public void readExternalStor (View view) throws FileNotFoundException {
        String newline = "";
        String data = "";
        File folder = getExternalFilesDir("<PANGALAN MO DITO>");
        File file = new File(folder, "storage.txt");
        fis = new FileInputStream(file);
        try{
            br = new BufferedReader(new InputStreamReader(fis));
            if ((newline = br.readLine()) != null)
                data = newline;
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tvData.setText(data);
    }

    public void readExtPubStor (View view) throws FileNotFoundException {
        String newline = "";
        String data = "";
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File (folder, "storage.txt");
        fis = new FileInputStream(file);
        try{
            br = new BufferedReader(new InputStreamReader(fis));
            if ((newline = br.readLine()) != null)
                data = newline;
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tvData.setText(data);

    }
}
