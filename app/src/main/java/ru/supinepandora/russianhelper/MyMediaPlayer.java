package ru.supinepandora.russianhelper;
import android.app.Service;
import android.os.*;
import android.content.*;
import android.media.*;


public class MyMediaPlayer extends Service
{
MediaPlayer mPlayer;
	@Override
	public IBinder onBind(Intent p1)
	{
		// TODO: Implement this method
		return null;
	}
	@Override
	public void onStart(Intent intent, int startId)
	{
		super.onStart(intent, startId);
	}
	@Override
	public void onCreate()
	{
		super.onCreate();
		
	}

}
