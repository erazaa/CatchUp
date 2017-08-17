package pe.edu.utp.catchup;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

import pe.edu.utp.catchup.models.Article;
import pe.edu.utp.catchup.models.Source;
import pe.edu.utp.catchup.network.NewApiService;

/**
 * Created by GrupoUTP on 08/07/2017.
 */

public class CatchUpApp extends Application {
    private static CatchUpApp instance;
    private NewApiService newApiService;

    public CatchUpApp() {
        super();
        instance = this;
    }

    public static CatchUpApp getInstance() { return instance; }

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
        newApiService = new NewApiService();
    }

    public CatchUpApp setCurrentSource(Source source) {
        newApiService.setCurrentSource(source);
        return this;
    }

    public Source getCurrentSource() {
        return newApiService.getCurrentSource();
    }

    public CatchUpApp setCurrentArticle(Article article) {
        newApiService.setCurrentArticle(article);
        return this;
    }

    public Article getCurrentArticle() { return newApiService.getCurrentArticle(); }
}
