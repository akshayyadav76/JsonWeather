package com.example.akshay.josn;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownLoadTask downLoadTask=new DownLoadTask();
        //https://samples.openweathermap.org/data/2.5/forecast?id=524901&appid=b1b15e88fa797225412429c1c50c122a1
        downLoadTask.execute("https://samples.openweathermap.org/data/2.5/forecast?id=524901&appid=b1b15e88fa797225412429c1c50c122a1");
    }



    public class DownLoadTask extends AsyncTask<String ,Void , String>
    {

        @Override
        protected String doInBackground(String... urls) {
            String string="";
            URL url;
            HttpURLConnection httpURLConnection;
            try {
                url =new URL(urls[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream in=httpURLConnection.getInputStream();
                InputStreamReader reader= new InputStreamReader(in);

                int data = reader.read();
                while (data != -1)
                {
                    char current =(char) data;
                    string += current;
                    data =reader.read();
                }
                return  string;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null; }

        @Override
        protected void onPostExecute(String string) {
            super.onPostExecute(string);
            try {
                JSONObject jsonObject =new JSONObject(string);
               String jeson= jsonObject.getString("weather");
                Log.i("see",jeson);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
