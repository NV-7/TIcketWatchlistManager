package com.example.ticketwatchlistmanager;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class TicketListFragment extends Fragment {

    private TicketListViewModel mViewModel;
    private SharedTickerViewModel sharedTickerViewModel;

    private Spinner tickerSpinner;
    private ArrayList<String> tickers;
    private String selectedItem;
    private EditText entry;
    private Button entryButton;

    private Boolean itemSelected = true;
    private SmsReceiver smsReceiver;
    private ArrayAdapter<String> adapter;


    public static TicketListFragment newInstance() {
        return new TicketListFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticket_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedTickerViewModel = new ViewModelProvider(requireActivity()).get(SharedTickerViewModel.class);
        mViewModel = new ViewModelProvider(this).get(TicketListViewModel.class);
        entry = view.findViewById(R.id.entryText);
        entryButton = view.findViewById(R.id.entryButton);
        tickers = new ArrayList<>();
        tickers.add("NEE");
        tickers.add("AAPL");
        tickers.add("DIS");
        entry.setHint("Add new entry here");

        entryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entryText = entry.getText().toString();
                addEntry(entryText);
                Toast.makeText(getContext(), "Entry added: " + entryText, Toast.LENGTH_SHORT).show();
            }

        }
        );


        tickerSpinner =  view.findViewById(R.id.tickerSpinner);
         adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, tickers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tickerSpinner.setAdapter(adapter);

        tickerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (itemSelected) {
                    itemSelected = false;
                } else {
                    selectedItem = parent.getItemAtPosition(pos).toString();
                    sharedTickerViewModel.setSelectedTicker(selectedItem);
                    Toast.makeText(getContext(), "Item selected: " + selectedItem, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sharedTickerViewModel.setSelectedTicker(null);
                Toast.makeText(getContext(), "Nothing selected", Toast.LENGTH_SHORT).show();
            }
        });
        sharedTickerViewModel.getNewTicker().observe(getViewLifecycleOwner(), newTicker -> {
            if(newTicker != null && !tickers.contains(newTicker))
            addEntry(newTicker);
        });
    }

public void addEntry(String entry){
        int size = tickers.size();
        if(size >= 6){
            tickers.set(5, entry);
        }
        else {
            tickers.add(entry);
        }
        if(adapter != null){
            adapter.notifyDataSetChanged();
        }
}

    public String getSelectedItem() {
        return selectedItem;
    }


}