package com.example.ticketwatchlistmanager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
public class SharedTickerViewModel extends ViewModel {

    private MutableLiveData<String> _selectedTicker = new MutableLiveData<String>();
    private LiveData<String> selectedTicker = _selectedTicker;
    private MutableLiveData<String> newTicker = new MutableLiveData<String>();
    public LiveData<String> newTickerFrom = newTicker;



    public void setSelectedTicker(String ticker) {
        _selectedTicker.setValue(ticker);
    }

    public LiveData<String> getSelectedTicker() {
        return selectedTicker;
    }

    public LiveData<String> getNewTicker() {
        return newTickerFrom;
    }


    public void addNewTicker(String ticker){
        newTicker.setValue(ticker);
    }

}

