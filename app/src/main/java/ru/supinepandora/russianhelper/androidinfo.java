package ru.supinepandora.russianhelper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.*;

import android.os.Bundle;
import android.os.Build;
import java.util.ArrayList;
import android.view.*;

public class androidinfo extends AppCompatActivity
{
	RecyclerView rv;
	Toolbar tb;
	LinearLayoutManager lm=new LinearLayoutManager(this);
	ArrayList<String> al1=new ArrayList<>();
	MyRecyclerViewAdapterlin mrva;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ainf);
		tb=(Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(tb);
		rv=(RecyclerView) findViewById(R.id.ainfRecyclerView1);
		String st1;
		switch(Build.VERSION.SDK_INT){
			case 1:
				st1="BASE";
				break;
			case 2:
				st1="BASE_1_1";
				break;
			case 3:
				st1="CUPCAKE";
				break;
			case 4:
				st1="DONUT";
				break;
			case 5:
				st1="ECLAIR";
				break;
			case 6:
				st1="ECLAIR_0_1";
				break;
			case 7:
				st1="ECLAIR_MR1";
				break;
			case 8:
				st1="FROYO";
				break;
			case 9:
				st1="GINGERBREAD";
				break;
			case 10:
				st1="GINGERBREAD_MR1";
				break;
			case 11:
				st1="HONEYCOMB";
				break;
			case 12:
				st1="HONEYCOMB_MR1";
				break;
			case 13:
				st1="HONEYCOMB_MR2";
				break;
			case 14:
				st1="ICE_CREAM_SANDWICH";
				break;
			case 15:
				st1="ICE_CREAM_SANDWICH_MR1";
				break;
			case 16:
				st1="JELLY_BEAN";
				break;
			case 17:
				st1="JELLY_BEAN_MR1";
				break;
			case 18:
				st1="JELLY_BEAN_MR2";
				break;
			case 19:
				st1="KITKAT";
				break;
			case 20:
				st1="KITKAT_WATCH";
				break;
			case 21:
				st1="LOLLIPOP";
				break;
			case 22:
				st1="LOLLIPOP_MR1";
				break;
			case 23:
				st1="MARSHMALLOW";
				break;
			case 24:
			case 25:
				st1="NOUGAT";
				break;
			case 26:
			case 27:
				st1="OREO";
				break;
			case 28:
				st1="P";
				break;
			default:
			    st1="на устройстве создателя приложения нету данных об этой версий Android";
			    break;
		}
		al1.add("SDK: " + Build.VERSION.SDK + " (" + Build.VERSION.RELEASE + "   " +st1 + ")");
		al1.add("производитель: "+Build.MANUFACTURER);
		al1.add("модель: "+Build.MODEL);
		al1.add("бренд: "+Build.BRAND);
		al1.add("ABI: "+Build.CPU_ABI);
		al1.add("ABI2: "+Build.CPU_ABI2);
		al1.add("хост: "+Build.HOST);
		al1.add("ID: "+Build.ID);
		al1.add("HARDWARE: "+Build.HARDWARE);
		al1.add("BOARD: "+Build.BOARD);
		al1.add("BOOTLOADER: "+Build.BOOTLOADER);
		al1.add("DEVICE: "+Build.DEVICE);
		al1.add("DISPLAY: "+Build.DISPLAY);
		al1.add("FINGERPIINT: "+Build.FINGERPRINT);
		al1.add("PRODUCT: "+Build.PRODUCT);
		al1.add("RADIO: "+Build.RADIO);
		al1.add("SERIAL: "+Build.SERIAL);
		al1.add("TAGS: "+Build.TAGS);
		al1.add("TIME: "+Build.TIME);
		al1.add("TYPE: "+Build.TYPE);
		al1.add("USER: "+Build.USER);
		try{
		al1.add("SECURITY_PATCH: "+Build.VERSION.SECURITY_PATCH);
		}catch(Exception e){e.printStackTrace();}
		//al1.add("PREVIEW_SDK_INT: "+Build.VERSION.PREVIEW_SDK_INT);
		al1.add("CODENAME: "+Build.VERSION.CODENAME);
		al1.add("BASE_OS: "+Build.VERSION.BASE_OS);
		al1.add("INCREMENTAL: "+Build.VERSION.INCREMENTAL);
		mrva=new MyRecyclerViewAdapterlin(this,al1,true);
		rv.setLayoutManager(lm);
		rv.addItemDecoration(new DividerItemDecoration(rv.getContext(),lm.getOrientation()));
		rv.setAdapter(mrva);
		MainActivity.setToolbarMode(getWindow(),this);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		al1=null;
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
