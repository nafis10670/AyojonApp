package com.example.asus.ayojoon;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.SystemClock;
import android.provider.Settings;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimePicker extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Button dateButton, makeButton;
    TextView dateTextView, timeTextView,priceview;
    EditText mynameis ;
    private String totalmount ="",EventName ,timemine,Datemine;
        String check ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time_picker);

        totalmount = getIntent().getStringExtra("Total Price") ;

        dateButton = findViewById(R.id.dateButton);
        makeButton = findViewById(R.id.buttonforhistory) ;
        dateTextView = findViewById(R.id.dateview) ;
        timeTextView=findViewById(R.id.timeview) ;
       priceview=findViewById(R.id.totmount) ;
        mynameis = findViewById(R.id.eventname) ;
        CreateOwn create = new CreateOwn () ;
        check = create.version ;



        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("History") ;
        final DatabaseReference anotherref = cartListRef.child(check) ;







        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDateButton();
                handleTimeButton();

                makeButton.setEnabled(true);

            }
        });

       makeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                EventName = mynameis.getText().toString() ;
                anotherref.child("EventName").setValue(EventName) ;
                anotherref.child("Date").setValue(Datemine) ;
                anotherref.child("Time").setValue(timemine) ;

                Intent intent = new Intent(DateTimePicker.this, Payement.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        priceview.setText("Total Amount : "+totalmount);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(DateTimePicker.this, HomePage.class);
        startActivity(intent);


    }

    private void handleDateButton() {
        Calendar calendar = Calendar.getInstance();

        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
//                String dateString = year + " " + month + " " + date;
//                dateTextView.setText(dateString);

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);
                CharSequence dateCharSequence = DateFormat.format("MMM d, yyyy", calendar1);

                dateTextView.setText(dateCharSequence);
                Datemine = dateTextView.getText().toString() ;

            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();

    }

    private void handleTimeButton() {
        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);
        boolean is24HourFormat = DateFormat.is24HourFormat(this);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Log.i(TAG, "onTimeSet: " + hour + minute);
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR, hour);
                calendar1.set(Calendar.MINUTE, minute);
                CharSequence timeCharSequence = DateFormat.format("hh:mm a", calendar1);
                timeTextView.setText(timeCharSequence);
                timemine = timeTextView.getText().toString() ;

            }
        }, HOUR, MINUTE, is24HourFormat);

        timePickerDialog.show();

    }
}