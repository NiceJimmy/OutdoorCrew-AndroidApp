package org.techtown.myrecyclerview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;



public class SearchActivity extends AppCompatActivity implements OnMapReadyCallback/*, GoogleMap.OnMarkerClickListener*/ { //onmapreadycallback 인터페이스를 구현한다고 선언한다.


    private GoogleMap mMap;
    private GoogleMap mMap2;

    private Marker m1;
    private Marker m2;
    private Marker m3;
    private Marker m4;
    private Marker m5;
    private Marker m6;
    private Marker m7;
    private Marker m8;
    private Marker m9;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);//맵 프래그먼트를 생성한다.
        mapFragment.getMapAsync(this);//getMapAsync를 실행한다.



    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        // 구글 맵 객체를 불러온다.
        mMap = googleMap;
mMap2 = googleMap;
        // 서울에 대한 위치 설정
        LatLng seoul = new LatLng(37.52487, 126.92723);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(seoul));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(8));

        m1 = mMap.addMarker(new MarkerOptions()// 마커추가
                .position(new LatLng(37.52487, 126.92723))
                .title("힐포인트 캠핑장"));

        m2 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(36, 126.92723))
                .title("도란도란 캠핑장"));

        m3 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(37, 126.923))
                .title("마리원 캠핑장"));

        m4 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.73194536971503, 128.86471465332247))
                .title("크러쉬 서프"));//강릉시 서핑장비

        m5 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(36.06456954802705, 129.31286110169307))
                .title("스티프 서프"));//포항시 서핑장비

        m6 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(35.172829591477196, 129.04547752136745))
                .title("부산 더 서프"));// 부산시 서핑장비

        m7 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(36.00437499030038, 127.60723163249479))
                .title("무주 스카이 스키"));//무주 스키샵

        m8 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.28336573320279, 128.4984883249448))
                .title("하이원 스타 스키샵"));//하이원스키샵

        m9 = mMap.addMarker(new MarkerOptions()
                .position(new   LatLng(37.803920818683935, 127.85405821527114))
                .title("비발디 리더스 스키샵"));//비발디 스키샵





        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() { // 마커를 클릭했을때의 클릭 리스너
            @Override
            public boolean onMarkerClick(Marker marker) {

                if(marker.equals(m1)){
                    changeView(0);
                }
                if(marker.equals(m2)){
                    changeView(1);
                }
                if(marker.equals(m3)){

                   changeView(2);
                }
                if(marker.equals(m4)){

                    changeView(3);
                }
                if(marker.equals(m5)){

                    changeView(4);
                }
                if(marker.equals(m6)){

                    changeView(5);
                }
                if(marker.equals(m7)){

                    changeView(6);
                }
                if(marker.equals(m8)){

                    changeView(7);
                }
                if(marker.equals(m9)){

                    changeView(8);
                }

           /*for(int i=0; i<2; i++){
               changeView(i);
           }*/


                return false;
            }
        });


    }





    private void changeView(int index) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE); //  레이아웃 인플레이터 객체 생성

        FrameLayout frame = (FrameLayout) findViewById(R.id.frame) ; //프레임레이아웃 객체 생성
        if (frame.getChildCount() > 0) {  //getChildCount ???
            // FrameLayout에서 뷰 삭제.
            frame.removeViewAt(0);
        }

        // XML에 작성된 레이아웃을 View 객체로 변환.
        View view = null ;


        if(index==0){

            view = inflater.inflate(R.layout.frame1,frame, false) ;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri browserUri = Uri.parse("http://healpoint.co.kr/main/index.html");
                    Intent intent = new Intent(Intent.ACTION_VIEW,browserUri);
                    startActivity(intent);
                }
            });



        }
        if(index==1){
            view = inflater.inflate(R.layout.frame2, frame, false) ;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri browserUri = Uri.parse("http://dorancg.kr/");
                    Intent intent = new Intent(Intent.ACTION_VIEW,browserUri);
                    startActivity(intent);
                }
            });

        }
        if(index==2){
            view = inflater.inflate(R.layout.frame3, frame, false) ;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri browserUri = Uri.parse("https://mariwon.modoo.at/");
                    Intent intent = new Intent(Intent.ACTION_VIEW,browserUri);
                    startActivity(intent);
                }
            });


        }

        if(index==3){
            view = inflater.inflate(R.layout.frame4, frame, false) ;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri browserUri = Uri.parse("https://crushsurf.modoo.at/");
                    Intent intent = new Intent(Intent.ACTION_VIEW,browserUri);
                    startActivity(intent);
                }
            });


        }

        if(index==4){
            view = inflater.inflate(R.layout.frame5, frame, false) ;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri browserUri = Uri.parse("https://steepsurf.com/");
                    Intent intent = new Intent(Intent.ACTION_VIEW,browserUri);
                    startActivity(intent);
                }
            });


        }

        if(index==5){
            view = inflater.inflate(R.layout.frame6, frame, false) ;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri browserUri = Uri.parse("http://www.thesurf.co.kr/");
                    Intent intent = new Intent(Intent.ACTION_VIEW,browserUri);
                    startActivity(intent);
                }
            });


        }

        if(index==6){
            view = inflater.inflate(R.layout.frame7, frame, false) ;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri browserUri = Uri.parse("http://www.mujusky.co.kr/");
                    Intent intent = new Intent(Intent.ACTION_VIEW,browserUri);
                    startActivity(intent);
                }
            });


        }

        if(index==7){
            view = inflater.inflate(R.layout.frame8, frame, false) ;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri browserUri = Uri.parse("http://www.istarski.co.kr/");
                    Intent intent = new Intent(Intent.ACTION_VIEW,browserUri);
                    startActivity(intent);
                }
            });


        }

        if(index==8){
            view = inflater.inflate(R.layout.frame9, frame, false) ;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri browserUri = Uri.parse("http://leadersski.net/");
                    Intent intent = new Intent(Intent.ACTION_VIEW,browserUri);
                    startActivity(intent);
                }
            });


        }

        // FrameLayout에 뷰 추가.
        if (view != null) {
            frame.addView(view) ;
        }
    }

        }


















































