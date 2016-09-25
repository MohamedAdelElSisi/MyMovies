package com.example.sos.mymovies;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
       // setContentView(R.layout.activity_main);

     if (MainActivity.TABLET){
            setContentView(R.layout.activity_main);


        }
        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().add(R.id.containerDetail, new DetailFragment()).commit();

        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,Setting.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void favorite(View v)
    {
        Button b = (Button)findViewById(R.id.favorite);
        if(b.getText().equals("FAVORITE"))
        {


            b.setText("UNFAVORITE");
            b.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.MULTIPLY);

            ContentValues values = new ContentValues();
            values.put(Database.NAME, DetailFragment.poster);
            values.put(Database.OVERVIEW, DetailFragment.overview);
            values.put(Database.RATING, DetailFragment.rating);
            values.put(Database.DATE, DetailFragment.date);
            values.put(Database.REVIEW, DetailFragment.review);
            values.put(Database.YOUTUBE1, DetailFragment.youtube);
            values.put(Database.YOUTUBE2, DetailFragment.youtube2);
            values.put(Database.TITLE, DetailFragment.title);

            getContentResolver().insert(Database.CONTENT_URI, values);

        }
        else
        {
            b.setText("FAVORITE");
            b.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
            getContentResolver().delete(Database.CONTENT_URI,
                    "title=?",new String[]{DetailFragment.title});
        }
    }
    public void trailer1(View v)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com" +
                "/watch?v=" + DetailFragment.youtube));
        startActivity(browserIntent);
    }
    public void trailer2(View v)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com" +
                "/watch?v=" + DetailFragment.youtube2));
        startActivity(browserIntent);
    }
}