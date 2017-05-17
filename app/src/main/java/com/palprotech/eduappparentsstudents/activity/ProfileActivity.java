package com.palprotech.eduappparentsstudents.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.palprotech.eduappparentsstudents.R;
import com.palprotech.eduappparentsstudents.utils.PreferenceStorage;
import com.squareup.picasso.Picasso;

/**
 * Created by Narendar on 05/04/17.
 */

public class ProfileActivity extends AppCompatActivity {

    private ImageView mProfileImage = null;
    private TextView txtUsrName, txtUserType;
    private EditText txtUsrID, txtMail, txtPassword, numPhone, txtAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //new LoadProfile().execute();
        SetUI();
    }

    private void SetUI() {

        mProfileImage = (ImageView) findViewById(R.id.image_profile_pic);
        txtUsrID = (EditText) findViewById(R.id.userid);
        txtMail = (EditText) findViewById(R.id.txtmail);
        txtPassword = (EditText) findViewById(R.id.txtpassword);
        txtAddress = (EditText) findViewById(R.id.txtaddress);
        numPhone = (EditText) findViewById(R.id.txtphone);
        txtUsrName = (TextView) findViewById(R.id.name);
        txtUserType = (TextView) findViewById(R.id.typename);

        txtUsrID.setText(PreferenceStorage.getUserName(getApplicationContext()));
        txtMail.setText(PreferenceStorage.getEmail(getApplicationContext()));
//        txtPassword.setText(PreferenceStorage.get(getApplicationContext()));
        txtAddress.setText(PreferenceStorage.getAddress(getApplicationContext()));
        numPhone.setText(PreferenceStorage.getHomePhone(getApplicationContext()));
        txtUsrName.setText(PreferenceStorage.getName(getApplicationContext()));
        txtUserType.setText(PreferenceStorage.getUserTypeName(getApplicationContext()));

        String url = PreferenceStorage.getUserPicture(this);
        if ((url == null) || (url.isEmpty())) {
           /* if ((loginMode == 1) || (loginMode == 3)) {
                url = PreferenceStorage.getSocialNetworkProfileUrl(this);
            } */
        }
        if (((url != null) && !(url.isEmpty()))) {
            Picasso.with(this).load(url).placeholder(R.drawable.profile_pic).error(R.drawable.profile_pic).into(mProfileImage);
        }

    }


}

