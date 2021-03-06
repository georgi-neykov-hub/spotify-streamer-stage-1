package com.neykov.spotifystreamer.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.neykov.spotifystreamer.R;
import com.neykov.spotifystreamer.ui.base.ActionBarConfigurable;
import com.neykov.spotifystreamer.ui.base.ActionbarConfigurator;

public class MainActivity extends AppCompatActivity implements ActionBarConfigurable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeToolbar();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, ArtistListFragment.newInstance(), ArtistListFragment.TAG)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getSupportFragmentManager().popBackStack();
                return true;
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                this.startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onApplyConfiguratorOptions(ActionbarConfigurator configurator) {
        boolean hasTitle = configurator.hasScreenTitle();
        getSupportActionBar().setDisplayShowTitleEnabled(hasTitle);

        if (hasTitle) {
            this.setTitle(configurator.getScreenTitle());
            getSupportActionBar().setSubtitle(configurator.getScreenSubtitle());
        }

        if (configurator.hasBackNavigation()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    private void initializeToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
    }
}
