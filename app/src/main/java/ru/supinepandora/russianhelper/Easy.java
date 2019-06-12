package ru.supinepandora.russianhelper;
import android.content.Context;
import android.widget.Toast;
import android.os.Environment;
import android.os.Bundle;
import android.graphics.Color;
import android.content.res.Resources;

import androidx.annotation.*;
import androidx.appcompat.app.*;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.*;
import androidx.core.content.*;
import androidx.recyclerview.widget.*;

import android.view.View;
import android.net.Uri;
import android.app.Notification;
import android.content.pm.PackageManager;
import android.util.Log;
import android.util.TypedValue;
import android.content.res.ColorStateList;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import java.util.List;
import java.util.ArrayList;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.*;

import java.io.InputStream;
import java.io.IOException;
import java.lang.Deprecated;

@android.annotation.TargetApi(19)
public class Easy
{
public Context mContext;
CoordinatorLayout mCoordinatorLayout;
@TechnicalSupport View mRootView;
@TechnicalSupport View mView;
@TechnicalSupport AppCompatActivity mActivity;
@TechnicalSupport String exceptionInit="you don't initialize class Easy,please initialize this class";

public Easy(@Nullable Context c){
//androidx.appcompat.app.AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
if(c==null) return;
this.mContext=c;
if(mContext instanceof AppCompatActivity){
	mActivity=(AppCompatActivity)mContext;
	mRootView=findViewById(android.R.id.content);
}
}
public Easy(){
	this((Context)null);
}
public Easy(@NonNull View v){
	this(v.getContext());
	mView=v;
}
public Easy(@NonNull androidx.fragment.app.Fragment fr){this(fr.getContext()); }
public Easy(@NonNull android.app.Fragment fr){
	this(fr.getActivity());
}
public Easy(@NonNull android.app.Activity act){
	this((Context)act);
}
public Easy(@NonNull androidx.appcompat.app.AppCompatActivity act){
	this((Context)act);
}
@NonNull
public static final Easy get(@Nullable Object obj){
	Easy e;
	if(obj instanceof AppCompatActivity)e=new Easy((AppCompatActivity)obj);
	else if(obj instanceof android.app.Activity)e=new Easy((android.app.Activity)obj);
	else if(obj instanceof View)e=new Easy((View)obj);
	else if(obj instanceof androidx.fragment.app.Fragment)e=new Easy((androidx.fragment.app.Fragment)obj);
	else if(obj instanceof android.app.Fragment)e=new Easy((android.app.Fragment)obj);
	else if(obj instanceof Context)e=new Easy((Context)obj);
	else e=new Easy();
	return e;
}
@Deprecated//Easy.class find Activity automaticaly
public void findActivity(@NonNull AppCompatActivity mact){
this.mActivity=mact;
}
@Deprecated//use setRootView
public void setCoordinatorLayout(@NonNull CoordinatorLayout cl){
if(cl!=null){
this.mCoordinatorLayout=cl;
mRootView=cl;
if(mContext==null){
	mContext=cl.getContext();
	if(mContext instanceof AppCompatActivity)mActivity=(AppCompatActivity)mContext;
}
}
}
public void setRootView(@NonNull View v){
	mRootView=v;
	if(mContext==null){mContext=v.getContext();if(mContext instanceof AppCompatActivity||mContext instanceof android.app.Activity){mActivity=(AppCompatActivity)mContext;}}
}
public void sbar(@NonNull String text){
	sbar(text,S.sblong);
}
public void sbar(@NonNull String text,@NonNull int time){
if(mRootView!=null) Snackbar.make(mRootView,text,time).show();
else if(mContext!=null)toast(text);
else Log.i("Easy.sbar","you don't find RootView, use setRootView(View). message:"+text);
}

public void toast(@NonNull String text){
toast(text,T.tshort);
}

public void toast(@NonNull String text,@NonNull int lenght){
if(mContext!=null) Toast.makeText(mContext,text,lenght).show();
else Log.i("Easy.toast","you don't find Context. message: "+text);
}

public void log(String tag,String msg){
	if(BuildConfig.DEBUG)
    Log.i(tag,msg);
}
@Nullable
public boolean hasIntentForPackage(@NonNull Intent intentPackage){
	if(mContext!=null){
		PackageManager pm=mContext.getPackageManager();
		if(pm.queryIntentActivities(intentPackage,0).size()>0||pm.queryBroadcastReceivers(intentPackage,0).size()>0||pm.queryIntentServices(intentPackage,0).size()>0){
			return true;
		}else return false;
	}else throw new IllegalArgumentException(exceptionInit);
}
@Nullable
public boolean hasIntentForPackage(@NonNull String intentPackage){
	Intent inte=new Intent();
	inte.setPackage(intentPackage);
	return hasIntentForPackage(inte);
}
@Nullable
@ColorInt
public int getAttrColor(@AttrRes int attrColor){
	TypedValue tval=new TypedValue();
	mContext.getTheme().resolveAttribute(attrColor,tval,true);
	@ColorInt int color=tval.data;
	return color;
}
@NonNull
public static final String getFileAssetString(@NonNull String fileName){
	return "file:///android_asset/"+fileName;
}
@NonNull
public static final Uri getFileAssetUri(@NonNull String fileName){
	return Uri.parse(getFileAssetString(fileName));
}
@Nullable
public String getResourceString(@NonNull int name){//example:  getResourceString(R.raw.music);
	return "android.resource://"+getPackageName()+"/"+name;
}
@Nullable
public Uri getResourceUri(@NonNull int name){
	return Uri.parse(getResourceString(name));
}
@NonNull
public static final String getResourceString(@NonNull String path){//example:  webWiew.loadUrl(Easy.getResourceString(raw/site.html));
	return "file:///android_res/"+path;
}
@NonNull
public static final Uri getResourceUri(@NonNull String path){
	return Uri.parse(getResourceString(path));
}
@Nullable
public String getPackageName(){
	if(!mContext.equals(null)){
		return mContext.getPackageName();
	} else throw throwErr();
}
@RequiresApi(9)
public static final void setFabBackgroundColor(@NonNull FloatingActionButton fab, @NonNull @ColorInt int color){
	fab.setBackgroundTintList(ColorStateList.valueOf(color));
}
@RequiresApi(9)
public void setFabBackgroundResourceColor(@NonNull FloatingActionButton fab,@NonNull @ColorRes int resourcecolor){
	setFabBackgroundColor(fab, ContextCompat.getColor(mContext,resourcecolor));
}
@NonNull
public static final boolean equalsString(@Nullable String p1,@Nullable String p2){
	return android.text.TextUtils.equals(p1,p2);
}
@NonNull
public static final int getApiVersion(){
	return android.os.Build.VERSION.SDK_INT;
}
@Nullable
public int getOrientation(){
	return mContext.getResources().getConfiguration().orientation;
}
public void startActivity(@NonNull Class<?> ActivityClass){
	mContext.startActivity(new Intent(mContext,ActivityClass));
}
public void startActivity(@NonNull Class<?> ActivityClass,@NonNull Bundle bundle){
	mContext.startActivity(new Intent(mContext,ActivityClass),bundle);
}
@TechnicalSupport
private IllegalArgumentException throwErr(){
	return new IllegalArgumentException(exceptionInit);
}
@Nullable
public <ViewName extends View> ViewName findViewById(@NonNull @IdRes int id){
	return  (ViewName) (mView!=null ? mView.findViewById(id) : mActivity.findViewById(id));
}
@Nullable
public String readAssetFile(@NonNull String fileName){
	try{
	InputStream is=mContext.getAssets().open(fileName);
	byte[] form=new byte[is.available()];
	is.read(form);
	is.close();
	return new String(form);
	}catch(Exception e){
		Log.w("Easy.readAssetFile","returned null  error:",e);
		//e.printStackTrace();
		return null;
	}
}
@NonNull
public boolean checkPermission(@NonNull String permission){
	int pid=android.os.Process.myPid(),uid=android.os.Process.myUid();
	String packageApp="";
	if(mContext.checkPermission(permission,pid,uid)==PackageManager.PERMISSION_DENIED)return false;
	String op= AppOpsManagerCompat.permissionToOp(permission);
	if(op==null)return true;
	if(getPackageName()==null){
		String[] packages=mContext.getPackageManager().getPackagesForUid(uid);
		if(packages==null||packages.length <=0)return false;
		packageApp=packages[0];
	}
	if(AppOpsManagerCompat.noteProxyOp(mContext,op,packageApp)!=AppOpsManagerCompat.MODE_ALLOWED){
		log("Easy.checkPermission","permission denied AppOp");
		return false;
	}
	return true;
}
@NonNull
public static final int random(@NonNull @IntRange(from=Integer.MIN_VALUE,to=Integer.MAX_VALUE) int min,@NonNull @IntRange(from=Integer.MIN_VALUE,to=Integer.MAX_VALUE) int max){
	//return (int)Math.floor((Math.random()*max)+min);
	return (int)(Math.random()*(max-min+1))+min;
}
@Nullable
public View inflate(@NonNull @LayoutRes int resource){
	if(mActivity!=null)return mActivity.getLayoutInflater().inflate(resource,null,false);
	else return LayoutInflater.from(mContext).inflate(resource,null,false);
}
@NonNull
public static final <E extends Object> List<E> toListArray(@NonNull E[] objs){//Tested
	List<E> lis=new ArrayList<>();
	for(int i=0;i<objs.length;i++){
		lis.add(objs[i]);
	}
	return lis;
}
@NonNull
public static final <E extends Object> Object[] toArrayList(@NonNull List<E> lis){
	return lis.toArray();
}
public static final <E extends Object> void addAll(@NonNull E[] from,@NonNull List<E> to){//Tested
	for(int i=0;i<from.length;i++){
		to.add(from[i]);
	}
}
public static final <E extends Object> void addAll(@NonNull List<E> from,@NonNull E[] to){
	try{
		for(int i=0;i<from.size();i++){
			to[i]=from.get(i);
		}
	}catch(java.lang.ArrayIndexOutOfBoundsException e){e.printStackTrace();}
}
public static final <E extends Object> void addAll(@NonNull List<E> from,@NonNull List<E> to){
	for(int i=0;i<from.size();i++){
		to.add(from.get(i));
	}
}
public static final String colorIntToHtml(@NonNull @ColorInt int color){
	return String.format("%06X", (0xFFFFFF & color));
}
@ColorInt
public static final int colorHtmlToInt(@NonNull String htmlcolor){
	return Color.parseColor(htmlcolor);
}
public static final android.graphics.Bitmap fillBitmap(@NonNull android.graphics.Bitmap bmp,@ColorInt int fromColor,@ColorInt int to){
	android.graphics.Bitmap bm=bmp;
	int w=bm.getWidth(),h=bm.getHeight();
	int from=fromColor!=0?fromColor:Color.BLACK;
	int toc=to!=0?to:Color.BLACK;
	if((w&h)>0){
		for(int y=0;y<h;y++){
			for(int x=0;x<w;x++){
				if(bm.getPixel(x,y)==from)bm.setPixel(x,y,toc);
			}
		}
	}
	return bm;
}
public static final android.graphics.Bitmap compressDrawableToBitmap(android.graphics.drawable.Drawable dr){
	android.graphics.drawable.BitmapDrawable bmd=((android.graphics.drawable.BitmapDrawable)dr);
	return bmd.getBitmap();
}
public static final android.graphics.drawable.Drawable compressBitmapToDrawable(android.graphics.Bitmap bmp){
	return new android.graphics.drawable.BitmapDrawable(bmp);
}
@Nullable
public static final int getInt(@Nullable String inint){
	return Integer.parseInt(inint);//getInteger
}
public static final class customTab{
        @TechnicalSupport String exceptionClassnotinit="please initialize this class: Easy.customTab eat=new Easy.customTab(Context)";
		boolean chooserenabled;
		String chooserName;
		String uri;
		Intent intent;
		Intent mIntent;
		@TechnicalSupport boolean urlload=false;
		@TechnicalSupport boolean initializated=false;
		Bundle bundle;
		Context mContext;
		int tbColor=0;

