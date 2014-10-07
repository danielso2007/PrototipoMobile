package br.com.pinngo.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.pinngo.R;
import br.com.pinngo.adapter.NavDrawerListAdapter;
import br.com.pinngo.adapter.model.NavDrawerItem;

/**
 * Fragmento usado para o gerenciamento de interações para e apresentação de uma
 * navigation drawer.
 * See the <a href=
 * "https://developer.android.com/design/patterns/navigation-drawer.html#Interaction"
 * >
 * design guidelines</a> para uma explicação completa dos comportamentos
 * implementados aqui.
 */
public class NavigationDrawerFragment extends Fragment {

	/**
	 * Lembre-se da posição do item selecionado.
	 */
	private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

	/**
	 * De acordo com as diretrizes de design, você deve mostrar a drawer no
	 * lançamento até que o
	 * usuário expande-lo manualmente. Esta preferência compartilhada acompanha
	 * isso.
	 */
	private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

	/**
	 * Um ponteiro para a instância callbacks atual (a atividade).
	 */
	private NavigationDrawerCallbacks mCallbacks;

	/**
	 * Helper componente que une a barra de ação para a navigation drawer.
	 */
	private ActionBarDrawerToggle mDrawerToggle;

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerListView;
	private View mFragmentContainerView;

	private int mCurrentSelectedPosition = 0;
	private boolean mFromSavedInstanceState;
	private boolean mUserLearnedDrawer;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	public NavigationDrawerFragment() {
	}

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Leia na bandeira indicando se o usuário tem demonstrado consciência
		// da gaveta. Veja PREF_USER_LEARNED_DRAWER para mais detalhes.
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
		mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, true);

		if (savedInstanceState != null) {
			mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
			mFromSavedInstanceState = true;
		}

		// Selecione o item padrão (0) ou o último item selecionado.
		selectItem(mCurrentSelectedPosition);
	}

	@Override
	public void onActivityCreated(final Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// Indicam que este fragmento gostaria de influenciar o conjunto de
		// ações na barra de ação.
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		// // mDrawerListView = (ListView)
		// // inflater.inflate(R.layout.fragment_navigation_drawer, container,
		// // false).findViewById(R.id.listDrawer);
		// mDrawerListView = (ListView)
		// inflater.inflate(R.layout.fragment_navigation_drawer, container,
		// false);
		// mDrawerListView.setOnItemClickListener(new
		// AdapterView.OnItemClickListener() {
		// @Override
		// public void onItemClick(AdapterView<?> parent, View view, int
		// position, long id) {
		// selectItem(position);
		// }
		// });
		// // mDrawerListView.setAdapter(new
		// // ArrayAdapter<String>(getActionBar().getThemedContext(),
		// // android.R.layout.simple_list_item_activated_1, android.R.id.text1,
		// // new String[]{
		// // getString(R.string.title_section1),
		// // getString(R.string.title_section2),
		// // getString(R.string.title_section3), }));
		//
		// // load slide menu items
		// navMenuTitles =
		// getResources().getStringArray(R.array.nav_drawer_items);
		//
		// // nav drawer icons from resources
		// navMenuIcons =
		// getResources().obtainTypedArray(R.array.nav_drawer_icons);
		//
		// navDrawerItems = new ArrayList<NavDrawerItem>();
		//
		// navDrawerItems.add(new NavDrawerItem(navMenuTitles[0],
		// navMenuIcons.getResourceId(0, -1)));
		// // Find People
		// navDrawerItems.add(new NavDrawerItem(navMenuTitles[1],
		// navMenuIcons.getResourceId(1, -1)));
		// // Photos
		// navDrawerItems.add(new NavDrawerItem(navMenuTitles[2],
		// navMenuIcons.getResourceId(2, -1)));
		// // Communities, Will add a counter here
		// navDrawerItems.add(new NavDrawerItem(navMenuTitles[3],
		// navMenuIcons.getResourceId(3, -1), true, "22"));
		// // Pages
		// navDrawerItems.add(new NavDrawerItem(navMenuTitles[4],
		// navMenuIcons.getResourceId(4, -1)));
		// // What's hot, We will add a counter here
		// navDrawerItems.add(new NavDrawerItem(navMenuTitles[5],
		// navMenuIcons.getResourceId(5, -1), true, "50+"));
		//
		// // Recycle the typed array
		// navMenuIcons.recycle();
		//
		// // setting the nav drawer list adapter
		// adapter = new
		// NavDrawerListAdapter(getActivity().getApplicationContext(),
		// navDrawerItems);
		// mDrawerListView.setAdapter(adapter);
		//
		// mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);
		// return mDrawerListView;

		View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

		mDrawerListView = (ListView) view.findViewById(R.id.navigationItems);
		mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				selectItem(position);
			}
		});
		mDrawerListView.setAdapter(new ArrayAdapter<String>(getActionBar().getThemedContext(), android.R.layout.simple_list_item_activated_1, android.R.id.text1, new String[]{
				getString(R.string.title_section1), getString(R.string.title_section2), getString(R.string.title_section3), }));
		mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);
		return view;
	}

	public boolean isDrawerOpen() {
		return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
	}

	/**
	 * Os usuários deste fragmento deve chamar esse método para definir as
	 * interações navigation drawer.
	 * @param fragmentId O android: id deste fragmento no layout da sua
	 *            actividade.
	 * @param drawerLayout O DrawerLayout contendo IU deste fragmento.
	 */
	public void setUp(final int fragmentId, final DrawerLayout drawerLayout) {
		mFragmentContainerView = getActivity().findViewById(fragmentId);
		mDrawerLayout = drawerLayout;

		// definir uma sombra personalizado que cobre o conteúdo principal
		// quando a gaveta abre
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

		// configurar listview da drawer com itens e clique ouvinte
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

		// ActionBarDrawerToggle une as as interações adequadas entre a gaveta
		// de navegação e o ícone do aplicativo barra de ação.
		mDrawerToggle = new ActionBarDrawerToggle(getActivity(), /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.navigation_drawer_open, /*
										 * description "gaveta aberta" de
										 * acessibilidade.
										 */
		R.string.navigation_drawer_close /*
										 * "close drawer" description for
										 * accessibility
										 */
		) {
			@Override
			public void onDrawerClosed(final View drawerView) {
				super.onDrawerClosed(drawerView);
				if (!isAdded()) {
					return;
				}
				getActivity().supportInvalidateOptionsMenu(); // calls
																// onPrepareOptionsMenu()
			}

			@Override
			public void onDrawerOpened(final View drawerView) {
				super.onDrawerOpened(drawerView);
				if (!isAdded()) {
					return;
				}

				if (!mUserLearnedDrawer) {
					// O usuário abriu a gaveta manualmente; armazenar esta
					// bandeira para evitar auto-mostrando a gaveta de navegação
					// automaticamente no futuro.
					mUserLearnedDrawer = true;
					SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
					sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply();
				}

				getActivity().supportInvalidateOptionsMenu(); // calls
																// onPrepareOptionsMenu()
			}
		};

		// Se o usuário não tenha "aprendido" sobre a gaveta, abri-lo para
		// apresentá-los para a gaveta, de acordo com as diretrizes de design da
		// gaveta de navegação.
		if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
			mDrawerLayout.openDrawer(mFragmentContainerView);
		}

		// Adiar código dependente de restauração do estado da instância
		// anterior.
		mDrawerLayout.post(new Runnable() {
			@Override
			public void run() {
				mDrawerToggle.syncState();
			}
		});

		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	private void selectItem(final int position) {
		mCurrentSelectedPosition = position;
		if (mDrawerListView != null) {
			mDrawerListView.setItemChecked(position, true);
		}
		if (mDrawerLayout != null) {
			mDrawerLayout.closeDrawer(mFragmentContainerView);
		}
		if (mCallbacks != null) {
			mCallbacks.onNavigationDrawerItemSelected(position);
		}
	}

	@Override
	public void onAttach(final Activity activity) {
		super.onAttach(activity);
		try {
			mCallbacks = (NavigationDrawerCallbacks) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = null;
	}

	@Override
	public void onSaveInstanceState(final Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
	}

	@Override
	public void onConfigurationChanged(final Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Encaminhar a nova configuração do componente gaveta de alternância.
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
		// Se a gaveta for aberta, mostrar as ações de aplicativos globais na
		// barra de ação.
		// Veja também showGlobalContextActionBar, que controla a área superior
		// esquerdo da barra de ação.
		if (mDrawerLayout != null && isDrawerOpen()) {
			inflater.inflate(R.menu.global, menu);
			showGlobalContextActionBar();
		}
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * De acordo com as diretrizes de design de gaveta navegação, atualiza a
	 * barra de ação para mostrar o app 'contexto' global, e não apenas o que
	 * está na tela atual.
	 */
	private void showGlobalContextActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setTitle(R.string.app_name);
	}

	private ActionBar getActionBar() {
		return ((ActionBarActivity) getActivity()).getSupportActionBar();
	}

	/**
	 * Interface de chamadas de retorno que todas as atividades que utilizam
	 * este fragmento deve implementar.
	 */
	public static interface NavigationDrawerCallbacks {
		/**
		 * Chamado quando um item na gaveta de navegação é selecionado.
		 */
		void onNavigationDrawerItemSelected(int position);
	}
}
