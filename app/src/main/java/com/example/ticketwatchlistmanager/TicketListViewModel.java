package com.example.ticketwatchlistmanager;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TicketListViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<String> selectedItem = new MutableLiveData<String>();
    public void setSelectedItem(String item) {
        selectedItem.setValue(item);
    }
    public MutableLiveData<String> getSelectedItem() {
        return selectedItem;

    }
}