        Bundle customTabAnimation=null;

		public customTab(@NonNull Context con){
				//url no http
				mIntent=new Intent(Intent.ACTION_VIEW);
				bundle=new Bundle();
				//
				BundleCompat.putBinder(bundle,Extras.EXTRA_SESSION,null);
				mIntent.putExtras(bundle);
				//
				mContext=con;
				//
				mIntent.putExtra(Extras.EXTRA_ENABLE_INSTANT_APPS,true);
				//
				initializated=true;
		}
		public void setUrl(@NonNull String url){
			setFullUrl("http://"+url);
		}
		public void setFullUrl(@NonNull String fullurl){
				if(initializated){
				uri=fullurl;
				urlload=true;
				mIntent.setData(Uri.parse(uri));
				Log.i("Easy.customTab","set(Full)Url:"+fullurl);
				}else throw new IllegalArgumentException(exceptionClassnotinit);
		}
		public void startCustomTab(){
				if(initializated){
						intent=mIntent;
						if(chooserenabled){
							Intent chooser=Intent.createChooser(intent,chooserName);
							try{
								ContextCompat.startActivity(mContext,chooser,customTabAnimation);
								Log.i("Easy.customTab","startCustomTab()(with Chooser),starting...");
							}catch(Exception e){
								new Easy(mContext).sbar(e.toString());
								Log.e("Easy.customTab","startCustomTab() ERROR: "+e.toString());
							}
						}else{
						try{
                        ContextCompat.startActivity(mContext,intent,customTabAnimation);
						Log.i("Easy.customTab","startCustomTab(),starting...");
						}catch(Exception e){
								new Easy(mContext).sbar(e.toString());
								Log.e("Easy.customTab","startCustomTab() ERROR: "+e.toString());
						}
						}
				}else throw new IllegalArgumentException("startCustomTab() please initialize class customTab(),and setUrl()");
		}

