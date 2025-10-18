package com.example.ticketwatchlistmanager;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private static final int SMS_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, SMS_PERMISSION_CODE);
        }


        if (savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.list_fragment, new TicketListFragment());
            ft.add(R.id.web_fragment, new infoWebFragment());
            ft.commit();
        }
        intentHandler(getIntent());

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        intentHandler(getIntent());
//    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        intentHandler(intent);
    }

    private void intentHandler(Intent intent){
        String tickerSymbol = "TickerSymbol";
        if(intent != null && intent.hasExtra(tickerSymbol)){
            String ticker = intent.getStringExtra(tickerSymbol);
            if(ticker != null && !ticker.isEmpty()){
                SharedTickerViewModel sharedViewModel = new ViewModelProvider(this).get(SharedTickerViewModel.class);
                sharedViewModel.addNewTicker(ticker);
                intent.removeExtra(tickerSymbol);
            }
        }


    }


}