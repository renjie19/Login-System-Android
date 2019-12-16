package com.example.testapplication.employee.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.R;
import com.example.testapplication.employee.model.Employee;

import java.util.List;

public class ManageEmployeeAdapter extends RecyclerView.Adapter<ManageEmployeeAdapter.ViewHolder> {

    private List<Employee> list;
    private ItemClick itemClick;

    public ManageEmployeeAdapter(List<Employee> list, ItemClick itemClick) {
        this.list = list;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_in_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view,this.itemClick);

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView name;
        ItemClick itemClick;

        public ViewHolder(@NonNull View itemView, ItemClick itemClick) {
            super(itemView);
            name = itemView.findViewById(R.id.employeeName);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            this.itemClick = itemClick;
        }

        @Override
        public void onClick(View v) {
            itemClick.itemClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            itemClick.longClickAction(getAdapterPosition());
            return false;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Employee employee = list.get(position);
        holder.name.setText(employee.getName());
    }

    public interface ItemClick{
        void itemClick(int position);
        void longClickAction(int position);
    }
}
