package com.example.ticketwatchlistmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                SmsMessage[] messages = new SmsMessage[pdusObj.length];
                for (int i = 0; i < messages.length; i++) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        String format = bundle.getString("format");
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i], format);
                    }
                   // String senderNum = messages[i].getOriginatingAddress();
                    String message = messages[i].getMessageBody();
                    if (formatChecker(message) == true) {
                        String ticker = reFormat(message);
                        String tickerKey = "TickerSymbol";
                        Intent activityIntentLauncher = new Intent(context, MainActivity.class);
                        activityIntentLauncher.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        activityIntentLauncher.putExtra(tickerKey, ticker);
                        context.startActivity(activityIntentLauncher);
                        Toast.makeText(context, "Ticker added: " + ticker , Toast.LENGTH_SHORT).show();
                    } else {
                        Intent activityIntentLauncher = new Intent(context, MainActivity.class);
                        activityIntentLauncher.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        Toast.makeText(context, "Invalid format, Ticker not added", Toast.LENGTH_SHORT).show();
                        context.startActivity(activityIntentLauncher);
                    }

                }
            }
        }
    }

    public Boolean formatChecker(String message) {
        String regex = "^Ticker:<<[a-zA-Z0-9]+>>$";

        return message != null && message.trim().matches(regex);
    }
    public String reFormat(String message){
        String rtn = trimTicker(message);
        rtn = rtn.toUpperCase();
        return rtn;
    }

    public String trimTicker(String message){
        return message.substring(9, message.length()-2);
    }
}