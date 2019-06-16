package com.shohiebsense.timeutils;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;

public class CustomTimePickerDialog extends TimePickerDialog {


    public CustomTimePickerDialog(Context context, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
        super(context, listener, hourOfDay, minute, is24HourView);
    }

    public CustomTimePickerDialog(Context context, int themeResId, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
        super(context, themeResId, listener, hourOfDay, minute, true);
    }


    @Override
    public void onTimeChanged (TimePicker view, int hourOfDay, int minute) {
        // to prevent the dialog from changing the title which by default contains AM/PM
    }




}
