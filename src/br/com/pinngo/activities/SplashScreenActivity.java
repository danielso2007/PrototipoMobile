package br.com.pinngo.activities;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import br.com.pinngo.R;

public class SplashScreenActivity extends Activity {

	long Delay = 1000;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_splash_screen);

		Timer RunSplash = new Timer();

		TimerTask ShowSplash = new TimerTask() {
			@Override
			public void run() {
				finish();

				Intent myIntent = new Intent(SplashScreenActivity.this, LoginActivity.class);
				startActivity(myIntent);
			}
		};

		RunSplash.schedule(ShowSplash, Delay);
	}
}
