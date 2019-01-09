package li.michelle.connection_checker;

import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private String wifi = "wifi";
    private String data = "data";
    private String nocon = "nocon";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         setContentView(R.layout.activity_main);

         super.onCreate(savedInstanceState);

         final IntentFilter theFilter = new IntentFilter();

         getApplicationContext().registerReceiver(new ScreenReceiver(), theFilter);

         SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

         if(isNetworkConnected() == 1){
             setContentView(R.layout.activity_main);
             boolean data_state = sharedPreferences.getBoolean(data, true);
             setTogs();
        }

        else if(isNetworkConnected() == 2){
             setContentView(R.layout.activity_main);
             boolean wifi_state = sharedPreferences.getBoolean(wifi, false);
             setTogs();
        }


        else{
             setContentView(R.layout.activity_main);
             boolean nocon_state = sharedPreferences.getBoolean(nocon, false);
             setTogs();
        }
    }

    private void setTogs(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        boolean data_state = sharedPreferences.getBoolean(data, true);
        boolean wifi_state = sharedPreferences.getBoolean(wifi, false);
        boolean nocon_state = sharedPreferences.getBoolean(nocon, false);

        ToggleButton data_t = findViewById(R.id.data_tog);
        data_t.setChecked(data_state);
        data_t.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(data, isChecked);
                editor.commit();
            }
        });

        ToggleButton wifi_t = findViewById(R.id.wifi_tog);
        wifi_t.setChecked(wifi_state);
        wifi_t.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(wifi, isChecked);
                editor.commit();
            }
        });

        ToggleButton nocon_t = findViewById(R.id.nocon_tog);
        nocon_t.setChecked(nocon_state);
        nocon_t.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(nocon, isChecked);
                editor.commit();
            }
        });
    }

    private int isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if(activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                return 1;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                return 2;
            } else {
                return 0;
            }
        }else{
            return 0;
        }
    }
}

