package com.nikola.exampleactivities.activities;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nikola.exampleactivities.R;
import com.nikola.exampleactivities.adapters.DrawerAdapter;
import com.nikola.exampleactivities.fragments.DetailFragment;
import com.nikola.exampleactivities.fragments.MasterFragment;
import com.nikola.exampleactivities.model.NavigationItem;

import java.util.ArrayList;

// Each activity extends Activity class
public class FirstActivity extends AppCompatActivity implements MasterFragment.OnItemSelectedListener {

    Toolbar toolbar;
    boolean landscape = false; // Portrait mode initaily

    // NavigationDrawer Attributes
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private ActionBarDrawerToggle drawerToggle;
    private RelativeLayout drawerRelativeLayoutPane;
    private CharSequence drawerTitle;

    private ArrayList<NavigationItem> drawerNavigationItems = new ArrayList<>();



    // onCreate method is a lifecycle method called when he activity is starting
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Each lifecycle method should call the method it overrides
        super.onCreate(savedInstanceState);
        // setContentView method draws UI
        setContentView(R.layout.activity_first);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Manage NavigationDrawer

        // 1. Populates a list of NavigationDrawer items, Initialize the Drawer List

        NavigationItem home = new NavigationItem("Home", "Show All Products",R.drawable.ic_action_product);
        drawerNavigationItems.add(home);
        //drawerNavigationItems.add(new NavigationItem("Home", "Show All Products",R.drawable.ic_action_product));
        NavigationItem settings = new NavigationItem("Settings", "Change App Settings", R.drawable.ic_action_settings);
        drawerNavigationItems.add(settings);
        NavigationItem about = new NavigationItem("About","About us", R.drawable.ic_action_about);
        drawerNavigationItems.add(about);

        drawerTitle = getTitle();
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerListView = (ListView)findViewById(R.id.navigation_list);

        // 2. Populates NavigationDrawer with otpions
        drawerRelativeLayoutPane = (RelativeLayout) findViewById(R.id.drawer_pane);

        //TODO add DrawerAdapter
        DrawerAdapter drawerAdapter = new DrawerAdapter(this, drawerNavigationItems);





        // Shows a toast message (a pop-up message)
        Toast toast = Toast.makeText(getBaseContext(), "FirstActivity.onCreate()", Toast.LENGTH_SHORT);
        toast.show();


        //1. CREATE MASTER FRAGMENT IF THE ACTIVITY IS STARTED FOR THE FIRST TIME
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            MasterFragment masterFragment = new MasterFragment();

            transaction.add(R.id.master_view, masterFragment,"Master_Fragment_1");
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        }

        //2. CREATE DETAIL FRAGMENT IF THE DEVICE IS IN LANDSCAPE MODE AND DETAIL FRAGMENT IS NULL
        if (findViewById(R.id.detail_view) != null) { // Check if Activity is in landscape mode
            landscape = true;

            getFragmentManager().popBackStack(); // Remove a fragment from the backstack.

            DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.detail_view);

            if (detailFragment == null) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                detailFragment = new DetailFragment();

                transaction.replace(R.id.detail_view, detailFragment, "Detail_Fragment_1");
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.commit();
            }

        }

    }

    // onStart method is a lifecycle method called after onCreate (or after onRestart when the
    // activity had been stopped, but is now again being displayed to the user)
    @Override
    protected void onStart() {
        super.onStart();

        Toast toast = Toast.makeText(getBaseContext(), "FirstActivity.onStart()", Toast.LENGTH_SHORT);
        toast.show();
    }

    // onRestart method is a lifecycle method called after onStop when the current activity is
    // being re-displayed to the user
    @Override
    protected void onRestart() {
        super.onRestart();

        Toast toast = Toast.makeText(getBaseContext(), "FirstActivity.onRestart()", Toast.LENGTH_SHORT);
        toast.show();
    }

    // onResume method is a lifecycle method called after onRestoreInstanceState, onRestart, or
    // onPause, for your activity to start interacting with the user
    @Override
    protected void onResume() {
        super.onResume();

        Toast toast = Toast.makeText(getBaseContext(), "FirstActivity.onResume()", Toast.LENGTH_SHORT);
        toast.show();
    }

    // onPause method is a lifecycle method called when an activity is going into the background,
    // but has not (yet) been killed
    @Override
    protected void onPause() {
        super.onPause();

        Toast toast = Toast.makeText(getBaseContext(), "FirstActivity.onPause()", Toast.LENGTH_SHORT);
        toast.show();
    }

    // onStop method is a lifecycle method called when the activity are no longer visible to the user
    @Override
    protected void onStop() {
        super.onStop();

        Toast toast = Toast.makeText(getBaseContext(), "FirstActivity.onStop()", Toast.LENGTH_SHORT);
        toast.show();
    }

    // onDestroy method is a lifecycle method that perform any final cleanup before an activity is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast toast = Toast.makeText(getBaseContext(), "FirstActivity.onDestroy()", Toast.LENGTH_SHORT);
        toast.show();
    }

    // 3. OVERRIDE onItemSelected METHOD HERE FROM MASTER FRAGMENT CLASS
    @Override
    public void onItemSelected(int position) {

        Toast.makeText(getBaseContext(), "FirstActivity.onItemSelected() ", Toast.LENGTH_SHORT).show();

        //Do something with the position value passed back
        Log.i("TAG","Position clicked " + position);


        if (landscape) {
            //3-A. IF IN LANDSCAPE MODE, UPDATE DETAIL FRAGMENT CONTENT

            DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.detail_view);
            detailFragment.updateFragmentContent(position);

        } else {
            //3-B. REPLACE EVERYTHING FORM MASTER FRAGMENT WITH DETAIL FRAGMENT BASED ON POSITION

            DetailFragment detailFragment = new DetailFragment();
            detailFragment.setFragmentContent(position);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.master_view, detailFragment, "Detail_Fragment_2");
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.addToBackStack(null); // So user can reverse transaction and bring back the previous fragment
            // by pressing the Back butto (from DetailFragment to MasterFragment)
            transaction.commit();

        }
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_food:
                Snackbar.make(findViewById(R.id.add_food),"ADD FOOD",Snackbar.LENGTH_LONG).show();
                return true;

            case R.id.edit_food:
                Snackbar.make(findViewById(R.id.edit_food),"EDIT FOOD",Snackbar.LENGTH_LONG).show();
                return true;

            case R.id.delete_food:
                Snackbar.make(findViewById(R.id.delete_food),"DELETE FOOD",Snackbar.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}