package com.epicodus.pilltracker.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.pilltracker.R;
import com.epicodus.pilltracker.models.Prescription;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrescriptionDetailFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.brandDetailTextView) TextView mBrandDetailTextView;
    @Bind(R.id.genericDetailTextView) TextView mGenericDetailTextView;
    @Bind(R.id.strengthDetailTextView) TextView mStrengthDetailTextView;
    @Bind(R.id.appearanceDetailTextView) TextView mAppearanceDetailTextView;
    @Bind(R.id.indicationDetailTextView) TextView mIndicationDetailTextView;
    @Bind(R.id.frequencyDetailTextView) TextView mFrequencyDetailTextView;
    @Bind(R.id.quantityDoseDetailTextView) TextView mQuantityDoseDetailTextView;
    @Bind(R.id.questionsRecyclerView) RecyclerView mQuestionsRecyclerView;
    @Bind(R.id.notesRecyclerView) RecyclerView mNotesRecyclerView;
    @Bind(R.id.addQuestionButton) Button mAddQuestionButton;
    @Bind(R.id.addNoteButton) Button mAddNoteButton;

    private Prescription mPrescription;


    public PrescriptionDetailFragment() {
        // Required empty public constructor
    }

    public static PrescriptionDetailFragment newInstance(Prescription prescription) {
        PrescriptionDetailFragment prescriptionDetailFragment = new PrescriptionDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("prescription", Parcels.wrap(prescription));
        prescriptionDetailFragment.setArguments(args);
        return prescriptionDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPrescription = Parcels.unwrap(getArguments().getParcelable("prescription"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_prescription_detail, container, false);
        ButterKnife.bind(this, view);

        mBrandDetailTextView.setText(mPrescription.getBrandName());
        mGenericDetailTextView.setText(android.text.TextUtils.join(", ", mPrescription.getActiveIngredients()));
        mStrengthDetailTextView.setText(mPrescription.getStrength());
        mAppearanceDetailTextView.setText(mPrescription.getAppearance());
        mIndicationDetailTextView.setText(mPrescription.getIndication());
        mFrequencyDetailTextView.setText(mPrescription.showPrettyFrequency());
        mQuantityDoseDetailTextView.setText(mPrescription.showPrettyQuantityPerDose());

        mAddNoteButton.setOnClickListener(this);
        mAddQuestionButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.addQuestionButton:
                //todo: add question logic here
                break;
            case R.id.addNoteButton:
                //todo: add note logic here
                break;
            default:
                break;
        }
    }

}
