package com.example.libraryspinnerwithdefaultelement2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

@SuppressLint("AppCompatCustomView")
public class CustomSpinner extends Spinner {

    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.answer, R.layout.custom_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        super.setAdapter(new NothingSelectedSpinnerAdapter(
                adapter,
                R.layout.contact_spinner_row_nothing_selected,
                getContext()));
    }


    public interface OnSpinnerEventsListener {
        void onSpinnerOpened(Spinner spinner);

        void onSpinnerClosed(Spinner spinner);
    }

    private OnSpinnerEventsListener mListener;
    private boolean mOpenInitiated = false;
    
    @SuppressLint("ResourceAsColor")
    public boolean performClick() {
        mOpenInitiated = true;
        if (mListener != null) {
            mListener.onSpinnerOpened(this);
        }
        return super.performClick();
    }

    public void performClosedEvent() {
        mOpenInitiated = false;
        if (mListener != null) {
            mListener.onSpinnerClosed(this);
        }
    }

    boolean hasBeenOpened() {
        return mOpenInitiated;
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasBeenOpened() && hasFocus) {
            performClosedEvent();
        }
    }
}