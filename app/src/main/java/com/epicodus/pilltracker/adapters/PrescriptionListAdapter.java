package com.epicodus.pilltracker.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.pilltracker.R;
import com.epicodus.pilltracker.models.Prescription;

import java.util.ArrayList;

/**
 * Created by abigailrolling on 5/1/16.
 */
public class PrescriptionListAdapter extends RecyclerView.Adapter<PrescriptionViewHolder> {
    private Context mContext;
    private ArrayList<Prescription> mPrescriptions = new ArrayList<>();

    public PrescriptionListAdapter(Context context, ArrayList<Prescription> prescriptions){
        this.mContext = context;
        this.mPrescriptions = prescriptions;
    }

    @Override
    public PrescriptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prescription_list_item, parent, false);
        PrescriptionViewHolder viewHolder = new PrescriptionViewHolder(view, mPrescriptions);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PrescriptionViewHolder holder, int position) {
        holder.bindPrescription(mPrescriptions.get(position));
    }

    @Override
    public int getItemCount(){
        return mPrescriptions.size();
    }


}
