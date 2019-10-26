package com.Softpig.View.fragment.Male;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MaleViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MaleViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}