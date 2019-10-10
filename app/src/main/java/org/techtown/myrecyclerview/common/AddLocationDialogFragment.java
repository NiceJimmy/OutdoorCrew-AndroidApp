package org.techtown.myrecyclerview.common;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import org.techtown.myrecyclerview.R;

public class AddLocationDialogFragment extends DialogFragment {

    private OnClickListener mOnClickListener;





    public interface OnClickListener {

        void onOkClicked(String city);

    }



    public void setOnClickListener(OnClickListener onClickListener) {

        mOnClickListener = onClickListener;

    }



    private EditText mCityEditText;



    public static AddLocationDialogFragment newInstance(OnClickListener listener) {



        Bundle args = new Bundle();



        AddLocationDialogFragment fragment = new AddLocationDialogFragment();

        fragment.setOnClickListener(listener);

        fragment.setArguments(args);

        return fragment;

    }



    public AddLocationDialogFragment() {

    }



    @NonNull

    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_location, null, false);



        mCityEditText = (EditText) view.findViewById(R.id.city_edit);



        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("위치 추가");

        builder.setView(view);

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialogInterface, int i) {

                String city = mCityEditText.getText().toString();

                mOnClickListener.onOkClicked(city);

            }

        });

        builder.setNegativeButton("취소", null);

        return builder.create();

    }



}