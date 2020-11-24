package com.example.dialogue.Utils;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.dialogue.R;
import com.example.dialogue.objects.MessageLog;
import com.example.dialogue.objects.Messages;

import java.util.ArrayList;
/**
 * @author Jacob Nett
 */
public class LogListAdapter extends ArrayAdapter{
    private  Context context;
    int resource;


    public LogListAdapter(Context c, int resource, ArrayList<Messages> objects) {
        super(c, resource, objects);
        context = c;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        Messages messages = (Messages) getItem(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.tvNameUM);
        TextView tvMessage = (TextView) convertView.findViewById(R.id.tvMessageUM);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);

        tvName.setText(messages.getSender());
        tvMessage.setText(messages.getData());
        tvDate.setText(messages.getDate());
        notifyDataSetChanged();
        return convertView;
    }

}