		public void enableChooser(String chooserLogo){
			chooserName=chooserLogo;
			chooserenabled=true;
		}
		public Intent getIntent(){
				return mIntent;
		}
		public void setEnterAnimation(@AnimRes int enterAnimRes,@AnimRes int exitAnimRes){//enterAnimRes for anim of customtab, exitAnimRes for anim of window in our app
            if(initializated){
                customTabAnimation=ActivityOptionsCompat.makeCustomAnimation(mContext,enterAnimRes,exitAnimRes).toBundle();
            }else throw new IllegalArgumentException(exceptionClassnotinit);
        }
		public void setExitAnimation(@AnimRes int enterableViewAnim,@AnimRes int exitableViewAnim){
			if(initializated){
				mIntent.putExtra(Extras.EXTRA_EXIT_ANIMATION_BUNDLE,ActivityOptionsCompat.makeCustomAnimation(mContext,enterableViewAnim,exitableViewAnim).toBundle());
			}else throw new IllegalArgumentException(exceptionClassnotinit);
		}
        public void setPackageIntent(@NonNull String packageName){
			if(initializated){
            mIntent.setPackage(packageName);
			Log.i("Easy.customTab","setPackageIntent( "+packageName+" )");
			}else throw new IllegalArgumentException(exceptionClassnotinit);
        }
		public void setUrlBarHiding(@NonNull boolean hiding) {
			if(initializated){
            mIntent.putExtra(Extras.EXTRA_ENABLE_URLBAR_HIDING, hiding);
			}else throw new IllegalArgumentException(exceptionClassnotinit);
		}
		public void setShowTitle(@NonNull boolean showTitle){
			if(initializated){
			int showing=0;
			if(showTitle) showing=1;
			mIntent.putExtra(Extras.EXTRA_TITLE_VISIBILITY_STATE,showing);
			}else throw new IllegalArgumentException(exceptionClassnotinit);
		}
		public void setToolbarColor(){
			setToolbarColor(new Easy(mContext).getAttrColor(R.attr.colorPrimary));
		}
		public void setToolbarColor(@ColorInt int toolbarColor){
			if(initializated){
			tbColor=toolbarColor;
			mIntent.putExtra(Extras.EXTRA_TOOLBAR_COLOR,tbColor);
			}else throw new IllegalArgumentException(exceptionClassnotinit);
		}
		public void setSecondaryToolbarColor(@ColorInt int secondarycolor1){
			if(initializated)mIntent.putExtra(Extras.EXTRA_SECONDARY_TOOLBAR_COLOR,secondarycolor1);
			else throw new IllegalArgumentException(exceptionClassnotinit);
		}
        public static final class Packages{
            public static final String PACKAGE_VK="com.vkontakte.android";
            public static final String PACKAGE_CHROME="com.android.chrome";
        }
		@TechnicalSupport
		public static final class Extras{
			public static final String EXTRA_SESSION = "android.support.customtabs.extra.SESSION";
			public static final String EXTRA_TOOLBAR_COLOR = "android.support.customtabs.extra.TOOLBAR_COLOR";
			public static final String EXTRA_ENABLE_URLBAR_HIDING = "android.support.customtabs.extra.ENABLE_URLBAR_HIDING";
			public static final String EXTRA_CLOSE_BUTTON_ICON = "android.support.customtabs.extra.CLOSE_BUTTON_ICON";
			public static final String EXTRA_TITLE_VISIBILITY_STATE = "android.support.customtabs.extra.TITLE_VISIBILITY";
			public static final String EXTRA_ACTION_BUTTON_BUNDLE = "android.support.customtabs.extra.ACTION_BUTTON_BUNDLE";
			public static final String EXTRA_TOOLBAR_ITEMS = "android.support.customtabs.extra.TOOLBAR_ITEMS";
			public static final String EXTRA_SECONDARY_TOOLBAR_COLOR = "android.support.customtabs.extra.SECONDARY_TOOLBAR_COLOR";
			public static final String KEY_ICON = "android.support.customtabs.customaction.ICON";
			public static final String KEY_DESCRIPTION = "android.support.customtabs.customaction.DESCRIPTION";
			public static final String KEY_PENDING_INTENT = "android.support.customtabs.customaction.PENDING_INTENT";
			public static final String EXTRA_TINT_ACTION_BUTTON = "android.support.customtabs.extra.TINT_ACTION_BUTTON";
			public static final String EXTRA_MENU_ITEMS = "android.support.customtabs.extra.MENU_ITEMS";
			public static final String KEY_MENU_ITEM_TITLE = "android.support.customtabs.customaction.MENU_ITEM_TITLE";
			public static final String EXTRA_EXIT_ANIMATION_BUNDLE = "android.support.customtabs.extra.EXIT_ANIMATION_BUNDLE";
			public static final String EXTRA_DEFAULT_SHARE_MENU_ITEM = "android.support.customtabs.extra.SHARE_MENU_ITEM";
			public static final String EXTRA_REMOTEVIEWS = "android.support.customtabs.extra.EXTRA_REMOTEVIEWS";
			public static final String EXTRA_REMOTEVIEWS_VIEW_IDS = "android.support.customtabs.extra.EXTRA_REMOTEVIEWS_VIEW_IDS";
			public static final String EXTRA_REMOTEVIEWS_PENDINGINTENT = "android.support.customtabs.extra.EXTRA_REMOTEVIEWS_PENDINGINTENT";
			public static final String EXTRA_REMOTEVIEWS_CLICKED_ID = "android.support.customtabs.extra.EXTRA_REMOTEVIEWS_CLICKED_ID";
			public static final String EXTRA_ENABLE_INSTANT_APPS = "android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS";
		}
}
public static final class notification{
	Context mContext;
	NotificationCompat.Builder b;
	Notification nc;
	Easy mEasy;
	boolean init=false;

