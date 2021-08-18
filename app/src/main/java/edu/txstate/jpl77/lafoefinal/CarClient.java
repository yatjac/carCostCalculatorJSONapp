package edu.txstate.jpl77.lafoefinal;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;

public class CarClient extends AppCompatActivity {

    private static final String BASE_URL = "https://cars-69b5b.firebaseio.com/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public static void get(Context context, String url, Header[] headers, RequestParams params,
                           AsyncHttpResponseHandler responseHandler) {
        client.get(context, getAbsoluteUrl(url), headers, params, responseHandler);
    }

    public static void put(Context context, String url, HttpEntity entity, String contentType,
                           ResponseHandlerInterface responseHandler) {
        client.put(context, getAbsoluteUrl(url), entity, contentType, responseHandler);


    }
}