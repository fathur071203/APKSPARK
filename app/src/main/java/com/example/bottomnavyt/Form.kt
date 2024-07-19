package com.example.bottomnavyt

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class Form : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        val dateField: EditText = findViewById(R.id.dateField)
        val timeRangeField: EditText = findViewById(R.id.timeRangeField)

        dateField.setOnClickListener {
            showDatePickerDialog(dateField)
        }

        timeRangeField.setOnClickListener {
            showTimeRangePickerDialog(timeRangeField)
        }

        val saveButton: Button = findViewById(R.id.submitButton)
        saveButton.setOnClickListener {
            val nomorPlatField: EditText = findViewById(R.id.nomorPlatField)
            val namaPemesanField: EditText = findViewById(R.id.namaPemesanField)
            val jenisMobilField: EditText = findViewById(R.id.jenisMobilField)
            val tempatParkirField: EditText = findViewById(R.id.tempatParkirField)

            val intent = Intent(this, ReceiptActivity::class.java)
            intent.putExtra("TANGGAL", dateField.text.toString())
            intent.putExtra("NOMOR_TRANSAKSI", generateTransactionNumber())
            intent.putExtra("NOMOR_PLAT", nomorPlatField.text.toString())
            intent.putExtra("NAMA_PEMESAN", namaPemesanField.text.toString())
            intent.putExtra("JENIS_MOBIL", jenisMobilField.text.toString())
            intent.putExtra("WAKTU", timeRangeField.text.toString())
            intent.putExtra("TEMPAT_PARKIR", tempatParkirField.text.toString())
            startActivity(intent)
        }
    }

    private fun showDatePickerDialog(dateField: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                dateField.setText(formattedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun showTimeRangePickerDialog(timeRangeField: EditText) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialogStart = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                val formattedStartTime = String.format("%02d:%02d", selectedHour, selectedMinute)

                val timePickerDialogEnd = TimePickerDialog(
                    this,
                    { _, endHour, endMinute ->
                        val formattedEndTime = String.format("%02d:%02d", endHour, endMinute)
                        val formattedTimeRange = "$formattedStartTime - $formattedEndTime"
                        timeRangeField.setText(formattedTimeRange)
                    },
                    hour,
                    minute,
                    true
                )
                timePickerDialogEnd.show()
            },
            hour,
            minute,
            true
        )
        timePickerDialogStart.show()
    }

    private fun generateTransactionNumber(): String {
        // Implement logic to generate a unique transaction number
        return "TRX" + System.currentTimeMillis().toString()
    }
}
