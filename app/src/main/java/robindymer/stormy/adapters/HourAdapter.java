package robindymer.stormy.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import robindymer.stormy.R;
import robindymer.stormy.model.Hour;

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> {

    private Hour[] mHours;
    private Context mContext;

    public HourAdapter(Context context, Hour[] hours) {
        mContext = context;
        mHours = hours;
    }

    @Override // Called whenever a new ViewHolder is needed
    public HourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hourly_list_item, parent, false);
        HourViewHolder viewHolder = new HourViewHolder(view);
        return viewHolder;
    }

    @Override // The bridge between the adapter and the bind method in the HourViewHolder class
    public void onBindViewHolder(HourViewHolder holder, int position) {
        holder.bindHour(mHours[position]);
    }

    @Override
    public int getItemCount() {
        return mHours.length;
    }

    // VH for RecyclerView contains the views and the data mapping code instead of using a separate getView() method
    // All goes inside the class ViewHolder
    public class HourViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.timeLabel) TextView mTimeLabel;
        @BindView(R.id.summaryLabel) TextView mSummaryLabel;
        @BindView(R.id.temperatureLabel) TextView mTemperatureLabel;
        @BindView(R.id.iconImageView) ImageView mIconImageView;

        // Here we are setting up the view
        public HourViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        // The method that will map all the data to the view
        public void bindHour(Hour hour) {
            mTimeLabel.setText(hour.getHour());
            mSummaryLabel.setText(hour.getSummary());
            mTemperatureLabel.setText(hour.getTemperature() + "");
            mIconImageView.setImageResource(hour.getIconId());
        }

        @Override
        public void onClick(View v) {
            String time = mTimeLabel.getText().toString(); // Get the text from the view
            String temperature = mTemperatureLabel.getText().toString();
            String summary = mSummaryLabel.getText().toString();
            String message = String.format("At %s it will be %s°C and %s",
                    time,
                    temperature,
                    summary);
            Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
        }
    }
}
