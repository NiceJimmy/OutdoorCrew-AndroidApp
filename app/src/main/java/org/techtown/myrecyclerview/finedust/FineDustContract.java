package org.techtown.myrecyclerview.finedust;

import org.techtown.myrecyclerview.model.dust_material.FineDust;

public class FineDustContract {

   public interface View {

        void showFineDustResult(FineDust fineDust);



        void showLoadError(String message);



        void loadingStart();



        void loadingEnd();



        void reload(double lat, double lng);

    }



   public interface UserActionsListener {

        void loadFineDustData();

    }

}
