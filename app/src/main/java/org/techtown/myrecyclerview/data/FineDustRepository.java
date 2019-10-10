package org.techtown.myrecyclerview.data;

import org.techtown.myrecyclerview.model.dust_material.FineDust;

import retrofit2.Callback;

public interface FineDustRepository {

    boolean isAvailable();
    void getFindDustData(Callback<FineDust> callback);

}
