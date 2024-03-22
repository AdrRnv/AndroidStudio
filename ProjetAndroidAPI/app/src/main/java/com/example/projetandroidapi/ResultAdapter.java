package com.example.projetandroidapi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroidapi.model.Result;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    private List<Result> resultList;
    private Context context;

    public ResultAdapter(Context context, List<Result> resultList) {
        this.resultList = resultList;
        this.context = context;
    }

    public void addItems(List<Result> newItems) {
        int startPosition = resultList.size();
        Log.d("ListSize", String.valueOf(startPosition));
        resultList.addAll(newItems);
        notifyItemRangeInserted(startPosition, newItems.size());
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new ResultViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        Result result = resultList.get(position);
        holder.bind(result);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                String name = result.getName();
                String marque = result.getMarque();
                String commune = result.getMetaNameCom();
                builder.setMessage("Nom : " + name +
                                "\n" + "Marque : " + marque +
                                "\n" + "Commune : " + commune
                        )
                        .setTitle("Informations :");
                AlertDialog dialog = builder.create();
                dialog.show();;
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public static class ResultViewHolder extends RecyclerView.ViewHolder {

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

            String name = result.getName();
            if (name != null) {
                nameTextView.setText(name);
            } else {
                nameTextView.setText("N/A");
            }

            Integer capacity = result.getCapacity();
            if (capacity != null) {
                capacityTextView.setText(capacity+"");
            } else {
                capacityTextView.setText("N/A");
            }

            String dep = result.getMetaCodeCom();
            if (capacity != null) {
                depTextView.setText(dep);
            } else {
                depTextView.setText("");
            }

            String commune = result.getMetaNameCom();
            if (capacity != null) {
                textNameCom.setText(commune);
            } else {
                textNameCom.setText("N/A");
            }

            String marque = result.getMarque();
            if (capacity != null) {
                marqueTextView.setText(marque);
            } else {
                marqueTextView.setText("N/A");
            }


        }
    }
}
