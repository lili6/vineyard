package com.example.facelovely;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MyActivity extends Activity {

	String[] images =null;
	AssetManager assets = null;
	int currentImg = 0;
	ImageView image;
	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		image = (ImageView)findViewById(R.id.imageDisplay);
		try {
			assets = getAssets();
			images = assets.list("");

			Log.d("所有图片",String.valueOf(images.length)          )           ;
		} catch (Exception e){
			e.printStackTrace();
		}

		final Button refresh = (Button)findViewById(R.id.refresh);
		refresh.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				Log.d("刷新", String.valueOf(currentImg));
				if (currentImg >= images.length) {
					currentImg = 0;
				}
				//找到下一个图片文件
				/*while (!images[currentImg].endsWith(".png")
						&& !images[currentImg].endsWith(".jpg")
						&& !images[currentImg].endsWith(".gif")) {         */
					currentImg++;
					try {
						//如果已发生数组越界
						if (currentImg >= images.length) {
							currentImg = 0;
						}
//						Log.d("错误号", String.valueOf(currentImg));

						InputStream assetFile = null;

							//打开指定的资源对应的输入流
						assetFile = assets.open(images[currentImg++]);

						BitmapDrawable bitmapDrawable = (BitmapDrawable) image.getDrawable();
						//如果图片还未回收,先强制回收该图片　
						if (bitmapDrawable != null &&
								!bitmapDrawable.getBitmap().isRecycled()) {
							bitmapDrawable.getBitmap().recycle();
						}
						//改变ImageView显示的图片
						image.setImageBitmap(BitmapFactory.decodeStream(assetFile));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

//			}

		});




		/*IotdHandler handler = new IotdHandler();
		new MyTask(handler).execute();
//		handler.processFeed();//start parsing
		resetDisplay(handler.getTitle(), handler.getDate(),handler.getImage()
				,handler.getDescription().toString());
				*/
	}


}

