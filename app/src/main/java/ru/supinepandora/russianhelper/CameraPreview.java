package ru.supinepandora.russianhelper;
import android.content.*;
import android.graphics.*;
import android.hardware.*;
import android.os.*;
import androidx.appcompat.app.*;
import android.util.*;
import android.view.*;
import java.io.*;
import java.util.*;

import android.hardware.Camera;
import android.widget.*;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback
{
SurfaceHolder mHolder;
Camera mCamera;
List<Camera.Size> mSupportedPreviewSize;
Camera.Size mPreviewSize;
	AppCompatActivity mact;
	public CameraPreview(Context c){
		this(c,null);
	}
	public CameraPreview(Context c,AttributeSet as){
		this(c,as,0);
	}
	public CameraPreview(Context c,AttributeSet as,int defStyleAttr){
		super(c,as,defStyleAttr);
		mact=(AppCompatActivity) c;
		setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
		//setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
		mHolder=getHolder();
		mHolder.addCallback(this);
		//if(Build.VERSION.SDK_INT<=11)
			mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
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
		if(mCamera!=null){try{
			//mCamera.stopFaceDetection();
			mCamera.stopPreview();
			mCamera.release();
			mCamera=null;}catch(Exception e){e.printStackTrace();}
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder p1)
	{
		Log.i("surfaceCreated","creating...");
		if(mCamera!=null){
			try
			{
				if(p1==mHolder)Log.i("surfaceCreated","p1 is mHolder");
				//mCamera.setPreviewTexture(new SurfaceTexture(0));
				mCamera.setPreviewDisplay(mHolder);
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
		if(!fullScreen){//false
			matrix.setRectToRect(rectPreview,rectDisplay,Matrix.ScaleToFit.START);
		}else{//true
			matrix.setRectToRect(rectDisplay,rectPreview,Matrix.ScaleToFit.START);
		}
		matrix.mapRect(rectPreview);

		getLayoutParams().height=(int)rectPreview.bottom;

		getLayoutParams().width=(int)rectPreview.right;
	}
	private void setOrientation(Camera cam){
		int rotation=mact.getWindowManager().getDefaultDisplay().getRotation();
		int degrees=0;
		switch(rotation){
			case Surface.ROTATION_0:
				degrees=0;
				break;
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
		Log.i("setOrientation","degrees: "+degrees);
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
		if(mact.getResources().getConfiguration().orientation==2){
			//result=0;
		}
		Log.i("setOrientation","result: "+result);
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

	/*@Override
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
	}*/

}
