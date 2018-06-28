package com.morpheus.recibiendosms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by Morpheus on 28/06/2018.
 */

public class SMSReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Bundle bundle = intent.getExtras();
        if(bundle != null)
        {
            Object pdus[] = (Object[])bundle.get("pdus");
            String format = bundle.getString("format");

            SmsMessage messages[] = new SmsMessage[pdus.length];
            for(int i = 0; i < pdus.length; i++)
            {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    messages[i] = SmsMessage.createFromPdu((byte[])pdus[i], format);
                else
                    messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);

                String numero = messages[i].getDisplayOriginatingAddress();
                String mensaje = messages[0].getMessageBody();

                Toast.makeText(context, "Numero: " + numero + "\nMensaje: " + mensaje, Toast.LENGTH_LONG).show();
            }
        }
    }
}
