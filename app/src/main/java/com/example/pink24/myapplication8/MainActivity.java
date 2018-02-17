package com.example.pink24.myapplication8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textview);
        StringRequest stringRequest= new StringRequest(Request.Method.GET,
                "https://raw.githubusercontent.com/lsv/fifa-worldcup-2018/master/data.json",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonfile = null;
                        try {
                            jsonfile = new JSONObject(response);

                            StringBuilder builder = new StringBuilder();
                            JSONArray jsonarray = jsonfile.getJSONArray("stadiums");
                            for (int i = 0; i < jsonarray.length(); i++) {
                                JSONObject jsonObject = jsonarray.getJSONObject(i);
                                String name = jsonObject.getString("name");
                                String city = jsonObject.getString("city");
                                builder.append(name + "\n");
                                builder.append(city + "\n");
                            }
                            textView.setText(builder.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        catch (IOException e) {
//                            e.printStackTrace();
//                        }
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);





    }
}
