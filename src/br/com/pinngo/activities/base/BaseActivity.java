package br.com.pinngo.activities.base;

import br.com.pinngo.R;
import br.com.pinngo.fragments.NavigationDrawerFragment;
import br.com.pinngo.util.Utils;
import android.app.Activity;

public class BaseActivity extends Activity {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	protected NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	protected CharSequence mTitle;
	
	private boolean back = false;

	@Override
	public void onBackPressed() {
		if (!back) {
			back = true;
			Utils.ShowToast(getApplicationContext(), getResources().getString(R.string.message_back_pressed));
		} else {
			super.onBackPressed();
		}
	}
	
}
