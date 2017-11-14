package com.unica.externalstorage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etData, etFilename;
    Button Btn_Shared;
    Button Btn_IntStor;
    Button Btn_IntCache;
    Button Btn_ExtCache;
    Button Btn_ExtStor;
    Button Btn_ExtPubStor;
    Button Btn_Next;
    FileOutputStream fos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etData = (EditText) findViewById(R.id.etData);
        etFilename = (EditText) findViewById(R.id.etFilename);
        Btn_Shared = (Button) findViewById(R.id.Btn_SharedPref);
        Btn_IntStor = (Button) findViewById(R.id.Btn_InternalStor);
        Btn_IntCache = (Button) findViewById(R.id.Btn_intCache);
        Btn_ExtCache = (Button) findViewById(R.id.Btn_extCache);
        Btn_ExtStor = (Button) findViewById(R.id.Btn_extStor);
        Btn_ExtPubStor = (Button) findViewById(R.id.Btn_extPub);
        Btn_Next = (Button) findViewById(R.id.Btn_next);
    }

    public void nextAct (View view) {
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        intent.putExtra("filename", etFilename.getText().toString());
        startActivity(intent);
    }

    private void writeData(File file, String message){
        fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void saveSharedPref (View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("data", etData.getText().toString());
        editor.commit();
        Toast.makeText(this, "Preferences Saved!", Toast.LENGTH_SHORT).show();
    }

    public void saveInternalStor (View view) {
        String message = etData.getText().toString();
        String filename = etFilename.getText().toString();
        try {
            fos = openFileOutput(filename + ".txt", Context.MODE_PRIVATE);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Internal Storage saved!", Toast.LENGTH_SHORT).show();
    }

    public void saveInternalCache(View view){
        String filename = etFilename.getText().toString();
        File folder = getCacheDir();
        File file = new File(folder, filename + ".txt");
        String message = etData.getText().toString();
        writeData(file, message);
        Toast.makeText(this,"Successfully written to internal cache!", Toast.LENGTH_LONG).show();
    }

    public void saveExternalCache (View view) {
        String filename = etFilename.getText().toString();
        File folder = getExternalCacheDir();
        File file = new File(folder, filename + ".txt");
        String message = etData.getText().toString();
        writeData(file, message);
        Toast.makeText(this, "Successfully written to external cache!", Toast.LENGTH_LONG).show();
    }

    public void saveExternalStorage (View view) {
        String filename = etFilename.getText().toString();
        File folder = getExternalFilesDir("Roseleen");
        File file = new File(folder, filename + ".txt");
        String message = etData.getText().toString();
        writeData(file, message);
        Toast.makeText(this, "Successfully written to external storage!", Toast.LENGTH_LONG).show();
    }

    public void saveExternalPublic (View view) {
        String filename = etFilename.getText().toString();
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File (folder, filename + ".txt");
        String message = etData.getText().toString();
        writeData(file, message);
        Toast.makeText(this, "Successfully written to external public storage!", Toast.LENGTH_LONG).show();
    }
}

