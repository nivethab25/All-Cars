package com.nivetha.cs478.allcars;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

class CustomGridAdapter extends BaseAdapter {

    //This will have to be passed to the ImageView and TextView
    private Context mContext;

    //Adapter must store AdapterView's items
    private List<Integer> mThumbImageIds;
    private List<Integer> mThumbTextIds;
    private List<Integer> mWebUrlIds;

    //Layout inflator for the gridview thumbnails
    private static LayoutInflater inflater = null;

    CustomGridAdapter(Context c, List<Integer> imageIds, List<Integer> manufacturerIds, List<Integer> urlIds) {

        //Save the list of image IDs, car manufacturers and the context
        mContext = c;
        this.mThumbImageIds = imageIds;
        this.mThumbTextIds = manufacturerIds;
        this.mWebUrlIds = urlIds;

        //Instantiate layout inflator
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //Now the methods inherited from abstract superclass BaseAdapter

    //Return the number of items in the Adapter
    @Override
    public int getCount() {
        return mThumbImageIds.size();
    }

    //Return the data item at position
    @Override
    public Object getItem(int position) {
        return mThumbImageIds.get(position);
    }

    //Will get called to provide the ID that is passed to OnItemClickListener.onItemClick()
    @Override
    public long getItemId(int position) {
        return mThumbImageIds.get(position);
    }

    //Will return the url for the manufactures's website
    public String getWebUrl(int position){
        return mContext.getString(mWebUrlIds.get(position));
    }

    //Holder for the gridview cell
    public class Holder
    {
        ImageView carImage;
        TextView manufacturer;
    }

    // Return a View for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            //Inflate the gridview cell
            View carView;
            carView = inflater.inflate(R.layout.car_gridcell, null);

            //Set the imageview and textview in the holder
            Holder holder = new Holder();
            holder.carImage = carView.findViewById(R.id.carImage);
            holder.carImage.setImageResource(mThumbImageIds.get(position));

            holder.manufacturer = carView.findViewById(R.id.manufacturerName);
            holder.manufacturer.setGravity(Gravity.CENTER);
            holder.manufacturer.setText(mThumbTextIds.get(position));

        return carView;
    }
}
