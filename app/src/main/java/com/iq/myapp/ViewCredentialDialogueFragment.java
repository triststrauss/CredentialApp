package com.iq.myapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ViewCredentialDialogueFragment extends DialogFragment
{

	private String key;
	private String value;

	@NonNull
	@Override
	public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		// Get the layout inflater
		LayoutInflater inflater = requireActivity().getLayoutInflater();

		//Inflate and set the layout for the dialog
		//Pass null as parent view because its going int the dialog layout

		View view = inflater.inflate(R.layout.dialog_view,null);
		builder.setView(view);
		final TextView textViewKey = view.findViewById(R.id.dialog_view_key);
		textViewKey.setText(key);
		final TextView textViewValue = view.findViewById(R.id.dialog_view_value);
		textViewValue.setText(value);

		Button editButton = view.findViewById(R.id.dialog_view_button);
		editButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				onEditClick(view);
			}
		});

		return builder.create();
	}


	public void onEditClick(View view)
	{
		MainActivity.instance.editCredential(key);
	}

	private void addNewCredential(CharSequence key, CharSequence value)
	{
		SharedPreferences.Editor editor = MainActivity.sharedPreferences.edit();
		editor.putString(key.toString(),value.toString());
		editor.apply();
		MainActivity.getcredentialslist(true);
	}

	public void setStrings(String key, String value)
	{
		this.key = key;
		this.value = value;
	}

}
