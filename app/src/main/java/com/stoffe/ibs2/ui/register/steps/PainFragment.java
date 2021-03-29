package com.stoffe.ibs2.ui.register.steps;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stoffe.ibs2.R;
import com.stoffe.ibs2.data.Day;
import com.stoffe.ibs2.data.DayViewModel;
import com.stoffe.ibs2.databinding.FragmentPainBinding;

import org.threeten.bp.LocalDate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class PainFragment extends Fragment {

    private FragmentPainBinding binding;
    private DayViewModel viewModel;
    private Day tempDay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pain, container, false);
        viewModel =
                ViewModelProviders.of(requireActivity()).get(DayViewModel.class);

        if (viewModel.getTempDay() != null) {
            tempDay = viewModel.getTempDay();
            binding.pain.slider.setValue(tempDay.getPain());
            binding.pain.currentValue.setText(Integer.toString(tempDay.getPain()));

        } else {
            tempDay = new Day();
        }
        binding.setTitle(getString(R.string.pain_titel));
        binding.setQuestion(getString(R.string.question_one));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.pain.slider.addOnChangeListener((slider, value, fromUser) -> {
            int intValue = (int) value;
            tempDay.setPain(intValue);
            viewModel.setTempDay(tempDay);
            binding.pain.currentValue.setText(Integer.toString(intValue));
        });


        binding.nextButton.setOnClickListener(view1 -> {
            try {
                Navigation.findNavController(view).navigate(R.id.action_navigation_fragment_pain_to_navigation_fragment_toilet);
            } catch(IllegalArgumentException e){
                Log.d("error",e.getLocalizedMessage());
            }
        });

    }

}
