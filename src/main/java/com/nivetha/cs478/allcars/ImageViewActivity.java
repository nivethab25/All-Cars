package com.nivetha.cs478.allcars;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ImageViewActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the Intent used to start this Activity
        final Intent intent = getIntent();

        // Make a new ImageView
        ImageView imageView = new ImageView(getApplicationContext());

        // Get the ID of the image to display and set it as the image for this ImageView
        imageView.setImageResource(intent.getIntExtra(CarThumbnails.EXTRA_RES_ID, 0));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Create an Intent to start browser activity
                Intent webView = new Intent(Intent.ACTION_VIEW);

                //Set the Intent data to the corresponding manufacturer's url
                webView.setData(Uri.parse(intent.getStringExtra(CarThumbnails.EXTRA_WEB_URL)));

                //Start the browser activity
                startActivity(webView);
            }
        });

        setContentView(imageView);
    }
}
