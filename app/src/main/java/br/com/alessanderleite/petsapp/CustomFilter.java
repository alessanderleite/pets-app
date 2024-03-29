package br.com.alessanderleite.petsapp;

import android.widget.Filter;

import java.util.ArrayList;

import br.com.alessanderleite.petsapp.model.Pet;

public class CustomFilter extends Filter {

    Adapter adapter;
    ArrayList<Pet> filterList;

    public CustomFilter(ArrayList<Pet> filterList, Adapter adapter) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        //check constraint validity
        if (constraint != null && constraint.length() > 0) {

            //change to upper
            constraint = constraint.toString().toUpperCase();
            //store our filtered players
            ArrayList<Pet> filteredPets = new ArrayList<>();

            for (int i = 0; i < filterList.size(); i++) {

                //check
                if (filterList.get(i).getName().toUpperCase().contains(constraint)) {
                    //add player to filtered players
                    filteredPets.add(filterList.get(i));
                }
            }

            results.count = filteredPets.size();
            results.values = filteredPets;

        } else {
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.pets = (ArrayList<Pet>) results.values;

        //refresh
        adapter.notifyDataSetChanged();
    }
}
