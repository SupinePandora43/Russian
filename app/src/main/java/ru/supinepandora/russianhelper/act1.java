package ru.supinepandora.russianhelper;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.LinearLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.*;
import androidx.recyclerview.widget.*;
import java.util.*;
import android.view.*;
import org.json.*;

public class act1 extends AppCompatActivity
{
LinearLayout ll1;
Toolbar tb;
NestedScrollView mNestedScrollView;
RecyclerView rv;
Easy ea;
JSONArray j;
Easy.ArrayRecyclerViewAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act1);
		tb=(Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(tb);
        mNestedScrollView=(NestedScrollView) findViewById(R.id.act1NestedScrollView1);
        mNestedScrollView.setScrollbarFadingEnabled(true);

		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		MainActivity.setToolbarMode(getWindow(),this);

		ea=new Easy(this);
		rv=ea.findViewById(R.id.act1RecyclerView1);
		List<String> sl=new ArrayList<>();
		try{
			j = new JSONObject(ea.readAssetFile("jsontestmy.json")).getJSONArray("act1");
			for(int i=0;i<j.length();i++){
				sl.add(j.getString(i));
			}
		}
		catch (JSONException e){e.printStackTrace();return;}


		adapter=new Easy.ArrayRecyclerViewAdapter(this,R.layout.actrecycler,R.id.recyclerhelpfixTextView1,sl);
		rv.setAdapter(adapter);
		rv.setLayoutManager(new LinearLayoutManager(this));
		j=null;

	}

	@Override
	protected void onDestroy()
	{
		rv=null;
		adapter=null;
		super.onDestroy();
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
