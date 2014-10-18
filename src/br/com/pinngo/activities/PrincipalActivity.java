package br.com.pinngo.activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import br.com.pinngo.R;
import br.com.pinngo.activities.base.BaseActionBarActivity;
import br.com.pinngo.fragments.NavigationDrawerFragment;

public class PrincipalActivity extends BaseActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
	}

}
