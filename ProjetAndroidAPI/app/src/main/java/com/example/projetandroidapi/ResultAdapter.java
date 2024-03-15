package com.example.projetandroidapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroidapi.model.Result;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    private List<Result> resultList;

    public ResultAdapter(List<Result> resultList) {
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate your item layout and create a ViewHolder
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new ResultViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        // Bind data to your ViewHolder
        Result result = resultList.get(position);
        holder.bind(result);
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView, marqueTextView, depTextView, capacityTextView, textNameCom;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textName);
            marqueTextView = itemView.findViewById(R.id.textMarque);
            depTextView = itemView.findViewById(R.id.textDep);
            capacityTextView = itemView.findViewById(R.id.textCapacity);
            textNameCom = itemView.findViewById(R.id.textNameCom);
        }

        public void bind(Result result) {
            nameTextView.setText(result.getName());
            marqueTextView.setText(String.valueOf(result.getMarque()));
            depTextView.setText(String.valueOf(result.getMetaCodeCom()));
            capacityTextView.setText(String.valueOf(result.getCapacity()));
            textNameCom.setText(String.valueOf(result.getMetaNameCom()));
        }
    }
}
