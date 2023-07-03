package com.example.pe_prm392_phamphuson;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pe_prm392_phamphuson.DAO.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryHolder> {
    private List<Country> countries = new ArrayList<>();

    public void setCountries(List<Country> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_rcv, parent, false);
        return new CountryHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, int position) {
        holder.tv_id.setText(String.valueOf(countries.get(position).getId()));
        holder.tv_name.setText(countries.get(position).getName());
        holder.tv_rank.setText(String.valueOf(countries.get(position).getRank()));
        holder.tv_gdppc.setText(String.valueOf(countries.get(position).getGdppc()));
        holder.tv_year.setText(countries.get(position).getYear());
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    class CountryHolder extends RecyclerView.ViewHolder {
        TextView tv_id;
        TextView tv_name;
        TextView tv_rank;
        TextView tv_gdppc;
        TextView tv_year;
        public CountryHolder(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_rank = itemView.findViewById(R.id.tv_rank);
            tv_gdppc = itemView.findViewById(R.id.tv_gdppc);
            tv_year = itemView.findViewById(R.id.tv_year);
        }
    }
}
