package ru.supinepandora.russianhelper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.*;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.view.*;
import android.content.Context;
import java.util.List;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.graphics.*;
import android.net.*;
import android.util.*;
import android.content.*;
import android.content.res.*;
import android.widget.Button;

public class alfavit extends AppCompatActivity
{
RecyclerView rv;
//Easy ea;
Toolbar tb;
//CoordinatorLayout cl;

MyRecyclerViewAdapter1 mAdapter;
//Context c=alfavit.this;

//NestedScrollView mNestedScrollView;
//SwipeRefreshLayout mSwipeRefreshLayout;

String[] rubalf =new String[]{"а","б","в","г","д","е","ё","ж","з","и","й","к","л","м","н","о","п","р","с","т","у","ф","х","ц","ч","ш","щ","ъ","ы","ь","э","ю","я"};
String[] engbalf=new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

boolean lang=false;
RecyclerView.LayoutManager mLayoutManager;

//RecyclerViewLanguageAdapter mLangChangeAdapter;

	@Override protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alfavit);
		tb=(Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(tb);
		//cl=(CoordinatorLayout)findViewById(R.id.cl);
		//ea=new Easy(this);
		//ea.setCoordinatorLayout(cl);
		//ea.findActivity(this);

		//mSwipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.alfavitSwipeRefreshLayout1);
		//mNestedScrollView=(NestedScrollView) findViewById(R.id.alfavitNestedScrollView1);

		rv=(RecyclerView)findViewById(R.id.alfavitRecyclerView1);
		//mSwipeRefreshLayout.setEnabled(false);
		if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT){
            mLayoutManager=new GridLayoutManager(this,5);
            rv.setLayoutManager(mLayoutManager);
        }
        if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
            mLayoutManager=new GridLayoutManager(this,9);
            rv.setLayoutManager(mLayoutManager);
        }

			//rv.setLayoutManager(mLayoutManager);
			if(savedInstanceState!=null){
			if(savedInstanceState.getBoolean("lang"))
				engset(null);
			else ruset(null);
			}else ruset(null);
			/*,List<String> li=new ArrayList<>();
			for(int i=0;i<rubalf.length;i++){
					li.add(rubalf[i]);
			}
			mAdapter=null;
			mAdapter=new MyRecyclerViewAdapter1(alfavit.this,li);


			rv.setAdapter(mAdapter);
			lang=false;*/

        getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		MainActivity.setToolbarMode(getWindow(),this);
	}

	public void ruset(View v){
		List<String> li=new ArrayList<>();
			for(int i=0;i<rubalf.length;i++){
					li.add(rubalf[i]);
			}
			mAdapter=null;
			mAdapter=new MyRecyclerViewAdapter1(alfavit.this,li);


			rv.setAdapter(mAdapter);
			lang=false;
	}
	public void engset(View v){
		List<String> li=new ArrayList<>();
		for(int i=0;i<engbalf.length;i++){
			li.add(engbalf[i]);
		}
		mAdapter=null;
		mAdapter=new MyRecyclerViewAdapter1(alfavit.this,li);


		rv.setAdapter(mAdapter);
		lang=true;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		outState.putBoolean("lang",lang);
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
	class MyRecyclerViewAdapter1 extends RecyclerView.Adapter<MyRecyclerViewAdapter1.ViewHolder>
	{
		Context mContext;
		LayoutInflater mInflater;

		String[] str1;

		List<String> alf=new ArrayList<>();

		public MyRecyclerViewAdapter1(Context c,List<String> l){
			mContext=c;
			mInflater=LayoutInflater.from(mContext);
			alf=l;
		}
		@Override
		public MyRecyclerViewAdapter1.ViewHolder onCreateViewHolder(ViewGroup p1, int p2)
		{
			View v=mInflater.inflate(R.layout.alfavitrec,p1,false);
			return new ViewHolder(v);
		}
		@Override
		public void onBindViewHolder(MyRecyclerViewAdapter1.ViewHolder p1, int p2)
		{
				p1.tv1.setText(alf.get(p2));
				if(alf.size()==26){
						switch(p2+1){// заметка:отчет чисел начинается с нуля
								case 1:
								case 5:
								case 9:
								case 15:
								case 21:
								case 25:
										p1.tv1.setTextColor(Color.RED);
										break;
						}
				} else if(alf.size()==33){
						switch(p2+1){
								case 1:
								case 6:
								case 7:
								case 10:
								case 16:
								case 21:
								case 29:
								case 31:
								case 32:
								case 33:
										p1.tv1.setTextColor(Color.RED);
										break;
						}
				}
		}
		@Override
		public int getItemCount()
		{
			return alf.size();
		}
		class ViewHolder extends RecyclerView.ViewHolder
		{
			public TextView tv1;
			public ViewHolder(View view1){
				super(view1);
				tv1=(TextView) view1.findViewById(R.id.alfavitrecTextView1);
			}
		}
	}
	/*class RecyclerViewLanguageAdapter extends RecyclerView.Adapter<RecyclerViewLanguageAdapter.Holder>
	{
	LayoutInflater mInflater;
	Context mContext;
	List<Button> mButtons=new ArrayList<>();
	List<View.OnClickListener> mListeners=new ArrayList<>();
	Button mButton;
		public RecyclerViewLanguageAdapter(Context c,List<View.OnClickListener> list){
			mContext=c;
			mInflater=LayoutInflater.from(mContext);
			mButton=new Button(mContext);
			mListeners=list;
		}
		@Override
		public alfavit.RecyclerViewLanguageAdapter.Holder onCreateViewHolder(ViewGroup p1, int p2)
		{
			p1.addView(mButton);
			return new Holder(mButton);
		}

		@Override
		public void onBindViewHolder(alfavit.RecyclerViewLanguageAdapter.Holder p1, int p2)
		{
			//int position=p2+1;
			p1.button.setOnClickListener(mListeners.get(p2));
			mButtons.add(p1.button);
			if(p2+1==1)p1.button.setText("русский");
			else p1.button.setText("английский");
		}
		@Override
		public int getItemCount()
		{
			return mButtons.size();
		}

		class Holder extends RecyclerView.ViewHolder{
			public Button button;
			public Holder(Button view){
				super(view);
				button=view;
			}
		}
	}*/
    /*  Uri uri=Uri.parse("http://youtube.com");
     Intent intent;
     Intent mIntent = new Intent(Intent.ACTION_VIEW);
     Bundle bundle=new Bundle();
     //new Builder()
     BundleCompat.putBinder(bundle,CustomTabsIntent.EXTRA_SESSION,null);
     mIntent.putExtras(bundle);
     //setTbColor
     mIntent.putExtra(CustomTabsIntent.EXTRA_TOOLBAR_COLOR, Color.GREEN);
     //build()
     mIntent.putExtra(CustomTabsIntent.EXTRA_ENABLE_INSTANT_APPS,true);
     //CustomTabsIntent()
     intent=mIntent;
     //launchUrl
     intent.setData(uri);
     ContextCompat.startActivity(alfavit.this,intent,null);*/
}
