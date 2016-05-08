package com.epicodus.pilltracker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.pilltracker.R;
import com.epicodus.pilltracker.models.Prescription;
import com.epicodus.pilltracker.util.FirebaseRecyclerAdapter;
import com.firebase.client.Query;

/**
 * Created by abigailrolling on 5/7/16.
 */
public class FirebasePrescriptionListAdapter extends FirebaseRecyclerAdapter<PrescriptionViewHolder, Prescription> {

    public FirebasePrescriptionListAdapter(Query query, Class<Prescription> itemClass){
        super(query, itemClass);
    }

    @Override
    public PrescriptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.prescription_list_item, parent, false);
        return new PrescriptionViewHolder(view, getItems());
    }

    @Override
    public void onBindViewHolder(PrescriptionViewHolder holder, int position) {
        holder.bindPrescription(getItem(position));
    }

    @Override
    protected void itemAdded(Prescription item, String key, int position) {

    }

    @Override
    protected void itemChanged(Prescription oldItem, Prescription newItem, String key, int position) {

    }

    @Override
    protected void itemRemoved(Prescription item, String key, int position) {

    }

    @Override
    protected void itemMoved(Prescription item, String key, int oldPosition, int newPosition) {

    }
}
