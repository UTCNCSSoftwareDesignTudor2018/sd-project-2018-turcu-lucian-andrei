package com.utcn.medpat.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.utcn.medpat.R;
import com.utcn.medpat.model.Appointment;

import java.util.List;

/**
 * Created by Lucian on 5/22/2018.
 */

public class AppointmentPatientAdapter extends ArrayAdapter<Appointment> {

    public AppointmentPatientAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public AppointmentPatientAdapter(Context context, int resource, List<Appointment> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.single_list_item, null);
        }

        Appointment a = getItem(position);

        if (a != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.mainField);
            TextView tt2 = (TextView) v.findViewById(R.id.subfield1);
            TextView tt3 = (TextView) v.findViewById(R.id.subfield2);

            if (tt1 != null) {
                tt1.setText(String.valueOf(a.getPatient().getName()));
            }

            if (tt2 != null) {
                tt2.setText(a.getDate());
            }

            if (tt3 != null) {
                tt3.setText(a.getLocation());
            }
        }

        return v;
    }
}
