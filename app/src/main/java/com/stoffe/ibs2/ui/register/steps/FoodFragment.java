package com.stoffe.ibs2.ui.register.steps;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.stoffe.ibs2.R;
import com.stoffe.ibs2.data.Day;
import com.stoffe.ibs2.data.DayViewModel;
import com.stoffe.ibs2.databinding.FragmentFoodBinding;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

public class FoodFragment extends Fragment {

    private FragmentFoodBinding binding;
    private DayViewModel viewModel;
    private Day tempDay;
    ListAdapter listAdapter;
    private List<String> foods;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food, container, false);
        viewModel =
                ViewModelProviders.of(requireActivity()).get(DayViewModel.class);

        if (viewModel.getTempDay() != null) {
            tempDay = viewModel.getTempDay();
        } else {
            tempDay = new Day();
        }
        binding.setQuestion(getString(R.string.question_five));
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false);
        listAdapter = new ListAdapter();
        binding.listView.setLayoutManager(layoutManager);
        binding.listView.setAdapter(listAdapter);
        foods = new ArrayList<>();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {

                if (viewModel.getTempDay() != null)
                    viewModel.removeTempDay(viewModel.getTempDay());
                tempDay.setDate(LocalDate.now());
                tempDay.setFoods(foods);
                viewModel.insertDay(tempDay);
                try {
                    Navigation.findNavController(view).navigate(R.id.action_navigation_fragment_food_to_navigation_home);
                } catch (IllegalArgumentException e) {
                    Log.d("error", e.getLocalizedMessage());
                }
            }
        });

        binding.playerField.setOnKeyListener((view1, i, keyEvent) -> {
            if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && i == KeyEvent.KEYCODE_ENTER) {
                listAdapter.addData(binding.playerField.getText().toString());
                hideKeyboard(view1);
                binding.playerField.setText("");
                foods.add(binding.playerField.getText().toString());
                tempDay.setFoods(foods);
                return true;

            }
            return false;
        });

    }

    @Override
    public void onDestroy() {
        if (viewModel.getTempDay() != null) {
            viewModel.removeTempDay(viewModel.getTempDay());
        }
        tempDay.setTempDay(true);
        tempDay.setDate(LocalDate.now());
        viewModel.insertDay(tempDay);
        super.onDestroy();
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
