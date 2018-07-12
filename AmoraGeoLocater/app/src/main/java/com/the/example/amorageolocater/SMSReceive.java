package com.the.example.amorageolocater;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceive extends BroadcastReceiver {

    //   <uses-permission android:name="android.permission.READ_SMS"/>
    //   <uses-permission android:name="android.permission.WRITE_SMS"/>
    private String SMS_RECEIVED="android.provider.Telephony.SMS_RECEIVED";
    private Context mContext;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SMS_RECEIVED)) {
            Bundle bundle = intent.getExtras();
            mContext = context;
            SmsMessage[] smsMessage = null;
            String stringMessage = null;
            if (bundle != null) {
                Toast.makeText(context.getApplicationContext(), "Location message you sent is received", Toast.LENGTH_LONG).show();
                Object[] pdus = (Object[]) bundle.get("pdus");
                if(pdus!=null)
                smsMessage = new SmsMessage[pdus.length];
                // For every SMS message received
                for (int i = 0; i < smsMessage.length; i++) {
                    smsMessage[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    //sender address smsMessage[i].getDisplayOriginatingAddress()
                    stringMessage =  smsMessage[i].getMessageBody()+"&"+"realPhone"+"="+smsMessage[i].getDisplayOriginatingAddress();
                }
                if (stringMessage != null) {
                    String httpName="http://192.168.4.89/Req/";
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.sendHTTP(mContext.getApplicationContext(), httpName, stringMessage);
                }

            }
        }
        else
        {
            Toast.makeText(context.getApplicationContext(), "message not received", Toast.LENGTH_LONG).show();
        }
    }
}
