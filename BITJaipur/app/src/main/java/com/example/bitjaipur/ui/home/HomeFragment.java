package com.example.bitjaipur.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bitjaipur.R;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;


public class HomeFragment extends Fragment {

private ImageView map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
       /* sliderLayout = view.findViewById(R.id.slider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(3);

        setSliderViews();
*/
        map= view.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });
        return view;
    }

    private void openMap() {
        Uri uri=Uri.parse("geo:0,0?q=Birla institue of technology jaipur");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

   /* private void setSliderViews() {
        for (int i = 0; i < 5; i++) {
            DefaultSliderView sliderView = new DefaultSliderView(getContext());
            switch (i) {
                case 0:
                    sliderView.setImageUrl("https://www.bitmesra.ac.in/UploadedDocuments/user_adminjaipur/Header/Header_20160715174354248_3f1de415a9804dbfb6d2dbb0c146bc46_BIT%20Jaipur.JPG");
                    break;
                case 1:
                    sliderView.setImageUrl("https://www.bitmesra.ac.in/UploadedDocuments/user_adminjaipur/Header/Header_20160715175141983_e22bac48b3bb4518837b346c9d601339_DSC05339.JPG");
                    break;
                case 2:
                    sliderView.setImageUrl("https://www.bitmesra.ac.in/UploadedDocuments/user_adminjaipur/Header/Header_20160715174947377_af8d5fbfb3784a3e96418bff8c83a350_BIT%20Jaipur%20_Side%20View2.jpg");
                    break;
                case 3:
                    sliderView.setImageUrl("https://www.bitmesra.ac.in/UploadedDocuments/user_adminjaipur/Header/Header_20160715175141983_e22bac48b3bb4518837b346c9d601339_DSC05339.JPG");
                    break;
                case 4:
                    sliderView.setImageUrl("https://www.bitmesra.ac.in/UploadedDocuments/user_adminjaipur/Header/Header_20160715174816637_ca3f7b2957714f37b0484d718e037f5b_BIT%20Jaipur%20_side%20view%202_Inner%20quadrangle.jpg");
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.FIT_XY);

            sliderLayout.addSliderView(sliderView);
        }

    }*/
}