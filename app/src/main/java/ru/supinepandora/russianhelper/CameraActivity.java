package ru.supinepandora.russianhelper;
import androidx.annotation.*;
import androidx.appcompat.app.*;
import androidx.appcompat.widget.*;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.os.*;
import android.hardware.*;
import android.view.*;
import com.bumptech.glide.*;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.util.*;
import java.io.*;
import android.content.*;
import java.util.*;
import android.graphics.SurfaceTexture;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Matrix;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.content.res.Resources.*;
import android.database.*;
import android.content.res.*;
import android.widget.*;

public class CameraActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,Camera.PreviewCallback
{

Easy ea;
Camera cam;
Camera.Parameters params;
SurfaceView cameraView;
SurfaceHolder mHolder;
CameraPreview mPreview;
int cameraId=0;
View sheetv;
BottomSheetBehavior sheet;
FloatingActionButton fabset;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camerl);
		ea=new Easy(this);
		//addContentView(getLayoutInflater().inflate(R.layout.camerl,null), mPreview.getLayoutParams());
		mPreview=(CameraPreview)findViewById(R.id.camerlPreview1);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//((ToggleButton)ea.findViewById(R.id.camerlToggleButton1)).setOnCheckedChangeListener(this);
		//((ToggleButton)ea.findViewById(R.id.camerlToggleButton2)).setOnCheckedChangeListener(this);

		Camera.CameraInfo info=new Camera.CameraInfo();
		for(int i=0;i<Camera.getNumberOfCameras();i++){
			Camera.getCameraInfo(i,info);
			if(info.facing==Camera.CameraInfo.CAMERA_FACING_BACK)cameraId=i;
		}
		Log.i("onCreated","activity created");
		initSheet();
	}
	private void initSheet(){
		CoordinatorLayout coordl=ea.findViewById(R.id.camerlCoordinatorLayout1);
		ea.setRootView(coordl);
		sheetv=coordl.findViewById(R.id.camerlNestedScrollView1);
		sheet=BottomSheetBehavior.from(sheetv);
		sheet.setSkipCollapsed(true);
		sheet.setState(BottomSheetBehavior.STATE_HIDDEN);
		fabset=ea.findViewById(R.id.camerlFloatingActionButton1);
		sheet.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback(){
				@Override
				public void onStateChanged(View p1, int p2)
				{
					switch(p2){
						case BottomSheetBehavior.STATE_EXPANDED:
							fabset.setImageResource(R.drawable.v_close);
							Log.i("state","expanded");
							break;
						case BottomSheetBehavior.STATE_COLLAPSED:
							sheet.setState(BottomSheetBehavior.STATE_HIDDEN);
							fabset.setImageResource(R.drawable.v_settings_primary);
							Log.i("state","collapsed");
							break;
						case BottomSheetBehavior.STATE_HIDDEN:
						//case BottomSheetBehavior.STATE_COLLAPSED:
							fabset.setImageResource(R.drawable.v_settings_primary);
							Log.i("state","hidden");
							break;
					}

				}
				@Override public void onSlide(View p1, float p2){}
			});
		fabset.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View p1){
					if(sheet.getState()==BottomSheetBehavior.STATE_EXPANDED){
						sheet.setState(BottomSheetBehavior.STATE_HIDDEN);
						fabset.setImageResource(R.drawable.v_settings_primary);
					}
					if(sheet.getState()==BottomSheetBehavior.STATE_HIDDEN||sheet.getState()==BottomSheetBehavior.STATE_COLLAPSED){
						sheet.setState(BottomSheetBehavior.STATE_EXPANDED);
						fabset.setImageResource(R.drawable.v_close);
					}
				}
			});
	}
	private void initSettings(){
		SwitchCompat camSw=ea.findViewById(R.id.camerlSwitchCompat1);
		camSw.setOnCheckedChangeListener(this);
		camSw.setEnabled(Camera.getNumberOfCameras()>1 ? true : false);
		camSw=null;
		((SwitchCompat)ea.findViewById(R.id.camerlSwitchCompat2)).setOnCheckedChangeListener(this);

		final List<String> effects=cam.getParameters().getSupportedColorEffects();
		AppCompatSpinner spinEffect=spin(R.id.camerlAppCompatSpinner1,effects,cam.getParameters().getColorEffect());
		spinEffect.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener(){
				@Override
				public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4)
				{
					Camera.Parameters parms=cam.getParameters();
					parms.setColorEffect(effects.get(p3));
					cam.setParameters(parms);
					mPreview.setCamera(cam);
				}

				@Override
				public void onNothingSelected(AdapterView<?> p1){}
			});
	}
	private AppCompatSpinner spin(int id,List<String> data,String curr){
		AppCompatSpinner spinner=ea.findViewById(id);
		android.widget.ArrayAdapter adapter=new android.widget.ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,data);
		adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		for(int i=0;i<data.size();i++){
			if(data.get(i).equals(curr)){
				spinner.setSelection(i);
			}
		}
		return spinner;
	}
	@Override
	protected void onResume()
	{
		super.onResume();
		cam=Camera.open(cameraId);
		mPreview.setCamera(cam);
		initSettings();
		Log.i("onResume","resumed");
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		if(cam!=null){
			cam.release();
			cam=null;
		}
		Log.i("onPause","paused");
	}


	@Override
	public void onCheckedChanged(CompoundButton p1, boolean p2)
	{
		params=mPreview.getCamera().getParameters();
		switch(p1.getId()){
			case R.id.camerlSwitchCompat1://camera
				if(cam.getNumberOfCameras()>1){
					cameraId=p2 ? 1:0;
					cam=Camera.open(cameraId);
					mPreview.setCamera(cam);
				}else p1.setChecked(false);
			break;
			case R.id.camerlSwitchCompat2://flash
				if(p2){//on
					if(params.getSupportedFlashModes().contains(Camera.Parameters.FLASH_MODE_TORCH)){
						params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
					}else if(params.getSupportedFlashModes().contains(Camera.Parameters.FLASH_MODE_ON)){
						params.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
					}else ea.sbar("ERROR: FlashMode unknown");
				}else{//off
					params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
				}
			break;
		}
		mPreview.getCamera().setParameters(params);
		mPreview.getCamera().setPreviewCallback(this);
		try{
			//cam.setPreviewDisplay(mHolder);
			//cam.startPreview();
			//mPreview.setCamera(cam);
		}catch(Exception e){e.printStackTrace();ea.sbar(""+e);}

	}
	@Override
	public void onPreviewFrame(@NonNull byte[] p1, @Nullable Camera p2){
		//Glide.with(this).load(p1).into(cameraView);
		Log.i("onPreviewFrame","min: "+p2.getParameters().getSupportedPreviewFpsRange().get(0)[0]+" , max: "+p2.getParameters().getSupportedPreviewFpsRange().get(0)[1]);

		//ImageView imgv=ea.findViewById(R.id.camerlImageView2);
		//Glide.with(this).load(p1).into(imgv);
	}


	public static class Preview extends ViewGroup implements SurfaceHolder.Callback
	{
	SurfaceView mSurface;
	SurfaceHolder mHolder;
	Camera mCamera;
	List<Camera.Size> mSupportedPreviewSize;
	Camera.Size mPreviewSize;
	AppCompatActivity mact;
		public Preview(Context c){
			this(c,null);
		}
		public Preview(Context c,AttributeSet as){
			this(c,as,0);
		}
		public Preview(Context c,AttributeSet as,int defStyleAttr){
			super(c,as,defStyleAttr);
			mact=(AppCompatActivity) c;
			mSurface=new SurfaceView(c);
			//mSurface=new SurfaceView(c);
			addView(mSurface);
			setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
			mHolder=mSurface.getHolder();
			if(mHolder==null)Log.w("Preview(Context)","mHolder is null");
			mHolder.addCallback(this);
			if(Build.VERSION.SDK_INT<=11)mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
			Log.i("Preview","Preview is initializated");
		}
		public Camera getCamera(){
			return mCamera;
		}
		public void setCamera(Camera camera){
			if(mCamera==camera)return;

			stopPreviewAndFreeCamera();

			mCamera=camera;
			if(mCamera!=null){
				List<Camera.Size> localSizes=mCamera.getParameters().getSupportedPreviewSizes();
				mSupportedPreviewSize=localSizes;
				requestLayout();
				try{
					mCamera.setPreviewTexture(new SurfaceTexture(0));
					mCamera.setPreviewDisplay(mHolder);

				}catch(IOException e){e.printStackTrace();}
				//mCamera.startPreview();
			}
		}
		public void stopPreviewAndFreeCamera(){
			if(mCamera!=null){
				mCamera.stopFaceDetection();
				mCamera.stopPreview();
				mCamera.release();
				mCamera=null;
			}
		}
		/*@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
		{
			int width=resolveSize(getSuggestedMinimumWidth(),widthMeasureSpec);
			int height=resolveSize(getSuggestedMinimumHeight(),heightMeasureSpec);
			setMeasuredDimension(width,height);

			if(mSupportedPreviewSize!=null){
				final double aspecttolerance=0.1;
				double targetRatio=(double)width/height;
				if(mSupportedPreviewSize==null)return;
				Camera.Size optimalSize=null;
				double minDiff=Double.MAX_VALUE;

				for(Camera.Size size:mSupportedPreviewSize){
					double ratio=(double)size.width/size.height;
					if(Math.abs(ratio-targetRatio)>aspecttolerance)continue;
					if(Math.abs(size.height-height)<minDiff){
						optimalSize=size;
						minDiff=Math.abs(size.height-height);
					}
				}
				if(optimalSize==null){
					minDiff=Double.MAX_VALUE;
					for(Camera.Size size:mSupportedPreviewSize){
						if (Math.abs(size.height - height) < minDiff) {
							optimalSize = size;
							minDiff = Math.abs(size.height - height);
						}
					}
				}
				mPreviewSize=optimalSize;
			}
		}*/

		@Override
		public void surfaceCreated(SurfaceHolder p1)
		{
			Log.i("surfaceCreated","creating...");
			if(mCamera!=null){
				try
				{
					if(p1==mHolder)Log.i("surfaceCreated","p1 is mHolder");
					//mCamera.setPreviewTexture(new SurfaceTexture(0));

					mCamera.setPreviewDisplay(p1);
				}
				catch (IOException e)
				{e.printStackTrace();}
			}else Log.w("surfaceCreated","mCamera is null");
		}

		@Override
		public void surfaceChanged(SurfaceHolder p1, int p2, int p3, int p4)
		{
			Log.i("surfaceChanged","changing...");
			if(p1==mHolder)Log.i("surfaceChanged","p1 is mHolder");
			if(mHolder.getSurface()==null)Log.i("surfaceChanged","mHolder Surface is null");
			if(mCamera!=null){
				try{
					//mCamera.stopPreview();
					//mCamera.setPreviewDisplay(mHolder);
				}catch(Exception e){e.printStackTrace();}
				Camera.Parameters params=mCamera.getParameters();
				//params.setPreviewSize(mPreviewSize.width,mPreviewSize.height);
				//params.setPreviewFpsRange(330,1000);//66 68
				//params.setPreviewFrameRate(2); don't work
				//params.setSceneMode(Camera.Parameters.SCENE_MODE_ACTION);
				requestLayout();
				setCameraSize(params);
				setOrientation(mCamera);
				mCamera.setParameters(params);
				mCamera.startPreview();
				//mCamera.setDisplayOrientation(90);
				if(params.getMaxNumDetectedFaces()>0)mCamera.startFaceDetection();
				else Log.i("surfaceChanged","Camera not support FaceDetection");
			}else Log.w("surfaceChanged","mCamera is null");
		}
		private void setCameraSize(Camera.Parameters params){
			boolean fullScreen=false;
			Display display=mact.getWindowManager().getDefaultDisplay();
			boolean widthIsMax=display.getWidth()>display.getHeight();
			Camera.Size size=params.getPreviewSize();
			RectF rectDisplay=new RectF();
			RectF rectPreview=new RectF();

			rectDisplay.set(0,0,display.getWidth(),display.getHeight());

			if(widthIsMax){
				rectPreview.set(0,0,size.width,size.height);
			}else{
				rectPreview.set(0,0,size.height,size.width);
			}
			Matrix matrix=new Matrix();
			if(!fullScreen){
				matrix.setRectToRect(rectPreview,rectDisplay,Matrix.ScaleToFit.START);
			}else{
				matrix.setRectToRect(rectDisplay,rectPreview,Matrix.ScaleToFit.START);
			}
			matrix.mapRect(rectPreview);

			mSurface.getLayoutParams().height=(int)rectPreview.bottom;

			mSurface.getLayoutParams().width=(int)rectPreview.right;
		}
		private void setOrientation(Camera cam){
			int rotation=mact.getWindowManager().getDefaultDisplay().getRotation();
			int degrees=0;
			switch(rotation){
				case Surface.ROTATION_90:
					degrees=90;
					break;
				case Surface.ROTATION_180:
					degrees=180;
					break;
				case Surface.ROTATION_270:
					degrees=270;
					break;
			}
			int result=0;
			Camera.CameraInfo info=new Camera.CameraInfo();
			Camera.getCameraInfo(0,info);
			if(info.facing==info.CAMERA_FACING_BACK){
				result=((360-degrees)+info.orientation);
			}else{
				result=((360-degrees)-info.orientation);
				result+=360;
			}
			result=result%360;
			cam.setDisplayOrientation(result);
		}
		@Override
		public void surfaceDestroyed(SurfaceHolder p1)
		{
			Log.i("surfaceDestoyed","destoying...");
			if(mCamera!=null){
				//mCamera.stopPreview();
			}
		}

		@Override
		protected void onLayout(boolean changed, int l, int t, int r, int b)
		{
			if (changed && getChildCount() > 0) {
				final View child = getChildAt(0);

				final int width = r - l;
				final int height = b - t;

				int previewWidth = width;
				int previewHeight = height;
				if (mPreviewSize != null) {
					previewWidth = mPreviewSize.width;
					previewHeight = mPreviewSize.height;
				}

				// Center the child SurfaceView within the parent.
				if (width * previewHeight > height * previewWidth) {
					final int scaledChildWidth = previewWidth * height / previewHeight;
					child.layout((width - scaledChildWidth) / 2, 0,
								 (width + scaledChildWidth) / 2, height);
				} else {
					final int scaledChildHeight = previewHeight * width / previewWidth;
					child.layout(0, (height - scaledChildHeight) / 2,
								 width, (height + scaledChildHeight) / 2);
				}
			}
		}


	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		return super.onTouchEvent(event);
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
