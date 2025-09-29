package com.example.ticketwatchlistmanager;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

private Spinner tickerSpinner;
    private final String[] dtickers = {"NEE", "AAPL", "DIS"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.list_fragment, new TicketListFragment());
        ft.add(R.id.web_fragment, new infoWebFragment());
        ft.commit();
        FragmentManager listFrag = getSupportFragmentManager();fm.findFragmentById(R.id.list_fragment);


    }
}