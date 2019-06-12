package ru.supinepandora.russianhelper.tabs;
import android.support.v4.app.*;
import android.os.*;
import android.view.*;
import ru.supinepandora.russianhelper.R;
import android.widget.*;
import android.view.View.*;
import android.content.*;
import android.net.*;
import ru.supinepandora.russianhelper.*;
import androidx.appcompat.app.*;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.*;

import android.util.*;
import android.preference.*;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ru.supinepandora.recyclerIntentChooser.*;

public class TabFrag2 extends Fragment
{
		Easy ea;
		Easy.customTab eat;
		CoordinatorLayout cl;
	public TabFrag2(){}
	@Override public void onCreate(Bundle savedInstanceState){super.onCreate(savedInstanceState);
	ea=new Easy(getContext());
	eat=new Easy.customTab(getContext());
	}
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		ea=new Easy(getContext());
		eat=new Easy.customTab(getContext());
		View v=inflater.inflate(R.layout.tabslayout2,container,false);
		cl=(CoordinatorLayout) v.findViewById(R.id.tabs1CoordinatorLayout1);
		ea.setCoordinatorLayout(cl);
		FloatingActionButton vk1=(FloatingActionButton) v.findViewById(R.id.tabslayout2FloatingActionButton1);
		vk1.setOnClickListener(new OnClickListener(){@Override public void onClick(View p1){
					try{
					eat.setUrl("vk.com/supinepandora43");
					eat.setEnterAnimation(R.anim.customtabenter,R.anim.customtabexit);
					eat.setExitAnimation(R.anim.activitymanimenter1,R.anim.activitymanimexit1);
					eat.setToolbarColor();
					final SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(getContext());
					/*if(sh.getBoolean("set",false)){
						switch(sh.getString("mList","n")){
						case "browser":
							//eat.setPackageIntent(Easy.customTab.Packages.PACKAGE_VK);
						break;
						case "chrome":
							eat.setPackageIntent(Easy.customTab.Packages.PACKAGE_CHROME);
						break;
					}}*/
					//eat.startCustomTab();
					Intent inte=new Intent(Intent.ACTION_VIEW);
					inte.setData(Uri.parse("http://vk.com/supinepandora43"));

					//BottomSheetBehavior sheet=BottomSheetBehavior.from((LinearLayout) getActivity().findViewById(R.id.tabs1LinearLayout1));
					//RecyclerView rv=(RecyclerView)getActivity().findViewById(R.id.tabs1RecyclerView1);
					//rv.setLayoutManager(new GridLayoutManager(getContext(),2));
					/*RecyclerViewIntentChooserAdapter adapt=new RecyclerViewIntentChooserAdapter(getContext(),inte);
						adapt.setListener(new RecyclerViewIntentChooserAdapter.onActivityStarted(){
								@Override public boolean started(Intent startedIntent, String packageName){
									if(sh.getBoolean("set",false)&&sh.getString("mList","n").equals("chrome")){

									}
									return false;
								}});
					//rv.setAdapter(new RecyclerViewIntentChooserAdapter(getContext(),inte));
					sheet.setState(BottomSheetBehavior.STATE_EXPANDED);
					*/
					if(sh.getBoolean("set",false)&&sh.getString("mList","").equals("chrome")){
						eat.setPackageIntent(Easy.customTab.Packages.PACKAGE_CHROME);
						eat.startCustomTab();
					}else{
						RecyclerView rv=(RecyclerView)getActivity().findViewById(R.id.tabs1RecyclerView1);
						rv.setLayoutManager(new GridLayoutManager(getContext(),2));
					    RecyclerViewIntentChooserAdapter adapt=new RecyclerViewIntentChooserAdapter(getContext(),eat.getIntent());
						/*adapt.setListener(new RecyclerViewIntentChooserAdapter.OnActivityStarted(){
								@Override public boolean started(Intent startedIntent, String packageName){
									if(packageName.equals(Easy.customTab.Packages.PACKAGE_CHROME)){

									}
									return false;
								}
							});*/
						rv.setAdapter(adapt);
						BottomSheetBehavior sheet=BottomSheetBehavior.from((LinearLayout) getActivity().findViewById(R.id.tabs1LinearLayout1));
						sheet.setState(BottomSheetBehavior.STATE_EXPANDED);
					}
					}catch(Exception e){
						ea.sbar(e.toString());
						Log.e("TabFrag2Catch",e.toString());
					}
		}});


		return v;
	}


}
