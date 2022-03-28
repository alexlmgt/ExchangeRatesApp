package com.example.exchangeratesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder>{
    private final LayoutInflater inflater;
    private final List<Valute> valutes;


    public CurrencyAdapter(Context context, List<Valute> valutes) {
        this.inflater = LayoutInflater.from(context);
        this.valutes = valutes;
    }

    @NonNull
    @Override
    public CurrencyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.currency_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyAdapter.ViewHolder holder, int position) {
        Valute valute = valutes.get(position);
        holder.valueView.setText(valute.getValue_n());
        holder.nameView.setText(valute.getName_n());
        holder.valuteView.setText(valute.getValute_n());
    }

    @Override
    public int getItemCount() {
        return valutes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView valuteView, nameView, valueView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            valueView = (TextView)itemView.findViewById(R.id.value_n);
            nameView = (TextView)itemView.findViewById(R.id.name_n);
            valuteView = (TextView)itemView.findViewById(R.id.valute_n);;
        }
    }
}
