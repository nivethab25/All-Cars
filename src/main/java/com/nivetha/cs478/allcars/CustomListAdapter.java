package com.nivetha.cs478.allcars;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by nivetha on 10/1/17.
 */

public class CustomListAdapter extends BaseAdapter{

    //This will have to be passed to the ImageView and TextView
    private Context mContext;

    //Adapter must store AdapterView's items
    private List<String> mDealerNameIds;
    private List<String> mDealerAddressIds;


    private static LayoutInflater inflater = null;

    CustomListAdapter(Context c, List<String> nameIds, List<String> addressIds) {

        //Save the list of image IDs, car manufacturers and the context
        mContext = c;
        this.mDealerNameIds = nameIds;
        this.mDealerAddressIds = addressIds;


        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //Now the methods inherited from abstract superclass BaseAdapter

    //Return the number of items in the Adapter
    @Override
    public int getCount() {
        return mDealerNameIds.size();
    }

    //Return the data item at position
    @Override
    public Object getItem(int position) {
        return mDealerNameIds.get(position);
    }

    //Will get called to provide the ID that is passed to OnItemClickListener.onItemClick()
    @Override
    public long getItemId(int position) {
        return Long.valueOf(mDealerNameIds.get(position));
    }

    //To disable onclick in the listview
    @Override
    public boolean isEnabled(int position){
        return false;
    }

    //Holder for the listview cell
    public class Holder
    {
        TextView dealerName;
        TextView dealerAddress;
    }

    //Return a View for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Inflate the listview
        View dealerView;
        dealerView = inflater.inflate(R.layout.dealer_list, null);

        //Set the textviews in the holder
        CustomListAdapter.Holder holder = new CustomListAdapter.Holder();
        holder.dealerName = dealerView.findViewById(R.id.dealerName);
        holder.dealerName.setText(mDealerNameIds.get(position));

        holder.dealerAddress = dealerView.findViewById(R.id.dealerAddress);
        holder.dealerAddress.setText(mDealerAddressIds.get(position));

        return dealerView;
    }
}
