package com.example.musicplayer;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class musiclistActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    ListView lv;
    ArrayList al,list;

    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musiclist);
        lv = (ListView) findViewById(R.id.musiclist);
        b1 = (Button) findViewById(R.id.button);
        al = new ArrayList();
        list = new ArrayList();
        b1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
        ContentResolver cr = getContentResolver();
        Cursor c = cr.query(uri,null,selection,null,null);
        while(c.moveToNext()){
            int x = c.getColumnIndex(MediaStore.Audio.Media.DATA);
            int y = c.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME);
            String songname = c.getString(y);
            String song = c.getString(x);
            al.add(song);
            list.add(songname);
        }
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String name = (String) al.get(position);
        String name1 = (String) list.get(position);
        Toast.makeText(this, name1, Toast.LENGTH_LONG).show();
        Intent i = new Intent(this,MainActivity.class);
        i.putExtra("N",name);
        i.putExtra("N1",name1);
        startActivity(i);
    }
}
