package ru.supinepandora.russianhelper.gameclicker;
import androidx.appcompat.app.*;
import androidx.appcompat.widget.Toolbar;
import androidx.customview.widget.ViewDragHelper;

import android.os.*;
import ru.supinepandora.russianhelper.*;
import android.widget.TextView;
import android.view.*;
import java.util.*;
import android.util.*;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GameActivity extends AppCompatActivity
{
Thread mThread;
Runnable mRunnable;
long bannedCount=0;
TextView mTextView;
FloatingActionButton fab;
LinearLayout laylin;
Timer mTimer;
TimerTask mTimerTask;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameclick);

		setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

		MainActivity.setToolbarMode(getWindow(),this);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		laylin=(LinearLayout) findViewById(R.id.gameclickLinearLayout1);
		mTextView=(TextView)findViewById(R.id.gameclickTextView1);
		fab=(FloatingActionButton)findViewById(R.id.gameclickFloatingActionButton1);
		mTimer=new Timer();
		mTimerTask = new TimerTask(){
			@Override public void run(){
				runOnUiThread(mRunnable);
			}
		};
		mRunnable=new Runnable(){
			@Override public void run(){
				bannedCount=bannedCount+(bannedCount/10000);
				//bannedCount=bannedCount*10;
				mTextView.setText("забаненно: "+bannedCount+", IP адресов");
			}
		};
		mTimer.schedule(mTimerTask,1,1);
		//mThread=new Thread(mRunnable);
		//mThread.start();
		fab.setOnClickListener(new View.OnClickListener(){
			@Override public void onClick(View p1){
				bannedCount=bannedCount+500;
				//mThread.run();
				//runOnUiThread(mRunnable);
			}});
		ViewDragHelper.Callback callb= new ViewDragHelper.Callback(){
			@Override
			public boolean tryCaptureView(View p1, int p2)
			{
				return true;
			}
			@Override
			public int getViewHorizontalDragRange(View view){
				return 100;
			}
			@Override
			public void onViewPositionChanged(View view,int left,int top,int dx,int dy){
				Log.i("posChanged","left:"+left+" top:"+top+" dx:+"+dx+" dy:"+dy);
			}
			@Override
			public void onViewDragStateChanged(int state){
				Log.i("dragState","state:"+state);
			}
		};
		ViewDragHelper dragh=ViewDragHelper.create(laylin,callb);

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
