package ru.supinepandora.recyclerIntentChooser;

import android.content.*;
import android.content.pm.*;
import android.graphics.drawable.*;
import androidx.recyclerview.widget.*;
import android.view.*;
import android.widget.*;
import java.util.*;

public class RecyclerViewIntentChooserAdapter extends RecyclerView.Adapter<RecyclerViewIntentChooserAdapter.ViewHolder>
{
public static interface OnActivityStarted{
	boolean started(Intent startedIntent,String packageName);
}
Context mContext;
List<ResolveInfo> activities=new ArrayList<>();
PackageManager mPM;
Intent mIntent;
OnActivityStarted listener;

		class ViewHolder extends RecyclerView.ViewHolder{
			public ImageView mImageView;
			public TextView mTextView;
			public ViewHolder(View itemView){
				super(itemView);
				mImageView=(ImageView)itemView.findViewById(R.id.recyclerchooseritemImageView1);
				mTextView=(TextView)itemView.findViewById(R.id.recyclerchooseritemTextView1);
			}
		}
		public void setListener(OnActivityStarted listen){
			listener=listen;
		}
		public RecyclerViewIntentChooserAdapter(Context c,Intent inten){//,List<ResolveInfo> activitiesi){
			mContext=c;
			mPM=mContext.getPackageManager();
			activities=mPM.queryIntentActivities(inten,0);
			mIntent=inten;
		}
		@Override
		public RecyclerViewIntentChooserAdapter.ViewHolder onCreateViewHolder(ViewGroup p1, int p2){
			LayoutInflater mInflater=LayoutInflater.from(mContext);
			View inflatedView=mInflater.inflate(R.layout.recyclerchooseritem,p1,false);
			return new ViewHolder(inflatedView);
		}
		@Override
		public void onBindViewHolder(RecyclerViewIntentChooserAdapter.ViewHolder p1, int p2){
			ResolveInfo activity=activities.get(p2);
			String name=(String) activity.activityInfo.loadLabel(mPM);
			Drawable logo=activity.activityInfo.loadIcon(mPM);
			final String packageact=activity.activityInfo.packageName;

			p1.mImageView.setImageDrawable(logo);
			p1.mTextView.setText(name);

			p1.itemView.setOnClickListener(new View.OnClickListener(){
				@Override public void onClick(View vi){
					Intent in=mIntent;
					in.setPackage(packageact);
					if(listener!=null){
						if(listener.started(in,packageact)){//if return false do our custom reaction
							mContext.startActivity(in);
						}
					}else
					mContext.startActivity(in);
					}});
			}
		@Override public int getItemCount(){return activities.size();}
}
