package com.stoffe.ibs2.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.stoffe.ibs2.R;
import com.stoffe.ibs2.data.Day;
import com.stoffe.ibs2.data.DayViewModel;
import com.stoffe.ibs2.databinding.FragmentNotificationsBinding;
import com.stoffe.ibs2.databinding.FragmnetSpecificDayBinding;
import com.stoffe.ibs2.ui.register.steps.ListAdapter;

import java.util.List;

public class SpecificDayFragment extends Fragment {

    FragmnetSpecificDayBinding binding;
    private DayViewModel viewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragmnet_specific_day, container, false);
        viewModel =
                ViewModelProviders.of(requireActivity()).get(DayViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false);
        binding.listView.setLayoutManager(layoutManager);
        List<Day> days = viewModel.getDays();
        Day currentDay;

        ListAdapter listAdapter = new ListAdapter();


        for(Day day: days){
            if(viewModel.getCurrentDate().equals(day.getDate())){
                currentDay = day;
                binding.setDay(currentDay);
                if(day.getFoods() != null)
                    listAdapter.setData(day.getFoods());
                binding.listView.setAdapter(listAdapter);
                break;
            }
        }

    }
}