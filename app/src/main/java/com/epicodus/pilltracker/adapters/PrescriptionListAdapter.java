package com.epicodus.pilltracker.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.pilltracker.R;
import com.epicodus.pilltracker.models.Prescription;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by abigailrolling on 5/1/16.
 */
public class PrescriptionListAdapter extends RecyclerView.Adapter<PrescriptionListAdapter.PrescriptionViewHolder> {
    private Context mContext;
    private ArrayList<Prescription> mPrescriptions = new ArrayList<>();

    public PrescriptionListAdapter(Context context, ArrayList<Prescription> prescriptions){
        this.mContext = context;
        this.mPrescriptions = prescriptions;
    }

    @Override
    public PrescriptionListAdapter.PrescriptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prescription_list_item, parent, false);
        PrescriptionViewHolder viewHolder = new PrescriptionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PrescriptionListAdapter.PrescriptionViewHolder holder, int position) {
        holder.bindPrescription(mPrescriptions.get(position));
    }

    @Override
    public int getItemCount(){
        return mPrescriptions.size();
    }

    public class PrescriptionViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        @Bind(R.id.brandNameTextView) TextView mBrandTextView;
        @Bind(R.id.genericNameTextView) TextView mGenericTextView;
        @Bind(R.id.dosagesTextView) TextView mDoseTextView;
        @Bind(R.id.indicationTextView) TextView mIndicationTextView;

        public PrescriptionViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindPrescription(Prescription prescription){
            mBrandTextView.setText(prescription.getmBrandName());
            mGenericTextView.setText(android.text.TextUtils.join(", ", prescription.getmActiveIngredients()));
            mDoseTextView.setText(prescription.getmStrength());
            mIndicationTextView.setText(prescription.getmIndication());
        }
    }
}
