package com.utcn.medpat.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.utcn.medpat.R;
import com.utcn.medpat.model.Prescription;

import java.util.List;

/**
 * Created by Lucian on 5/22/2018.
 */

public class PrescriptionMedicAdapter extends ArrayAdapter<Prescription> {

    public PrescriptionMedicAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public PrescriptionMedicAdapter(Context context, int resource, List<Prescription> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.single_message, null);
        }

        Prescription p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.from);
            TextView tt2 = (TextView) v.findViewById(R.id.date);
            TextView tt3 = (TextView) v.findViewById(R.id.message);

            if (tt1 != null) {
                tt1.setText(String.valueOf(p.getMedic().getName()));
            }

            if (tt2 != null) {
                tt2.setText(p.getCreationDate());
            }

            if (tt3 != null) {
                tt3.setText(p.getDisease());
            }
        }

        return v;
    }
}
