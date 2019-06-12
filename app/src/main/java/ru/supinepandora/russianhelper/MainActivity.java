package ru.supinepandora.russianhelper;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import android.view.View;

import info.kimjihyok.ripplesoundplayer.SoundPlayerView;
import info.kimjihyok.ripplesoundplayer.SoundPlayerView.OnMediaPlayFinishCallback;
import ru.supinepandora.russianhelper.tabs.*;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.KeyEvent;
import android.os.Build;
import android.view.animation.Animation;
import android.content.DialogInterface;
import android.view.animation.AnimationUtils;
import android.media.MediaPlayer;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;
import java.util.List;
import java.util.ArrayList;
import android.app.Notification;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import info.kimjihyok.ripplesoundplayer.RippleVisualizerView;
import info.kimjihyok.ripplesoundplayer.renderer.*;
import info.kimjihyok.ripplesoundplayer.util.PaintUtil;
import ru.supinepandora.russianhelper.gameclicker.*;
import android.content.res.*;
import android.util.*;
import android.annotation.*;
import com.bumptech.glide.*;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import codetail.graphics.drawables.*;


public class MainActivity extends AppCompatActivity
{
/*static{
	AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
}*/
enum MyOrientation{
P,LAND
}
MyOrientation mOrientation;
Context c=MainActivity.this;
CoordinatorLayout cl1;
//Toolbar tb;
//AppBarLayout mAppBarLayout;
FloatingActionButton fab1;
FloatingActionButton fab2;
//NestedScrollView mNestedScrollView;
BottomSheetBehavior bsheetb1;
RecyclerView rv1;
MyRecyclerViewAdaptersheet adrvsheet1;
List<String> musicsname=new ArrayList<>();
List<OnClickListener> musicslistener=new ArrayList<>();
Animation animscale1;
Animation animscale2;
Animation slide1;
View sheetcontent;
public MediaPlayer mp1;
Easy ea;
Bundle animBundle;
MediaPlayer.OnCompletionListener mpl1;
RippleVisualizerView mRippleVisualizerView;
private OnMediaPlayFinishCallback mediaplayfinish;
boolean playing=false;
int playName=0;
FloatingActionButton mFab;
NavigationView mNavigationSheet;
boolean alertBackDialog=false;
RippleDrawable musidr;
RippleDrawable closidr;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
		setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		setToolbarMode(this);
		ea=new Easy(this);
		//tb=(Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar((Toolbar) ea.findViewById(R.id.toolbar));
		//mAppBarLayout=(AppBarLayout)findViewById(R.id.appBar);
		cl1=(CoordinatorLayout) findViewById(R.id.mainCoordinatorLayout1);

