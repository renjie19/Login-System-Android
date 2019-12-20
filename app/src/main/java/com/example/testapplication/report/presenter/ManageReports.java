package com.example.testapplication.report.presenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.testapplication.R;
import com.example.testapplication.report.adapter.ReportAdapter;
import com.example.testapplication.report.model.Report;

import java.util.List;

public class ManageReports extends AppCompatActivity {

    private RecyclerView rv;
    List<Report> reports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reports_manage);
        reports = (List<Report>) getIntent().getSerializableExtra("report");
    }

    @Override
    protected void onResume() {
        super.onResume();
        rv = findViewById(R.id.reports_rv);
        ReportAdapter adapter = new ReportAdapter(reports);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
