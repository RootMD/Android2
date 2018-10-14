package com.third.michau.organizerlist.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.third.michau.organizerlist.Model.Task;
import com.third.michau.organizerlist.R;

import java.util.ArrayList;

public class AddTask extends Activity {

	public static final String TAG = "Add Activity";

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_task_layout);
		final ArrayList<Task> taskList = getIntent().getParcelableArrayListExtra("List");

		final EditText editText = findViewById(R.id.editText);
		editText.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
		Button btnAdd = findViewById(R.id.bAdd);
		btnAdd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String text = editText.getText().toString();
				taskList.add(new Task(text));
				Intent data = new Intent();
				data.putParcelableArrayListExtra("List", taskList);
				setResult(RESULT_OK, data);
				finish();
			}
		});
		Log.e(TAG, "onCreate");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.e(TAG, "onStop");
	}
}
