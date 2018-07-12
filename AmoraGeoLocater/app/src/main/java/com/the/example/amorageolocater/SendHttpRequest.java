package com.the.example.amorageolocater;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by what on 8/21/2017.
 */

class SendHttpRequest extends AsyncTask<String,Void,Boolean> {

    @Override
    protected Boolean doInBackground(String... params) {

        String backgroundMessage="background done";
        HttpURLConnection httpURLConnection=null;
        OutputStream outputStream=null;
        BufferedWriter bufferedWriter=null;
        try
        {

            URL url = new URL(params[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setFixedLengthStreamingMode(params[1].getBytes().length);
            httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            httpURLConnection.connect();
            outputStream = httpURLConnection.getOutputStream();
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.write(params[1]);
            bufferedWriter.flush();
            int httpResponse=httpURLConnection.getResponseCode();
            if(httpResponse==HttpURLConnection.HTTP_OK) {
                Log.d("TAG", "The respose is HTTP_OK");
                return true;
            }
            if(httpResponse==HttpURLConnection.HTTP_ACCEPTED) {
                Log.d("TAG", "The respose is HTTP_ACCEPTED");
                return true;
            }
            if(httpResponse==HttpURLConnection.HTTP_BAD_GATEWAY) {
                Log.d("TAG", "The respose is HTTP_BAD_GATEWAY");
                return false;
            }
            if (httpResponse==HttpURLConnection.HTTP_NOT_FOUND) {
                Log.d("Tag", "The resposnse is HTTP_NOT_FOUND");
                return false;
            }
            outputStream.close();
            bufferedWriter.close();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        finally {
            if (httpURLConnection!=null)
                httpURLConnection.disconnect();
            if(outputStream!=null)
                try {
                    outputStream.close();
                    if (bufferedWriter!=null)
                        bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return false;
    }
}
