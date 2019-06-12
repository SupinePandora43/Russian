package ru.supinepandora.russianhelper;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.*;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.*;
import android.widget.*;
import android.content.*;
import org.json.*;


public class wafersActivity extends AppCompatActivity
{
Easy ea;
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wafers);
		ea=new Easy(this);
		setSupportActionBar((Toolbar) ea.findViewById(R.id.toolbar));
		MainActivity.setToolbarMode(getWindow(),this);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		wafersRecyclerAdapter wrva=new wafersRecyclerAdapter(this);
		RecyclerView rv=ea.findViewById(R.id.wafersRecyclerView1);
		rv.setAdapter(wrva);
		rv.setLayoutManager(new LinearLayoutManager(this));
		rv.addItemDecoration(new DividerItemDecoration(this,Easy.Orientation.VERTICAL));
	}
	@RequiresApi(5)
	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		overridePendingTransition(R.anim.activitymanimenter1,R.anim.activitymanimexit1);
	}
	@RequiresApi(5)
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
	private class wafersRecyclerAdapter extends RecyclerView.Adapter<wafersRecyclerAdapter.Holder>
	{
	Context mContext;
	JSONArray recipeList;
		public wafersRecyclerAdapter(@NonNull Context context){
			mContext=context;
			try{
				recipeList=new JSONObject(new Easy(mContext).readAssetFile("jsontestmy.json")).getJSONArray("wafers");
			}catch(Exception e){}
		}
		@Override
		public wafersActivity.wafersRecyclerAdapter.Holder onCreateViewHolder(ViewGroup p1,@Nullable int p2)
		{
			View v=LayoutInflater.from(mContext).inflate(R.layout.wafersrecycler,p1,false);
			Holder holder=new Holder(v);
			return holder;
		}
		@Override
		public void onBindViewHolder(wafersActivity.wafersRecyclerAdapter.Holder p1, int p2)
		{try{
			String waferName=recipeList.getJSONObject(p2).getString("name");
			String waferIngredients=recipeList.getJSONObject(p2).getString("ingredients");
			String waferRecipe=recipeList.getJSONObject(p2).getString("recipe");
			String waferCount=""+recipeList.getJSONObject(p2).getInt("count");

			p1.TextViewName.setText(waferName);
			p1.TextViewIngredients.setText(waferIngredients);
			p1.TextViewRecipe.setText(waferRecipe);
			p1.TextViewCount.setText(waferCount);
		}catch(@Nullable Exception e){}
		}
		@Override
		public int getItemCount()
		{
			return recipeList.length();
		}

		private class Holder extends RecyclerView.ViewHolder{
			public TextView TextViewName;
			public TextView TextViewIngredients;
			public TextView TextViewRecipe;
			public TextView TextViewCount;

			public Holder(@NonNull View itemView){
				super(itemView);

				TextViewName=fiText(R.id.wafersrecyclerTextView1);
				TextViewIngredients=fiText(R.id.wafersrecyclerTextView2);
				TextViewRecipe=fiText(R.id.wafersrecyclerTextView3);
				TextViewCount=fiText(R.id.wafersrecyclerTextView4);
			}
			private TextView fiText(@IdRes int id){
				return (TextView)itemView.findViewById(id);
			}
		}
	}
}
