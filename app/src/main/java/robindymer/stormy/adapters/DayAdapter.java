package robindymer.stormy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import robindymer.stormy.R;
import robindymer.stormy.model.Day;

public class DayAdapter extends BaseAdapter {

    private Context mContext; // Where we should use it, example this
    private Day[] mDays; // We also want an array of day object

    // Our adapter that we will use. The constructor is called once the object is initialized
    public DayAdapter(Context context, Day[] days) {
        mContext = context;
        mDays = days;
    }

    @Override
    public int getCount() {
        return mDays.length; // Return the number of items in the array that the adapter is using
    }

    @Override
    public Object getItem(int position) {
        return mDays[position]; // Get an item from the array that the adapter is using
    }

    @Override
    public long getItemId(int position) {
        return 0; // Tag items for easy reference. We are not going to use this
    }

    // Where the mapping occurs
    @Override // Called before the views are displayed on the screen and each time we scroll a new item onto the list
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // convertView is null the first time getView is called, then we need to create it. Else we can change the view
        // convertView is used to reuse old view
        if (convertView == null) {
            // brand new
            // LayoutInflater - object that takes xml layouts and turns them into views in code that we can use
            convertView = LayoutInflater.from(mContext).inflate(R.layout.daily_list_item, null);
            holder = new ViewHolder();
            // Set the variables by accessing the views from the xml file
            holder.iconImageView = (ImageView) convertView.findViewById(R.id.iconImageView);
            holder.temperatureLabel = (TextView) convertView.findViewById(R.id.temperatureLabel);
            holder.dayLabel = (TextView) convertView.findViewById(R.id.dayLabel);

            convertView.setTag(holder); // Set a tag for the view
        }
        else {
            holder = (ViewHolder) convertView.getTag(); // Get the ViewHolder with the views
        }

        Day day = mDays[position]; // Use the position parameter to get the Day array of that position

        // Set the data using holder class
        holder.iconImageView.setImageResource(day.getIconId()); // Use helper method
        holder.temperatureLabel.setText(day.getTemperatureMax() + "");

        if (position == 0) {
            holder.dayLabel.setText("Today");
        }
        else {
            holder.dayLabel.setText(day.getDayOfTheWeek()); // Use helper method
        }

        return convertView; // return the view we created
    }

    private static class ViewHolder {
        ImageView iconImageView; // public by default
        TextView temperatureLabel;
        TextView dayLabel;
    }
}
