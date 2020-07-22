package com.iq.myapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditCredentialDialogueFragment extends DialogFragment
{
	String key, value;


	@NonNull
	@Override
	public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		// Get the layout inflater
		LayoutInflater inflater = requireActivity().getLayoutInflater();

		//Inflate and set the layout for the dialog
		//Pass null as parent view because its going int the dialog layout

		View view = inflater.inflate(R.layout.dialog_edit, null);
		builder.setView(view);
		final TextView textViewKey = view.findViewById(R.id.key);
		textViewKey.setText(key);
		final TextView textViewValue = view.findViewById(R.id.value);
		textViewValue.setText(value);


		//set Action buttons
		builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int id)
			{
				editEntry(textViewKey.getText(), textViewValue.getText());
			}
		});

		builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int id)
					{
						// User cancelled the dialog
					}
				});


		return builder.create();
	}


	public void setStrings(String key, String value)
	{
		this.key = key;
		this.value = value;

	}

	private void editEntry(CharSequence key, CharSequence value)
	{
		SharedPreferences.Editor editor = MainActivity.sharedPreferences.edit();
		editor.remove(this.key);
		editor.putString(key.toString(), value.toString());
		editor.apply();
		MainActivity.getcredentialslist(true);
	}

}
