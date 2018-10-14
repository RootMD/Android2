package com.third.michau.organizerlist.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.third.michau.organizerlist.Model.Task;
import com.third.michau.organizerlist.R;

import java.util.ArrayList;

import static android.widget.CompoundButton.OnCheckedChangeListener;

public class ListAdapter extends BaseAdapter {

	private ArrayList<Task> modelList = new ArrayList<>();
	private Context context;

	public ListAdapter(ArrayList<Task> modelList, Context context) {
		this.modelList = modelList;
		this.context = context;
	}

	@Override
	public int getCount() {
		return modelList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public ArrayList<Task> getData() {
		return modelList;
	}

	@Override
	public View getView(final int position, View cView, ViewGroup parent) {
		final ListViewHolder viewHolder;
		final View result;
		if (cView == null) {
			viewHolder = new ListViewHolder();
			LayoutInflater inflater = LayoutInflater.from(context);
			result = inflater.inflate(R.layout.task_item, parent, false);
			viewHolder.text = result.findViewById(R.id.taskText);
			viewHolder.taskCheckbox = result.findViewById(R.id.taskCheck);
			viewHolder.taskButton = result.findViewById(R.id.taskButton);
			viewHolder.taskCheckbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
					Task element = (Task) viewHolder.taskCheckbox.getTag();
					element.setChecked(compoundButton.isChecked());
				}
			});
			viewHolder.taskButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					getData().remove(position);
					Toast.makeText(view.getContext(), "Removed a Task", Toast.LENGTH_SHORT).show();
					notifyDataSetChanged();
				}
			});
			result.setTag(viewHolder);
			viewHolder.taskCheckbox.setTag(modelList.get(position));
		} else {
			result=cView;
			((ListViewHolder) result.getTag()).taskCheckbox.setTag(modelList.get(position));
			viewHolder = (ListViewHolder) result.getTag();
		}
		viewHolder.text.setText(modelList.get(position).getText());
		viewHolder.taskCheckbox.setChecked(modelList.get(position).getChecked());

		return result;
	}

	private static class ListViewHolder {
		protected TextView text;
		protected CheckBox taskCheckbox;
		protected Button taskButton;
	}

}
