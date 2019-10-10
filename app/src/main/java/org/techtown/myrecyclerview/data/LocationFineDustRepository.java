package org.techtown.myrecyclerview.data;

import org.techtown.myrecyclerview.util.FineDustUtil;

import retrofit2.Callback;

public class LocationFineDustRepository implements FineDustRepository{
    private FineDustUtil mFineDustUtil;
    private double mLatitude;
    private double mLongitude;



    public LocationFineDustRepository(double lat, double lng) {
        mFineDustUtil = new FineDustUtil();
        this.mLatitude = lat;
        this.mLongitude = lng;
    }



    @Override
    public void getFindDustData(Callback callback) {

        mFineDustUtil.getApi().getFineDust(mLatitude, mLongitude)
                .enqueue(callback);


    }

    @Override
    public boolean isAvailable() {
        if(mLatitude != 0.0 && mLongitude != 0.0){
            return true;
        }
        return false;
    }
}
