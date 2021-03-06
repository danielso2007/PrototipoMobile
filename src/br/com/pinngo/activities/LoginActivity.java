package br.com.pinngo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import br.com.pinngo.R;
import br.com.pinngo.activities.base.BaseActivity;

public class LoginActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
	}

	public void actionLogin(View view) {
		Intent intent = new Intent(this, PrincipalActivity.class);
		finish();
		startActivity(intent);
	}

}
