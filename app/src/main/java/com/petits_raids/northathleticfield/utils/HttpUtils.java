package com.petits_raids.northathleticfield.utils;

import com.petits_raids.northathleticfield.listener.HttpCallBackListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

    public static void requestData(String address, HttpCallBackListener listener) {
        new Thread(() -> {
            HttpURLConnection connection = null;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = null;
            try {
                URL url = new URL(address);
                connection = (HttpURLConnection) url.openConnection();
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String val;
                while ((val = reader.readLine()) != null) {
                    builder.append(val);
                }
                listener.onSuccess(builder.toString());
            } catch (IOException e) {
                e.printStackTrace();
                listener.onError(e);
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
