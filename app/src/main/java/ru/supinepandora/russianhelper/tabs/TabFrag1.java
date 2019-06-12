package ru.supinepandora.russianhelper.tabs;
import android.support.v4.app.*;
import android.os.*;
import android.view.*;
import ru.supinepandora.russianhelper.*;
import android.util.*;
import com.bumptech.glide.Glide;
import android.widget.*;
import android.net.*;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.request.*;
import com.bumptech.glide.load.engine.*;
import com.bumptech.glide.load.resource.drawable.*;
public class TabFrag1 extends Fragment
{
	public TabFrag1(){}
	@Override
	public void onCreate(Bundle savedInstanceState)
	{super.onCreate(savedInstanceState);}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.tabslayout1, container, false);
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
			ImageView im=(ImageView)view.findViewById(R.id.tabslayout1ImageView1);
			
			RequestOptions opt=new RequestOptions()
			.skipMemoryCache(false)
			.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
			//.fitCenter()
			//.centerCrop()
			;
			
			Uri ute=new Easy(this).getFileAssetUri("java-certificate-lol.jpg");
			Glide.with(this.getContext())
			.asDrawable()
			.apply(opt)
			.load(ute)

			.transition(DrawableTransitionOptions.withCrossFade())

			.into(im);

	}
	
}
