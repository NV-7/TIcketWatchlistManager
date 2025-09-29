package com.example.ticketwatchlistmanager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
public class SharedTickerViewModel extends ViewModel {

    private MutableLiveData<String> _selectedTicker = new MutableLiveData<String>();
    private LiveData<String> selectedTicker = _selectedTicker;

    public void setSelectedTicker(String ticker) {
        _selectedTicker.setValue(ticker);
    }

    public LiveData<String> getSelectedTicker() {
        return selectedTicker;
    }

}

