package com.example.bottomnavyt

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReceiptActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt)

        val tanggalTextView: TextView = findViewById(R.id.tanggalTextView)
        val nomorTransaksiTextView: TextView = findViewById(R.id.nomorTransaksiTextView)
        val nomorPlatTextView: TextView = findViewById(R.id.nomorPlatTextView)
        val namaPemesanTextView: TextView = findViewById(R.id.namaPemesanTextView)
        val jenisMobilTextView: TextView = findViewById(R.id.jenisMobilTextView)
        val waktuTextView: TextView = findViewById(R.id.waktuTextView)
        val tempatParkirTextView: TextView = findViewById(R.id.tempatParkirTextView)

        val intent = intent
        tanggalTextView.text = "Tanggal: " + intent.getStringExtra("TANGGAL")
        nomorTransaksiTextView.text = "Nomor Transaksi: " + intent.getStringExtra("NOMOR_TRANSAKSI")
        nomorPlatTextView.text = "Nomor Plat: " + intent.getStringExtra("NOMOR_PLAT")
        namaPemesanTextView.text = "Nama Pemesan: " + intent.getStringExtra("NAMA_PEMESAN")
        jenisMobilTextView.text = "Jenis Mobil: " + intent.getStringExtra("JENIS_MOBIL")
        waktuTextView.text = "Waktu: " + intent.getStringExtra("WAKTU")
        tempatParkirTextView.text = "Tempat Parkir: " + intent.getStringExtra("TEMPAT_PARKIR")
    }

    fun onOkButtonClicked(view: android.view.View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
