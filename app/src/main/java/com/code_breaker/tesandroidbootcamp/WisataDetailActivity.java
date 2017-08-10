/*
 * Copyright (C) 2017
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
package com.code_breaker.tesandroidbootcamp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WisataDetailActivity extends AppCompatActivity
	implements AppBarLayout.OnOffsetChangedListener {
	private static final int PERCENTAGE_TO_SHOW_IMAGE = 20;
	private View mFab;
	private int mMaxScrollSize;
	private boolean mIsImageHidden;

	@BindView(R.id.detail_alamat)
	TextView tvAlamat;
	@BindView(R.id.detail_deskripsi) TextView tvDeskripsi;
	@BindView(R.id.detail_nama) TextView tvNama;
	@BindView(R.id.detail_gambar)
	ImageView ivGambar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wisata_detail);

		ButterKnife.bind(this);

		Toolbar toolbar = (Toolbar) findViewById(R.id.flexible_example_toolbar);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
            public void onClick(View v) {
				onBackPressed();
			}
		});

		AppBarLayout appbar = (AppBarLayout) findViewById(R.id.flexible_example_appbar);
		appbar.addOnOffsetChangedListener(this);

		Bundle b = getIntent().getExtras();
		String nama = b.getString("nama");
		String desk = b.getString("deskripsi");
		String alamat = b.getString("alamat");
		String gambar = b.getString("gambar");

		getSupportActionBar().hide();
		//getSupportActionBar().setTitle(nama);
		//toolbar.setTitle(nama);

		tvNama.setText(nama);
		tvDeskripsi.setText(desk);
		tvAlamat.setText("Alamat : \n" + alamat);
		new DownloadImage().execute(gambar);

	}

	@Override
	public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
		if (mMaxScrollSize == 0)
			mMaxScrollSize = appBarLayout.getTotalScrollRange();

		int currentScrollPercentage = (Math.abs(i)) * 100
			/ mMaxScrollSize;

		if (currentScrollPercentage >= PERCENTAGE_TO_SHOW_IMAGE) {
			if (!mIsImageHidden) {
				mIsImageHidden = true;

				ViewCompat.animate(mFab).scaleY(0).scaleX(0).start();
			}
		}

		if (currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE) {
			if (mIsImageHidden) {
				mIsImageHidden = false;
				ViewCompat.animate(mFab).scaleY(1).scaleX(1).start();
			}
		}
	}

	public static void start(Context c) {
		c.startActivity(new Intent(c, WisataDetailActivity.class));
	}

	// DownloadImage AsyncTask
	private class DownloadImage extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Bitmap doInBackground(String... URL) {

			String imageURL = URL[0];

			Bitmap bitmap = null;
			try {
				// Download Image from URL
				InputStream input = new java.net.URL(imageURL).openStream();
				// Decode Bitmap
				bitmap = BitmapFactory.decodeStream(input);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// Set the bitmap into ImageView
			ivGambar.setImageBitmap(result);
			// Close progressdialog
		}
	}
}
