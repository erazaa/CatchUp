package pe.edu.utp.catchup.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.edu.utp.catchup.network.ClearbitLogoApiService;

/**
 * Created by GrupoUTP on 08/07/2017.
 */

public class Source {
    private String id;
    private String name;
    private String description;
    private String url;
    private String category;
    private String language;
    private String country;
    private List<String> sortBysAvailable;

    public Source() {
    }

    public Source(String id, String name, String description, String url, String category, String language, String country, List<String> sortBysAvailable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.category = category;
        this.language = language;
        this.country = country;
        this.sortBysAvailable = sortBysAvailable;
    }

    public String getId() {
        return id;
    }

    public Source setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Source setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Source setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Source setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Source setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public Source setLanguage(String language) {
        this.language = language;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Source setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getUrlToSmallLogo() {
        return ClearbitLogoApiService.getUrlToLogoFor(url, "small");
    }

    public String getUrlToMediumLogo() {
        return ClearbitLogoApiService.getUrlToLogoFor(url, "medium");
    }

    public String getUrlToLargeLogo() {
        return ClearbitLogoApiService.getUrlToLogoFor(url, "large");
    }

    public List<String> getSortBysAvailable() {
        return sortBysAvailable;
    }

    public Source setSortBysAvailable(List<String> sortBysAvailable) {
        this.sortBysAvailable = sortBysAvailable;
        return this;
    }

    public static Source build(JSONObject jsonSource) {
        if(jsonSource == null) return null;
        Source source = new Source();
        List<String> sortBysAvailable = new ArrayList<>();
        try {
            int length = jsonSource.getJSONArray("sortBysAvailable").length();
            for(int i = 0; i< length; i++) sortBysAvailable.add(
                    jsonSource.getJSONArray("sortBysAvailable").getString(i));

            source.setId(jsonSource.getString("id"))
                    .setName(jsonSource.getString("name"))
                    .setDescription(jsonSource.getString("description"))
                    .setUrl(jsonSource.getString("url"))
                    .setCategory(jsonSource.getString("category"))
                    .setLanguage(jsonSource.getString("language"))
                    .setCountry(jsonSource.getString("country"))
                    .setSortBysAvailable(sortBysAvailable);
            return source;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Source> build(JSONArray jsonSources) {
        if(jsonSources == null) return null;
        int length = jsonSources.length();
        List<Source> sources = new ArrayList<>();
        for(int i = 0; i < length; i++)
            try {
                sources.add(Source.build(jsonSources.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        return sources;
    }

}
