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

import java.util.ArrayList;
/**
 * @author Jacob Nett
 */
public class MessageListAdapter extends ArrayAdapter<MessageLog> {
    private  Context context;
    int resource;

    public MessageListAdapter(Context c, int resource, ArrayList<MessageLog> objects) {
        super(c, resource,objects);
        context = c;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position,@NonNull View convertView,@NonNull ViewGroup parent) {
        MessageLog messageLog = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvMessage = (TextView) convertView.findViewById(R.id.tvMessage);

        tvName.setText(messageLog.getRecipient());
        tvMessage.setText(messageLog.getMostRecentMessage());
        notifyDataSetChanged();
        return convertView;
    }

}
