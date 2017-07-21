/**package robindymer.stormy;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private Current mCurrentWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Use variables so we can handle it more dynamically
        String apiKey = "bca5d88e53a97d189aef76bd36b31410";
        double latitude = 37.8267;
        double longitude = -122.4233;
        String forecastUrl = "https://api.darksky.net/forecast/" + apiKey +
                "/" + latitude + "," + longitude;

        Log.d(TAG, forecastUrl);

        // Before we request any data, check if network is available
        if (isNetworkAvailable()) {
            // Main client object
            OkHttpClient client = new OkHttpClient();
            // Build a request with the url
            // Chain methods together
            Request request = new Request.Builder()
                    .url("https://requestb.in/15u0jpc1")
                    .build();

            // Put the request inside a Call object
            Call call = client.newCall(request);
            // Using asynchronous processing. Doing things on different threads
            // Execute the code but doesn't do it right away, it puts the code in a queue and will execute it in the background
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        // Store JSON data in variable
                        String jsonData = response.body().string();
                        // Get a string representation of the response body
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            mCurrentWeather = getCurrentDetails(jsonData);
                        } else {
                            // Call method if the response is not successful
                            alertUserAboutError();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    } catch (JSONException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }
                }
            });
        }
        else {
            // Change to dialog
            Toast.makeText(this, R.string.network_unavailable_message,
                    Toast.LENGTH_LONG).show();
        }

        Log.d(TAG, "Main UI code is running!");
    }

    // throws - Move the responsibility of handling the exception so you don't have to use a try/catch block in this method
    // the responsibility moves to the place that calls this method
    private Current getCurrentDetails(String jsonData) throws JSONException {
        // JSONObject class can hold any object represented in the JSON format
        // Properties can be accessed using a few different methods
        // JSONObject has a constructor that lets us pass in a string of JSON data to create a new JSON object
        JSONObject forecast = new JSONObject(jsonData);
        // Access the timezone value. "timezone" - key
        String timezone = forecast.getString("timezone");
        Log.i(TAG, "From JSON: " + timezone);

        return new Current();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }

        return isAvailable;
    }

    private void alertUserAboutError() {
        // Use a dialog so the user can get information about error
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error_dialog");
    }
}
*/