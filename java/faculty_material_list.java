package com.example.administrator.namotab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;




public class faculty_material_list extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_material_list);



        //generate list
        ArrayList<String> list = new ArrayList<String>();
        list.add("mp4");
        list.add("mp3");
        list.add("mp3");
        list.add("mp3");
        list.add("mp3");
        list.add("mp3");
        list.add("mp3");





        //instantiate custom adapter
        MyAdapter_2 adapter = new MyAdapter_2(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView) findViewById(R.id.material_list);
        lView.setAdapter(adapter);

    }
}




//*******Pop menu*******
//registerForContextMenu(list);

//____________________________________________________________________________________________

//    ****************************Long Click Pop Menu**********************************
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//
//        menu.add("Click Here");
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//         super.onContextItemSelected(item);
//         if (item.getTitle()=="Click Here")
//         {
//             Toast.makeText(this,"Hello World", Toast.LENGTH_LONG).show();
//         }
//         return true;
//
//    }
//    *********************************************************************************

