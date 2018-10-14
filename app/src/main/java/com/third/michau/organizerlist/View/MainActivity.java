package com.third.michau.organizerlist.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.third.michau.organizerlist.Model.Task;
import com.third.michau.organizerlist.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	protected ArrayList<Task> taskList;
	private final int REQUEST_CODE = 1;
	private ListAdapter listAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		taskList = getTaskMockup();
		listAdapter = new ListAdapter(taskList,this);
		ListView modelList = findViewById(R.id.lvModelList);
		modelList.setAdapter(listAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_add) {
			Intent intent = new Intent(getBaseContext(), AddTask.class);
			intent.putParcelableArrayListExtra("List", taskList);
			startActivityForResult(intent, REQUEST_CODE);
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
			// Extract name value from result extras
			taskList = data.getExtras().getParcelableArrayList("List");
			listAdapter.getData().clear();
			listAdapter.getData().addAll(taskList);
			listAdapter.notifyDataSetChanged();
			Toast.makeText(this, "new Task added", Toast.LENGTH_SHORT).show();

		}
	}
	private ArrayList<Task> getTaskMockup() {
		ArrayList<Task> list = new ArrayList<>();
		list.add(new Task("task1"));
		list.add(new Task("task2"));
		list.add(new Task("task3"));
		list.add(new Task("task4"));
		list.add(new Task("task5"));
		list.add(new Task("task6"));
		list.add(new Task("task7"));
		list.add(new Task("task8"));
		return list;
	}
}
