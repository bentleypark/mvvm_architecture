package com.share.mvvm_architecture.data.remote.repository;

import androidx.lifecycle.MutableLiveData;

import com.share.mvvm_architecture.data.model.RentalSpot;
import com.share.mvvm_architecture.data.remote.api.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class RentalSpotRepository {
    private List<RentalSpot> rentalOffices = new ArrayList<>();
    private MutableLiveData<List<RentalSpot>> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<RentalSpot>> getRentalSpotData() {

        Call<List<RentalSpot>> call = ApiService.Creator.create().getRentalOffices();
        call.enqueue(new Callback<List<RentalSpot>>() {
            @Override
            public void onResponse(Call<List<RentalSpot>> call, Response<List<RentalSpot>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        // 서버로부터 response가 잘 넘어오면 데이터를 넘겨줌.
                        rentalOffices = response.body();
                        mutableLiveData.setValue(rentalOffices);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<RentalSpot>> call, Throwable t) {
                Timber.d("대여소 데이터 로딩실패: %s", t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