	public notification(@NonNull Context c){
		mContext=c;
		init=true;
		b=new NotificationCompat.Builder(mContext);
		mEasy=new Easy(mContext);
	}
	public void startNotification(int id){
		if(init){
			nc=b.build();
			NotificationManagerCompat.from(mContext).notify(id,nc);
		}
	}
	public void startNotification(){
		startNotification(0);
	}
	public void setSmallIcon(@DrawableRes int drawableResId){
		b.setSmallIcon(drawableResId);
	}
	public void setSoundAsset(String pathAssets){
		b.setSound(Easy.getFileAssetUri(pathAssets));
	}
	public void setSoundResource(@RawRes int name){//R.raw.(your file name) is required
		b.setSound(mEasy.getResourceUri(name));
	}
	public void setCategory(String category){
		b.setCategory(category);
	}
	}

	public static final class RecyclerHelper {
		public static interface OnDragListener {
			public abstract boolean onDrag(RecyclerView recycler, RecyclerView.ViewHolder holder1, RecyclerView.ViewHolder holder2, int fromPos, int toPos);
		}
		public static interface OnSwipeListener {
			public abstract void onSwipe(RecyclerView recycler,RecyclerView.ViewHolder holder,int pos,int direction);
		}
		public static interface OnActionStateListener {
			public abstract void onStateChanged(RecyclerView recyclerView,RecyclerView.ViewHolder holder,int actionState);
		}
		public static interface OnChildDrawListener
		{
			public abstract void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder holder, float dx, float dy, boolean isCurrentlyActive);
		}
		public static final class Direction{
			public static final int UP=1;
			public static final int DOWN=2;
			public static final int LEFT=4;
			public static final int RIGHT=8;
		}
		public static final class ActionState{
			public static final int IDLE=0;
			public static final int SWIPE=1;
			public static final int DRAG=2;
		}
		@TechnicalSupport
		private final class holderpos{
			public int pos;
			public RecyclerView.ViewHolder hold;
			public int directions;
			public holderpos(@NonNull RecyclerView.ViewHolder hold,@NonNull int pos,@NonNull int p3){
				this.hold=hold;
				this.pos=pos;
				this.directions=p3;
			}
		}

