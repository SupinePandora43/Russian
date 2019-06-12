package ru.supinepandora.russianhelper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import android.widget.EditText;
import java.io.*;
import org.json.*;
import android.content.*;
import android.os.*;
import android.net.*;

import com.google.android.material.textfield.TextInputLayout;

//
public class password extends AppCompatActivity
{
CoordinatorLayout cl;
EditText ed1;
TextInputLayout mput;
Toolbar tb;
RecyclerView rv;
Easy ea;/*
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{super.onCreate(savedInstanceState);
	setContentView(R.layout.pass);
	ea=new Easy(this);
	cl=(CoordinatorLayout) findViewById(R.id.cl);
	ed1=(EditText) findViewById(R.id.passAppCompatEditText1);
	tb=(Toolbar) findViewById(R.id.toolbar);
	rv=ea.findViewById(R.id.passRecyclerView1);
	mput=(TextInputLayout)findViewById(R.id.passTextInputLayout1);
	mput.setPasswordVisibilityToggleEnabled(true);
	mput.setPasswordVisibilityToggleContentDescription("mDescrip");
	setSupportActionBar(tb);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	MainActivity.setToolbarMode(getWindow(),this);
	}
	@RequiresApi(5)
	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		overridePendingTransition(R.anim.activitymanimenter1,R.anim.activitymanimexit1);
	}
	@RequiresApi(5)
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
	String assetJsonFind(String fileName)throws IOException
	{
		InputStream file=getAssets().open(fileName);
		byte[] formArray=new byte[file.available()];
		file.read(formArray);
		file.close();
		return new String(formArray);
	}
	public void testpass(View v) throws JSONException, IOException{
		TextView textView=ea.findViewById(R.id.passTextView1);
		JSONObject objec=new JSONObject(assetJsonFind("jsontestmy.json"));
		//String tex=objec.getInt("v1")+objec.getString("v2");
		if(ea.equalsString(objec.getString("password"),ed1.getText().toString())){

			//textView.setText("!congrulations!");
			rv.setLayoutManager(new LinearLayoutManager(this));
			//rv.setAdapter(new adapt(password.this));
			rv.addItemDecoration(new DividerItemDecoration(this,new LinearLayoutManager(this).getOrientation()));
			//rv.getAdapter().notifyDataSetChanged();
		}
	}
class adapt extends RecyclerView.Adapter<password.adapt.mh>
{
JSONObject file;
JSONArray passwords;
Context mContext;

public adapt(Context c){
	try
	{
		file = new JSONObject(ea.readAssetFile("jsontestmy.json"));
		passwords=file.getJSONArray("passwords");
		mContext=c;
	}
	catch (JSONException ioe){}
}
		@Override
		public mh onCreateViewHolder(ViewGroup p1, int p2)
		{
			View v=LayoutInflater.from(mContext).inflate(R.layout.passrecycler,p1,false);
			return new mh(v);
		}

		@Override
		public void onBindViewHolder(mh p1, int p2)
		{try{
			mh holder=p1;
			String name=passwords.getJSONObject(p2).getString("name");
			String login=passwords.getJSONObject(p2).getString("login");
			String password=passwords.getJSONObject(p2).getString("pass");

			holder.Textname.setText(name);
			holder.Textlogin.setText(login);
			holder.Textpass.setText(password);
		}catch(JSONException e){}
		}

		@Override
		public int getItemCount()
		{
			return passwords.length();
		}

		class mh extends RecyclerView.ViewHolder{
			public TextView Textname;
			public TextView Textlogin;
			public TextView Textpass;
			public mh(View itemView){
				super(itemView);
				Textname=(TextView) itemView.findViewById(R.id.passrecyclerTextView1);
				Textlogin=(TextView) itemView.findViewById(R.id.passrecyclerTextView2);
				Textpass=(TextView) itemView.findViewById(R.id.passrecyclerTextView3);
			}
		}
	}*/
}
