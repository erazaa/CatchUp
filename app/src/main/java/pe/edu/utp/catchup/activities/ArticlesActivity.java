package pe.edu.utp.catchup.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.edu.utp.catchup.CatchUpApp;
import pe.edu.utp.catchup.R;
import pe.edu.utp.catchup.adapters.ArticlesAdapter;
import pe.edu.utp.catchup.models.Article;
import pe.edu.utp.catchup.models.Source;
import pe.edu.utp.catchup.network.NewApiService;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class ArticlesActivity extends AppCompatActivity {
    RecyclerView articlesRecyclerView;
    ArticlesAdapter articlesAdapter;
    RecyclerView.LayoutManager articlesLayoutManager;
    List<Article> articles;
    Source currentSource;
    private static String TAG = "CatchUp";
    private int spanCount = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        articlesRecyclerView = (RecyclerView) findViewById(R.id.articlesRecyclerView);
        articles = new ArrayList<>();
        articlesAdapter = new ArticlesAdapter(articles);
        spanCount = getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT ? 2 : 3;
        articlesLayoutManager = new GridLayoutManager(this, spanCount);
        articlesRecyclerView.setAdapter(articlesAdapter);
        articlesRecyclerView.setLayoutManager(articlesLayoutManager);
        currentSource = CatchUpApp.getInstance().getCurrentSource();
        updateArticles();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        spanCount = newConfig.orientation == ORIENTATION_PORTRAIT ? 2 : 3;
        ((GridLayoutManager)articlesLayoutManager).setSpanCount(spanCount);
    }

    private void updateArticles() {
        AndroidNetworking.get(NewApiService.ARTICLES_URL)
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .addQueryParameter("source", currentSource.getId())
                .addQueryParameter("apiKey", getString(R.string.news_api_key))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("status").equalsIgnoreCase("error")) {
                                Log.d(TAG, response.getString("message"));
                                return;
                            }
                            articles = Article.build(response.getJSONArray("articles"), currentSource);
                            articlesAdapter.setArticles(articles);
                            articlesAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, anError.getLocalizedMessage());
                    }
                });

    }

}
