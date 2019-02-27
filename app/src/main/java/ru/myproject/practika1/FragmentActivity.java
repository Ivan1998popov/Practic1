package ru.myproject.practika1;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;


public class FragmentActivity extends Activity {


    private DrawerLayout mDrawerLayout;
    private static String KEY_FRAGMENT = "key_fragment";
    private int temp_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


        mDrawerLayout = findViewById(R.id.my_drawer);
        NavigationView navigationView = findViewById(R.id.nav_view);


        if (savedInstanceState != null) {
            temp_id = savedInstanceState.getInt(KEY_FRAGMENT);

            switch (temp_id) {
                case R.id.str1:
                    Fragment_1 frag1 = new Fragment_1();
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, frag1)
                            .commit();
                    temp_id = 0;
                    break;
                case R.id.str3:
                    Fragment_3 frag3 = new Fragment_3();
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, frag3)
                            .commit();
                    temp_id = 1;
                    break;
                case R.id.str4:
                    Fragment_4 frag4 = new Fragment_4();
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, frag4)
                            .commit();
                    temp_id = 2;
                    break;
            }
        } else {
            Fragment_2 frag2 = new Fragment_2();
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, frag2)
                    .commit();
        }


        navigationView.setNavigationItemSelectedListener(


                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {
                            case R.id.str1:
                                menuItem.setChecked(true);
                                Fragment_1 frag1 = new Fragment_1();
                                getFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.content_frame, frag1).addToBackStack(null)
                                        .commit();

                                temp_id = 0;
                                break;
                            case R.id.str2:
                                Fragment_2 frag2 = new Fragment_2();
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.content_frame, frag2)
                                        .addToBackStack(null)
                                        .commit();

                                break;
                            case R.id.str3:
                                Fragment_3 frag3 = new Fragment_3();

                                getFragmentManager().beginTransaction()
                                        .replace(R.id.content_frame, frag3)
                                        .addToBackStack(null)
                                        .commit();
                                temp_id = 1;
                                break;
                            case R.id.str4:
                                Fragment_4 frag4 = new Fragment_4();
                                getFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.content_frame, frag4)
                                        .addToBackStack(null)
                                        .commit();
                                temp_id = 2;
                                break;
                        }
                        mDrawerLayout.closeDrawers();
                        return true;

                    }
                });


    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_FRAGMENT, temp_id);

    }
}