		OnSwipeListener mSwipeListener;
		OnDragListener mDragListener;
		OnActionStateListener mActionStateListener;
		OnChildDrawListener mChildDrawListener;
		ItemTouchHelper.Callback callback;
		public ItemTouchHelper mTouchHelper;
		RecyclerView mRecyclerView;//      left  right    up   down   left  right
		@TechnicalSupport
		private boolean[] directions=new boolean[]{false,false,  false,false,false,false};

		List<holderpos> unswipingholders=new ArrayList<>();
		boolean unswipeenabled=false;
		List<holderpos> undraggableholders=new ArrayList<>();
		boolean undragenabled=false;

		public RecyclerHelper(@NonNull RecyclerView recycler){
			mRecyclerView=recycler;
		}
		public void setSwipingHorizontal(final boolean left,final boolean right){
			directions[0]=left;
			directions[1]=right;
			/*callback = new ItemTouchHelper.Callback(){
				@Override
				@NonNull
				public int getMovementFlags(RecyclerView p1, RecyclerView.ViewHolder p2)
				{
					int direct = 0;
					if(left&&right)direct=Direction.LEFT|Direction.RIGHT;
					else if(left)direct=Direction.LEFT;
					else if(right)direct=Direction.RIGHT;

					if(unswipeenabled){
						for(int i=0;i<unswipingholders.size();i++){
							if(unswipingholders.get(i).hold==p2&&unswipingholders.get(i).pos==p2.getAdapterPosition()){
								if(unswipingholders.get(i).directions!=0){
									direct &= ~unswipingholders.get(i).directions;
								}
							}
						}
					}

					return makeMovementFlags(0,direct);
				}

				@Override
				public boolean onMove(RecyclerView p1, RecyclerView.ViewHolder p2, RecyclerView.ViewHolder p3)
				{
					return false;
				}
				@Override
				public boolean isLongPressDragEnabled(){
					return false;
				}
				@Override
				public boolean isItemViewSwipeEnabled(){
					return true;
				}
				@Override
				public void onSwiped(RecyclerView.ViewHolder p1, int direction)
				{
					if(mSwipeListener!=null) mSwipeListener.onSwipe(mRecyclerView,p1,p1.getAdapterPosition(),direction);
				}

				@Override
				public void onChildDraw(Canvas canvas,RecyclerView recyclerView,RecyclerView.ViewHolder holder,float dx,float dy,int actionState,boolean isCurrentlyActive){
					Drawable backg = new ColorDrawable(Color.BLUE);
					backg.setBounds(holder.itemView.getRight()+(int)dx,holder.itemView.getTop(),holder.itemView.getRight(),holder.itemView.getBottom());
					backg.draw(canvas);

					Drawable backg2=new ColorDrawable(Color.RED);
					backg2.setBounds(holder.itemView.getLeft(),holder.itemView.getTop(),holder.itemView.getLeft()+(int)dx,holder.itemView.getBottom());
					backg2.draw(canvas);

					super.onChildDraw(canvas,recyclerView,holder,dx,dy,actionState,isCurrentlyActive);
					//if(mActionStateListener!=null)mActionStateListener.onStateChanged(recyclerView,holder,dx,dy,actionState);
				}
			};*/
		}

