package com.polites.android.example;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.polites.android.GestureImageView;

public class StandardImageProgrammatic extends ExampleActivity {

	protected GestureImageView view;
	private String urlString = "http://cdn.likeorz.com/23fc736b91964b09845a0b40277f439a_1460943664_w_1280_h_854_297949.jpg?imageView2/0/w/960/q/85";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.empty);
		view = new GestureImageView(this);
		RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
		ImageRequest imageRequest = new ImageRequest(urlString,
				new Listener<Bitmap>() {

					@Override
					public void onResponse(Bitmap response) {
						view.setImageBitmap(response);
					}
				}, 0, 0, Config.ARGB_8888, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Toast.makeText(getApplication(), error.getMessage(), 0).show();
						view.setImageResource(R.drawable.image);
					}

				});
		queue.add(imageRequest);
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);

		view.setLayoutParams(params);
		ViewGroup layout = (ViewGroup) findViewById(R.id.layout);
		layout.addView(view);
	}
}