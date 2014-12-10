package com.example.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by liguofang on 2014/12/10.
 */
public class SongsActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		textView.setText("这是个音乐册子！");
		setContentView(textView);
	}
}
