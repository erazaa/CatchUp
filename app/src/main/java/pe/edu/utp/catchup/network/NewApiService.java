package pe.edu.utp.catchup.network;

import pe.edu.utp.catchup.models.Article;
import pe.edu.utp.catchup.models.Source;

/**
 * Created by GrupoUTP on 08/07/2017.
 */

public class NewApiService {
    public static String SOURCES_URL = "https://newsapi.org/v1/sources";
    public static String ARTICLES_URL = "https://newsapi.org/v1/articles";
    private Source currentSource;
    private Article currentArticle;

    public Source getCurrentSource() {
        return currentSource;
    }

    public NewApiService setCurrentSource(Source currentSource) {
        this.currentSource = currentSource;
        return this;
    }

    public NewApiService setCurrentArticle(Article currentArticle) {
        this.currentArticle = currentArticle;
        return this;
    }

    public Article getCurrentArticle() { return currentArticle; }
}
