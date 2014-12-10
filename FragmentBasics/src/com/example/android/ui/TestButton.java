package com.example.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by liguofang on 2014/12/10.
 */
public class TestButton extends Activity {
	/** Called when the activity is first created. */
	private Button btn1,btn2;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.button_layout);
		btn1=(Button)findViewById(R.id.button1);
		btn2=(Button)findViewById(R.id.button2);
		//为控件设置监听，当点击了按钮一，就弹出一个提示，当点击按钮二，退出程序
		btn1.setOnClickListener(new Button.OnClickListener()
		{

			@Override
			public void onClick(View arg0) {
				Toast toast=Toast.makeText(TestButton.this, "你点击了按钮"+btn1.getText().toString(), Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.TOP,10,250);
				toast.show();
			}

		});
		btn2.setOnClickListener(new Button.OnClickListener()
		{

			@Override
			public void onClick(View v) {
				Toast toast=Toast.makeText(TestButton.this, "您将要退出应用...", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.TOP,100,500);
				toast.show();
				TestButton.this.finish();
			}

		});
	}

}
