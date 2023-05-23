package com.example.animalislands;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final List<RecyclerViewItem> items;
    private final ArrayList<String> detailedItems;
    private final LayoutInflater layoutInflater;
    private final OnClickListener onClickListener;

    RecyclerViewAdapter(Context context, List<RecyclerViewItem> items, ArrayList<String> detailedItems, OnClickListener onClickListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.items = items;
        this.detailedItems = detailedItems;
        this.onClickListener = onClickListener;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Island> islandList) {
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.title);
        }

        @Override
        public void onClick(View view) {
            onClickListener.onClick(detailedItems.get(getAdapterPosition()));
        }
    }

    public interface OnClickListener {
        void onClick(String item);
    }
}