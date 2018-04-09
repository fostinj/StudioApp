package com.example.nandhu.studioapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().hide();
        Element adsElement = new Element();
        adsElement.setTitle("Call Us");
        adsElement.setIconDrawable(android.R.drawable.ic_menu_call);
        adsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityCompat.requestPermissions(About.this,
                        new String[]{Manifest.permission.CALL_PHONE}, 1);


            }
        });
        View aboutPage = new AboutPage(this)
                .isRTL(false)
                /*.setImage(R.drawable.logoneelima)*/
                .setDescription("Neelima Studio \n Ponkunnam")
                .addItem(new Element().setTitle("Version 1.0"))
                .addItem(adsElement)
                .addGroup("Connect with us")
                .addEmail("neelimastudio@gmail.com")
                .addWebsite("http://www.neelimastudio.com")
                .addFacebook("https://www.facebook.com/neelimastudio/")
                .addYoutube("https://www.youtube.com/user/jensneelima/videos?view=0&flow=grid")
                .addInstagram("https://www.instagram.com/jensneelima09/")
                .create();

        setContentView(aboutPage);
    }




    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_CALL);

                    intent.setData(Uri.parse("tel:" + "9947103148"));
                    if (ActivityCompat.checkSelfPermission(About.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(intent);
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(About.this, "Permission Not Granted", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }



}
