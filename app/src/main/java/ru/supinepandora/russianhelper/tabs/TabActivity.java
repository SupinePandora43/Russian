package ru.supinepandora.russianhelper.tabs;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import ru.supinepandora.russianhelper.R;
import java.util.List;
import java.util.ArrayList;
import androidx.appcompat.app.*;
import androidx.appcompat.widget.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.*;
import ru.supinepandora.russianhelper.*;
import android.graphics.drawable.*;
import android.support.v4.app.*;
import android.content.*;

import com.google.android.material.tabs.TabLayout;

public class TabActivity extends AppCompatActivity
{
	Toolbar tb;
	TabLayout tabl;
	ViewPager vp;
	Easy ea;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs1);
		tb=(Toolbar) findViewById(R.id.tabs1toolbar);
		setSupportActionBar(tb);
		tabl=(TabLayout) findViewById(R.id.tabs1TabLayout1);
		vp=(ViewPager) findViewById(R.id.tabs1ViewPager1);
		setupViewPager(vp);
		tabl.setupWithViewPager(vp);
		//getDelegate().setCompatVectorFromResourcesEnabled(true);
		tabl.getTabAt(1).setIcon(R.drawable.ic_messagetab2);
		tabl.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

				@Override public void onTabSelected(TabLayout.Tab p1){
					if(p1.getPosition()==1){
						p1.setIcon(R.drawable.ic_messagetab);
						//p1.setIcon(R.drawable.v_mail);
					}
				}

				@Override public void onTabUnselected(TabLayout.Tab p1){
					if(p1.getPosition()==1){
						p1.setIcon(R.drawable.ic_messagetab2);
					}
				}

				@Override public void onTabReselected(TabLayout.Tab p1){

				}
			});

		ea=new Easy(this);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		//getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ea.getAttrColor(R.attr.colorPrimary)));

		MainActivity.setToolbarMode(getWindow(),this);
	}

	private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),this);
        adapter.addFragment(new TabFrag1(), "info");
        adapter.addFragment(new TabFrag2(), "");


        viewPager.setAdapter(adapter);
    }


	class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
		TabLayout mTabLayout;
		Context mContext;
		private final List<Drawable> mFragmentTitleIcons = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager, Context c) {
            super(manager);
			mContext=c;
        }
		/*public void findTabLayout(TabLayout tl){
			mTabLayout=tl;
		}*/
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
		/*public void addFragment(Fragment frag,String title,int resid){
			addFragment(frag,title,ContextCompat.getDrawable(mContext,resid));
		}
		public void addFragment(Fragment frag,String title,Drawable ic){
			addFragment(frag,title);
			mFragmentTitleIcons.add(ic);
		}*/
        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
		/*public Drawable getPageIcon(int pos){
			return mFragmentTitleIcons.get(pos);
		}*/
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
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
