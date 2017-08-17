package pe.edu.utp.catchup.network;

/**
 * Created by GrupoUTP on 08/07/2017.
 */

public class ClearbitLogoApiService {
    public static String LOGO_BASE_URL = "https://logo.clearbit.com/";
    public static String SMALL_SIZE = "128";
    public static String MEDIUM_SIZE = "256";
    public static String LARGE_SIZE = "512";

    public static String getUrlToLogoFor(String domain, String size) {
        String url = LOGO_BASE_URL + domain;
        String logoSize = "?size=";
        switch(size) {
            case "small": logoSize += SMALL_SIZE; break;
            case "medium": logoSize += MEDIUM_SIZE; break;
            case "large": logoSize += LARGE_SIZE; break;
            default: logoSize += SMALL_SIZE; break;
        }
        return url + logoSize;
    }


}
