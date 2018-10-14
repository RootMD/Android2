package com.third.michau.organizerlist.View;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

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

	public void removeItems(){

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


	public void onClick(View v) {

		Snackbar.make(v, "Release date ", Snackbar.LENGTH_LONG).setAction("No action", null).show();
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
			viewHolder.taskCheckbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
					Task element = (Task) viewHolder.taskCheckbox.getTag();
					element.setChecked(compoundButton.isChecked());
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
	}

}
