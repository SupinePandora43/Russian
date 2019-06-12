package ru.supinepandora.russianhelper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.app.*;
import android.view.*;
import android.graphics.drawable.*;
import android.content.*;
import android.preference.*;

public class SettingsActivity extends AppCompatActivity
{
Easy ea;
Toolbar tb;
SettingsActivityFragment saf;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		ea=new Easy(this);
		setContentView(R.layout.setspreflay);
		tb=(Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(tb);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		//FragmentTransaction ft=getFragmentManager().beginTransaction();
		//ft.add(R.id.setspreflayLinearLayout1,new SettingsActivityFragment(),"SettingsActivity");
		//ft.commit();
		saf=new SettingsActivityFragment();
		getSupportFragmentManager().beginTransaction().add(R.id.setspreflayLinearLayout1,saf,"SettingsActivity").commit();

		MainActivity.setToolbarMode(getWindow(),this);

		//SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(this);

	}
	public void reload(){
		getSupportFragmentManager().beginTransaction().remove(saf).commit();
		saf=new SettingsActivityFragment();
		getSupportFragmentManager().beginTransaction().add(R.id.setspreflayLinearLayout1,saf,"SettingsActivity").commit();
	}
	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		overridePendingTransition(R.anim.activitymanimenter1,R.anim.activitymanimexit1);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId()){
			case android.R.id.home:
				finish();
				overridePendingTransition(R.anim.activitymanimenter1,R.anim.activitymanimexit1);
			break;
		}
		return super.onOptionsItemSelected(item);
	}


}
