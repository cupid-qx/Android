package com.cupid.viewpagedemo;

import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by QcQ on 2016/12/20.
 */

public class PagerFragment extends Fragment {

    public static PagerFragment newInstance(String msg) {

        PagerFragment fragment = new PagerFragment();
        Bundle args = new Bundle();
        args.putString("message", msg);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.actitvity_pagerfragment, null);

        TextView tv_msg = (TextView) view.findViewById(R.id.tv_msg);

        Bundle args = getArguments();
        if (args != null) {
            String message = args.getString("message");
            tv_msg.setText(message);
        }
        return view;
    }
}
