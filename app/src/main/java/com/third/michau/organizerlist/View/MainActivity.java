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

	protected ArrayList<Task> taskList;
	private final int REQUEST_CODE = 1;
	private ListAdapter listAdapter;
	public static final String TAG = "Main Activity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		taskList = getTasks();
		listAdapter = new ListAdapter(taskList,this);
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
			Snackbar.make(findViewById(R.id.main_activity), "new Task added", Toast.LENGTH_SHORT).setAction("No action",null).show();

		}
	}
	private ArrayList<Task> getTasks() {
		ArrayList<Task> list = new ArrayList<>();
		list.add(new Task("Do it 1"));
		list.add(new Task("Do it 2"));
		list.add(new Task("Do it 3"));
		list.add(new Task("Do it 4"));
		return list;
	}
}
