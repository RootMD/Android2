package com.third.michau.organizerlist.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.third.michau.organizerlist.Model.Task;
import com.third.michau.organizerlist.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	public static final String TAG = "Main Activity";
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
		listAdapter = new ListAdapter(taskList, this);
		ListView modelList = findViewById(R.id.lvModelList);
		modelList.setAdapter(listAdapter);
		Log.e(TAG, "onCreate");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.e(TAG, "onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.e(TAG, "onPause");
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
		if (id == R.id.action_remove) {
			ArrayList<Integer> positions = new ArrayList<>();
			for (Task task : taskList) {
				if (task.getChecked()) {
					positions.add(taskList.indexOf(task));
				}
			}
			for(int i=0; i<positions.size(); i++) {
				int position = positions.get(i) - i;
				taskList.remove(position);
			}
			Toast.makeText(getApplicationContext(), "Removed a Task", Toast.LENGTH_SHORT).show();
		}
		listAdapter.notifyDataSetChanged();
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
			Snackbar.make(findViewById(R.id.main), "ITEM ADDED", Snackbar.LENGTH_LONG).setAction("No action", null).show();
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