		public void setDragging(final boolean up,final boolean down,final boolean left,final boolean right){
			directions[2]=up;
			directions[3]=down;
			directions[4]=left;
			directions[5]=right;
		}
		public void addUnswipingHolder(@NonNull RecyclerView.ViewHolder holderUnSwipe,@NonNull int pos){
			addUnswipingHolder(holderUnSwipe,pos,Direction.UP|Direction.DOWN|Direction.LEFT|Direction.RIGHT);
		}
		public void addUnswipingHolder(@NonNull RecyclerView.ViewHolder holderUnswipe,@NonNull int pos,@NonNull int directions){
			unswipingholders.add(new holderpos(holderUnswipe,pos,directions));
			unswipeenabled=true;
		}
		public void addUnDraggableHolder(@NonNull RecyclerView.ViewHolder holderUnDrag,@NonNull int pos){
			addUnDraggableHolder(holderUnDrag,pos,Direction.UP|Direction.DOWN|Direction.LEFT|Direction.RIGHT);
		}
		public void addUnDraggableHolder(@NonNull RecyclerView.ViewHolder holderUnDrag,@NonNull int pos,@NonNull int directions){
			undraggableholders.add(new holderpos(holderUnDrag,pos,directions));
			undragenabled=true;
		}
		public void initialize(){
			callback = new ItemTouchHelper.Callback(){
				@Override
				@NonNull
				public int getMovementFlags(@Nullable RecyclerView p1, @NonNull RecyclerView.ViewHolder p2){
					int dirsSwipe=0;
					if(directions[0])dirsSwipe |=Direction.LEFT;
					if(directions[1])dirsSwipe |=Direction.RIGHT;

					if(unswipeenabled){
						for(int i=0;i<unswipingholders.size();i++){
							if(unswipingholders.get(i).hold==p2&&unswipingholders.get(i).pos==p2.getAdapterPosition()){
								if(unswipingholders.get(i).directions!=0){
									dirsSwipe &= ~unswipingholders.get(i).directions;
								}
							}
						}
					}
					int dirsDrag=0;
					if(directions[2])dirsDrag |=Direction.UP;
					if(directions[3])dirsDrag |=Direction.DOWN;
					if(directions[4])dirsDrag |=Direction.LEFT;
					if(directions[5])dirsDrag |=Direction.RIGHT;

					if(undragenabled){
						for(int i=0;i<undraggableholders.size();i++){
							if(undraggableholders.get(i).hold==p2&&undraggableholders.get(i).pos==p2.getAdapterPosition()){
								if(undraggableholders.get(i).directions!=0){
									dirsDrag &= ~undraggableholders.get(i).directions;
								}
							}
						}
					}
					return makeMovementFlags(dirsDrag,dirsSwipe);
				}
				@Override
				@NonNull
				public boolean isLongPressDragEnabled(){
					return directions[2]||directions[3]||directions[4]||directions[5];
				}
				@Override
				@NonNull
				public boolean isItemViewSwipeEnabled(){
					return directions[0]||directions[1];
				}
				@Override
				@NonNull
				public boolean onMove(@NonNull RecyclerView p1, @NonNull RecyclerView.ViewHolder p2, @NonNull RecyclerView.ViewHolder p3){
					if(mDragListener!=null)return mDragListener.onDrag(p1,p2,p3,p2.getAdapterPosition(),p3.getAdapterPosition());
					return true;
				}
				@Override
				public void onSwiped(@NonNull RecyclerView.ViewHolder p1, @NonNull int p2)//p2 (direction (left||right))
				{
					if(mSwipeListener!=null)mSwipeListener.onSwipe(mRecyclerView,p1,p1.getAdapterPosition(),p2);
				}
				@Override
				public void onChildDraw(@NonNull Canvas canvas,@NonNull RecyclerView recyclerView,@NonNull RecyclerView.ViewHolder holder,@NonNull float dx,@NonNull float dy,@NonNull int actionState,@Nullable boolean isCurrentlyActive){
					if(mChildDrawListener!=null)mChildDrawListener.onChildDraw(canvas,recyclerView,holder,dx,dy,isCurrentlyActive);
					super.onChildDraw(canvas,recyclerView,holder,dx,dy,actionState,isCurrentlyActive);
					if(mActionStateListener!=null)mActionStateListener.onStateChanged(recyclerView,holder,actionState);
				}
			};
		}
		public void setup(){
			initialize();
			mTouchHelper=new ItemTouchHelper(callback);
			mTouchHelper.attachToRecyclerView(mRecyclerView);
		}
		public void setOnDragListener(OnDragListener odl){
			mDragListener=odl;
		}
		public void setOnSwipeListener(OnSwipeListener osl){
			mSwipeListener=osl;
		}
		public void setOnActionStateChangedListener(OnActionStateListener oasl){
			mActionStateListener=oasl;
		}
	}

	public static final class ArrayRecyclerViewAdapter<T extends java.lang.Object> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
	{
	Context mContext;
	int resourceId;
	int textViewId;
	List<T> objects;
		public ArrayRecyclerViewAdapter(@NonNull Context context,@NonNull int resourceId,@NonNull int textViewId,@NonNull List<T> objects){
			mContext=context;
			this.resourceId=resourceId;
			this.textViewId=textViewId;
			this.objects=objects;
		}
		@Override
		@NonNull
		public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup p1, @Nullable int p2)
		{
			View v=LayoutInflater.from(mContext).inflate(resourceId,p1,false);
			return new ArrayRecyclerViewViewHolder(v,textViewId);
		}

		@Override
		public void onBindViewHolder(@NonNull RecyclerView.ViewHolder p1, @NonNull int p2)
		{
			ArrayRecyclerViewViewHolder holder=(ArrayRecyclerViewAdapter.ArrayRecyclerViewViewHolder) p1;
			holder.textView.setText((String)objects.get(p2));
		}

		@Override
		@NonNull
		public int getItemCount()
		{
			return objects.size();
		}
		public static final class ArrayRecyclerViewViewHolder extends RecyclerView.ViewHolder{
			public TextView textView;
			ArrayRecyclerViewViewHolder(@NonNull View itemView,@NonNull int textViewId){
				super(itemView);
				textView=(TextView)itemView.findViewById(textViewId);
			}
		}
		public void getItems(){

		}
	}

public static final class Orientation{
//screen orientation
public static final int PORTRAIT=1;
public static final int LANDSCAPE=2;
@java.lang.Deprecated()
public static final int SQUARE=3;
public static final int UNDEFINED=0;
//layout orientation
public static final int HORIZONTAL=0;
public static final int VERTICAL=1;
}

public static final class T{
public static final int tlong=Toast.LENGTH_LONG;
public static final int tshort=Toast.LENGTH_SHORT;
}
public static final class S{
public static final int sblong = Snackbar.LENGTH_LONG;
public static final int sbshort = Snackbar.LENGTH_SHORT;
public static final int sbindefinite = Snackbar.LENGTH_INDEFINITE;
}

public static @interface TechnicalSupport {
//public abstract int value();
}
}
