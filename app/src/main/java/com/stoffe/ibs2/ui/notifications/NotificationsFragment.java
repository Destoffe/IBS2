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
import androidx.navigation.Navigation;

import com.stoffe.ibs2.R;
import com.stoffe.ibs2.data.DayViewModel;
import com.stoffe.ibs2.databinding.FragmentNotificationsBinding;

import org.threeten.bp.LocalDate;

public class NotificationsFragment extends Fragment {

    FragmentNotificationsBinding binding;
    private DayViewModel viewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notifications, container, false);
        viewModel =
                ViewModelProviders.of(requireActivity()).get(DayViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {

                LocalDate currentDate = LocalDate.of(year,month+1,day);
                viewModel.setCurrentDate(currentDate);
                Log.d("destoffe",currentDate.toString());
                try {
                    Navigation.findNavController(view).navigate(R.id.action_navigation_notifications_to_navigation_specific_day2);
                } catch (IllegalArgumentException e) {
                    Log.d("error", e.getLocalizedMessage());
                }
            }
        });
    }
}