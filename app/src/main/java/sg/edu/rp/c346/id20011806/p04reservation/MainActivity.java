package sg.edu.rp.c346.id20011806.p04reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText cusName;
        cusName = findViewById(R.id.customerName);
        EditText mobileNum;
        mobileNum = findViewById(R.id.mobileNumber);
        EditText groupSize;
        groupSize = findViewById(R.id.groupSize);
        RadioGroup smokingTable;
        smokingTable = findViewById(R.id.smokingTable);
        TimePicker tp;
        tp = findViewById(R.id.timePicker);
        DatePicker dp;
        dp = findViewById(R.id.datePicker);
        Button submitBtn;
        submitBtn = findViewById(R.id.submitBtn);
        Button resetBtn;
        resetBtn = findViewById(R.id.resetBtn);

        dp.updateDate(2020,5,1);
        tp.setCurrentHour(19);
        tp.setCurrentMinute(30);

        submitBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (cusName.getText().toString().length() == 0 || mobileNum.getText().toString().length() == 0 || groupSize.getText().toString().length() == 0) {
                    Context context = getApplicationContext();
                    String text = "Please fill in your details";
                    Toast.makeText(context, text, Toast.LENGTH_SHORT);
                } else {
                    Context context = getApplicationContext();
                    String text = "Thank you for reserving with us " + cusName.getText().toString();
                    if (smokingTable.getCheckedRadioButtonId() == R.id.smokingBtn) {
                        text += ", you have reserved a smoking table";
                    } else {
                        text += ", you have reserved a non-smoking table";
                    }
                    text += " for " + groupSize.getText().toString() + " people";
                    text += " under the contact number " + mobileNum.getText().toString();
                    text += " at " + String.format("%d:%02d", tp.getCurrentHour(), tp.getCurrentMinute());
                    text += " on " + dp.getDayOfMonth() + "/" + (dp.getMonth()+1) + "/" + dp.getYear();

                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                }
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dp.updateDate(2020,5,1);
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
                cusName.setText("");
                mobileNum.setText("");
                groupSize.setText("");
                smokingTable.clearCheck();
                Toast.makeText(getApplicationContext(), "All inputs cleared!", Toast.LENGTH_SHORT);
            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker v, int hourOfDay, int minute){
                if (hourOfDay > 20) {
                    tp.setCurrentHour(20);
                } else if (hourOfDay < 8) {
                    tp.setCurrentHour(8);
                }
            }
        });

        Calendar todaysDate = Calendar.getInstance();
        int currentYear = todaysDate.get(Calendar.YEAR);
        int currentMonth = todaysDate.get(Calendar.MONTH);
        int currentDay = todaysDate.get(Calendar.DAY_OF_MONTH);

        dp.updateDate(currentYear,currentMonth,currentDay+1);
        tp.setCurrentMinute(30);
        tp.setCurrentHour(19);
        dp.setMinDate(System.currentTimeMillis() + 86400000);
    }
}