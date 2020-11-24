package com.example.dialogue.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.dialogue.R;
import com.example.dialogue.objects.MessageLog;
import com.example.dialogue.objects.Report;

import java.util.ArrayList;
import java.util.List;

public class reportAdapter extends ArrayAdapter<Report> {
    private  Context context;
    int resource;

    public reportAdapter(Context c, int resource, ArrayList<Report> objects) {
        super(c, resource, objects);

        context = c;
        this.resource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        Report report = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.tvNameReport);
        TextView tvMessage = (TextView) convertView.findViewById(R.id.tvMessageReport);

        tvName.setText(report.getUser());
        tvMessage.setText(report.getMessage());
        notifyDataSetChanged();
        return convertView;
    }
}
