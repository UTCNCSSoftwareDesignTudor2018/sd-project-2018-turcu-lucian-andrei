package com.utcn.medpat.presentation.fragment;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.TimePicker;

import com.utcn.medpat.R;
import com.utcn.medpat.presentation.activity.TimePickerInterface;

import org.w3c.dom.Text;

import java.util.Calendar;

/**
 * Created by Lucian on 5/23/2018.
 */

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    public TimePickerInterface delegate = null;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        int hour, minute;

        try {
            hour = getArguments().getInt("hour");
            minute = getArguments().getInt("minute");

        } catch (Exception e) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            hour = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);
        }

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                /*DateFormat.is24HourFormat(getActivity())*/true);
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        delegate.onTimeSelected(hourOfDay, minute);
    }

}
