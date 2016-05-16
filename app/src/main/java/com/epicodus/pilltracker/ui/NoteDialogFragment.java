package com.epicodus.pilltracker.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.pilltracker.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class NoteDialogFragment extends DialogFragment implements TextView.OnEditorActionListener {
    @Bind(R.id.addNoteEditText) EditText mAddNoteEditText;

    public interface NoteDialogListener {
        void onFinishEditDialog(String inputText);
    }

    public NoteDialogFragment() {
        // Required empty public constructor
    }

    public static NoteDialogFragment newInstance(String title) {
        NoteDialogFragment frag = new NoteDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        return inflater.inflate(R.layout.fragment_note_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mAddNoteEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        mAddNoteEditText.setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            NoteDialogListener listener = (NoteDialogListener) getTargetFragment();
            listener.onFinishEditDialog(mAddNoteEditText.getText().toString());
            dismiss();
            return true;
        }
        return false;
    }

}
