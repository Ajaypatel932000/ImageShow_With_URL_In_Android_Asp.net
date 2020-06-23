package com.example.imageshow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    ImageView imageView;
    // this is url of api of asp.net
    String URL="http://10.0.2.2:5467/PROJECT2020/aayesha.asmx/getImage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.img_id);
        // volley request Initialization
        requestQueue= Volley.newRequestQueue(getApplicationContext());
//   send request with 3 arguments post method ,url ,and response and error
      StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
          try{      // response add into object
                       JSONObject object=new JSONObject(response);
                           // pass json key and fetch the value

                     String url= object.getString("path_key");
                     Toast.makeText(MainActivity.this,"URL ="+url,Toast.LENGTH_LONG).show();
                            // object of class is created and send url of image
                   new DownloadImageTask(imageView).execute("http://10.0.2.2:5467/PROJECT2020/App_Themes/Theme1/assets/images/anis.png");

             }catch (Exception e)
             {
                 Toast.makeText(MainActivity.this,"URL = "+e.getMessage(),Toast.LENGTH_LONG).show();
            }

          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
              Toast.makeText(MainActivity.this,"URL = "+error.getMessage(),Toast.LENGTH_LONG).show();
          }
      }){

      };
      requestQueue.add(stringRequest);
}
}

