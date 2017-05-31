package com.nikola.exampleactivities.activities;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nikola.exampleactivities.R;
import com.nikola.exampleactivities.adapters.DrawerAdapter;
import com.nikola.exampleactivities.async.CommentService;
import com.nikola.exampleactivities.async.SimpleReceiver;
import com.nikola.exampleactivities.async.SimpleService;
import com.nikola.exampleactivities.dialogs.AboutDialog;
import com.nikola.exampleactivities.fragments.DetailFragment;
import com.nikola.exampleactivities.fragments.MasterFragment;
import com.nikola.exampleactivities.model.NavigationItem;
import com.nikola.exampleactivities.tools.ReviewerTools;

import java.util.ArrayList;
import java.util.Date;

// Each activity extends Activity class
public class FirstActivity extends AppCompatActivity implements MasterFragment.OnItemSelectedListener {

    Toolbar toolbar;
    boolean landscape = false; // Portrait mode initially

    // NavigationDrawer Attributes
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private ActionBarDrawerToggle drawerToggle;
    private RelativeLayout drawerRelativeLayoutPane;
    private CharSequence drawerTitle;

    private ArrayList<NavigationItem> drawerNavigationItems = new ArrayList<>();

    // Attributes used by Dialog
    private AlertDialog dialog;


    private SimpleReceiver sync;
    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;

    private SharedPreferences sharedPreferences;
    private String syncTime;
    private boolean allowSync;

