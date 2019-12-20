package com.share.mvvm_architecture.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.share.mvvm_architecture.R;
import com.share.mvvm_architecture.data.model.RentalSpot;
import com.share.mvvm_architecture.databinding.ActivityMainBinding;
import com.share.mvvm_architecture.viewmodel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel viewModel;
    private RentalSpotAdapter adapter;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViewModel();
        setupView();
    }

    private void setupView() {
        activityMainBinding.recyclerview.setHasFixedSize(true);
        // 대여소 정보를 observer 하다가, 서버로 부터 데이터가 넘어오면 뷰에 데이터를 넘겨줌.
        viewModel.allRentalSpots.observe(this, this::loadData);
    }

    private void setupViewModel() {
        // 뷰모델 초기화
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        // 데이터 바인딩 설정
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }


    private void loadData(List<RentalSpot> rentalSpots) {
        if (!rentalSpots.isEmpty()) {
            adapter = new RentalSpotAdapter(rentalSpots);
            activityMainBinding.recyclerview.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "데이터를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
