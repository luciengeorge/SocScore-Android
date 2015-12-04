package com.SocScore.android.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.SocScore.framework.AnalysisViewer;
import com.SocScore.framework.data.Match;

import java.util.ArrayList;
import java.util.List;

public class MatchByDateActivity extends AppCompatActivity {

    private ListView list_view_matches_date;
    private List<Match> matches = new ArrayList<>();
    private AnalysisViewer analysisViewer = new AnalysisViewer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_by_date);
        setUpVariables();
        MatchAdapter matchAdapter = new MatchAdapter(matches , MatchByDateActivity.this);
        list_view_matches_date.setAdapter(matchAdapter);
    }

    public void setUpVariables()
    {
        matches = analysisViewer.getMatchesByDate();
        list_view_matches_date = (ListView) findViewById(R.id.list_view_matches_date);
    }

    public void closeMatchAnalysisDate(View view)
    {
        Intent intent = new Intent(this , AnalysisViewerActivity.class);
        startActivity(intent);
    }

    private class MatchAdapter extends ArrayAdapter<Match>
    {
        private List<Match> match_list;
        private Context context;
        private int layout_id;
        public MatchAdapter(List<Match> matches , Context ctx)
        {
            super(ctx, R.layout.match_analysis_view, matches);
            this.match_list = matches;
            this.context = ctx;
            this.layout_id = R.layout.match_analysis_view;
        }
        public View getView(int position , View convertView , ViewGroup parent)
        {
            if(convertView == null)
            {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(layout_id , parent , false);
            }


            return convertView;
        }
    }

}