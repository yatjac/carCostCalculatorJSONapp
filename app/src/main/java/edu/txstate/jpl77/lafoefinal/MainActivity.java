package edu.txstate.jpl77.lafoefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class MainActivity extends ListActivity {

    List<Car> list = new ArrayList<Car>();

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Car selectedCar = list.get(position);


        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = pref.edit();

        editor.putInt("KeyID", selectedCar.getId());
        editor.putString("KeyMake", selectedCar.getMake());
        editor.putString("KeyModel", selectedCar.getModel());
        editor.putString("KeyUrl", selectedCar.getUrl());
        editor.putFloat("KeyMsrp", (float) selectedCar.getMsrp());
        editor.putInt("KeyPosition", position);

        editor.commit();

        list.set(position,selectedCar);

        startActivity(new Intent(MainActivity.this, DisplayActivity.class));


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/json"));
        CarClient.get(MainActivity.this, "new_cars_jpl77.json", headers.toArray(new Header[headers.size()]),
                null, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        //super.onSuccess(statusCode, headers, response);

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                Car bean = new Car(response.getJSONObject(i));
                                list.add(bean);
                            } catch (JSONException ex) {
                                ex.printStackTrace();
                            }
                        }
                        setListAdapter(new ArrayAdapter<Car>(MainActivity.this, R.layout.activity_main, R.id.txtLayout, list));
                    }
                });
    }
}