package com.example.muhammadazmiquiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class hasil extends AppCompatActivity {

    TextView textViewNamaPelanggan;
    TextView textViewTipePelanggan;
    TextView textViewKodeBarang;
    TextView textViewNamaBarang;
    TextView textViewHarga;
    TextView textViewTotalHarga;
    TextView textViewDiskonHarga;
    TextView textViewDiskonMember;
    TextView textViewJumlahBayar;
    Button shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);
        textViewNamaPelanggan = findViewById(R.id.hasilname);
        textViewTipePelanggan = findViewById(R.id.tipe_member);
        textViewKodeBarang = findViewById(R.id.kode_barang);
        textViewNamaBarang = findViewById(R.id.nama_barang);
        textViewHarga = findViewById(R.id.harga);
        textViewTotalHarga = findViewById(R.id.total_harga);
        textViewDiskonHarga = findViewById(R.id.diskon_harga);
        textViewDiskonMember = findViewById(R.id.diskon_member);
        textViewJumlahBayar = findViewById(R.id.jumlah_bayar);
        shareButton = findViewById(R.id.share);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SHARE();
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String namaPelanggan = extras.getString("Nama Pelanggan");
            String kodeBarang = extras.getString("Kode Barang");
            String namaBarang = extras.getString("Nama Barang");
            String Tipe_Pelanggan = extras.getString("Tipe Pelanggan");
            long harga = extras.getLong("Harga");
            int jumlahBarang = extras.getInt("Jumlah Barang");
            long totalHarga = extras.getLong("Total Harga");
            long diskonHarga = extras.getLong("Diskon Harga");
            long diskonMember = extras.getLong("Diskon Member");
            long jumlahBayar = extras.getLong("Jumlah Bayar");



            textViewNamaPelanggan.setText("Selamat Berbelanja " + namaPelanggan);

            textViewKodeBarang.setText("Kode Barang : " + kodeBarang);
            textViewNamaBarang.setText("Nama Barang : " + namaBarang);
            textViewHarga.setText("Harga :Rp." + harga);
            textViewTotalHarga.setText("Total Harga : Rp." + totalHarga);
            textViewDiskonHarga.setText("Diskon Harga : Rp." + diskonHarga);
            textViewDiskonMember.setText("Diskon Member : Rp." + diskonMember);
            textViewJumlahBayar.setText("Jumlah Bayar : Rp." + jumlahBayar);
        }
    }

    public void SHARE() {
        String dataToShare = "Detail Transaksi\n" +
                textViewNamaPelanggan.getText().toString() + "\n" +
                textViewKodeBarang.getText().toString() + "\n" +
                textViewNamaBarang.getText().toString() + "\n" +
                textViewHarga.getText().toString() + "\n" +
                textViewTotalHarga.getText().toString() + "\n" +
                textViewDiskonHarga.getText().toString() + "\n" +
                textViewDiskonMember.getText().toString() + "\n" +
                textViewJumlahBayar.getText().toString();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, dataToShare);
        startActivity(Intent.createChooser(shareIntent, "Bagikan via"));
    }
}
