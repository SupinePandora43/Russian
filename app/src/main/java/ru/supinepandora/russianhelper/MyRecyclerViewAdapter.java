package ru.supinepandora.russianhelper;
import android.view.*;
import android.widget.*;
import java.util.*;
import android.content.*;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>
{
	private List<String> mData;
	private LayoutInflater mInflater;
	
	
	MyRecyclerViewAdapter(Context c,List<String> data){
		this.mInflater=LayoutInflater.from(c);
		this.mData=data;
	}
	
	@Override
	public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder p1, int p2)
	{
		String st1=mData.get(p2);
		p1.tv1.setText(st1);
	}


	@Override
	public ViewHolder onCreateViewHolder(ViewGroup p1, int p2)
	{
		View v1=mInflater.inflate(R.layout.recyclerhelpfix,p1,false);
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
		tv1=(TextView) itemView.findViewById(R.id.recyclerhelpfixTextView1);
		
	}
	String getItem(int id){
		return mData.get(id);
	}
	
}
}
