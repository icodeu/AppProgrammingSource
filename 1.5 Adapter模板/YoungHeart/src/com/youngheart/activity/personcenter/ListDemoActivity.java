package com.youngheart.activity.personcenter;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.youngheart.R;
import com.youngheart.adapter.CinemaAdapter;
import com.youngheart.base.AppBaseActivity;
import com.youngheart.entity.CinemaBean;

public class ListDemoActivity extends AppBaseActivity {
	ListView lvCinemaList;
	ArrayList<CinemaBean> cinemaList;
	
	@Override
	protected void initVariables() {
		cinemaList = new ArrayList<CinemaBean>();
		CinemaBean cinema1 = new CinemaBean();
		cinema1.setCinemaId("1");
		cinema1.setCinemaName("星美");
		CinemaBean cinema2 = new CinemaBean();
		cinema2.setCinemaId("2");
		cinema2.setCinemaName("万达");
		
		cinemaList.add(cinema1);
		cinemaList.add(cinema2);
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		setContentView(R.layout.activity_listdemo);		

		lvCinemaList = (ListView) findViewById(R.id.lvCinemalist);
		
		CinemaAdapter adapter = new CinemaAdapter(
				cinemaList, ListDemoActivity.this);
		lvCinemaList.setAdapter(adapter);
		lvCinemaList.setOnItemClickListener(
				new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						//do something
					}
				});
	}

	@Override
	protected void loadData() {

	}
}