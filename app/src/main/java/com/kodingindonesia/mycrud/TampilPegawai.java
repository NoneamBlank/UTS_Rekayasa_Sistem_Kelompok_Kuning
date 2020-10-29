package com.kodingindonesia.mycrud;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by muhammadyusuf on 01/19/2017.
 * kodingindonesia
 */

public class TampilPegawai extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextId;
    private EditText editTextNoInduk;
    private EditText editTextNama;
    private EditText editTextJenisKelamin;
    private EditText editTextTempatLahir;
    private EditText editTextTanggalLahir;
    private EditText editTextSekolahAsal;
    private EditText editTextAlamat;
    private EditText editTextNamaWali;
    private EditText editTextTelp;
    private EditText editTextPaket;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_pegawai);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.app_name) + "</font>"));

        Intent intent = getIntent();

        id = intent.getStringExtra(konfigurasi.MHS_ID);

        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextNoInduk = (EditText) findViewById(R.id.editTextNoInduk);
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextJenisKelamin = (EditText) findViewById(R.id.editTextJenisKelamin);
        editTextTempatLahir = (EditText) findViewById(R.id.editTextTempatLahir);
        //tgl lahir
        editTextTanggalLahir = (EditText) findViewById(R.id.editTextTanggalLahir);

        editTextSekolahAsal = (EditText) findViewById(R.id.editTextSekolahAsal);
        editTextAlamat = (EditText) findViewById(R.id.editTextAlamat);
        editTextNamaWali = (EditText) findViewById(R.id.editTextNamaWali);
        editTextTelp = (EditText) findViewById(R.id.editTextNoTelp);
        editTextPaket = (EditText) findViewById(R.id.editTextPaket);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        editTextId.setText(id);

        getEmployee();
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilPegawai.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_EMP,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String noinduk = c.getString(konfigurasi.TAG_NO_INDUK);
            String nama = c.getString(konfigurasi.TAG_NAMA);
            String jk = c.getString(konfigurasi.TAG_JENIS_KELAMIN);
            String tempatlahir = c.getString(konfigurasi.TAG_TEMPAT_LAHIR);
            String tanggallahir = c.getString(konfigurasi.TAG_TANGGAL_LAHIR);
            String sekolahasal = c.getString(konfigurasi.TAG_SEKOLAH_ASAL);
            String alamat = c.getString(konfigurasi.TAG_ALAMAT);
            String namawali = c.getString(konfigurasi.TAG_NAMA_WALI);
            String telp = c.getString(konfigurasi.TAG_NO_TELP);
            String paket = c.getString(konfigurasi.TAG_PAKET);

            editTextNoInduk.setText(noinduk);
            editTextNama.setText(nama);
            editTextJenisKelamin.setText(jk);
            editTextTempatLahir.setText(tempatlahir);
            editTextTanggalLahir.setText(tanggallahir);
            editTextSekolahAsal.setText(sekolahasal);
            editTextAlamat.setText(alamat);
            editTextNamaWali.setText(namawali);
            editTextTelp.setText(telp);
            editTextPaket.setText(paket);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateEmployee(){
        final String noinduk = editTextNoInduk.getText().toString().trim();
        final String nama = editTextNama.getText().toString().trim();
        final String jk = editTextJenisKelamin.getText().toString().trim();
        final String tempatlahir = editTextTempatLahir.getText().toString().trim();
        final String tanggallahir = editTextTanggalLahir.getText().toString().trim();
        final String sekolahasal = editTextSekolahAsal.getText().toString().trim();
        final String alamat = editTextAlamat.getText().toString().trim();
        final String namawali = editTextNamaWali.getText().toString().trim();
        final String telp = editTextTelp.getText().toString().trim();
        final String paket = editTextPaket.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilPegawai.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilPegawai.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(konfigurasi.KEY_EMP_ID,id);
                hashMap.put(konfigurasi.KEY_EMP_NO_INDUK,noinduk);
                hashMap.put(konfigurasi.KEY_EMP_NAMA,nama);
                hashMap.put(konfigurasi.KEY_EMP_JENIS_KELAMIN,jk);
                hashMap.put(konfigurasi.KEY_EMP_TEMPAT_LAHIR,tempatlahir);
                hashMap.put(konfigurasi.KEY_EMP_TANGGAL_LAHIR,tanggallahir);
                hashMap.put(konfigurasi.KEY_EMP_SEKOLAH_ASAL,sekolahasal);
                hashMap.put(konfigurasi.KEY_EMP_ALAMAT,alamat);
                hashMap.put(konfigurasi.KEY_EMP_NAMA_WALI,namawali);
                hashMap.put(konfigurasi.KEY_EMP_NO_TELP,telp);
                hashMap.put(konfigurasi.KEY_EMP_PAKET,paket);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(konfigurasi.URL_UPDATE_EMP,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilPegawai.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilPegawai.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_DELETE_EMP, id);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Mahasiswa ini?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        startActivity(new Intent(TampilPegawai.this,TampilSemuaPgw.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateEmployee();
        }

        if(v == buttonDelete){
            confirmDeleteEmployee();
        }
    }
}
