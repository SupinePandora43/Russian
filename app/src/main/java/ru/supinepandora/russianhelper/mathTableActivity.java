package ru.supinepandora.russianhelper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.content.Context;
import com.evrencoskun.tableview.adapter.recyclerview.holder.*;
import com.evrencoskun.tableview.adapter.*;
import android.view.*;
import android.widget.*;
import com.evrencoskun.tableview.sort.*;
import com.evrencoskun.tableview.filter.*;
import com.evrencoskun.tableview.*;
import java.util.*;
import android.util.*;

public class mathTableActivity extends AppCompatActivity
{
Easy ea;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mattable);
		ea=new Easy(this);
		setSupportActionBar((Toolbar) ea.findViewById(R.id.toolbar));
		MainActivity.setToolbarMode(this);
		MainActivity.setHome(this);
		TableView tv=ea.findViewById(R.id.mattableTableView1);
		MyAdapter a=new MyAdapter(this);
		tv.setAdapter(a);
		List<RowHeader> hs=new ArrayList<>();
		List<ColumnHeader> cs=new ArrayList<>();
		List<List<Cell>> cl=new ArrayList<>();
		for(int i=2;i<=9;i++){
			hs.add(new RowHeader(""+i,"row "+i));
			cs.add(new ColumnHeader(""+i,"column: "+i));
			List<Cell> t=new ArrayList<>();
			for(int j=2;j<=9;j++){
				t.add(new Cell(j+"-"+i,"cell "+j+" "+i));
			}
			cl.add(t);
			t=null;
		}
		a.setAllItems(cs,hs,cl);
	}
	class MyAdapter extends AbstractTableAdapter<ColumnHeader,RowHeader,Cell>
	{
Context context;
		@Override
		public int getColumnHeaderItemViewType(int p1)
		{
			// TODO: Implement this method
			return 0;
		}

		@Override
		public int getRowHeaderItemViewType(int p1)
		{
			// TODO: Implement this method
			return 0;
		}

		@Override
		public int getCellItemViewType(int p1)
		{
			// TODO: Implement this method
			return 0;
		}

		@Override
		public AbstractViewHolder onCreateCellViewHolder(ViewGroup p1, int p2)
		{
			MyCellHolder h=new MyCellHolder(LayoutInflater.from(context).inflate(R.layout.table_holder,null,false));
			return h;
		}

		@Override
		public void onBindCellViewHolder(AbstractViewHolder p1, Object p2, int p3, int p4)
		{
			//Cell c= (Cell)p2;
			MyCellHolder p1a=(MyCellHolder)p1;
			p3++;p4++;p3++;p4++;
			p1a.text.setText(""+(p3*p4));

		}

		@Override
		public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup p1, int p2)
		{
			MyCellHolder h=new MyCellHolder(LayoutInflater.from(context).inflate(R.layout.table_holder,null,false));
			return h;
		}

		@Override
		public void onBindColumnHeaderViewHolder(AbstractViewHolder p1, Object p2, int p3)
		{
			MyCellHolder p1a=(MyCellHolder)p1;
			p3+=2;
			p1a.text.setText(""+p3);
			Log.e("math","onBindColumn p3: "+p3+", p2: "+p2+", p1: "+p1);
		}

		@Override
		public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup p1, int p2)
		{
			MyCellHolder h=new MyCellHolder(LayoutInflater.from(context).inflate(R.layout.table_holder,null,false));
			return h;
		}

		@Override
		public void onBindRowHeaderViewHolder(AbstractViewHolder p1, Object p2, int p3)
		{

			try{
				MyCellHolder p1a=(MyCellHolder)p1;
			    p1a.text.setText(""+(p3+2));
				Log.e("math","onBindRow p3: "+p3+", p2: "+p2+", p1: "+p1);
			}catch(Exception e){
				ea.sbar(""+e);
				Log.e("math",""+e);
			}
		}

		@Override
		public View onCreateCornerView()
		{
			View cor=LayoutInflater.from(context).inflate(R.layout.table_conrer,null,false);
			return cor;
		}

		public MyAdapter(Context c){
			super(c);
			context=c;
		}

	}
	class ColumnHeader extends Cell{
			public ColumnHeader(String id){
				super(id);
			}
			public ColumnHeader(String id,String data){
				super(id,data);
			}
		}
		class MyCellHolder extends AbstractViewHolder{
			public TextView text;
			public MyCellHolder(View itemView){
				super(itemView);
				text=(TextView)itemView.findViewById(R.id.table_tv);
			}
		}
		class Cell implements ISortableModel,IFilterableModel{
			String mId;
			Object mData;
			String mFilterKeyword;
			public Cell(String id){
				this.mId=id;
			}
			public Cell(String id,Object data){
				this.mId=id;
				this.mData=data;
				this.mFilterKeyword=String.valueOf(data);
			}
			@Override
			public String getId()
			{
				return mId;
			}
			@Override
			public Object getContent()
			{
				// TODO: Implement this method
				return mData;
			}
			public Object getData(){
				return mData;
			}
			public void setData(Object data){mData=data;}
			public String getFilterKeyword(){
				return mFilterKeyword;
			}

			@Override
			public String getFilterableKeyword()
			{
				// TODO: Implement this method
				return mFilterKeyword;
			}
		}
		class RowHeader extends Cell{
			public RowHeader(String id){
				super(id);
			}
			public RowHeader(String id,String data){
				super(id,data);
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
