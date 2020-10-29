package com.kodingindonesia.mycrud;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by muhammadyusuf on 01/19/2017.
 * kodingindonesia
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    //Dibawah ini merupakan perintah untuk mendefinikan View
    //dtp
    EditText editTextTanggalLahir;
    DatePickerDialog.OnDateSetListener setListener;
    //textbox
    private EditText editTextNoInduk;
    private EditText editTextNama;
    private EditText editTextTempatLahir;
    private EditText editTextAlamat;
    private EditText editTextNamaWali;
    private EditText editTextTelp;
    //spinner

    Spinner spinnerPaket;
    Spinner spinnerSekolahAsal;
    Spinner spinnerJenisKelamin;

    ArrayList<String> paketList = new ArrayList<>();
    ArrayList<String> sekolahList = new ArrayList<>();
    ArrayAdapter<String> paketAdapter;
    ArrayAdapter<String> sekolahAdapter;
    RequestQueue requestQueue;
    RequestQueue requestQueue2;
    //button
    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.app_name) + "</font>"));
        //Inisialisasi dari View

        //txt no induk, nama
        editTextNoInduk = (EditText) findViewById(R.id.editTextNoInduk);
        editTextNama = (EditText) findViewById(R.id.editTextNama);

        //spinner jk

        spinnerJenisKelamin = (Spinner) findViewById(R.id.spinnerJenisKelamin);
        ArrayAdapter<CharSequence> adapterJenisKelamin = ArrayAdapter.createFromResource(this, R.array.jk, android.R.layout.simple_spinner_item);
        adapterJenisKelamin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJenisKelamin.setAdapter(adapterJenisKelamin);
        spinnerJenisKelamin.setOnItemSelectedListener(this);

        //txt tempat lahir
        editTextTempatLahir = (EditText) findViewById(R.id.editTextTempatLahir);

        //tgl lahir
        editTextTanggalLahir = findViewById(R.id.editTextTanggalLahir);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        editTextTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        editTextTanggalLahir.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        //spinner sekolah asal
        requestQueue2 = Volley.newRequestQueue(this);
        spinnerSekolahAsal = (Spinner) findViewById(R.id.spinnerSekolahAsal);

        //note kalau url di yg asli itu url2
        String url2 = "http://babypinkapi.scalondesign.com/list_sekolah.php";
        JsonObjectRequest jsonObjectRequest2 =  new JsonObjectRequest(Request.Method.POST, url2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("asal_sekolah");
                    for(int i =0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String sekolah = jsonObject.optString("asal_sekolah");
                        sekolahList.add(sekolah);
                        sekolahAdapter =  new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, sekolahList);
                        sekolahAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerSekolahAsal.setAdapter(sekolahAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        requestQueue2.add(jsonObjectRequest2);

        //txt alamat, nama wali, telp
        editTextAlamat = (EditText) findViewById(R.id.editTextAlamat);
        editTextNamaWali = (EditText) findViewById(R.id.editTextNamaWali);
        editTextTelp = (EditText) findViewById(R.id.editTextNoTelp);

        //spinner paket
        requestQueue = Volley.newRequestQueue(this);
        spinnerPaket = (Spinner) findViewById(R.id.spinnerPaket);

        String url = "http://babypinkapi.scalondesign.com/list_paket.php";

        JsonObjectRequest jsonObjectRequest =  new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("jenis_paket");
                    for(int i =0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String paket = jsonObject.optString("jenis_paket");
                        paketList.add(paket);
                        paketAdapter =  new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, paketList);
                        paketAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerPaket.setAdapter(paketAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        requestQueue.add(jsonObjectRequest);


        //button
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }


    //Dibawah ini merupakan perintah untuk Menambahkan Pegawai (CREATE)
    private void addEmployee(){

        final String noinduk = editTextNoInduk.getText().toString().trim();
        final String nama = editTextNama.getText().toString().trim();
        final String jk = spinnerJenisKelamin.getSelectedItem().toString().trim();
        final String tempatlahir = editTextTempatLahir.getText().toString().trim();
        final String tanggallahir = editTextTanggalLahir.getText().toString().trim();
        final String sekolahasal = spinnerSekolahAsal.getSelectedItem().toString().trim();
        final String alamat = editTextAlamat.getText().toString().trim();
        final String namawali = editTextNamaWali.getText().toString().trim();
        final String telp = editTextTelp.getText().toString().trim();
        final String paket = spinnerPaket.getSelectedItem().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(konfigurasi.KEY_EMP_NO_INDUK,noinduk);
                params.put(konfigurasi.KEY_EMP_NAMA,nama);
                params.put(konfigurasi.KEY_EMP_JENIS_KELAMIN,jk);
                params.put(konfigurasi.KEY_EMP_TEMPAT_LAHIR,tempatlahir);
                params.put(konfigurasi.KEY_EMP_TANGGAL_LAHIR,tanggallahir);
                params.put(konfigurasi.KEY_EMP_SEKOLAH_ASAL,sekolahasal);
                params.put(konfigurasi.KEY_EMP_ALAMAT,alamat);
                params.put(konfigurasi.KEY_EMP_NAMA_WALI,namawali);
                params.put(konfigurasi.KEY_EMP_NO_TELP,telp);
                params.put(konfigurasi.KEY_EMP_PAKET,paket);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_ADD, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addEmployee();
        }

        if(v == buttonView){
            startActivity(new Intent(this,TampilSemuaPgw.class));
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //String choice = adapterView.getItemAtPosition(i).toString();
        //Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
