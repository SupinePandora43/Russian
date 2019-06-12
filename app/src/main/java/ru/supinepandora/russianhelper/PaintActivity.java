package ru.supinepandora.russianhelper;
import android.content.*;
import android.os.*;
import androidx.appcompat.app.*;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.Toolbar;

import android.util.*;
import android.view.*;
import android.graphics.*;

import com.google.android.material.textfield.TextInputEditText;
import com.skydoves.colorpickerview.*;
import com.skydoves.colorpickerview.flag.FlagMode;
import com.skydoves.colorpickerview.flag.FlagView;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;
import com.skydoves.colorpickerview.listeners.ColorListener;
import ru.supinepandora.russianhelper.view.*;
import codetail.graphics.drawables.*;
import android.graphics.drawable.Drawable;
import android.content.res.*;
import android.widget.LinearLayout;

public class PaintActivity extends AppCompatActivity implements ColorEnvelopeListener
{
PaintMyView mPV;
Toolbar tb;
Easy ea;
int lastColor=0;
Context c=PaintActivity.this;
	@Override
	public void onColorSelected(ColorEnvelope colorEnvelope, boolean fromUser)
	{
		if(mPV!=null){
			if(lastColor!=colorEnvelope.getColor())

			mPV.getPaint().setColor(colorEnvelope.getColor());

		}
			if(lastColor==0)lastColor=colorEnvelope.getColor();
	}


	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		ea=new Easy(this);
		LollipopDrawablesCompat.registerDrawable(RippleDrawable.class,"ripple");
		setContentView(R.layout.pai);
		mPV=ea.findViewById(R.id.paiPaintMyView1);
		tb=ea.findViewById(R.id.toolbar);
		setSupportActionBar(tb);
		getSupportActionBar().setTitle("");
		tb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener(){
				@Override
				public boolean onMenuItemClick(MenuItem p1)
				{
					switch(p1.getItemId()){
						case R.id.palleteitemmenu:
							ColorPickerDialog.Builder b=new ColorPickerDialog.Builder(PaintActivity.this);
							b.getColorPickerView().setColorListener(PaintActivity.this);
							b.getColorPickerView().setFlagView(new CustomPaletteFlag(c,R.layout.mycustomflag));
							b.getColorPickerView().getFlagView().setFlagMode(FlagMode.ALWAYS);
							b.setNeutralButton("cancel",null);
							b.setPositiveButton("ok",PaintActivity.this);
							b.setPreferenceName("paintColorChooser");
							//b.show();
							AlertDialog.Builder bil=new AlertDialog.Builder(c);
							bil.setNegativeButton("cancel",null);
							LinearLayout pivie=(LinearLayout) getLayoutInflater().inflate(R.layout.pickercolorlay,null,false);
							final ColorPickerView picker=(ColorPickerView)pivie.getChildAt(0);
							picker.setPreferenceName("PaintActivity.Dialog.ColorPickerView");
							picker.setFlagView(new CustomPaletteFlag(c,R.layout.mycustomflag));
							picker.getFlagView().setFlagMode(FlagMode.ALWAYS);
							//picker.setSavedColor(mPV.getPaint().getColor());
							bil.setPositiveButton("ok", new DialogInterface.OnClickListener(){

									@Override
									public void onClick(DialogInterface p1, int p2)
									{
										onColorSelected(picker.getColorEnvelope(), false);
									}
								});
							bil.setView(pivie);
							bil.show();
							//AlertDialog.Builder bi=new AlertDialog.Builder(c);
							//bi.setView(R.layout.wafersrecycler);
							//bi.show();
							break;
						case R.id.eraseitemmenu:
							mPV.getPaint().setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
							break;
						case R.id.paintwidthitem:
							AlertDialog.Builder ab=new AlertDialog.Builder(PaintActivity.this);
							ab.setNegativeButton("cancel",null);
							ab.setTitle("ширина");
							final TextInputEditText inedt=new TextInputEditText(c);
							inedt.setHint("ширина");
							inedt.setText(""+mPV.getPaint().getStrokeWidth());
							//ab.setView(inedt);
							inedt.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
							AppCompatSeekBar sb=new AppCompatSeekBar(c);
							sb.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
							LinearLayout nl=new LinearLayout(c);
							nl.addView(inedt);
							nl.addView(sb);
							nl.setOrientation(LinearLayout.VERTICAL);
							ab.setView(nl);
							ab.setPositiveButton("ok", new DialogInterface.OnClickListener(){
									@Override
									public void onClick(DialogInterface p1, int p2)
									{
										mPV.getPaint().setStrokeWidth(Float.parseFloat(inedt.getEditableText().toString()));
									}
							});
							ab.show();
							break;
							case R.id.paintmenufill:
								//mPV.getPaint().setStyle(Paint.Style.FILL);
								mPV.fmode=true;
							break;
							case R.id.paintmenuedit:
								mPV.fmode=false;
								mPV.getPaint().setXfermode(null);
								mPV.colpm=false;
							break;
							case R.id.paintmenucolorize:
								mPV.colpm=true;
							break;
					}
					return false;
				}
			});
		LinearLayout pivie=(LinearLayout) getLayoutInflater().inflate(R.layout.pickercolorlay,null,false);
		final ColorPickerView picker=(ColorPickerView)pivie.getChildAt(0);
		picker.setPreferenceName("PaintActivity.Dialog.ColorPickerView");
		onColorSelected(picker.getColorEnvelope(), false);
		tb.inflateMenu(R.menu.paintmenu);
		MainActivity.setToolbarMode(getWindow(),this);
		MainActivity.setHome(this);

	}
	private Drawable getDrawable2(int rid){
		return LollipopDrawablesCompat.getDrawable(getResources(),rid,getTheme());
	}
	public void rippl(final View v){
		//v.getBackground().setHotspot(2f,4f);
		//LollipopDrawable rld=getDrawable2(R.drawable.roipltes);
		//LayerDrawable ld=new LayerDrawable(new Drawable[]{v.getBackground(),getDrawable2(R.drawable.roipltes)});
		if(!(v.getBackground() instanceof LollipopDrawable)){
		v.setBackgroundDrawable(getDrawable2(R.drawable.roipltes));
		v.setOnTouchListener(new DrawableHotspotTouch((LollipopDrawable)v.getBackground()));
		}
	}
	public void coloriz(int mc){
		mPV.getPaint().setColor(mc);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.paintmenu,menu);
		//MenuItem erasecheck=menu.findItem(R.id.eraseitemmenu);
		//AppCompatCheckBox chb=(AppCompatCheckBox) MenuItemCompat.getActionView(erasecheck);


		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		overridePendingTransition(R.anim.activitymanimenter1,R.anim.activitymanimexit1);
	}
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		tb=null;
		ea=null;
		mPV=null;
		//c=null;
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
	private class CustomPaletteFlag extends FlagView
	{
	View colview;
	android.widget.TextView coltext;
		public CustomPaletteFlag(Context co,int flaglay){
			super(co,flaglay);
			colview=findViewById(R.id.mycustomflagView1);
			coltext=(android.widget.TextView)findViewById(R.id.mycustomflagTextView1);
		}
		@Override
		public void onRefresh(ColorEnvelope colorEnvelope)
		{
			colview.setBackgroundColor(colorEnvelope.getColor());
			coltext.setText("#"+colorEnvelope.getHexCode());
		}


	}

}
