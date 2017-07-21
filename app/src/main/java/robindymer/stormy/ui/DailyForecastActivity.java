package robindymer.stormy.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import robindymer.stormy.R;
import robindymer.stormy.adapters.DayAdapter;
import robindymer.stormy.model.Day;

public class DailyForecastActivity extends AppCompatActivity {

    private Day[] mDays;
    private Toast mToastMessage;

    @BindView(android.R.id.list) ListView mListView;
    @BindView(android.R.id.empty) TextView mEmptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);
        ButterKnife.bind(this);

        // Get intent that started this activity
        Intent intent = getIntent();
        // Parcelable is a java interface
        // Used in android to make data easy to transfer to one thing from another

        // Save the data in a Parcelable array and not in the mDays array
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.DAILY_FORECAST);
        // Convert parcelable into an array by passing in three parameters
        // The parcelable array, the length of the array and what we want to convert into
        mDays = Arrays.copyOf(parcelables, parcelables.length, Day[].class);

        DayAdapter adapter = new DayAdapter(this, mDays);
        // Instead of setListAdapter() because we don't extend ListViewActivity
        mListView.setAdapter(adapter);
        mListView.setEmptyView(mEmptyTextView);
        // The equivalent of onClickListener but for list items
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String dayOfTheWeek = mDays[position].getDayOfTheWeek();
                String conditions = mDays[position].getSummary();
                String highTemp = mDays[position].getTemperatureMax() + "";
                String message = String.format("On %s the high will be %s and it will be %s",
                        dayOfTheWeek,
                        highTemp,
                        conditions);

                if (mToastMessage != null) {
                    mToastMessage.cancel();
                }

                mToastMessage = Toast.makeText(DailyForecastActivity.this, message, Toast.LENGTH_LONG);
                mToastMessage.show();
            }
        });
    }
}
