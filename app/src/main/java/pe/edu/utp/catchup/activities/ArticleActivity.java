package pe.edu.utp.catchup.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import pe.edu.utp.catchup.CatchUpApp;
import pe.edu.utp.catchup.R;
import pe.edu.utp.catchup.models.Article;

public class ArticleActivity extends AppCompatActivity {
    private ANImageView pictureANImageView;
    private TextView titleTextView;
    private TextView descriptionTextView;
    private ImageButton browserImageButton;
    Article article;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pictureANImageView = (ANImageView) findViewById(R.id.pictureANImageView);
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        browserImageButton = (ImageButton) findViewById(R.id.browserImageButton);

        article = CatchUpApp.getInstance().getCurrentArticle();
        titleTextView.setText(article.getTitle());
        descriptionTextView.setText(article.getDescription());
        pictureANImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        pictureANImageView.setErrorImageResId(R.mipmap.ic_launcher);
        pictureANImageView.setImageUrl(article.getUrlToImage());

        browserImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(article.getUri());
                startActivity(browserIntent);
            }
        });



    }

}
