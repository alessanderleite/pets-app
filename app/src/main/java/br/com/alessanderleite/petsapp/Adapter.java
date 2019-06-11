package br.com.alessanderleite.petsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.List;

import br.com.alessanderleite.petsapp.model.Pets;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> implements Filterable {

    List<Pets> pets;
    private Context context;

    public Adapter(List<Pets> pets, Context context) {
        this.pets = pets;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {

        holder.mName.setText(pets.get(position).getName());
        holder.mType.setText(pets.get(position).getSpecies());
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mName, mType;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.name);
            mType = (TextView) itemView.findViewById(R.id.type);
        }
    }
}
