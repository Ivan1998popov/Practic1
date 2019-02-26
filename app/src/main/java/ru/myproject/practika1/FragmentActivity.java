package ru.myproject.practika1;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

public class FragmentActivity extends Activity {


    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);




        mDrawerLayout = findViewById(R.id.my_drawer);
        NavigationView navigationView = findViewById(R.id.nav_view);

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
                                        .replace(R.id.content_frame,frag1)
                                        .commit();
                                Toast.makeText(FragmentActivity.this,"Привет из str1"
                                        ,Toast.LENGTH_LONG).show();
                                break;
                            case R.id.str2:
                                Fragment_2 frag2 = new Fragment_2();
                                getFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.content_frame,frag2)
                                        .commit();
                                break;
                            case R.id.str3:

                                break;
                            case R.id.str4:

                                break;
                        }
                        mDrawerLayout.closeDrawers();
                        return true;

                    }
                });


    }


}
