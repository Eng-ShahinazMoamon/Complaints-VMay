package com.example.shahi.citizenscomplaints.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shahi.citizenscomplaints.R;

import java.util.HashMap;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder>{

    private HashMap<String, String> values;
    public Context ss;
    public ProfileAdapter(HashMap<String, String> values) {
        this.values = values;
    }


    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ss= parent.getContext();
        return new ProfileAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_view,parent,false));
    }

    @Override
    public void onBindViewHolder(ProfileAdapter.ViewHolder holder, int position) {
        holder.value.setText(values.get(values.keySet().toArray()[position]));
        holder.label.setText((CharSequence) values.keySet().toArray()[position]);

	/* for valueTextView
		holder.value.setText(values.get(values.keySet().toArray()[position]));*/
    }

    @Override
    public int getItemCount() {
        return values.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView label , value;
        ViewHolder(View itemView)
        {
            super(itemView);
            label = (TextView) itemView.findViewById(R.id.acc_text1);
            value = (TextView) itemView.findViewById(R.id.acc_text2);
        }

    }
}

