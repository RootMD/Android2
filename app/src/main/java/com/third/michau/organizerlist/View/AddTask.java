package com.third.michau.organizerlist.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.third.michau.organizerlist.Model.Task;
import com.third.michau.organizerlist.R;

import java.util.ArrayList;

public class AddTask extends Activity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_task_layout);
		final ArrayList<Task> taskList = getIntent().getParcelableArrayListExtra("List");

		final EditText editText = findViewById(R.id.editText);
		Button btnAdd = findViewById(R.id.bAdd);
		btnAdd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				taskList.add(new Task(editText.getText().toString()));
				Intent data = new Intent();
				data.putParcelableArrayListExtra("List", taskList);
				setResult(RESULT_OK, data);
				finish();
			}
		});
	}
}
