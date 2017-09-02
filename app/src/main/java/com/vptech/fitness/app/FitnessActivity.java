package com.vptech.fitness.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vptech.fitness.app.fitness.ActivityResult;
import com.vptech.fitness.app.fitness.ActivityProperty;
import com.vptech.fitness.app.fitness.FitnessManager;
import com.vptech.fitness.app.fitness.FitnessProperty;
import com.vptech.fitness.app.fitness.FitnessRequest;
import com.vptech.fitness.app.fitness.FitnessResult;

import java.util.List;

/**
 * Activity which displays fitness information about the current user
 * @author Aaron Vontell
 */
public class FitnessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /**
     * Downloads and updates the displayed fitness information
     */
    public void refreshFitnessOverview() {

        FitnessManager manager = FitnessManager.connect();
        FitnessRequest req = new FitnessRequest();
        manager.fitnessRequest(req, new FitnessResult() {
            @Override
            public void onResult(List<FitnessProperty> properties) {


            }
        });

    }

    /**
     * Downloads and updates the displayed activity information
     */
    public void refreshActivityOverview() {

        FitnessManager manager = FitnessManager.connect();
        FitnessRequest req = new FitnessRequest();
        manager.activityRequest(req, new ActivityResult() {
            @Override
            public void onResult(List<ActivityProperty> activities) {


            }
        });

    }

}
