package com.example.ticketwatchlistmanager;

import androidx.core.view.DragAndDropPermissionsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class infoWebFragment extends Fragment {

    private SharedTickerViewModel sharedTickerViewModel;
    private InfoWebViewModel mViewModel;
    private WebView webView;
    private TicketListFragment ticketListFragment;
    private String defaultUrl = "https://seekingalpha.com/";

    public infoWebFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_web, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedTickerViewModel = new ViewModelProvider(requireActivity()).get(SharedTickerViewModel.class);

        mViewModel = new ViewModelProvider(this).get(InfoWebViewModel.class);
        ticketListFragment = new TicketListFragment();

        webView = (WebView) getView().findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl(defaultUrl);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });

        sharedTickerViewModel.getSelectedTicker().

    observe(getViewLifecycleOwner(), new Observer<String>()

    {

        @Override
        public void onChanged (String ticker){
        if (ticker != null && !ticker.isEmpty()) {
            webView.loadUrl(defaultUrl + "/symbol/" + ticker);
        } else {
            webView.loadUrl(defaultUrl);
        }
    }
    });

        if(sharedTickerViewModel.getSelectedTicker().

    getValue() ==null)

    {
        webView.loadUrl(defaultUrl);
    }
}


}

