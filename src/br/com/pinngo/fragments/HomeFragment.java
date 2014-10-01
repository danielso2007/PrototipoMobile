package br.com.pinngo.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import br.com.pinngo.R;
import br.com.pinngo.adapter.CustomListAdapter;
import br.com.pinngo.enumerators.TipoIconPesquisa;
import br.com.pinngo.model.Movie;

public class HomeFragment extends Fragment {

	private ProgressDialog pDialog;
	private List<Movie> movieList = new ArrayList<Movie>();
	private ListView listView;
	private CustomListAdapter adapter;

	public HomeFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_principal, container, false);

//		pDialog = new ProgressDialog(getActivity());
//		pDialog.setMessage(getString(R.string.string_loading));
//		pDialog.show();

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

//		hidePDialog();

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
	
}
