package ru.supinepandora.russianhelper;
import androidx.appcompat.app.*;
import androidx.recyclerview.widget.*;

import android.os.*;
import java.util.*;
import android.view.*;

public class lineyka extends AppCompatActivity
{
RecyclerView rv;
ArrayList<String> al=new ArrayList<>();
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lineyka);

		rv=(RecyclerView) findViewById(R.id.lineykaRecyclerView1);
		for(int b1=1;b1<10;b1++){
			al.add(""+b1);
		}
		MyRecyclerViewAdapterlin ad=new MyRecyclerViewAdapterlin(this,al);
		LinearLayoutManager lm=new LinearLayoutManager(this);
		rv.setLayoutManager(lm);
		rv.addItemDecoration(new DividerItemDecoration(rv.getContext(),lm.getOrientation()));
		rv.setAdapter(ad);

		MainActivity.setToolbarMode(getWindow(),this);
		//getSupportActionBar().setHomeButtonEnabled(true);
		//getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
