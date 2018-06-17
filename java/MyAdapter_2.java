package com.example.administrator.namotab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MyAdapter_2 extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<>();
    private Context context;


    public MyAdapter_2(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int getItemId) {
        return this.getItemId(getItemId);
        //return getItemId;

    }
        @Override
        public View getView ( final int position, View convertView, ViewGroup parent){
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.list_item_2, null);
            }

            //Handle TextView and display string from your list
            TextView listItemText = (TextView) view.findViewById(R.id.list_item_string);
            listItemText.setText(list.get(position));

            //Handle buttons and add onClickListeners
            Button allow = (Button) view.findViewById(R.id.allow);
            Button deny = (Button) view.findViewById(R.id.deny);

            allow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //list.remove(position); //or some other task
                    Toast.makeText(context, "Allow Click", Toast.LENGTH_LONG).show();

                }
            });
            deny.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //list.remove(position); //or some other task
                    Toast.makeText(context, "Deny Click", Toast.LENGTH_LONG).show();


                }
            });

            return view;
        }
    }
