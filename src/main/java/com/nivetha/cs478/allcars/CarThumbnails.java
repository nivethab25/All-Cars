package com.nivetha.cs478.allcars;

import java.util.ArrayList;
import java.util.Arrays;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class CarThumbnails extends AppCompatActivity {

    //Static fields to set intent extras
    protected static final String EXTRA_RES_ID = "POS";
    protected static final String EXTRA_WEB_URL = "URL";
    protected static final String EXTRA_RES_POS = "ID";

    private int position;
    private  Context context;

    //List of car images initialized using string resources
    private ArrayList<Integer> mThumbIdsCars = new ArrayList<>(
            Arrays.asList(R.drawable.image1, R.drawable.image2,
                    R.drawable.image3, R.drawable.image4, R.drawable.image5,
                    R.drawable.image6, R.drawable.image7, R.drawable.image8,
                    R.drawable.image9, R.drawable.image10, R.drawable.image11,
                    R.drawable.image12,R.drawable.image13,R.drawable.image14,R.drawable.image15));

    //List of manufacturers initialized using string resources
    private ArrayList<Integer> mThumbIdsManufacturers = new ArrayList<>(
            Arrays.asList(R.string.manufacturer1, R.string.manufacturer2,
                    R.string.manufacturer3, R.string.manufacturer4, R.string.manufacturer5,
                    R.string.manufacturer6, R.string.manufacturer7, R.string.manufacturer8,
                    R.string.manufacturer9, R.string.manufacturer10, R.string.manufacturer11,
                    R.string.manufacturer12, R.string.manufacturer13,R.string.manufacturer14,R.string.manufacturer15));

    //List of urls for manufacturer's website initialized using string resources
    private ArrayList<Integer> mWebUrlIds = new ArrayList<>(
            Arrays.asList(R.string.url1, R.string.url2, R.string.url3,
                    R.string.url4, R.string.url5, R.string.url6, R.string.url7,
                    R.string.url8, R.string.url9, R.string.url10, R.string.url11,
                    R.string.url12,R.string.url13,R.string.url14,R.string.url15));


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_thumbnails);

        //Binding gridview for car thumbnails
        final GridView gridview = (GridView) findViewById(R.id.gridview);

        // Create a new CustomGridAdapter and set it as the Adapter for this GridView
        final CustomGridAdapter adapter = new CustomGridAdapter(this, mThumbIdsCars, mThumbIdsManufacturers, mWebUrlIds);

        //Setting the adapter for the gridview
        gridview.setAdapter(adapter);

        //Registering the gridview for the context menu
        registerForContextMenu(gridview);

        // Set an setOnItemClickListener on the GridView
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                //Create an Intent to start the ImageViewActivity
                Intent intent = new Intent(CarThumbnails.this,
                        ImageViewActivity.class);

                // Add the ID of the thumbnail to display as an Intent Extra
                intent.putExtra(EXTRA_RES_ID, (int) id);

                //Add the weburl for the manufacturer as intent extra
                intent.putExtra(EXTRA_WEB_URL, adapter.getWebUrl(position));

                // Start the ImageViewActivity
                startActivity(intent);
            }
        });
    }

    // Create Context Menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

        //Inflate the context menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_thumbnails, menu);

        //Get the adapter context menu info for position of gridcell which was long-clicked
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
        position = adapterContextMenuInfo.position;
        context = v.getContext();

    }

    // Invoked when user clicks on the Context Menu Items
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.view_image:

                //Create an intent to start the ImageViewActivity
                Intent intent = new Intent(CarThumbnails.this,
                        ImageViewActivity.class);

                // Add the ID of the thumbnail to display as an Intent Extra
                intent.putExtra(EXTRA_RES_ID, (int) mThumbIdsCars.get(position));

                //Add the weburl to navigate to manufacturer's official website
                intent.putExtra(EXTRA_WEB_URL, context.getString(mWebUrlIds.get(position)));

                // Start the ImageViewActivity
                startActivity(intent);

                return true;

            case R.id.open_website:

                //Create an intent to start browser activity
                Intent webView = new Intent(Intent.ACTION_VIEW);

                //Set the manufacturer's url to open in the browser
                webView.setData(Uri.parse(context.getString(mWebUrlIds.get(position))));

                //Start the browser activity
                startActivity(webView);

                return true;

            case R.id.view_dealers:

                //Create an intent to display dealers info
                Intent dealerView = new Intent(CarThumbnails.this, DealersInfo.class);

                //Add position of thumbnail to get dealer info
                dealerView.putExtra(EXTRA_RES_POS, position);

                //Start the DealersInfo activity
                startActivity(dealerView);

                return true;

            default:
                return false;
        }
    }


}