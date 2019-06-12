package ru.supinepandora.russianhelper;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import androidx.appcompat.app.*;
import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import android.content.*;

public class SettingsActivityFragment extends PreferenceFragmentCompat
{
SharedPreferences sh;
	@Override
	public void onCreatePreferences(Bundle p1, String p2)
	{
		addPreferencesFromResource(R.xml.settingspreference);

		Preference.OnPreferenceClickListener statusListener=new Preference.OnPreferenceClickListener(){
			public boolean onPreferenceClick(Preference p1){
				MainActivity.setToolbarMode(getActivity().getWindow(),(AppCompatActivity)getActivity());
				return false;
			}};

		//findPreference("statusbar").setVisible(false);
		//CheckBoxPreference setPref=(CheckBoxPreference) findPreference("set");
		//Bitmap bitmap=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_settings),96,96,false);
		//BitmapDrawable icon=new BitmapDrawable(bitmap);
		//setPref.setIcon(icon);

		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
			findPreference("statusbar").setEnabled(false);
			/*findPreference("statusbar").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){

					@Override
					public boolean onPreferenceClick(Preference p1)
					{
						// TODO: Implement this method
						Easy ea=new Easy(getContext());
						ea.setCoordinatorLayout((CoordinatorLayout)getActivity().findViewById(R.id.setspreflayCoordinatorLayout1));
						ea.sbar("ваша версия андройда имеет подстраемовый статус бар,эта функция будет работать только на старых андройдах");
						return false;
					}});*/
		}else{
			findPreference("set").setOnPreferenceClickListener(statusListener);
		findPreference("statusbar").setOnPreferenceClickListener(statusListener);
		}
		findPreference("set").setIcon(VectorDrawableCompat.create(getResources(),R.drawable.v_settings_white,getActivity().getTheme()));
		findPreference("reset").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
				@Override public boolean onPreferenceClick(Preference p1){
					if(sh!=null){
						sh.edit().clear().commit();
						if(Build.VERSION.SDK_INT<21){
							MainActivity.setToolbarMode((AppCompatActivity)getActivity());
						}
						((SettingsActivity)getActivity()).reload();
					}
					return false;
				}
			});
	}

	public SettingsActivityFragment(){}
	CheckBoxPreference cbp;
	ListPreference lp;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		sh= PreferenceManager.getDefaultSharedPreferences(getActivity());
		/*addPreferencesFromResource(R.xml.settingspreference);
		findPreference("set").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){

				@Override
				public boolean onPreferenceClick(Preference p1)
				{
					MainActivity.setToolbarMode(getActivity().getWindow(),((AppCompatActivity)getActivity()));
					return false;
				}
			});

		((CheckBoxPreference)findPreference("statusbar")).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){

				@Override
				public boolean onPreferenceClick(Preference p1)
				{
					MainActivity.setToolbarMode(getActivity().getWindow(),((AppCompatActivity)getActivity()));
					return false;
				}


		});*/
	}


}
