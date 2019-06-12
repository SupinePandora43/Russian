package ru.supinepandora.russianhelper.view;

import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;
import java.util.*;
import ru.supinepandora.russianhelper.*;

public class PaintMyView extends View 
{
	Paint mPaint;
	Path mPath;
	Paint mBitmapPaint;
	public Bitmap mBitmap;
	Canvas mCanvas;
	public boolean fmode;
	public boolean colpm;
	public PaintMyView(Context c){
		this(c,null);
	}
	public PaintMyView(Context c,AttributeSet as){
		this(c,as,0);
	}
	public PaintMyView(Context c,AttributeSet as,int defStyleAttr){
		super(c,as,defStyleAttr);
		mPath=new Path();
		mBitmapPaint=new Paint(Paint.DITHER_FLAG);

		mPaint = new Paint();
		//mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(0xFFFF0000);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(12);

	}
	public void setColor(int color){
		mPaint.setColor(color);
	}
	public Paint getPaint(){
		return mPaint;
	}
	private void fill(float xf,float yf){
		int xi=(int) xf,yi=(int) yf;
		int w=mBitmap.getWidth(),h=mBitmap.getHeight();
		int color2=mBitmap.getPixel(xi,yi);
		if(mPaint.getColor()==color2){return;}
		
		ArrayList<int[]> stack=new ArrayList<>();
		ArrayList<int[]> stackTemp=new ArrayList<>();
		stackTemp.add(new int[]{xi,yi});
		stack.addAll(stackTemp);
		
		while(!stackTemp.isEmpty()){
			stack.clear();
			stack.addAll(stackTemp);
			stackTemp.clear();
			
			for(int[] p : stack){
				/*int[] p1=p;
				while(p1[0] < w){//horizontal
					while(p1[0] >0&&mBitmap.getPixel(p1[0],p[1])==color2){//left
					    mBitmap.setPixel(p1[0],p[1],mPaint.getColor());
						p1[0] =p1[0]- 1;
						
					}
				}*/
				boolean flagdown=true;
				boolean flagup=true;
				while(p[0]>0&&mBitmap.getPixel(p[0],p[1])==color2){
					p[0]--;
				}
				//p[0]++;
				while(p[0]<w&&mBitmap.getPixel(p[0],p[1])==color2){
					mBitmap.setPixel(p[0],p[1],mPaint.getColor());
					
					if(flagdown&&p[1]<h-1&&mBitmap.getPixel(p[0],p[1]+1)==color2){
						stackTemp.add(new int[]{p[0],p[1]+1});
						flagdown=false;
					}else if(!flagdown&&p[1]<h&&mBitmap.getPixel(p[0],p[1]+1)!=color2){
						flagdown=true;
					}
					if(flagup&&p[1]>0&&mBitmap.getPixel(p[0],p[1]-1)==color2){
						stackTemp.add(new int[]{p[0],p[1]-1});
					}else if(!flagup&&p[1]>0&&mBitmap.getPixel(p[0],p[1]-1)!=color2){
						flagup=true;
					}
					p[0]++;
				}
			}
		}
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);
		mBitmap=Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_4444);
		mCanvas=new Canvas(mBitmap);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		canvas.drawColor(Color.TRANSPARENT);
		canvas.drawBitmap(mBitmap,0,0,mBitmapPaint);
		canvas.drawPath(mPath,mPaint);
	}
	float mX,mY;
	void touchStart(float x,float y){
		if(colpm){
			int xi=(int)x,yi=(int)y;
			((PaintActivity)getContext()).coloriz(mBitmap.getPixel(xi,yi));
			colpm=false;
		}
		else
		mPath.reset();
		mPath.moveTo(x,y);
		mX=x;
		mY=y;
	}
	void touchMove(float x,float y){
		float dx=Math.abs(x-mX),dy=Math.abs(y-mY);
		if(dx >=(float)4 ||dy >= (float)4){
			mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
			mX = x;
			mY = y;
		}
	}
	void touch_up() {
		mPath.lineTo(mX, mY);
		mCanvas.drawPath(mPath, mPaint);
		mPath.reset();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		float x=event.getX(),y=event.getY();
		switch(event.getAction()){
			case MotionEvent.ACTION_DOWN:
				if(fmode)fill(x,y);else
				touchStart(x,y);
				invalidate();
				break;
			case MotionEvent.ACTION_MOVE:
				if(!fmode)
				touchMove(x,y);
				invalidate();
				break;
			case MotionEvent.ACTION_UP:
				if(!fmode)
				touch_up();
				invalidate();
				break;
		}

		return true;
	}

}
