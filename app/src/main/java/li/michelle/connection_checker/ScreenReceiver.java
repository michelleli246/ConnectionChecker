package li.michelle.connection_checker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //System.out.println(intent.getAction());
        if (intent.getAction().equals(Intent.ACTION_USER_PRESENT))
        {
            Intent intent1 = new Intent(context, notifs.class);
            //intent1.addFlags(Intent.FLAG_SERVICE_NEW_TASK);
            context.startService(intent1);
        }
    }
}
