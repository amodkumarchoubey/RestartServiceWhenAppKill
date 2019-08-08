package servicesrestart.com.restartservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class OwnService extends Service {
    public int counter_ = 0;

    public OwnService(Context applicationContext) {
        super();
        Log.e("OwnService", "OwnService");
    }

    public OwnService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        startTimer();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Inside", "onDestroy");
        Intent broadcastIntent = new Intent(this, LocalBroadcastReceiver.class);
        sendBroadcast(broadcastIntent);
        stoptimer();
    }

    Timer timer;
    TimerTask timerTask;
    long oldTime = 0;

    public void startTimer() {
        //set a new Timer
        timer = new Timer();


        initializeTimer();


        timer.schedule(timerTask, 1000, 1000); //
    }


    public void initializeTimer() {
        timerTask = new TimerTask() {
            public void run() {
                Log.e("Timer is", "====>" + (counter_++));
            }
        };
    }

    public void stoptimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}