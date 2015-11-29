package com.SocScore.android.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.SocScore.framework.data.LeagueAnalysis;
import com.SocScore.framework.data.Team;
import com.SocScore.framework.scorekeeper.BatchInput;

import org.joda.time.LocalDateTime;

public class BatchInputActivity extends AppCompatActivity {

    private FloatingActionButton fab_create_new_team;
    BatchInput batchInput = new BatchInput();
    private EditText et_team1;
    private EditText et_team2;
    private String str_team1;
    private String str_team2;
    Team team1;
    Team team2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_input);
        setUpVariables();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_batch);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_league:
                        Intent leagueInput = new Intent(BatchInputActivity.this, LeagueInputActivity.class);
                        startActivity(leagueInput);
                        return true;

                    case R.id.action_live:
                        Intent liveInput = new Intent(BatchInputActivity.this, LiveMatchActivity.class);
                        startActivity(liveInput);
                        return true;

                    case R.id.action_main:
                        Intent batchInput = new Intent(BatchInputActivity.this, MainActivity.class);
                        startActivity(batchInput);
                        return true;

                    case R.id.access_analysis_viewer:
                        Intent analysisViewer = new Intent(BatchInputActivity.this , AnalysisViewerActivity.class);
                        startActivity(analysisViewer);
                        return true;

                    default:
                        // If we got here, the user's action was not recognized.
                        // Invoke the superclass to handle it.
                        return onMenuItemClick(menuItem);
                }
            }
        });
        fab_create_new_team = (FloatingActionButton) findViewById(R.id.fab_create_team);
        fab_create_new_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_team1 = et_team1.getText().toString();
                str_team2 = et_team2.getText().toString();
                if(LeagueInputActivity.foundTeam(str_team1) && LeagueInputActivity.foundTeam(str_team2))
                {
                    team1 = LeagueAnalysis.findTeam(LeagueInputActivity.findTeamID(str_team1));
                    team2 = LeagueAnalysis.findTeam(LeagueInputActivity.findTeamID(str_team2));
                    batchInput.createMatch(team1 , team2 , new LocalDateTime() , new LocalDateTime().plusMinutes(90));
                }
                else Toast.makeText(getApplicationContext() , "Teams " + str_team1 + " and " + str_team2 + " are not available" , Toast.LENGTH_LONG).show();
            }
        });
    }

    public void setUpVariables()
    {
        et_team1 = (EditText) findViewById(R.id.et_team1);
        et_team2 = (EditText) findViewById(R.id.et_team2);
    }

}
