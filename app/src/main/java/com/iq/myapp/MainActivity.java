package com.iq.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{

	static SharedPreferences sharedPreferences;
	static EditCredentialDialogueFragment editDialog;

	static ListView itemsListView;
	static ArrayList<Credential> itemsArrayList;
	static CustomListAdapter adapter;
	public static MainActivity instance;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sharedPreferences = getPreferences(Context.MODE_PRIVATE);
		itemsArrayList = new ArrayList<>();
		instance = this;

		getcredentialslist(false);
		adapter = new CustomListAdapter(this, itemsArrayList);
		itemsListView  = findViewById(R.id.list_view);
		itemsListView.setAdapter(adapter);
		itemsListView.setOnItemClickListener(this);


		editDialog = new EditCredentialDialogueFragment();
	}



	public static void getcredentialslist(boolean refresList)
	{
		itemsArrayList.clear();

		Map<String,?> pairs = sharedPreferences.getAll();

		Set keys = pairs.keySet();

		Iterator it = keys.iterator();

		while (it.hasNext())
		{
			String key = (String) it.next();
			itemsArrayList.add(new Credential(key, (String) pairs.get(key)));
		}

		if(refresList)
			adapter.notifyDataSetChanged();
	}

	public static void d(String s)
	{
		System.out.println("MyAPP >>"+ s);
	}


	ViewCredentialDialogueFragment viewDialog = new ViewCredentialDialogueFragment();
	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
	{
		Credential credential = ((Credential)adapterView.getItemAtPosition(i));
		viewDialog.setStrings(credential.getKey(),credential.getValue());
		viewDialog.show(getSupportFragmentManager(),"");
	}



	public void editCredential(AdapterView<?> adapterView, int i)
	{
		Credential credential = ((Credential)adapterView.getItemAtPosition(i));
		editDialog.setStrings(credential.getKey(),credential.getValue());
		editDialog.show(getSupportFragmentManager(),"");
	}


	public void editCredential(String key)
	{
		viewDialog.dismiss();
		String val = sharedPreferences.getString(key,null);
		editDialog.setStrings(key,val);
		editDialog.show(getSupportFragmentManager(),"");
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_layout,menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.action_add:
			{
				addNewCredential();
			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	AddCredentialDialogueFragment addCredentialDialogueFragment = new AddCredentialDialogueFragment();

	private void addNewCredential()
	{
		addCredentialDialogueFragment.show(getSupportFragmentManager(),null);
	}
}
