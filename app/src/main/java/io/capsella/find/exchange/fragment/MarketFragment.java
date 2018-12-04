package io.capsella.find.exchange.fragment;

import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.capsella.find.exchange.R;
import io.capsella.find.exchange.utility.SmoothLineChart;

public class MarketFragment extends Fragment {

    SmoothLineChart smoothChart;

    public static MarketFragment newInstance() {
        return new MarketFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_market, container, false);

        initViews(rootView);

        return rootView;
    }

    public void initViews(View rootView){

        smoothChart = rootView.findViewById(R.id.smooth_chart);
        smoothChart.setData(new PointF[] {
                new PointF(15, 39), // {x, y}
                new PointF(20, 21),
                new PointF(28, 9),
                new PointF(37, 21),
                new PointF(40, 25),
                new PointF(50, 31),
                new PointF(62, 24),
                new PointF(80, 28)
        });
    }
}