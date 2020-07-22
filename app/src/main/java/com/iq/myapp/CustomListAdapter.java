package com.iq.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter
{

	private Context context; //context
	private ArrayList<Credential> credentials; //data source of the list adapter

	//public constructor
	public CustomListAdapter(Context context, ArrayList<Credential> items)
	{
		this.context = context;
		this.credentials = items;
	}

	@Override
	public int getCount()
	{
		return credentials.size();
	}

	@Override
	public Object getItem(int i)
	{
		return credentials.get(i);
	}

	@Override
	public long getItemId(int i)
	{
		return i;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup)
	{
		// inflate the layout for each list row
		if (view == null)
		{
			view = LayoutInflater.from(context).inflate(R.layout.activity_listview, viewGroup, false);
		}

		// get current item to be displayed
		Credential currentItem = (Credential) getItem(position);

		// get the TextView for item name and item description
		TextView textViewItemName = view.findViewById(R.id.text_view_key);
//		TextView textViewItemDescription = view.findViewById(R.id.text_view_value);

		//sets the text for item name and item description from the current item object
		textViewItemName.setText(currentItem.getKey());
//		textViewItemDescription.setText(currentItem.getValue());

		// returns the view for the current row
		return view;
	}

}
