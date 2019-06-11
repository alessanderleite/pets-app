package br.com.alessanderleite.petsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.alessanderleite.petsapp.model.Pets;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> implements Filterable {

    List<Pets> pets, petsFilter;
    private Context context;
    private RecyclerViewClickListener mListener;
    CustomFilter filter;

    public Adapter(List<Pets> pets, Context context, RecyclerViewClickListener listener) {
        this.pets = pets;
        this.petsFilter = pets;
        this.context = context;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view, mListener);
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
        if (filter == null) {
            filter = new CustomFilter((ArrayList<Pets>) petsFilter, this);
        }
        return filter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener mListener;
        private TextView mName, mType;
        private RelativeLayout mRowContainer;

        public MyViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.name);
            mType = (TextView) itemView.findViewById(R.id.type);
            mRowContainer = (RelativeLayout) itemView.findViewById(R.id.row_container);

            mListener = listener;
            mRowContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.row_container:
                    mListener.onRowClick(mRowContainer, getAdapterPosition());
                    break;
                default:
                    break;
            }
        }
    }

    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
    }
}
