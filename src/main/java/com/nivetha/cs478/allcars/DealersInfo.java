package com.nivetha.cs478.allcars;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;

public class DealersInfo extends AppCompatActivity {

    private Intent mIntent;
    private int position ;
    private Context mContext ;

    //Private fields to hold the resource array identifiers built dynamically
    private int mDealerNames ;
    private int mDealerAddresses ;

    //List to hold dealer names from resource file
    private ArrayList<String> mDealerNameIds ;

    //List to hold Dealer addresses from resource file
    private ArrayList<String> mDealerAddressIds ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealers_info);

        final ListView listView = (ListView) findViewById(R.id.listView);

        //Get the intent that started this activity
        mIntent = getIntent();

        //Get the position of the grid cell selected from the intent extra
        position = mIntent.getIntExtra(CarThumbnails.EXTRA_RES_POS,0) + 1;

        //Get the context for the listview
        mContext = listView.getContext();

        //Dynamically construct the string array identifiers in the resource file based on the grid cell selected
        mDealerNames = mContext.getResources().getIdentifier("dealersNames"+position,"array",this.getPackageName());
        mDealerAddresses = mContext.getResources().getIdentifier("dealersAddresses"+position,"array",this.getPackageName());

        //Set the dealer names for the manufacturer
        mDealerNameIds = new ArrayList<>(
                Arrays.asList(mContext.getResources().getStringArray(mDealerNames)));

        //Set the dealer addresses for the manufacturer
        mDealerAddressIds = new ArrayList<>(
                Arrays.asList(mContext.getResources().getStringArray(mDealerAddresses)));

        // Create a new CustomListAdapter and set it as the Adapter for this GridView
        final CustomListAdapter adapter = new CustomListAdapter(this, mDealerNameIds, mDealerAddressIds);
        listView.setAdapter(adapter);

        }


}
