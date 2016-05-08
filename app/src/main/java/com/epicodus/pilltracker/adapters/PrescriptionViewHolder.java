package com.epicodus.pilltracker.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.pilltracker.R;
import com.epicodus.pilltracker.models.Prescription;
import com.epicodus.pilltracker.ui.PrescriptionDetailActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by abigailrolling on 5/7/16.
 */
public class PrescriptionViewHolder extends RecyclerView.ViewHolder{
    private Context mContext;
    @Bind(R.id.brandNameTextView) TextView mBrandTextView;
    @Bind(R.id.genericNameTextView) TextView mGenericTextView;
    @Bind(R.id.dosagesTextView) TextView mDoseTextView;
    @Bind(R.id.indicationTextView) TextView mIndicationTextView;

    private ArrayList<Prescription> mPrescriptions = new ArrayList<>();

    public PrescriptionViewHolder(View itemView, ArrayList<Prescription> prescriptions){
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mPrescriptions = prescriptions;

        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, PrescriptionDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("prescriptions", Parcels.wrap(mPrescriptions));
                mContext.startActivity(intent);
            }
        });
    }

    public void bindPrescription(Prescription prescription){
        mBrandTextView.setText(prescription.getBrandName());
        mGenericTextView.setText(android.text.TextUtils.join(", ", prescription.getActiveIngredients()));
        mDoseTextView.setText(prescription.getStrength());
        mIndicationTextView.setText(prescription.getIndication());
    }
}
