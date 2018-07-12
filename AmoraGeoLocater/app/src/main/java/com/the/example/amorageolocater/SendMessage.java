package com.the.example.amorageolocater;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by what on 8/17/2017.
 */

public class SendMessage {


    private SendMessage sendMessage;
    private Context mContext;
   SendMessage()
   {
       //constructor
   }



    void sendSMS(Context context, final String smsTo,final String message) {
      mContext=context;
       String SMS_SENT = "SMS SENT";

       PendingIntent sentPendingIntent = PendingIntent.getBroadcast(mContext, 0, new Intent(SMS_SENT), 0);
       String SMS_DELIVERED = "SMS DELIVERED";
       PendingIntent deliveredPendingIntent = PendingIntent.getBroadcast(mContext, 0, new Intent(SMS_DELIVERED), 0);
       //for sms SENT
       mContext.registerReceiver(new BroadcastReceiver() {
           @Override
           public void onReceive(Context context, Intent intent) {
               switch (getResultCode()) {
                   case Activity.RESULT_OK:
                       Toast.makeText(context, "SMS sent successfully", Toast.LENGTH_SHORT).show();
                       break;
                   case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                       Toast.makeText(context, "Generic failure cause", Toast.LENGTH_SHORT).show();
                       sendMessage.sendHTTP(mContext,smsTo, message);
                       break;
                   case SmsManager.RESULT_ERROR_NO_SERVICE:
                       Toast.makeText(context, "Service is currently unavailable", Toast.LENGTH_SHORT).show();
                       sendMessage.sendHTTP(mContext,smsTo, message);
                       break;
                   case SmsManager.RESULT_ERROR_NULL_PDU:
                       Toast.makeText(context, "No pdu provided", Toast.LENGTH_SHORT).show();
                       sendMessage.sendHTTP(mContext,smsTo, message);
                       break;
                   case SmsManager.RESULT_ERROR_RADIO_OFF:
                       Toast.makeText(context, "Radio was explicitly turned off", Toast.LENGTH_SHORT).show();
                       sendMessage.sendHTTP(mContext,smsTo, message);
                       break;
               }
           }
       }, new IntentFilter(SMS_SENT));
//for sms Receive
        mContext.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(mContext.getApplicationContext(), "SMS delivered", Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(mContext.getApplicationContext(), "SMS not delivered", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        },new IntentFilter(SMS_DELIVERED));

       SmsManager smsManager = SmsManager.getDefault();



       smsManager.sendTextMessage(smsTo, null, message, sentPendingIntent, deliveredPendingIntent);
        //  short portNumber=70;
        //sending sms using SendDataMessage.
      // smsManager.sendDataMessage(smsTo,null,portNumber,message.getBytes(),sentPendingIntent,deliveredPendingIntent);
   }


     void sendHTTP(final Context context, final String httpRequestTo, String message)  {
        mContext=context;
         SendHttpRequest sendHttpClass=new SendHttpRequest();
          sendHttpClass.execute(httpRequestTo,message);

    }



}
