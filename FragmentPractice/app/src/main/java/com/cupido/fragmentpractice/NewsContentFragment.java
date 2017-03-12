package com.cupido.fragmentpractice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author: qcq
 * @data: 2017/3/9.
 * @content:
 */

public class NewsContentFragment extends Fragment {
    private View view ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news_content,container,false);
        return view ;
    }


    public void refresh(String newsTitle,String newsContent){
        View visibilityLayout = view.findViewById(R.id.linearLayout_visibility);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView titleText = (TextView) view.findViewById(R.id.tv_newsTitle);
        TextView contentText = (TextView) view.findViewById(R.id.tv_newsContent);
        titleText.setText(newsTitle);
        contentText.setText(newsContent);
    }
}
