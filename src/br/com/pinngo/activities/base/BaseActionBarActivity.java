package br.com.pinngo.activities.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import br.com.pinngo.R;
import br.com.pinngo.fragments.CommunityFragment;
import br.com.pinngo.fragments.FindPeopleFragment;
import br.com.pinngo.fragments.HomeFragment;
import br.com.pinngo.fragments.NavigationDrawerFragment;
import br.com.pinngo.fragments.PagesFragment;
import br.com.pinngo.fragments.PhotosFragment;
import br.com.pinngo.fragments.WhatsHotFragment;
import br.com.pinngo.util.Utils;

public abstract class BaseActionBarActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	public static final String ARG_SECTION_NUMBER = "section_number";
	
	private boolean back = false;

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

	@Override
	public void onNavigationDrawerItemSelected(final int position) {
		// update the main content by replacing fragments
		displayView(position);
	}

	public void onSectionAttached(final int number) {
		displayView(number);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 */
	private void displayView(final int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
			case 0:
				fragment = new HomeFragment();
				break;
			case 1:
				fragment = new FindPeopleFragment();
				break;
			case 2:
				fragment = new PhotosFragment();
				break;
			case 3:
				fragment = new CommunityFragment();
				break;
			case 4:
				fragment = new PagesFragment();
				break;
			case 5:
				fragment = new WhatsHotFragment();
				break;

			default:
				break;
		}

		if (fragment != null) {
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, position);
			fragment.setArguments(args);

			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_fade_in, R.anim.abc_fade_out);
			transaction.replace(R.id.container, fragment);
//			transaction.addToBackStack(null);
			transaction.commit();

			// update selected item and title, then close the drawer
			// mDrawerList.setItemChecked(position, true);
			// mDrawerList.setSelection(position);
			// setTitle(navMenuTitles[position]);
			// mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.principal, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

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
