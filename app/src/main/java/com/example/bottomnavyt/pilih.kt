package com.example.bottomnavyt

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class pilih : AppCompatActivity() {

    data class Seat(var state: Int)

    companion object {
        const val BOOKED = 0
        const val AVAILABLE = 1
        const val SELECTED = 2
    }

    private lateinit var seats: MutableMap<String, Seat>
    private lateinit var seatImageViews: MutableMap<String, ImageView>
    private var currentlySelectedSeat: Pair<String, ImageView>? = null
    var btnparkir: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih)
        btnparkir = findViewById<Button>(R.id.btnpilih)
        btnparkir?.setOnClickListener(View.OnClickListener {
            val i = Intent(this@pilih, Form::class.java)
            startActivity(i)
            finish()
        })

        seats = mutableMapOf()
        seatImageViews = mutableMapOf()

        // Initialize seats
        initializeSeats()

        // Initialize ImageViews
        initializeSeatImageViews()

        // Set initial seat images based on their state
        setInitialSeatImages()

        // Set up click listeners
        setupClickListeners()
    }

    private fun initializeSeats() {
        for (i in 1..51) {
            val seatId = "seat_$i"
            seats[seatId] = Seat(AVAILABLE) // Ganti dengan status kursi yang diinginkan (AVAILABLE/BOOKED)
        }
    }

    private fun initializeSeatImageViews() {
        for (i in 1..51) {
            val seatId = "seat_$i"
            val resId = resources.getIdentifier(seatId, "id", packageName)
            seatImageViews[seatId] = findViewById(resId)
        }
    }


    private fun setInitialSeatImages() {
        for ((seatId, seat) in seats) {
            val seatImageView = seatImageViews[seatId] ?: continue
            when (seat.state) {
                BOOKED -> seatImageView.setImageResource(R.drawable.booked_img)
                AVAILABLE -> seatImageView.setImageResource(R.drawable.available_img)
                SELECTED -> seatImageView.setImageResource(R.drawable.your_seat_img)
            }
        }
    }

    private fun setupClickListeners() {
        for ((seatId, seatImageView) in seatImageViews) {
            seatImageView.setOnClickListener {
                val seat = seats[seatId] ?: return@setOnClickListener
                when (seat.state) {
                    BOOKED -> {
                        // Do nothing or show a message
                    }
                    AVAILABLE -> {
                        currentlySelectedSeat?.let {
                            // Reset previously selected seat to available
                            val (previousSeatId, previousImageView) = it
                            seats[previousSeatId]?.state = AVAILABLE
                            previousImageView.setImageResource(R.drawable.available_img)
                        }
                        // Update the newly selected seat
                        seat.state = SELECTED
                        seatImageView.setImageResource(R.drawable.your_seat_img)
                        currentlySelectedSeat = Pair(seatId, seatImageView)
                    }
                    SELECTED -> {
                        // Deselect the currently selected seat
                        seat.state = AVAILABLE
                        seatImageView.setImageResource(R.drawable.available_img)
                        currentlySelectedSeat = null
                    }
                }
            }
        }
    }
}
