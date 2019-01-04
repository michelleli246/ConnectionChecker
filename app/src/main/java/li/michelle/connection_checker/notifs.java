package li.michelle.connection_checker;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

public class notifs extends IntentService {
    private String wifi = "wifi";
    private String data = "data";
    private String nocon = "nocon";

    /**
     * A constructor is required, and must call the super <code><a href="/reference/android/app/IntentService.html#IntentService(java.lang.String)">IntentService(String)</a></code>
     * constructor with a name for the worker thread.
     */
    public notifs() {
        super("notifs");
    }

    @Override
    protected void onHandleIntent(Intent intent){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if(isNetworkConnected() == 2){
            boolean data_state = sharedPreferences.getBoolean(data, false);

            if(data_state){
                performHeadsUpData();
            }
        }

        else if(isNetworkConnected() == 1){
            boolean wifi_state = sharedPreferences.getBoolean(wifi, false);

            if(wifi_state){
                performHeadsUpWifi();
            }
        }


        else{
            boolean nocon_state = sharedPreferences.getBoolean(nocon, false);

            if(nocon_state){
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
        Intent intent = new Intent(notifs.this, notifs.class);
        PendingIntent pi = PendingIntent.getActivity(notifs.this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(this);

        builder.setContentTitle("Data network connected!")
                .setContentText("Touch to adjust notification settings")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .setVibrate(new long[] {Notification.DEFAULT_VIBRATE})
                .setPriority(Notification.PRIORITY_MAX);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }

    public void performHeadsUpWifi(){
        Intent intent = new Intent(notifs.this, notifs.class);
        PendingIntent pi = PendingIntent.getActivity(notifs.this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(this);

        builder.setContentTitle("Wifi network connected!")
                .setContentText("Touch to adjust notification settings")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .setVibrate(new long[] {Notification.DEFAULT_VIBRATE})
                .setPriority(Notification.PRIORITY_MAX);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }

    public void performHeadsUpNoConnection(){
        Intent intent = new Intent(notifs.this, notifs.class);
        PendingIntent pi = PendingIntent.getActivity(notifs.this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(this);

        builder.setContentTitle("No network connection!")
                .setContentText("Touch to adjust notification settings")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .setVibrate(new long[] {Notification.DEFAULT_VIBRATE})
                .setPriority(Notification.PRIORITY_MAX);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
}