    // onCreate method is a lifecycle method called when he activity is starting
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Each lifecycle method should call the method it overrides
        super.onCreate(savedInstanceState);
        // setContentView method draws UI
        setContentView(R.layout.activity_first);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ACTION BAR
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_action_drawer);
            actionBar.setHomeButtonEnabled(true);
            actionBar.show();
        }

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
        DrawerAdapter drawerAdapter = new DrawerAdapter(this, drawerNavigationItems);

        // Sets a custom shadow that overlays the main content when NavigationDrawer opens
        //drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        // 3. setOnItemClickListener
        drawerListView.setOnItemClickListener(new DrawerItemClickListener());
        drawerListView.setAdapter(drawerAdapter);

        // Toggle Button
        drawerToggle = new ActionBarDrawerToggle(
                this,                           /* host Activity */
                drawerLayout,                   /* DrawerLayout object */
                toolbar,                        /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,           /* "open drawer" description for accessibility */
                R.string.drawer_close           /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu();        // Creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu();        // Creates call to onPrepareOptionsMenu()
            }
        };


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


    // SET CLICK LISTENER FOR LISTVIEW IN THE NAVIGATION DRAWER
    private class DrawerItemClickListener implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position == 0) {
                // First activity is already shown
            } else if (position == 1) {
                // SettingsActivity
                Intent settings = new Intent(FirstActivity.this, SettingsActivity.class);
                startActivity(settings);
            } else if (position == 2){
                // AboutDialog
                if (dialog == null) {
                    dialog = new AboutDialog(FirstActivity.this).prepareDialog();
                } else {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }

                dialog.show();
            }

            drawerListView.setItemChecked(position, true);
            setTitle(drawerNavigationItems.get(position).getTitle());
            drawerLayout.closeDrawer(drawerRelativeLayoutPane);
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
            case R.id.refresh:
                //new SimpleSyncTask(FirstActivity.this).execute(); //Dobro pokrenuta sinhronizacija
                Snackbar.make(findViewById(R.id.refresh), R.string.action_refresh,Snackbar.LENGTH_SHORT).show();
                int status = ReviewerTools.getConnectivityStatus(getApplicationContext());

                Intent serviceIntent = new Intent(FirstActivity.this, SimpleService.class);
                serviceIntent.putExtra("STATUS",status);

                startService(serviceIntent); // Pass Data to SimpleService
                return true; // break

            case R.id.add_dialog:
                addDialog();
                return true; // break

            case R.id.read:
                // Read From File
                String text = ReviewerTools.readFromFile(this,"my-file.txt");
                Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
                return true;

            case R.id.write:
                // Write To File
                ReviewerTools.writeToFile(new Date().toString(), this, "my-file.txt");
                return true;

            case R.id.test:
                // Testira da li fajl postoji i to prikazuje korisniku
                if (ReviewerTools.isFileExists(FirstActivity.this,"my-file.txt")){
                    Toast.makeText(this, "FILE EXISTS", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "FILE DOES NOT FEXISTS", Toast.LENGTH_SHORT).show();
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void addDialog() {
        // Potvrdom komentara ako ima interneta startuje se novi Servis koji pokrece AsynctAsk
        // Kada zavrsi posao salje Broadcast poruku sa sadrzajem komentara koja ce se
        // pokazati u NotificationManager-u.

        final Dialog dialog = new Dialog(FirstActivity.this);
        dialog.setContentView(R.layout.dialog_layout);
        dialog.setTitle(R.string.dialog);

        Button btnOK = (Button)dialog.findViewById(R.id.btn_ok);
        Button btnCancel = (Button)dialog.findViewById(R.id.btn_cancel);


        // OK: PREUZIMAMO SADRZAJ TEKSTUALNIH POLJA IZ DIJALOGA
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTitle = (EditText)dialog.findViewById(R.id.dialog_title);
                EditText editComment  = (EditText)dialog.findViewById(R.id.dialog_comment);

                Intent comment = new Intent(FirstActivity.this,CommentService.class);
                comment.putExtra("title",editTitle.getText().toString());
                comment.putExtra("comment",editComment.getText().toString());

                startService(comment);

                dialog.cancel();
            }
        });

        // CANCEL: ISPISUJEMO SNACKBAR
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this, R.string.no_comment, Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        dialog.show();
    }


    // onResume method is a lifecycle method called after onRestoreInstanceState, onRestart, or
    // onPause, for your activity to start interacting with the user
    /**
     * Prilikom startovanja aplikacije potrebno je registrovati
     * elemente sa kojima radimo. Kada aplikacija nije aktivna
     * te elemente moramo da uklonimo.
     */
    @Override
    protected void onResume() {
        super.onResume();

        setUpReceiver(); // Registracija jednog filtera
        setUpManager(); // AlarmManager
        
        Toast toast = Toast.makeText(getBaseContext(), "FirstActivity.onResume()", Toast.LENGTH_SHORT);
        toast.show();
    }


    /**
     * Registrujemo nas BroadcastReceiver i dodajemo mu 'filter'.
     * Filter koristimo prilikom prispeca poruka. Jedan receiver
     * moze da reaguje na vise tipova poruka. One nam kazu
     * sta tacno treba da se desi kada poruka odredjenog tipa (filera)
     * stigne.
     * */
    private void setUpReceiver() {
        sync= new SimpleReceiver(); // BroadcastReceiver

        // Registracija jednog filtera: <intent-filter>
        IntentFilter filter = new IntentFilter();
        filter.addAction("SYNC_DATA");
        filter.addAction("COMMENT");
        registerReceiver(sync,filter);

         /**
            getDefaultSharedPreferences():
            koristi podrazumevano ime preference-file-a.
            Podrzazumevani fajl je setovan na nivou aplikacije tako da sve aktivnosti u istom context-u
            mogu da mu pristupe jednostavnije

            getSharedPreferences(name,mode):
            trazi da se specificira ime preference file-a requires i mod u kome se radi
             (e.g. private, world_readable, etc.)
        */
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        consultPreferences();
    }

    private void consultPreferences() {
        // ListPreference KEY
        syncTime = sharedPreferences.getString(getString(R.string.pref_sync_list), "1");
        // CheckBoxPreference KEY
        allowSync = sharedPreferences.getBoolean(getString(R.string.pref_sync), false);

        /** UPISIVANJE VREDNOSTI
         * SharedPreferences.Editor editor = sharedPreferences.edit();
         * editor.putString("KLJUC","VREDNOST");
         * editor.commit();
         */

        /** IZVLACENJE VREDNOSTI
         * String str = sharedPreferences.getString("KLJUC", "PODRAZUMEVANA VREDNOST");
         */
    }


    /**
     *  Kada zelimo da se odredjeni zadaci ponavljaju potrebno je da regustrujemo manager
        koji ce motriti kada je vreme da se taj posao obavi. Kada registruje vreme za pokretanje zadatka
        on emituje Intent operativnom sistemu sta je potrebno da se desi
    * */
    private void setUpManager() {
        Intent intent = new Intent(this,SimpleService.class);
        int status = ReviewerTools.getConnectivityStatus(getApplicationContext());
        intent.putExtra("STATUS",status);

        if (allowSync) { // Ukoliko korisnik dozvoli sinhronizaciju
            // Definisemo manager i kazemo kada je potrebno da se ponavlja
            pendingIntent = PendingIntent.getService(this,0,intent,0);
            // Definisemo alarm, i kako ce alarm da reaguje
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),ReviewerTools.calculateTimeTillNextSync(1),pendingIntent);
            // calculateTimeTillNextSync(1) 1 minute

            Toast.makeText(this,"ALARM SET", Toast.LENGTH_SHORT).show();
        }

    }

    // onPause method is a lifecycle method called when an activity is going into the background,
    // but has not (yet) been killed
    @Override
    protected void onPause() {
        super.onPause();

        //Ako je manager kreiran potrebno je da ga uklonimp
        if (alarmManager !=null){
            alarmManager.cancel(pendingIntent);
            alarmManager = null;
        }

        //Osloboditi resurse koje koristi receiver
        if (sync !=null) {
            unregisterReceiver(sync);
            sync = null;
        }

        Toast toast = Toast.makeText(getBaseContext(), "FirstActivity.onPause()", Toast.LENGTH_SHORT);
        toast.show();
    }

}
