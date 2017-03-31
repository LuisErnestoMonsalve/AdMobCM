package co.edu.udea.compumovil.gr01_20171.mobileads;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity{
    private InterstitialAd interstitialAd;
    private boolean videoLoad;
    private Object mLock = new Object();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-2496422086428451~6777844121");

        AdView adView = (AdView) findViewById(R.id.adView);
        //AdRequest adRequest = new AdRequest.Builder().build(); si quieres que tu aplicacion muestre el ad sin ser tester
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getResources().getString(R.string.bannerON));
        requestNewInterstitial();

        /* si quieres que aparezca el ad sin necesitar el botón solo agregas
           if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                }
           y comentas la parte del botón
        */
        Button viewButton = (Button) findViewById(R.id.buttonView);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                }else{
                    requestNewInterstitial();
                }
            }
        });


    }

    private void requestNewInterstitial() {
        //AdRequest adRequest = new AdRequest.Builder().build(); si quieres que tu aplicacion muestre el ad sin ser tester
        //para probar se necesita el id del dispositivo, si ejecutas así y miras el log, aparece una recomendacion
        //la recomendacion te da el id de tu dispositivo.
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("33836106356A636490B9B055A7A1C1C9")
                .build();

        interstitialAd.loadAd(adRequest);
    }




}

