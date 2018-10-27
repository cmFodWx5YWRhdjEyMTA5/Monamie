package am.monamie.shop.view.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import am.monamie.shop.R;

@SuppressLint("ValidFragment")
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = DatePickerFragment.class.getName();
    private TextView birdOfDay;
    private Context context;
    private String isoDate;

    public DatePickerFragment(TextView birdOfDay, Context context) {
        this.birdOfDay = birdOfDay;
        this.context = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(context, this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        birdOfDay.setText(context.getResources().getString(R.string.user_registration_day).concat(":").concat(String.valueOf(day)).concat(" / ").concat(
                context.getResources().getString(R.string.user_registration_month)).concat(":").concat(String.valueOf(month+1)).concat(" / ").concat(
                context.getResources().getString(R.string.user_registration_year)).concat(":").concat(String.valueOf(year)));
    }
}
