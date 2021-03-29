package com.stoffe.ibs2.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.tabs.TabLayout;
import com.stoffe.ibs2.R;
import com.stoffe.ibs2.data.DataType;
import com.stoffe.ibs2.data.Day;
import com.stoffe.ibs2.data.DayViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class DashboardFragment extends Fragment {

    private DayViewModel viewModel;
    private LineChart chart;
    private PieChart pieChart;
    private List<Day> days;
    ArrayList<ILineDataSet> dataSets;
    private DataType currentDataType;
    private int currentDataLimit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel =
                ViewModelProviders.of(requireActivity()).get(DayViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        chart = root.findViewById(R.id.chart);
        pieChart = root.findViewById(R.id.foodChart);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        days = viewModel.getDays();

        currentDataType = DataType.ALL;
        currentDataLimit = 7;
        init(currentDataLimit, currentDataType);
        initPieChart();

        TabLayout tabLayoutDataLimit = view.findViewById(R.id.tabLayout_data_limit);
        tabLayoutDataLimit.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        currentDataLimit = 7;
                        init(currentDataLimit, currentDataType);
                        break;
                    case 1:
                        currentDataLimit = 30;
                        init(currentDataLimit, currentDataType);
                        break;

                    case 2:
                        currentDataLimit = days.size();
                        init(currentDataLimit, currentDataType);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        TabLayout tabLayoutDataType = view.findViewById(R.id.tabLayout);
        tabLayoutDataType.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        currentDataType = DataType.PAIN;
                        init(currentDataLimit, currentDataType);
                        break;
                    case 1:
                        currentDataType = DataType.TOILET;
                        init(currentDataLimit, currentDataType);
                        break;

                    case 2:
                        currentDataType = DataType.SPOOL;
                        init(currentDataLimit, currentDataType);
                        break;
                    case 3:
                        currentDataType = DataType.EXERCISE;
                        init(currentDataLimit, currentDataType);
                        break;
                    case 4:
                        currentDataType = DataType.ALL;
                        init(currentDataLimit, currentDataType);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void init(int length, DataType dataType) {
        dataSets = new ArrayList<>();
        ArrayList<Entry> pain = new ArrayList<>();
        ArrayList<Entry> toilet = new ArrayList<>();
        ArrayList<Entry> stool = new ArrayList<>();
        ArrayList<Entry> exercise = new ArrayList<>();


        for (int i = 0; i < days.size(); i++) {
            pain.add(new Entry(i, days.get(i).getPain()));
            toilet.add(new Entry(i, days.get(i).getToiletVisits()));
            stool.add(new Entry(i, days.get(i).getStool()));
            exercise.add(new Entry(i, days.get(i).getExercise()));

        }
        LineDataSet set1;
        LineDataSet set2;
        LineDataSet set3;
        LineDataSet set4;
        set1 = new LineDataSet(pain, "Pain");
        set2 = new LineDataSet(toilet, "Toilet");
        set3 = new LineDataSet(stool, "Stool");
        set4 = new LineDataSet(exercise, "Exercise");
        set1.setDrawIcons(false);
        if (dataType == DataType.PAIN)
            dataSets.add(set1);
        else if (dataType == DataType.TOILET)
            dataSets.add(set2);
        else if (dataType == DataType.SPOOL)
            dataSets.add(set3);
        else if (dataType == DataType.EXERCISE)
            dataSets.add(set4);
        else {
            dataSets.add(set1);
            dataSets.add(set2);
            dataSets.add(set3);
            dataSets.add(set4);
        }

        LineData data = new LineData(dataSets);

        chart.setDrawGridBackground(true);
        chart.getDescription().setEnabled(false);
        chart.setDrawBorders(false);

        chart.invalidate();
        set1.setColor(colors[0 % colors.length]);
        set1.setDrawValues(false);
        set2.setDrawValues(false);
        set3.setDrawValues(false);
        set4.setDrawValues(false);
        set1.setLineWidth(5);
        set2.setLineWidth(5);
        set3.setLineWidth(5);
        set4.setLineWidth(5);
        set1.setDrawCircles(false);
        set2.setColor(colors[1 % colors.length]);
        set2.setDrawCircles(false);
        set3.setColor(colors[2 % colors.length]);
        set3.setDrawCircles(false);
        set4.setColor(colors[3 % colors.length]);
        set4.setDrawCircles(false);

        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setDrawAxisLine(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getXAxis().setDrawAxisLine(false);
        chart.getXAxis().setDrawGridLines(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMaximum(length);

        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);

        /*
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);

         */

        Legend l = chart.getLegend();
        l.setWordWrapEnabled(true);
        l.setFormSize(14f);
        l.setTextSize(9f);
        l.setYOffset(13f);
        chart.setNoDataText("Need more than 1 day of data");
        chart.setExtraBottomOffset(16f);
        if (days.size() > 1)
            chart.setData(data);
        chart.animateX(500);
        chart.animateY(500);
    }

    private final int[] colors = new int[]{
            ColorTemplate.VORDIPLOM_COLORS[0],
            ColorTemplate.VORDIPLOM_COLORS[1],
            ColorTemplate.VORDIPLOM_COLORS[2],
            ColorTemplate.VORDIPLOM_COLORS[3]
    };

    private void initPieChart() {
        ArrayList<PieEntry> entries = new ArrayList<>();

        for (int i = 0; i < days.size(); i++) {
            if (days.get(i).getFoods() == null) {
                continue;
            }
            for (int y = 0; y < days.get(i).getFoods().size(); y++) {
                entries.add(new PieEntry(1, days.get(i).getFoods().get(y)));
            }
        }

        PieDataSet dataSet = new PieDataSet(entries, "Food");
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate();
    }

}