		//ea.setCoordinatorLayout(cl1);
		//ea.findActivity(this);
		fab1=(FloatingActionButton) findViewById(R.id.mainFloatingActionButton1);
		fab2=(FloatingActionButton) findViewById(R.id.mainFloatingActionButton2);
        //mNestedScrollView=(NestedScrollView) findViewById(R.id.mainNestedScrollView1);
		fab2.setVisibility(View.INVISIBLE);
        //mNestedScrollView.setScrollbarFadingEnabled(true);
		rv1=(RecyclerView) findViewById(R.id.sheetRecyclerView1);
		animscale1= AnimationUtils.loadAnimation(this,R.anim.scale1);
		animscale2=AnimationUtils.loadAnimation(this,R.anim.scale2);
		//mNavigationSheet=(NavigationView)findViewById(R.id.mainNavigationView1);
		sheetcontent=cl1.findViewById(R.id.bottom_sheet);
		final int closid=R.drawable.v_close;
		final int musid=R.drawable.v_music;
		//musidr=(RippleDrawable) LollipopDrawablesCompat.getDrawable(getResources(), R.drawable.r_music);
		bsheetb1=BottomSheetBehavior.from(sheetcontent);
		bsheetb1.setState(BottomSheetBehavior.STATE_HIDDEN);
		bsheetb1.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback(){ @Override public void onStateChanged(View p1, int p2){
					switch(p2){
					case BottomSheetBehavior.STATE_HIDDEN:
					case BottomSheetBehavior.STATE_COLLAPSED:
						fab1.setImageResource(musid);
						//musidf();
						//Glide.with(c).asDrawable().load(R.drawable.ic_music).into(fab1);
						break;
					case BottomSheetBehavior.STATE_EXPANDED:
						fab1.setImageResource(closid);
						//Glide.with(c).asDrawable().load(R.drawable.ic_close_button).into(fab1);
						break;
					}}
				@Override public void onSlide(View p1, float p2){}});
		fab1.setOnClickListener(new OnClickListener(){ @Override public void onClick(View p1) {
					if(bsheetb1.getState()==BottomSheetBehavior.STATE_EXPANDED){
					bsheetb1.setState(BottomSheetBehavior.STATE_HIDDEN);
						fab1.setImageResource(musid);
						//musidf();
						//Glide.with(c).asDrawable().load(R.drawable.ic_music).into(fab1);
					}
					if(bsheetb1.getState()==BottomSheetBehavior.STATE_HIDDEN||bsheetb1.getState()==BottomSheetBehavior.STATE_COLLAPSED){
					bsheetb1.setState(BottomSheetBehavior.STATE_EXPANDED);
					fab1.setImageResource(closid);
					//Glide.with(c).asDrawable().load(R.drawable.ic_close_button).into(fab1);
					}}});

		fab2.setOnClickListener(new OnClickListener(){ @Override public void onClick(View p1){
					animatePlay(p1);
                    setOthersNormal();
					mRippleVisualizerView.stop();
					mRippleVisualizerView.setAmplitudePercentage(0.0);
					playing=false;}});
		mRippleVisualizerView=(RippleVisualizerView)findViewById(R.id.mainRippleVisualizerView1);
		mediaplayfinish = new OnMediaPlayFinishCallback(){@Override public void finished(){mpl1.onCompletion(mp1);}};
		mRippleVisualizerView.setOnMediaPlayFinishCallbackk(mediaplayfinish);
		setupRecyclerSheet(savedInstanceState);
		if(savedInstanceState==null)animateViews();
		animBundle= ActivityOptionsCompat.makeCustomAnimation(this,R.anim.customtabenter,R.anim.customtabexit).toBundle(); //R.anim.customtabexit).toBundle();
		mpl1 = new MediaPlayer.OnCompletionListener(){@Override public void onCompletion(MediaPlayer p1){
				SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
				if(sh.getBoolean("set",false)&&sh.getBoolean("repeatplayer",false)){
					mRippleVisualizerView.play();
				}else{
					animatePlay(fab2);
					mRippleVisualizerView.stop();
					playing=false;}}};
		if(savedInstanceState!=null){
			if(savedInstanceState.getBoolean("alertBack"))onBackPressed();
		}
		List<View> views = new ArrayList<>();
		android.widget.LinearLayout lins=ea.findViewById(R.id.lins),buts=ea.findViewById(R.id.mainbutLinearLayout1);
		try{
		for(int i=0;i<lins.getChildCount();i++){
			if(lins.getChildAt(i).getId()==R.id.mainbutLinearLayout1)continue;
			views.add(lins.getChildAt(i));
		}
		for(int i=0;i<buts.getChildCount();i++){
			views.add(buts.getChildAt(i));
		}
		for(int i=0;i<views.size();i++){
			View v=views.get(i);
			v.setBackgroundDrawable(LollipopDrawablesCompat.getDrawable(getResources(),R.drawable.roipltes,getTheme()));
			v.setOnTouchListener(new DrawableHotspotTouch((LollipopDrawable)v.getBackground()));
			v=null;
		}
		}catch(Exception err){err.printStackTrace();}
		finally{
			views=null;
			lins=null;
			buts=null;
		}
    }

	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		if(bsheetb1.getState()==BottomSheetBehavior.STATE_EXPANDED) outState.putBoolean("sheet",true);
		if(playing){
			outState.putBoolean("playing",true);
			outState.putInt("seek",mRippleVisualizerView.mediaPlayer.getCurrentPosition());
			outState.putInt("playName",playName);
		}
		outState.putBoolean("alertBack",alertBackDialog);
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState)
	{
		super.onRestoreInstanceState(savedInstanceState);
		if(savedInstanceState.getBoolean("sheet")){
			//bsheetb1.setState(BottomSheetBehavior.STATE_EXPANDED);
		}
	}

	void setPlayerButton(){
		playing=true;
		try{
		mRippleVisualizerView.setMediaPlayer(mp1);
		}catch(Exception err){
			ea.toast(""+err);
		}
		mRippleVisualizerView.play();
		getRendererRipple();
		if(mOrientation==MyOrientation.P) mRippleVisualizerView.setAmplitudePercentage(1+((double) 4/10.0));
		else mRippleVisualizerView.setAmplitudePercentage(1+((double) 6/10.0));
	}
	@Override
	protected void onResume()
	{
		super.onResume();
		setToolbarMode(getWindow(),this);
		getRendererRipple();
	}
	void getRendererRipple(){
		SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
		if(sh.getBoolean("set",false)==true){
			switch(sh.getString("modeMain","bar")){
				case "bar":
					mRippleVisualizerView.setCurrentRenderer(new BarRenderer(16,PaintUtil.getBarGraphPaint(Color.GREEN)));
					break;
				case "line":
					mRippleVisualizerView.setCurrentRenderer(new LineRenderer(PaintUtil.getLinePaint(Color.RED)));
					break;
				case "color":
					mRippleVisualizerView.setCurrentRenderer(new ColorfulBarRenderer(8,PaintUtil.getBarGraphPaint(Color.BLUE),Color.CYAN,Color.YELLOW));
					break;
			}
		}else mRippleVisualizerView.setCurrentRenderer(new BarRenderer(16,PaintUtil.getBarGraphPaint(Color.GREEN)));
		if(!playing){
			mRippleVisualizerView.setAmplitudePercentage(0.0);
		}
		sh=null;
	}


	public static void setHome(AppCompatActivity cont){
		cont.getSupportActionBar().setHomeButtonEnabled(true);
		cont.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}
	public static void setToolbarMode(AppCompatActivity cont){
		setToolbarMode(cont.getWindow(),cont);
	}
	public static void setToolbarMode(Window window,AppCompatActivity cont){

		SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(cont);
		WindowManager.LayoutParams wmlp=window.getAttributes();
		View vie=cont.findViewById(R.id.fakebar);
		if(Easy.getApiVersion()>=21){
			if(vie!=null){
				vie.setVisibility(View.GONE);
				((AppBarLayout.LayoutParams)vie.getLayoutParams()).setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
			}
		}else if(sh.getBoolean("set",false)==true&&sh.getBoolean("statusbar",false)==true){
			TypedValue tval=new TypedValue();
			cont.getTheme().resolveAttribute(R.attr.bartheme,tval,true);//если тема не суппорт без прозрачного бара
			if(!tval.string.equals("true")){//если не бартема
				wmlp.flags |=WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
				if(vie!=null){
					vie.setVisibility(View.VISIBLE);
					((AppBarLayout.LayoutParams)vie.getLayoutParams()).setScrollFlags(0);
				}
			}
		}else {
			wmlp.flags &= ~WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
			if(vie!=null){
				vie.setVisibility(View.GONE);
				((AppBarLayout.LayoutParams)vie.getLayoutParams()).setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
			}
		}
		window.setAttributes(wmlp);
	}

	void animateViews(){
		fab1.startAnimation(AnimationUtils.loadAnimation(this,R.anim.abc_slide_in_bottom));
	}
	void setupRecyclerSheet(Bundle stat){
		Log.i("Main.setupRecyclerSheet","setuping...");
		// musicsname musicslistener
		//FloatingActionButton icecsgobutton=new FloatingActionButton(this);
		//FloatingActionButton alanfabebutton=new FloatingActionButton(this);
		musicsname.add("Тает Лёд CS:GO");
		musicsname.add("AlanWalker Fade");
		musicslistener.add(new OnClickListener(){@Override public void onClick(View p1){
			playfab();
			mp1=MediaPlayer.create(c,R.raw.icecsgo);
            animatePlay(p1);
			playName=1;
			setPlayerButton();
			mFab=(FloatingActionButton)p1;
			}});
		musicslistener.add(new OnClickListener(){@Override public void onClick(View p1){
			playfab();
			mp1=MediaPlayer.create(c,R.raw.walkerfade);
			playName=2;
            animatePlay(p1);
			setPlayerButton();
			mFab=(FloatingActionButton)p1;
		}});
		/*if(stat!=null){
			if(stat.getBoolean("playing")){
				//nom=stat.getInt("playName");
			}
		}*/
		Easy.RecyclerHelper rh=new Easy.RecyclerHelper(rv1);
		adrvsheet1=new MyRecyclerViewAdaptersheet(this,musicsname,musicslistener,stat,rh);
		rv1.setLayoutManager(new LinearLayoutManager(this));
		rv1.setAdapter(adrvsheet1);

		//rv1.postInvalidate();

		rh.setDragging(true,true,true,true);
		rh.setup();

		rv1.invalidate();
		rv1.postInvalidate();
		//icecsgobutton.setOnClickListener(musicslistener.get(0));
		//alanfabebutton.setOnClickListener(musicslistener.get(1));

		//Menu menusheet=mNavigationSheet.getMenu();

		//menusheet.getItem(0).setActionView(icecsgobutton);
		//menusheet.getItem(1).setActionView(alanfabebutton);

		//menusheet.getItem(0).setTitle("Тает Лёд CS:GO");
		//menusheet.getItem(1).setTitle("AlanWalker Fade");
		if(stat!=null){
			//Log.i("Main","onPost.loadState");
			if(stat.getBoolean("sheet")){
				//bsheetb1.setState(BottomSheetBehavior.STATE_COLLAPSED);
				//bsheetb1.setState(BottomSheetBehavior.STATE_EXPANDED);
			}
			if(stat.getBoolean("playing")){
				//animatePlay((FloatingActionButton)findViewById(savedInstanceState.getInt("fabId")));
				//animatePlay(adrvsheet1.getFloatingActionButton(1));
			}
		}
	}

	void playfab(){
		if(playing){
			mRippleVisualizerView.stop();
		}else{
		fab2.startAnimation(animscale1);
		fab2.setVisibility(View.VISIBLE);
		}
	}

	void setOthersNormal(){
		try{
			for(int i=0;i<adrvsheet1.getInitCount();i++){
                FloatingActionButton fab=adrvsheet1.getFloatingActionButton(i);
                if(fab.getVisibility()==View.INVISIBLE||fab.getVisibility()==View.GONE){
                adrvsheet1.getFloatingActionButton(i).setVisibility(View.VISIBLE);
				  adrvsheet1.getFloatingActionButton(i).startAnimation(animscale1);
		}}}catch(Exception e){
			Log.e("Main.setOthersMonral","Error: "+e);
		}
                 }

	void animatePlay(View v){
		Log.i("Main.animatePlay","animating...");
        if(v.getVisibility()!=View.INVISIBLE){
      setOthersNormal();
       v.startAnimation(animscale2);
       v.setVisibility(View.INVISIBLE);
       }}

	public void timeless(View v){
		Easy.customTab eac=new Easy.customTab(this);
		SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(this);
		eac.setFullUrl(sh.getString("schooltime","http://docs.google.com/spreadsheets/d/1oE_VShFd_4DY6xynGvxPPkztb2duZoVvOtF6emfTGtI/htmlview#gid=0"));
		eac.setEnterAnimation(R.anim.customtabenter,R.anim.customtabexit);
		eac.setExitAnimation(R.anim.activitymanimenter1,R.anim.activitymanimexit1);
		eac.setToolbarColor();
		if(sh.getBoolean("set",false)) {
			if(sh.getString("mList","").equals("chrome")) eac.setPackageIntent(Easy.customTab.Packages.PACKAGE_CHROME);}
		eac.startCustomTab();
	}
	public void rkncl(View v){
		startActivity(new Intent(MainActivity.this,GameActivity.class),animBundle);
	}
	public void pass(View v){
		//startActivity(new Intent(MainActivity.this,password.class),animBundle);
	}
	public void click1(View v){
		Intent intenact=new Intent(MainActivity.this,ru.supinepandora.russianhelper.act1.class);
		startActivity(intenact,animBundle);
		}

	public void lineyka(View v){
		startActivity(new Intent(this,lineyka.class),animBundle);
	}
	public void matem(View v){
		startActivity(new Intent(this,MatemActivity.class),animBundle);
	}
	public void ainf(View v){
		startActivity(new Intent(this,androidinfo.class),animBundle);
	}
	public void alf(View v){
		startActivity(new Intent(this,alfavit.class),animBundle);
	}
	public void gdz(View v){
		startActivity(new Intent(this,gdzActivity.class),animBundle);
	}
	public void wafly(View v){
		ea.startActivity(wafersActivity.class,animBundle);
	}

	public void cameram(View v){
		ea.startActivity(CameraActivity.class,animBundle);
	}
	public void paint(View v){
		ea.startActivity(PaintActivity.class,animBundle);
	}

	public void sensor(View v){
		ea.startActivity(SensorActivity.class,animBundle);
	}
	public void math(View v){
		ea.startActivity(mathTableActivity.class,animBundle);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.mainmenu,menu);
		//menu.getItem(1).setIcon(VectorDrawableCompat.create(getResources(),R.drawable.v_settings_primary,getTheme()));
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId()){
			case R.id.mainmenuitem1:
				startActivity(new Intent(this,TabActivity.class),animBundle);
			break;
			case R.id.mainmenuitemsettings:
				startActivity(new Intent(this,SettingsActivity.class),animBundle);
			break;
		}
		return super.onOptionsItemSelected(item);
	}


	public void onBackPressed(){
		AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
		b.setMessage("Вы хотите выйти?");
		b.setNegativeButton("Нет", null);//new DialogInterface.OnClickListener(){@Override public void onClick(DialogInterface p1, int p2){}});
		b.setPositiveButton("Да", new DialogInterface.OnClickListener(){@Override public void onClick(DialogInterface p1, int p2){finish();}});
		AlertDialog d = b.create();
		d.show();
		alertBackDialog=true;
	}
	  @Override protected void onDestroy() {
        super.onDestroy();
		if(playing) mRippleVisualizerView.destroy();
		WindowManager.LayoutParams lpw=getWindow().getAttributes();
		lpw.flags &= ~ WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		getWindow().setAttributes(lpw);
	  }
	  @Override
	  public void onWindowFocusChanged(boolean hasFocus)
	  {
		  super.onWindowFocusChanged(hasFocus);
		  if(mRippleVisualizerView!=null){
		  switch(getResources().getConfiguration().orientation){
			  case Configuration.ORIENTATION_PORTRAIT:
				  mOrientation=MyOrientation.P;
				  break;
			  case Configuration.ORIENTATION_LANDSCAPE:
				  mOrientation=MyOrientation.LAND;
				  break;
			  case Configuration.ORIENTATION_SQUARE:
			  case Configuration.ORIENTATION_UNDEFINED:
				  mOrientation=MyOrientation.P;
				  break;
		  }}
	  }



