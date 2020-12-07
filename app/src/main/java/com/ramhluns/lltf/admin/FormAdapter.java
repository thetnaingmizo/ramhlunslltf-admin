package com.ramhluns.lltf.admin;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.ViewHolder> {
    private List<FormModel> values;
    private ItemClickListener mListener;

    public FormAdapter(List<FormModel> myDataset, ItemClickListener listener) {
        values = myDataset;
        mListener = listener;
    }

    @Override
    public FormAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_form, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final FormModel item = values.get(position);
        holder.tvHming.setText(item.hming);
        holder.tvSection.setText(item.section);
        holder.tvChhuahni.setText(item.chhuahni + " " + item.chhuahhnuDarkar);
        int statusImg = R.drawable.ic_pending;
        if (item.status.equalsIgnoreCase("reject"))
            statusImg = R.drawable.ic_reject;
        else if (item.status.equalsIgnoreCase("approved"))
            statusImg = R.drawable.ic_approved;
        holder.ivStatus.setImageResource(statusImg);
        holder.layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(view, item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvHming;
        public TextView tvSection;
        public TextView tvChhuahni;
        public ImageView ivStatus;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            tvHming = v.findViewById(R.id.tvHming);
            tvSection = v.findViewById(R.id.tvSection);
            tvChhuahni = v.findViewById(R.id.tvChhuahni);
            ivStatus = v.findViewById(R.id.ivStatus);
        }
    }


    public interface ItemClickListener {
        public void onItemClick(View view, FormModel item);
    }
}
