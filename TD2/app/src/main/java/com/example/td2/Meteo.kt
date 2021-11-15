package com.example.td2

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.health.TimerStat
import java.text.SimpleDateFormat

import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.util.Log
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import android.widget.Toast

import android.content.DialogInterface





class Meteo : AppCompatActivity(),AdapterView.OnItemSelectedListener{

    var etatsDuCiel = arrayOf("Dégagé", "Couvert", "Pluvieux")
    var dateString = "";
    var heureSrting = "";
    var etatDuCiel = "";
    var donnes : String = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meteo)

        var datePicker = findViewById<EditText>(R.id.datePicker);
        var timerPicker = findViewById<EditText>(R.id.timerPicker);
        var myCalendar1 = Calendar.getInstance();
        var myCalendar2 = Calendar.getInstance();
        var spinner = findViewById<Spinner>(R.id.spinner)
        var enregister = findViewById<Button>(R.id.enregistrer)


        val date = OnDateSetListener { view, year, monthOfYear, dayOfMonth -> // TODO Auto-generated method stub
            myCalendar1[Calendar.YEAR] = year
            myCalendar1[Calendar.MONTH] = monthOfYear
            myCalendar1[Calendar.DAY_OF_MONTH] = dayOfMonth
            val myFormat = "MM/dd/yy" //In which you need put here
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            datePicker.setText(sdf.format(myCalendar1.getTime()))
            dateString = sdf.format(myCalendar1.getTime());
        }

        datePicker.setOnClickListener {
            DatePickerDialog(
                this@Meteo, date, myCalendar1[Calendar.YEAR], myCalendar1[Calendar.MONTH],
                myCalendar1[Calendar.DAY_OF_MONTH]
            ).show()
        }

        timerPicker.setOnClickListener {
            val hour = myCalendar2[Calendar.HOUR_OF_DAY]
            val minute = myCalendar2    [Calendar.MINUTE]

            val mTimePicker: TimePickerDialog = TimePickerDialog(
                this,
                { timePicker, selectedHour, selectedMinute -> timerPicker.setText("$selectedHour:$selectedMinute");heureSrting="$selectedHour:$selectedMinute"; },
                hour,
                minute,
                true
            )
            mTimePicker.setTitle("Select Time")
            mTimePicker.show()
        }


        spinner!!.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this@Meteo, android.R.layout.simple_spinner_item, etatsDuCiel)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner!!.setAdapter(aa)

        enregister.setOnClickListener {

            var temperature: EditText = findViewById(R.id.temperature);
            var vent: EditText = findViewById(R.id.vent)

            donnes =
                "\nDate : " + dateString + "\n\n" +
                "Heure : " + heureSrting + "\n\n" +
                "Etat du ciel : " + etatDuCiel + "\n\n" +
                "Température : " + temperature.text + "\n\n" +
                "Vitesse du vent : " + vent.text + "\n\n";

            alertDialog()
        }

    }

    private fun alertDialog() {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(this)
        dialog.setMessage(donnes)
        dialog.setTitle("Données enrégistrés : ")

        val alertDialog: AlertDialog = dialog.create()
        alertDialog.show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        etatDuCiel= etatsDuCiel[position];
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}