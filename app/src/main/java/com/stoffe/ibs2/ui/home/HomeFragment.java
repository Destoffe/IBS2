package com.stoffe.ibs2.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.stoffe.ibs2.R;
import com.stoffe.ibs2.data.Day;
import com.stoffe.ibs2.data.DayViewModel;
import com.stoffe.ibs2.databinding.FragmentHomeBinding;

import org.threeten.bp.LocalDate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

public class HomeFragment extends Fragment {

    private DayViewModel viewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel =
                ViewModelProviders.of(requireActivity()).get(DayViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.particleView.resume();
        viewModel.initDays().observe(getViewLifecycleOwner(), data -> {
            if (data == null) {
                return;
            }

            LocalDate todayDate = LocalDate.now();

            for (Day day : data) {
                Log.d("destoffe","date: " + day.getDate().toString());
                if (day.getDate().equals(todayDate)) {
                    viewModel.setTempDay(day);
                    binding.setOldRegisterExists(true);
                    binding.oldRegister.setText(R.string.change_day);
                    break;
                }
            }
            viewModel.setDays(data);
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Animation logoMoveAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.animations);
        binding.textHome.startAnimation(logoMoveAnimation);

        binding.newRegister.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putBoolean("newRegister", true);
            Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_navigation_fragment_pain,bundle);
        });

        binding.oldRegister.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putBoolean("newRegister", false);
            Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_navigation_fragment_pain,bundle);
        });
    }


    @BindingAdapter({"bind:layout_marginTop"})
    public static void setLayoutMarginLeft(Button view, float marginLeft) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins( p.leftMargin, (int)marginLeft, p.rightMargin, p.bottomMargin);
            view.requestLayout();
        }
    }
}