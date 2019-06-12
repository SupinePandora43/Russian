package ru.supinepandora.russianhelper;
import android.content.*;
import java.util.*;
import android.view.*;
import android.widget.*;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerViewAdapterlin extends RecyclerView.Adapter<MyRecyclerViewAdapterlin.ViewHolder>
{
	private List<String> mData;
	private LayoutInflater mInflater;
	private boolean bln=false;
	
	public MyRecyclerViewAdapterlin(Context c,List<String> dat1){
		this.mInflater=LayoutInflater.from(c);
		this.mData=dat1;
		this.bln=false;
	}
	public MyRecyclerViewAdapterlin(Context c,List<String> data,boolean bn){
		this.mInflater=LayoutInflater.from(c);
		this.mData=data;
		//this.bln=bn;
	}
	
	@Override
	public void onBindViewHolder(MyRecyclerViewAdapterlin.ViewHolder p1, int p2)
	{
		String st1=mData.get(p2);
		p1.tv1.setText(st1);
	}


	@Override
	public ViewHolder onCreateViewHolder(ViewGroup p1, int p2)
	{
		View v1=mInflater.inflate(R.layout.linaykarecycler2,p1,false);
		return new ViewHolder(v1);
	}


	@Override
	public int getItemCount()
	{
		return mData.size();
	}
public class ViewHolder extends RecyclerView.ViewHolder
{
	public  TextView tv1;
	ViewHolder(View itemView)
	{
		super(itemView);
		tv1=(TextView) itemView.findViewById(R.id.linaykarecycler2TextView1);
		tv1.setTextIsSelectable(bln);
	}
	String getItem(int id){
		return mData.get(id);
	}
	
}
}
