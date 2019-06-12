package ru.supinepandora.russianhelper;
import androidx.appcompat.app.*;
import androidx.appcompat.widget.*;
import android.os.Bundle;
import android.view.MenuItem;

public class MatemActivity extends AppCompatActivity
{
Toolbar tb;
Easy ea;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mat);
		ea=new Easy(this);
		tb=ea.findViewById(R.id.toolbar);
		setSupportActionBar(tb);
		MainActivity.setHome(this);
		MainActivity.setToolbarMode(this);
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
