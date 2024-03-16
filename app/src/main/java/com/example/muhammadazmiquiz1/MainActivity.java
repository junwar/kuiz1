package com.example.muhammadazmiquiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, kode_barang, jumlah_barang;
    Button proses;
    RadioGroup tipe_pelanggan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        kode_barang = findViewById(R.id.kode_barang);
        jumlah_barang = findViewById(R.id.jumlah_barang);
        tipe_pelanggan = findViewById(R.id.tipe_pelanggan);
        proses = findViewById(R.id.proses);

        proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = name.getText().toString();
                String kodeBarang = kode_barang.getText().toString();
                String jumlahBarangText = jumlah_barang.getText().toString();
                String tipemember = tipe_pelanggan.getDisplay().toString();

                // Get the ID of the selected RadioButton in the RadioGroup
                int selectedRadioButtonId = tipe_pelanggan.getCheckedRadioButtonId();

                // Find the RadioButton by the selected ID
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                // Get the text of the selected RadioButton
                String tipemember1 = selectedRadioButton.getText().toString();

                if (inputText.isEmpty() || kodeBarang.isEmpty() || jumlahBarangText.isEmpty() || tipemember.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Silahkan isi terlebih dahulu boskuu!!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int jumlahBarang = Integer.parseInt(jumlahBarangText);

                long hargaBarang = getHarga(kodeBarang);
                if (hargaBarang == -1) {
                    Toast.makeText(MainActivity.this, "Kode barang tidak valid", Toast.LENGTH_SHORT).show();
                    return;
                }

                long totalHarga = hargaBarang * jumlahBarang;
                long diskonHarga = hitungDiskonHarga(totalHarga);
                long diskonMember = (long) hitungDiskonMember(totalHarga);

                long jumlahBayar = totalHarga - diskonHarga - diskonMember;

                Intent intent = new Intent(MainActivity.this, hasil.class);
                intent.putExtra("Nama Pelanggan", inputText);
                intent.putExtra("Tipe Pelanggan", inputText);
                intent.putExtra("Kode Barang", kodeBarang);
                intent.putExtra("Nama Barang", getNamaBarang(kodeBarang));
                intent.putExtra("Harga", hargaBarang);
                intent.putExtra("Jumlah Barang", jumlahBarang);
                intent.putExtra("Total Harga", totalHarga);
                intent.putExtra("Diskon Harga", diskonHarga);
                intent.putExtra("Diskon Member", diskonMember);
                intent.putExtra("Jumlah Bayar", jumlahBayar);
                startActivity(intent);
            }
        });
    }

    private long getHarga(String kodeBarang) {
        switch (kodeBarang) {
            case "LV3":
                return 6666666;
            case "OAS":
                return 1989123;
            case "MP3":
                return 28999999;
            default:
                return -1;
        }
    }

    private String getNamaBarang(String kodeBarang) {
        switch (kodeBarang) {
            case "LV3":
                return "LENOVO V14 GEN 3";
            case "OAS":
                return "OPPO A5s";
            case "MP3":
                return "Macbook Pro M3";
            default:
                return "";
        }
    }

    private long hitungDiskonHarga(long totalHarga) {
        if (totalHarga > 10000000) {
            return 100000;
        }
        return 0;
    }

    private double hitungDiskonMember(long totalHarga) {
        RadioButton radioButton = findViewById(tipe_pelanggan.getCheckedRadioButtonId());
        String membership = radioButton.getText().toString();
        switch (membership) {
            case "Gold":
                return (long) (totalHarga * 0.10);
            case "Silver":
                return (long) (totalHarga * 0.05);
            case "Biasa":
                return (long) (totalHarga * 0.02);
            default:
                return 0;
        }
    }
}
