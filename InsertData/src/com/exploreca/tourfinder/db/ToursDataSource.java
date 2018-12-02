package com.exploreca.tourfinder.db;

import com.exploreca.tourfinder.model.Tour;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ToursDataSource {

	public static final String LOGTAG="EXPLORECA";
	
	SQLiteOpenHelper dbhelper;
	SQLiteDatabase database;
	
	public ToursDataSource(Context context) {
		dbhelper = new ToursDBOpenHelper(context);
	}
	
	public void open() {
		Log.i(LOGTAG, "Database opened");
		database = dbhelper.getWritableDatabase();
	}

	public void close() {
		Log.i(LOGTAG, "Database closed");		
		dbhelper.close();
	}
	
	public Tour create(Tour tour) {
		ContentValues values = new ContentValues();
		values.put(ToursDBOpenHelper.COLUMN_TITLE, tour.getTitle());
		values.put(ToursDBOpenHelper.COLUMN_DESC, tour.getDescription());
		values.put(ToursDBOpenHelper.COLUMN_PRICE, tour.getPrice());
		values.put(ToursDBOpenHelper.COLUMN_IMAGE, tour.getImage());
		long insertid = database.insert(ToursDBOpenHelper.TABLE_TOURS, null, values);
		tour.setId(insertid);
		return tour;
	}
	
}
