package ru.supinepandora.russianhelper;
import android.content.*;
import android.os.*;
import android.preference.*;
import androidx.appcompat.app.*;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.view.*;
import android.widget.*;

import java.util.*;


public class gdzActivity extends AppCompatActivity
{
CoordinatorLayout cl;
Toolbar tb;
Easy ea;
SharedPreferences sh;
String[] gdzss={"Математика","Русский язык"};
int gdzi=0;
EditText numg;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gdz);
		tb=(Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(tb);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		MainActivity.setToolbarMode(this);
		ea=new Easy(this);
		sh=PreferenceManager.getDefaultSharedPreferences(this);
		AppCompatSpinner spin=ea.findViewById(R.id.gdzAppCompatSpinner1);
		List<String> gdzs=ea.toListArray(gdzss);
		ArrayAdapter adap=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,gdzs);
		adap.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		spin.setAdapter(adap);
		//spin.setSelection(0);
		spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4)
				{
					gdzi=p3;
				}

				@Override
				public void onNothingSelected(AdapterView<?> p1)
				{
					// TODO: Implement this method
				}
			});
		numg=ea.findViewById(R.id.gdzEditText1);
	}

	public void gdzru(View v){
		sh=PreferenceManager.getDefaultSharedPreferences(this);
		EditText et=(EditText) findViewById(R.id.gdzEditText1);
		Easy.customTab eat=new Easy.customTab(this);
		String ur="";
		/*eat.setFullUrl(sh.getString("gdzrus","http://lololol")+et.getText());
		if(sh.getBoolean("set",false)){
			switch(sh.getString("mList","n")){
				case "browser":
					//eat.setPackageIntent(Easy.customTab.Packages.PACKAGE_VK);
					break;
				case "chrome":
					eat.setPackageIntent(Easy.customTab.Packages.PACKAGE_CHROME);
					break;
			}}
		eat.startCustomTab();*/
		switch(gdzi){
			case 0://matesha
				ur=sh.getString("gdzmatem","");
				ur=String.format(ur,numg.getText());
			break;
			case 1://russ
				ur=sh.getString("gdzrus","");
				ur+=numg.getText();
			break;
		}
		if(sh.getBoolean("set",false)&&Easy.equalsString(sh.getString("mList","browser"),"chrome")){
			eat.setPackageIntent(Easy.customTab.Packages.PACKAGE_CHROME);
		}
		eat.setFullUrl(ur);
		eat.setEnterAnimation(R.anim.customtabenter,R.anim.customtabexit);
		eat.setExitAnimation(R.anim.activitymanimenter1,R.anim.activitymanimexit1);
		eat.setToolbarColor();
		eat.startCustomTab();
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