class MyRecyclerViewAdaptersheet extends RecyclerView.Adapter<MyRecyclerViewAdaptersheet.ViewHolder>
{
	private List<String> mData;
	private LayoutInflater mInflater;
	private List<OnClickListener> musics;

	public List<FloatingActionButton> mButtons=new ArrayList<>();
	Bundle savedkostylState;

	Easy.RecyclerHelper rhelp;

	public MyRecyclerViewAdaptersheet(Context c,List<String> data,List<OnClickListener> listener1,Bundle sta,Easy.RecyclerHelper rechelp){
		this.mInflater=LayoutInflater.from(c);
		this.mData=data;
		this.musics=listener1;
		this.savedkostylState=sta;
		this.rhelp=rechelp;
	}

	@Override
	public void onBindViewHolder(MyRecyclerViewAdaptersheet.ViewHolder p1, int p2)
	{
		OnClickListener l1=musics.get(p2);
		String st1=mData.get(p2);
		p1.tv1.setText(st1);
		p1.f1.setOnClickListener(l1);
		mButtons.add(p1.f1);
		if(p2==1)rhelp.addUnDraggableHolder(p1,p2,Easy.RecyclerHelper.Direction.RIGHT|Easy.RecyclerHelper.Direction.DOWN);
		if(savedkostylState!=null){//есть сохранение?
			if(savedkostylState.getBoolean("playing",false)){//игралась музыка?
				if(savedkostylState.getInt("playName")==(p2+1)){//номер трека равен номеру holder'а ?
					l1.onClick(p1.f1);//настраивается плеер
					//mp1.seekTo(savedkostylState.getInt("seek"));//перематывает в нужный момент
					mRippleVisualizerView.mediaPlayer.seekTo(savedkostylState.getInt("seek"));
					//setPlayerButton();//настройка.возпроизведение
					//getRendererRipple();//нахождение рендера//было лишнее
				}
			}
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup p1, int p2)
	{
		View v1=mInflater.inflate(R.layout.musicrecyclerhelp,p1,false);
		return new ViewHolder(v1);
	}

	@Override
	public int getItemCount()
	{
		return mData.size();
	}
	public FloatingActionButton getFloatingActionButton(int pos){
			return mButtons.get(pos);
	}
	public int getInitCount(){
		return mButtons.size();
	}
public class ViewHolder extends RecyclerView.ViewHolder
{
	public TextView tv1;
	public FloatingActionButton f1;
	ViewHolder(View itemView)
	{
		super(itemView);
		tv1=(TextView) itemView.findViewById(R.id.musicrecyclerhelpTextView1);
		f1=(FloatingActionButton) itemView.findViewById(R.id.musicrecyclerhelpFloatingActionButton1);
	}
	String getItem(int id){
		return mData.get(id);
	}}}

}
