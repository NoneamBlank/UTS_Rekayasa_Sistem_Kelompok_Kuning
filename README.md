# BabyPink_CRUD
What Is In Here: 
- CRUD yang sudah berjalan dan di fix sesuai Tutorial di kodingindonesia.com
- Posisi melakukan input dengan menggunakan spinner. 
Kelompok Baby Pink(Kuning kalau di Rekayasa sistem)
1. Derwin Galen 1831139
2. Wilson Vernando 1831011
3. Kelvin Kurniawan 1831171
4. Junifer 1831135
5. Helvin 1831112

Kendala yang ditemukan dan Cara Memperbaikinya(note: yang diupload adalah versi yang sudah bisa dirunkan dan terkoneksi ke database):
- Lokasi: htdocs > Android > pegawai > koneksi.php
Kendala: database tidak terkoneksi, dikarenakan databasenya memiliki password yaitu 'root'
Solusi : hapus kata root atau gantikan dengan password database(jika ada passwordnya) di define('PASS','root');

- Lokasi: Gradle Scripts > build.gradle (Project: derwi-MyCRUD)
Kendala: com.android.tools.build:gradle:2.3.1 nya versinya terlalu lama
Solusi: update dan sync now melewati error checkingnya Android Studio

- Lokasi: app > java > com.kodingindonesia.mycrud > konfigurasi
Kendala: ip addressnya tidak sesuai
Solusi: ubah ip addressnya sesuai ip address kita(cek di command prompt dengan mengetik ipconfig, lihat ipv4 nya)

- Lokasi: Gradle Scripts > build.gradle (Module: MyCRUD-app)
Kendala: di bagian android { } compileSdkVersion dan targetSdkVersion beda versi
Solusi: ubah versi kedua sesuai API emulator

- Lokasi: Gradle Scripts > build.gradle (Module: MyCRUD-app)
Kendala: di bagian android { } buildToolsVersion "25.0.2"
Solusi: ubah versi menjadi buildToolsVersion "25.0.3"

- Lokasi: Gradle Scripts > build.gradle (Module: MyCRUD-app)
Kendala: di bagian dependecies { } scriptnya menggunakan versi lama app compat
Solusi: copy coding dibawah dah paste(timpa) dependencies
dependencies {
    implementation fileTree(dir: "libs", include: ["*.jar"])
    implementation 'androidx.appcompat:appcompat:1.2.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.1'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'

}

- Lokasi: app > java > com.kodingindonesia.mycrud > ListView
Kendala: di bagian import, menggunakan versi appcompat lama dan harus diubah
Solusi: copy coding import androidx.appcompat.app.AppCompatActivity; dah paste(timpa) import AppCompatActivity yang lama

- Lokasi: app > java > com.kodingindonesia.mycrud > MainActivity
Kendala: di bagian import, menggunakan versi appcompat lama dan harus diubah
Solusi: copy coding import androidx.appcompat.app.AppCompatActivity; dah paste(timpa) import AppCompatActivity yang lama

- Lokasi: app > java > com.kodingindonesia.mycrud > TampilPegawai
Kendala: di bagian import, menggunakan versi alertdialog lama dan harus diubah
Solusi: copy coding import import androidx.appcompat.app.AlertDialog; dah paste(timpa) import AlertDialog yang lama

- Lokasi: app > java > com.kodingindonesia.mycrud > TampilPegawai
Kendala: di bagian import, menggunakan versi appcompat lama dan harus diubah
Solusi: copy coding import androidx.appcompat.app.AppCompatActivity; dah paste(timpa) import AppCompatActivity yang lama

- Lokasi: app > java > com.kodingindonesia.mycrud > TampilSemuaPgw
Kendala: di bagian import, menggunakan versi appcompat lama dan harus diubah
Solusi: copy coding import androidx.appcompat.app.AppCompatActivity; dah paste(timpa) import AppCompatActivity yang lama

