package com.kodingindonesia.mycrud;

/**
 * Created by muhammadyusuf on 01/19/2017.
 * kodingindonesia
 */

public class konfigurasi {

    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
    public static final String URL_ADD="http://babypinkapi.scalondesign.com/tambahMhs.php";
    public static final String URL_GET_ALL = "http://babypinkapi.scalondesign.com/tampilSemuaMhs.php";
    public static final String URL_GET_EMP = "http://babypinkapi.scalondesign.com/tampilMhs.php?id=";
    public static final String URL_UPDATE_EMP = "http://babypinkapi.scalondesign.com/updateMhs.php";
    public static final String URL_DELETE_EMP = "http://babypinkapi.scalondesign.com/hapusMhs.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NO_INDUK = "no_induk";
    public static final String KEY_EMP_NAMA = "nama";
    public static final String KEY_EMP_JENIS_KELAMIN = "jenis_kelamin";
    public static final String KEY_EMP_TEMPAT_LAHIR = "tempat_lahir";
    public static final String KEY_EMP_TANGGAL_LAHIR = "tanggal_lahir";
    public static final String KEY_EMP_SEKOLAH_ASAL = "sekolah_asal";
    public static final String KEY_EMP_ALAMAT = "alamat";
    public static final String KEY_EMP_NAMA_WALI = "nama_wali";
    public static final String KEY_EMP_NO_TELP = "no_telp";
    public static final String KEY_EMP_PAKET = "paket";


    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NO_INDUK = "no_induk";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_JENIS_KELAMIN = "jenis_kelamin";
    public static final String TAG_TEMPAT_LAHIR = "tempat_lahir";
    public static final String TAG_TANGGAL_LAHIR = "tanggal_lahir";
    public static final String TAG_SEKOLAH_ASAL = "sekolah_asal";
    public static final String TAG_ALAMAT = "alamat";
    public static final String TAG_NAMA_WALI = "nama_wali";
    public static final String TAG_NO_TELP = "no_telp";
    public static final String TAG_PAKET = "paket";

    //ID mahasiswa
    //mhs itu singkatan dari Mahasiswa
    public static final String MHS_ID = "mhs_id";
}
