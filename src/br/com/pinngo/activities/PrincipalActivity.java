package br.com.pinngo.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import br.com.pinngo.R;
import br.com.pinngo.adapter.CustomListAdapter;
import br.com.pinngo.enumerators.TipoIconPesquisa;
import br.com.pinngo.model.Movie;

public class PrincipalActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(position + 1)).commit();
	}

	public void onSectionAttached(int number) {
		switch (number) {
			case 1:
				mTitle = getString(R.string.title_section1);
				break;
			case 2:
				mTitle = getString(R.string.title_section2);
				break;
			case 3:
				mTitle = getString(R.string.title_section3);
				break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
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
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		private ProgressDialog pDialog;
	    private List<Movie> movieList = new ArrayList<Movie>();
	    private ListView listView;
	    private CustomListAdapter adapter;
		
		/**
		 * Returns a new instance of this fragment for the given section
		 * number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_principal, container, false);
			
			pDialog = new ProgressDialog(getActivity());
	        pDialog.setMessage(getString(R.string.string_loading));
	        pDialog.show();
	        
	        listView = (ListView) rootView.findViewById(R.id.listPesquisas);
	        adapter = new CustomListAdapter(getActivity(), movieList);
	        listView.setAdapter(adapter);

	        
	        
	        Movie movie = new Movie();
	        movie.setTitle("Auto Posto Valdevez Ltda.");
	        movie.setIcon(TipoIconPesquisa.GAS);
	        movie.setRating(2.2d);
	        movie.setYear(2014);
	        ArrayList<String> genre = new ArrayList<String>();
	        genre.add("Estrada do Pau Ferro");
	        genre.add("1128");
	        movie.setGenre(genre);
	        
	        movieList.add(movie);
	        
	        movie = new Movie();
            movie.setTitle("Auto Posto Tirol Ltda.");
            movie.setIcon(TipoIconPesquisa.OIL_CHECK);
            movie.setRating(5.2d);
            movie.setYear(2014);
            genre = new ArrayList<String>();
            genre.add("Estrada do Bananal");
            genre.add("301 Freguesia");
            movie.setGenre(genre);
            
            movieList.add(movie);
            
            movie = new Movie();
            movie.setTitle("Auto Posto Pistao Ltda.");
            movie.setIcon(TipoIconPesquisa.OIL);
            movie.setRating(2.2d);
            movie.setYear(2014);
            genre = new ArrayList<String>();
            genre.add("Estrada do Tindiba");
            genre.add("530");
            movie.setGenre(genre);
            
            movieList.add(movie);
            
            movie = new Movie();
            movie.setTitle("Auto Posto Rio 92 Ltda.");
            movie.setIcon(TipoIconPesquisa.GAS_CHECK);
            movie.setRating(4.2d);
            movie.setYear(2014);
            genre = new ArrayList<String>();
            genre.add("Rua Andre Rocha");
            genre.add("777");
            movie.setGenre(genre);
            
            movieList.add(movie);
            
            movie = new Movie();
            movie.setTitle("Auto Posto Max Ltda.");
            movie.setIcon(TipoIconPesquisa.GAS_ERROR);
            movie.setRating(4.2d);
            movie.setYear(2014);
            genre = new ArrayList<String>();
            genre.add("Rua Andre Rocha");
            genre.add("654");
            movie.setGenre(genre);
            
            movieList.add(movie);
            
            movie = new Movie();
            movie.setTitle("Auto Posto AOC Ltda.");
            movie.setIcon(TipoIconPesquisa.OIL);
            movie.setRating(4.2d);
            movie.setYear(2014);
            genre = new ArrayList<String>();
            genre.add("Rua Andre Rocha");
            genre.add("321");
            movie.setGenre(genre);
            
            movieList.add(movie);
            
            movie = new Movie();
            movie.setTitle("Auto Posto Powerade Ltda.");
            movie.setIcon(TipoIconPesquisa.GAS);
            movie.setRating(4.2d);
            movie.setYear(2014);
            genre = new ArrayList<String>();
            genre.add("Rua Andre Rocha");
            genre.add("358");
            movie.setGenre(genre);
            
            movieList.add(movie);
	        
	        
	        hidePDialog();
	        
			return rootView;
		}

		@Override
	    public void onDestroy() {
	        super.onDestroy();
	        hidePDialog();
	    }
	 
	    private void hidePDialog() {
	        if (pDialog != null) {
	            pDialog.dismiss();
	            pDialog = null;
	        }
	    }
		
		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((PrincipalActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
		}
	}

}
