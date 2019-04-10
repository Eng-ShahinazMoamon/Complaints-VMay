package com.example.shahi.citizenscomplaints.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shahi.citizenscomplaints.R;
import com.example.shahi.citizenscomplaints.UserDetails;

import java.util.HashMap;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.ViewHolder> {
    private HashMap<Object, HashMap> values;
    public Context ss;

    public ListUserAdapter(HashMap<Object, HashMap> values) {
        this.values = values;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ss = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.cName.setText(String.valueOf(values.get(values.keySet().toArray()[position]).get("userName")));
        holder.phone.setText(String.valueOf(values.get(values.keySet().toArray()[position]).get("userId")));
        holder.cId.setText(String.valueOf(values.get(values.keySet().toArray()[position]).get("userPhone")));
        holder.cAdd.setText(String.valueOf(values.get(values.keySet().toArray()[position]).get("userAddress")));


    }

    @Override
    public int getItemCount() {
        return values.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView cName, cId, cAdd, phone;

        ViewHolder(View itemView) {
            super(itemView);
            cName = itemView.findViewById(R.id.list_user1);
            cId = itemView.findViewById(R.id.list_user2);
            phone = itemView.findViewById(R.id.list_user3);
            cAdd = itemView.findViewById(R.id.list_user4);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            String cName = String.valueOf(values.get(values.keySet().toArray()[getAdapterPosition()]).get("userName"));
            String cId = String.valueOf(values.get(values.keySet().toArray()[getAdapterPosition()]).get("userId"));
            String cPhone = String.valueOf(values.get(values.keySet().toArray()[getAdapterPosition()]).get("userPhone"));
            String cAddress = String.valueOf(values.get(values.keySet().toArray()[getAdapterPosition()]).get("userAddress"));
            //Extra Data for user details page
            String instName = String.valueOf(values.get(values.keySet().toArray()[getAdapterPosition()]).get("institution Name"));
            String subName = String.valueOf(values.get(values.keySet().toArray()[getAdapterPosition()]).get("Subject"));
            String desName = String.valueOf(values.get(values.keySet().toArray()[getAdapterPosition()]).get("Description"));
            String comImage = String.valueOf(values.get(values.keySet().toArray()[getAdapterPosition()]).get("comPhoto"));


            Intent md = new Intent(ss, UserDetails.class);
            md.putExtra("userName", cName);
            md.putExtra("userId", cId);
            md.putExtra("userPhone", cPhone);
            md.putExtra("userAddress", cAddress);
            md.putExtra("institution Name", instName);
            md.putExtra("Subject", subName);
            md.putExtra("Description", desName);
            md.putExtra("comPhoto", comImage);

            ss.startActivity(md);

        }
    }

}



