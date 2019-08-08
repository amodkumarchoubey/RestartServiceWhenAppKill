package servicesrestart.com.restartservice;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    Intent mServiceIntent;
    private OwnService ownService;

    Context context;

    public Context getCtx() {
        return context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
        ownService = new OwnService(getCtx());
        mServiceIntent = new Intent(getCtx(), ownService.getClass());
        if (!isServiceRunning(ownService.getClass())) {
            startService(mServiceIntent);
        }
    }

    private boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.e("isServiceRunning?", true + "");
                return true;
            }
        }
        Log.e("isServiceRunning?", false + "");
        return false;
    }


    @Override
    protected void onDestroy() {
        stopService(mServiceIntent);
        Log.e("MainActivity", "onDestroy!");
        super.onDestroy();

    }
}

