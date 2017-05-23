package com.palprotech.eduappparentsstudents.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.palprotech.eduappparentsstudents.R;
import com.palprotech.eduappparentsstudents.interfaces.DialogClickListener;

/**
 * Created by Narendar on 18/04/17.
 */

public class CommunicationActivity extends AppCompatActivity implements DialogClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);
    }

    @Override
    public void onAlertPositiveClicked(int tag) {

    }

    @Override
    public void onAlertNegativeClicked(int tag) {

    }
}
