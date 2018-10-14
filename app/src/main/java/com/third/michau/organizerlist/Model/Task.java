package com.third.michau.organizerlist.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable{

	private String text;
	private Boolean isChecked;

	public Task(String text) {
		this.text = text;
		this.isChecked = false;
	}

	protected Task(Parcel in) {
		text = in.readString();
		byte tmpIsChecked = in.readByte();
		isChecked = tmpIsChecked == 0 ? null : tmpIsChecked == 1;
	}

	public static final Creator<Task> CREATOR = new Creator<Task>() {
		@Override
		public Task createFromParcel(Parcel in) {
			return new Task(in);
		}

		@Override
		public Task[] newArray(int size) {
			return new Task[size];
		}
	};

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getChecked() {
		return isChecked;
	}

	public void setChecked(Boolean checked) {
		isChecked = checked;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(text);
		parcel.writeByte((byte) (isChecked == null ? 0 : isChecked ? 1 : 2));
	}
}
