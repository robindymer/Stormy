package robindymer.stormy.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import robindymer.stormy.R;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.DataViewHolder> {

    private String[] mData;
    private Context mContext;

    // data is passed into the constructor
    public GridAdapter(Context context, String[] data) {
        mContext = context;
        mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()) // Access the xml from the parent parameter
                .inflate(R.layout.hourly_grid, parent, false);
        DataViewHolder viewHolder = new DataViewHolder(view);
        return viewHolder;
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        holder.bindData(mData[position]);
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    public class DataViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.infoText) TextView mInfoText;

        public DataViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this); // Set a OnClickListener on each view
        }

        public void bindData(String data) {
            mInfoText.setText(data);
        }

        @Override
        public void onClick(View v) {
            String message = mInfoText.getText().toString();
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        }
    }
}
