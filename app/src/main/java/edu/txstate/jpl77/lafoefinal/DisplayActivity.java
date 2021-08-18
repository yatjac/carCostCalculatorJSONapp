package edu.txstate.jpl77.lafoefinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.TextHttpResponseHandler;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

public class DisplayActivity extends AppCompatActivity {

    int intIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        TextView carMake = findViewById(R.id.txtMake);
        TextView carModel = findViewById(R.id.txtModel);
        TextView carMsrp = findViewById(R.id.txtMsrp);
       // final EditText carDiscount = findViewById(R.id.txtInput);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(DisplayActivity.this);

        Car selectedCar = new Car(pref.getInt("KeyID", 0),
               pref.getString("KeyMake", null),
                pref.getString("KeyModel", null),
                pref.getString("KeyURL", null),
                pref.getFloat("KeyMsrp", 0));

        intIndex = pref.getInt("KeyPosition", 0);
        //DecimalFormat df = new DecimalFormat("###,###.##");

        carMake.setText("Make: " + selectedCar.getMake());
        carModel.setText("Model: " + selectedCar.getModel());
        carMsrp.setText("Msrp: " + selectedCar.getMsrp());



        Button update = findViewById(R.id.btnCalculate);

        //update.setOnClickListener(new View.OnClickListener() {
           // @Override
            //public void onClick(View v) {
                String strNewCost = carMsrp.getText().toString();
                StringEntity entity = null;

                try {
                    entity = new StringEntity(strNewCost);
                    entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/text"));
                    String url = "new_cars_jpl77/" + intIndex + "/MSRP.json";
                    CarClient.put(DisplayActivity.this, url, entity, "application/text", new TextHttpResponseHandler() {
                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            Toast.makeText(DisplayActivity.this, "Fail", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, String responseString) {
                            Toast.makeText(DisplayActivity.this, "Success", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


    }

