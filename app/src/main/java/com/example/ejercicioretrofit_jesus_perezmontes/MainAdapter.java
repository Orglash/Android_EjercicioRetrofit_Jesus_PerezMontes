package com.example.ejercicioretrofit_jesus_perezmontes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RyMViewHolder> {

    private List<Result> list;
    private Context context;


    public MainAdapter(Context context, ArrayList<Result>list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RyMViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RyMViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_rym, parent, false));
    }

    @Override
    public void onBindViewHolder(RyMViewHolder holder, final int position) {
        final Result pokemon = list.get(position);
        holder.tvName.setText(pokemon.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class RyMViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public RyMViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}