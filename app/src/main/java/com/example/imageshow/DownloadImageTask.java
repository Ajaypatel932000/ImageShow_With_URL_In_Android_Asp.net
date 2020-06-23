package com.example.imageshow;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<String,Void, Bitmap> {

 ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String urldisplay=strings[0];
        Bitmap mIcon=null;
        try{
            InputStream in=new java.net.URL(urldisplay).openStream();
            mIcon= BitmapFactory.decodeStream(in);
        }catch (Exception e)
        {
            Log.e("Error =",e.getMessage());
            e.printStackTrace();

        }
        return mIcon;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        //super.onPostExecute(bitmap);
        bmImage.setImageBitmap(result);
    }
}
