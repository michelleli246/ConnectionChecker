package li.michelle.connection_checker;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ToggleButton data_t = (ToggleButton) findViewById(R.id.data_tog);
        ToggleButton wifi_t = (ToggleButton) findViewById(R.id.wifi_tog);
        ToggleButton nocon_t = (ToggleButton) findViewById(R.id.nocon_tog);

        super.onCreate(savedInstanceState);

        final IntentFilter theFilter = new IntentFilter();

        getApplicationContext().registerReceiver(new ScreenReceiver(), theFilter);

        if(isNetworkConnected() == 2){
            setContentView(R.layout.activity_main);
            if(data_t != null){
                performHeadsUpData();
            }
            //Kills app but it opens it for like a second so its kind of wonky
            //System.exit(0);
        }
        else if(isNetworkConnected() == 1){
            setContentView(R.layout.wifi_on);
            if(wifi_t != null) {
                performHeadsUpWifi();
            }
        }
        else{
            setContentView(R.layout.no_connection);
            if(nocon_t != null) {
                performHeadsUpNoConnection();
            }
        }
    }

    private int isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if(activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                return 1;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                return 2;
            } else {
                return 0;
            }
        }else{
            return 0;
        }
    }

    public void performHeadsUpData(){
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
        Notification.Builder builder = new Notification.Builder(this);

        builder.setContentTitle("Your data is on!")
                .setContentText("text")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .setVibrate(new long[] {Notification.DEFAULT_VIBRATE})
                .setPriority(Notification.PRIORITY_MAX);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

    }

    public void performHeadsUpWifi(){
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
        Notification.Builder builder = new Notification.Builder(this);

        builder.setContentTitle("Your wifi is on!")
                .setContentText("text")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .setVibrate(new long[] {Notification.DEFAULT_VIBRATE})
                .setPriority(Notification.PRIORITY_MAX);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

    }

    public void performHeadsUpNoConnection(){
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
        Notification.Builder builder = new Notification.Builder(this);

        builder.setContentTitle("No connection!")
                .setContentText("text")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .setVibrate(new long[] {Notification.DEFAULT_VIBRATE})
                .setPriority(Notification.PRIORITY_MAX);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

    }
}

