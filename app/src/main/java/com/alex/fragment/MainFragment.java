package com.alex.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alex.twork.MainActivity;
import com.alex.twork.QRScanner;
import com.alex.twork.R;

/**
 * Created by alex on 15-11-17.
 * List all topics
 */
public class MainFragment extends BaseFragment {

    public interface OnTopicSelectedListener {
        void onTopicSelected(Object topic);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mTopicListener = (OnTopicSelectedListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        String[] mTopics = new String[] {
                getString(R.string.topic_sign_in_layout),
                getString(R.string.topic_sign_up_layout),
                getString(R.string.topic_round_image),
                getString(R.string.topic_scale_layout),
                getString(R.string.topic_scan_qr_code)
        };

        ListView topicsList = (ListView) view.findViewById(R.id.topics);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mTopics);
        topicsList.setAdapter(adapter);

        topicsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object target = null;
                switch (position) {
                    case 0:{
                        target = new SignInFragment();
                        break;
                    }
                    case 1:{
                        target = null;
                        break;
                    }
                    case 2:{
                        target = new RoundImageFragment();
                        break;
                    }
                    case 3:{
                        target = new ScaleLayoutFragment();
                        break;
                    }
                    case 4:{
                        Intent intent = new Intent(getActivity(), QRScanner.class);
                        intent.putExtra("type", MainActivity.TopicType.Activity.ordinal());
                        target = intent;
                        break;
                    }
                }

                if(null != target) {
                    mTopicListener.onTopicSelected(target);
                }
            }
        });

        return view;
    }

    private OnTopicSelectedListener mTopicListener;
}
