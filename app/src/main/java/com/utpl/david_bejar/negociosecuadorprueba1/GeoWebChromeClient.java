package com.utpl.david_bejar.negociosecuadorprueba1;

import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by david on 12-Jul-17.
 */

//ChromeClient para poder usar los permisios de geolocalización

public class GeoWebChromeClient extends WebChromeClient {
    @Override
    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
        callback.invoke(origin, true, false);
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url){
        return false;
    }
}
