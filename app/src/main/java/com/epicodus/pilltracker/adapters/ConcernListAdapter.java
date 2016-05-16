package com.epicodus.pilltracker.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.pilltracker.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by abigailrolling on 5/15/16.
 */
public class ConcernListAdapter extends RecyclerView.Adapter<ConcernListAdapter.ConcernViewHolder> {
    private List<String> mConcerns;
    private Context mContext;

    public ConcernListAdapter(Context context, List<String> concerns){
        this.mContext = context;
        this.mConcerns = concerns;
    }

    public ConcernListAdapter.ConcernViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.concern_list_item, parent, false);
        ConcernViewHolder viewHolder = new ConcernViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ConcernListAdapter.ConcernViewHolder holder, int position) {
        holder.bindConcern(mConcerns.get(position));
    }

    @Override
    public int getItemCount() {
        return mConcerns.size();
    }

    public class ConcernViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.concernContentTextView) TextView mConcernTextView;
        private Context mContext;

        public ConcernViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindConcern(String concern) {
            mConcernTextView.setText(concern);
        }
    }
}
