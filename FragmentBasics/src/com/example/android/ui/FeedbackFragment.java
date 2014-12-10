/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * 反馈信息
 */
public class FeedbackFragment extends Fragment implements View.OnClickListener {
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {

        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.feedback, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Button button = (Button) getActivity().findViewById(R.id.feedback_button);
        Log.d("FeedbackFragment","显示反馈页面。。。。");
        button.setOnClickListener(this);

        Log.d("FeedbackFragment","反馈页面开始运行。。。。");

    }


    @Override
    public void onClick(View view) {
        Log.d("BUTTON CLICK:====","在此调用服务提交反馈信息！！！TODO");
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setCancelable(true);
//        builder.
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("非常感谢，您的反馈已提交！");
        builder.setNegativeButton("确 定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        ;
                    }
                });
        builder.show();

    }
}