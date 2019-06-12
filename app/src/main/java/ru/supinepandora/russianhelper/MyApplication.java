package ru.supinepandora.russianhelper;
import android.app.Application;
import android.os.Bundle;
import android.app.Activity;
import androidx.appcompat.app.*;
import android.os.*;
import android.util.*;
import com.singhajit.sherlock.core.Sherlock;
//import android.support.multidex.*;
import android.content.*;
//import com.rey.material.app.*;

public class MyApplication extends Application
{
static{
	AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
}
	public MyApplication(){

	}
	@Override
	public void onCreate()
	{
		super.onCreate();
		//registerActivityLifecycleCallbacks(this);
		Sherlock.init(this);
		//ThemeManager.init(this,1,0,null);

	}
}
