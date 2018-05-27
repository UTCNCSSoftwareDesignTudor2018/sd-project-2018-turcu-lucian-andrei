package com.utcn.medpat.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.utcn.medpat.R;
import com.utcn.medpat.model.Message;

import java.util.List;

/**
 * Created by Lucian on 5/22/2018.
 */

public class MessageAdapter extends ArrayAdapter<Message> {

    public MessageAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public MessageAdapter(Context context, int resource, List<Message> items) {
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

        Message m = getItem(position);

        if (m != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.mainField);
            TextView tt2 = (TextView) v.findViewById(R.id.subfield1);
            TextView tt3 = (TextView) v.findViewById(R.id.subfield2);

            if (tt1 != null) {
                tt1.setText(String.valueOf(m.getFrom().getUsername()));
            }

            if (tt2 != null) {
                tt2.setText(m.getDate());
            }

            if (tt3 != null) {
                tt3.setText(m.getMessage());
            }
        }

        return v;
    }

}
