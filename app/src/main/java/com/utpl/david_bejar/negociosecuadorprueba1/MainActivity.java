package com.utpl.david_bejar.negociosecuadorprueba1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static com.utpl.david_bejar.negociosecuadorprueba1.R.id.fab;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); //Permite activar la barra de notificaciones con acciones
        setSupportActionBar(toolbar);

        //Abre la URL
        WebView webView = (WebView) findViewById(R.id.web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebChromeClient(new GeoWebChromeClient()); //Llama a un ChromeClient para poder usar los permisios de geolocalización
        webView.loadUrl("https://negocios-ecuador.com/es");

        //Permite abrir los enlaces dentro de la misma webView
        webView.setWebViewClient(new WebViewClient()
        {
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                return false;
            }
        });



        //Provee comportamiento para el botón flotante, pero se lo ha ocultado.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    //permite cerrar el menú hamburguesa si está abierto al presionar la tecla atrás o retroceder en el Webview si hay páginas abiertas anteriormente
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        WebView webView = (WebView) findViewById(R.id.web);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (webView.copyBackForwardList().getCurrentIndex() > 0) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        WebView webView = (WebView) findViewById(R.id.web);

        if (id == R.id.nav_productos_servicios) {
            webView.loadUrl("https://negocios-ecuador.com/es/producto_servicio");
        } else if (id == R.id.nav_hospedar) {
            webView.loadUrl("https://negocios-ecuador.com/es/hotel_hostal");
        } else if (id == R.id.nav_cafeterías) {
            webView.loadUrl("https://negocios-ecuador.com/es/cafeteria");
        } else if (id == R.id.nav_restaurante) {
            webView.loadUrl("https://negocios-ecuador.com/es/restaurante");
        } else if (id == R.id.nav_populares) {
            webView.loadUrl("https://negocios-ecuador.com/es/populares");
        } else if (id == R.id.nav_contacto) {
            webView.loadUrl("https://negocios-ecuador.com/es/contact");
        } else if (id == R.id.nav_inicio) {
            webView.loadUrl("https://negocios-ecuador.com/es");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
