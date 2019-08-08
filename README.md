# RestartServiceWhenAppKill
service that runs even when the app is closed from the Task manager. I have created a service and then Logged a message to check if it's running and I noticed that it works only if if the app is running or in foreground
There are three parts in the code: (i) an Activity (the foreground app), (ii) a Service and (iii) a BroadcastReceiver which will receive a signal when someone or something kills the service; its role is to restart the service
