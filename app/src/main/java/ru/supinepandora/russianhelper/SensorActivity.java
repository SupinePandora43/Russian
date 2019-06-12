package ru.supinepandora.russianhelper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.hardware.*;
import android.content.*;
import android.os.*;
import java.util.*;
import android.view.MenuItem;
import android.graphics.Color;
import com.jjoe64.graphview.series.*;
import com.jjoe64.graphview.*;
/*
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.components.*;
import com.github.mikephil.charting.interfaces.datasets.*;
import com.github.mikephil.charting.listener.*;
import com.github.mikephil.charting.highlight.*;
import com.github.mikephil.charting.charts.*;*/

public class SensorActivity extends AppCompatActivity implements SensorEventListener
{
LineGraphSeries<DataPoint> series;//XY
LineGraphSeries<DataPoint> seriesXZ;
LineGraphSeries<DataPoint> seriesZY;
float[] poss=new float[]{0,0,0};
Easy ea;
SensorManager manager;
Sensor temperature;
Battery battery;
Timer mTimer;
Toolbar tb;
//LineChart chart;
//LineChart chart;
GraphView graph;
Random rnd=new Random();
Runnable r=null;
Handler mh=new Handler();
List<Sensor> sensors;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sensors);
		ea=new Easy(this);
		tb=ea.findViewById(R.id.toolbar);
		setSupportActionBar(tb);
		MainActivity.setToolbarMode(this);
		MainActivity.setHome(this);
		graph=ea.findViewById(R.id.graph);
		manager=(SensorManager)getSystemService(SENSOR_SERVICE);
		temperature=manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);//TYPE_AMBIENT_TEMPERATURE
		sensors=manager.getSensorList(Sensor.TYPE_ALL);
		battery=new Battery();
		//mTimer=new Timer();

		/*TimerTask mtask=new TimerTask(){
			@Override
			public void run()
			{
				runOnUiThread(r);
			}
		};*/
		r = new Runnable(){
			@Override
			public void run()
			{
				adn(null);
				mh.postDelayed(this,10);
			}
		};
		/*mTimer.schedule(new TimerTask(){
				@Override
				public void run(){
					runOnUiThread(new Runnable(){
							@Override
							public void run(){
								//addEntry();
									graph.addSeries(new LineGraphSeries<DataPoint>(new DataPoint[]{

																					   new DataPoint(graph.getSeries().size()+1,ea.random(0,100))}));
									//Thread.sleep(25);
							}});}}, 1,1);*/
		//chart.setOnChartValueSelectedListener(this);
		/*chart.getDescription().setEnabled(true);
		chart.setDragEnabled(true);
		chart.setScaleEnabled(true);
		chart.setPinchZoom(true);
		chart.setBackgroundColor(Color.LTGRAY);

		LineData data=new LineData();
		data.setValueTextColor(Color.RED);
		chart.setData(data);
		Legend l=chart.getLegend();
		l.setForm(Legend.LegendForm.LINE);
		l.setTextColor(Color.GREEN);*/
		series=new LineGraphSeries<>(new DataPoint[]{
			/*new DataPoint(0,1),
			new DataPoint(1,5),
			new DataPoint(2,3)*/
		});
		seriesXZ=new LineGraphSeries<>(new DataPoint[]{});
		seriesZY=new LineGraphSeries<>(new DataPoint[]{});
		series.setDrawDataPoints(true);
		seriesXZ.setDrawDataPoints(true);
		series.setDrawBackground(true);
		graph.addSeries(series);
		graph.addSeries(seriesXZ);
		graph.addSeries(seriesZY);
		graph.getViewport().setXAxisBoundsManual(true);
		graph.getViewport().setMinX(0);
		graph.getViewport().setMaxX(10);
	}
	double lad=5d;
	public void adn(android.view.View v){
		//series.resetData(getData());
		lad+=0.25d;
		//addEntry();
		series.appendData(new DataPoint(lad,rand()),true,100);
		seriesXZ.appendData(new DataPoint(lad,poss[1]),true,100);
		seriesZY.appendData(new DataPoint(lad,poss[2]),true,100);
		 /*<!--dependencies>
    <dependency>
      <groupId>androidx.annotation</groupId>
      <artifactId>annotation</artifactId>
      <version>1.0.0</version>
      <scope>runtime</scope>
    </dependency>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
  </dependencies-->*/
	}
	double lr=2;
	double rand(){
		return lr+=rnd.nextDouble()*0.5-0.25;
	}
	/*void addEntry(){
		LineData data=chart.getData();
		if(data==null)return;
		ILineDataSet set=data.getDataSetByIndex(0);
		if(set==null){
			set=new LineDataSet(null,"Dynamic Data");
			set.setAxisDependency(YAxis.AxisDependency.LEFT);
			data.addDataSet(set);
		}
		data.addEntry(new Entry(set.getEntryCount(),(float)ea.random(0,100)),0);
		data.notifyDataChanged();
		chart.notifyDataSetChanged();
		chart.setVisibleXRangeMaximum(120);
		chart.moveViewToX(data.getEntryCount());

	}*/
	/*DataPoint[] getData(){
		int count=30;
		DataPoint[] vals=new DataPoint[count];
		for(int i=0;i<count;i++){
			double x=i;
			double f=rnd.nextDouble()*0.15+0.3;
			double y=Math.sin(i*f+2)+rnd.nextDouble()*0.3;
			DataPoint v=new DataPoint(x,y);
			vals[i]=v;
		}
		return vals;
	}*/
	@Override
	public void onResume()
	{
		super.onResume();
		manager.registerListener(this,temperature,SensorManager.SENSOR_DELAY_NORMAL);
		registerReceiver(battery,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		mh.postDelayed(r,1000);
	}

	@Override
	public void onPause()
	{
		super.onPause();
		manager.unregisterListener(this);
		unregisterReceiver(battery);
	}

	@Override
	public void onSensorChanged(SensorEvent p1)
	{
		poss=p1.values;
	}

	@Override
	public void onAccuracyChanged(Sensor p1, int p2)
	{
		ea.sbar("ac"+p2);
	}
	class Battery extends BroadcastReceiver
	{
	public int status;
	public int health;
	public int volt;
		@Override
		public void onReceive(Context p1, Intent p2)
		{
			status=p2.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
			health=p2.getIntExtra(BatteryManager.EXTRA_HEALTH,1);
			volt=p2.getIntExtra(BatteryManager.EXTRA_VOLTAGE,0);
		}


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
