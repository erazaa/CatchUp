package pe.edu.utp.catchup.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.edu.utp.catchup.R;
import pe.edu.utp.catchup.adapters.SourcesAdapter;
import pe.edu.utp.catchup.models.Source;
import pe.edu.utp.catchup.network.NewApiService;

public class SourcesActivity extends AppCompatActivity {
    List<Source> sources;
    private static String TAG = "CatchUp";
    RecyclerView sourcesRecyclerView;
    SourcesAdapter sourcesAdapter;
    RecyclerView.LayoutManager sourcesLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sources = new ArrayList<>();
        sourcesRecyclerView = (RecyclerView) findViewById(R.id.sourcesRecyclerView);
        sourcesAdapter = new SourcesAdapter(sources);
        sourcesLayoutManager = new LinearLayoutManager(this);
        sourcesRecyclerView.setAdapter(sourcesAdapter);
        sourcesRecyclerView.setLayoutManager(sourcesLayoutManager);
        updateSources();
    }

    private void updateSources() {
        AndroidNetworking
                .get(NewApiService.SOURCES_URL)
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response == null) return;
                        try {
                            if("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(TAG, response.getString("message"));
                                return;
                            }
                            sources = Source.build(response.getJSONArray("sources"));
                            Log.d(TAG, "Found Sources: " + String.valueOf(sources.size()));
                            sourcesAdapter.setSources(sources);
                            sourcesAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, anError.getMessage());

                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sources, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
