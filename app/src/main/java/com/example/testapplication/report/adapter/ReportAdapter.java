package com.example.testapplication.report.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.R;
import com.example.testapplication.report.model.Report;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {
    List<Report> reports;

    public ReportAdapter(List<Report> reports) {
        this.reports = reports;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Report report = reports.get(position);
        DateFormat dateFormat = DateFormat.getDateInstance();
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String date = dateFormat.format(new Date(report.getTimeInLog().getTime()));
        String login = "IN: " + timeFormat.format(new Date(report.getTimeInLog().getTime()));
        String logout = "OUT: ";
        if ((report.getTimeOutLog() != null)) {
            logout += timeFormat.format(new Date(report.getTimeOutLog().getTime()));
        }

        holder.date.setText(date);
        holder.login.setText(login);
        holder.logout.setText(logout);
        holder.total.setText(String.format("Total Hours: %s",report.getTotalHours()));
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView date;
        private TextView login;
        private TextView logout;
        private TextView total;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.reportDate);
            login = itemView.findViewById(R.id.loginDetails);
            logout = itemView.findViewById(R.id.logOutDetails);
            total = itemView.findViewById(R.id.totalHours);
        }
    }
}
