package com.journaldev.youplusmegaevent.filechooser;

import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


import com.journaldev.youplusmegaevent.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class FileChooser extends ListActivity {
	private File currentDir;
	private FileArrayAdapter adapter;
	private FileFilter fileFilter;
	private File fileSelected;
	private ArrayList<String> extensions;
	//DBController controller = new DBController(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			if (extras.getStringArrayList("filterFileExtension") != null) {
				extensions = extras.getStringArrayList("filterFileExtension");
				Log.i("AAA", extensions.size()+ "");
				fileFilter = new FileFilter() {
					@Override
					public boolean accept(File pathname) {
						Log.i("AAA", pathname.getName()+ "");
						Log.i("AAA", pathname.getName()+ "");
						return (pathname.getName().contains(".")?extensions.contains(pathname.getName().substring(pathname.getName().lastIndexOf("."))):true);
					}
				};
			}
		}
		
		currentDir = new File("/sdcard/");
		fill(currentDir);		
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	if ((!currentDir.getName().equals("sdcard")) && (currentDir.getParentFile() != null)) {
	        	currentDir = currentDir.getParentFile();
	        	fill(currentDir);
        	} else {
        		finish();
        	}
            return false;
        }
        return super.onKeyDown(keyCode, event);
	}

	private void fill(File f) {
		File[] dirs = null;
		if (fileFilter != null)
			dirs = f.listFiles(fileFilter);
		else 
			dirs = f.listFiles();
			
		this.setTitle(getString(R.string.currentDir) + ": " + f.getName());
		List<Option> dir = new ArrayList<Option>();
		List<Option> fls = new ArrayList<Option>();
		try {
			for (File ff : dirs) {
				if (ff.isDirectory() && !ff.isHidden())
					dir.add(new Option(ff.getName(), getString(R.string.folder), ff
							.getAbsolutePath(), true, false));
				else {
					if (!ff.isHidden())
						fls.add(new Option(ff.getName(), getString(R.string.fileSize) + ": "
								+ ff.length(), ff.getAbsolutePath(), false, false));
				}
			}
		} catch (Exception e) {

		}
		Collections.sort(dir);
		Collections.sort(fls);
		dir.addAll(fls);
		if (!f.getName().equalsIgnoreCase("sdcard")) {
			if (f.getParentFile() != null) dir.add(0, new Option("..", getString(R.string.parentDirectory), f.getParent(), false, true));
		}
		adapter = new FileArrayAdapter(FileChooser.this, R.layout.file_view,
				dir);
		this.setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		ProgressDialog dialog;
		dialog = new ProgressDialog(FileChooser.this);
		dialog.setMessage("Loading, please wait");
		// dialog.setTitle("Connecting server");
		dialog.show();
		dialog.setCancelable(false);
		super.onListItemClick(l, v, position, id);
		Option o = adapter.getItem(position);
		if (o.isFolder() || o.isParent()) {			
			currentDir = new File(o.getPath());
			fill(currentDir);
		} else {
			//onFileClick(o);
			fileSelected = new File(o.getPath());
			Intent intent = new Intent();
			intent.setType("gagt/sdf");
			intent.putExtra("fileSelected", fileSelected.getAbsolutePath());
			dialog.cancel();
			Toast.makeText(this, "Successfully File Imported " + o.getName(), Toast.LENGTH_SHORT)
					.show();
			//public void sendmail(){




//String tosender={"",""};
				//String to=txtto.getText().toString().trim();
	/*	int pos = spnr.getSelectedItemPosition();
		String tosender=celebrities[+pos];*/
				File exportDir = new File(Environment.getExternalStorageDirectory(), "");
				if (!exportDir.exists()) {
					exportDir.mkdirs();
				}
				Calendar c1 = Calendar.getInstance();

				System.out.println("Current time => " + c1.getTime());

				SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
				String createdTimeforFile = df.format(c1.getTime());
				String filename="youpluscamp"+createdTimeforFile;


				String extension=filename+".csv";

				File  fileloc = new File(exportDir,extension);

				// String subj=txtsubj.getText().toString().trim();
				// String msg=txttextmsg.getText().toString().trim();
				Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
				emailIntent.setType("plain/text");
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"ritesh@youplus.co","vindhya@youplus.co","adam.hussain@youplus.co"});
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "YouPlus_MegaeventCSVFILE"+createdTimeforFile);
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "YouPlus_MegaeventCSVFILE");
				//  emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(file.getAbsolutePath()));
				emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(fileSelected.getAbsoluteFile()));
				startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			//fileSelected.getAbsoluteFile()
				//	Toast.makeText(getApplicationContext(),"Mail send",Toast.LENGTH_LONG).show();

			//}



			finish();

			/////////
			//controller = new DBController(getApplicationContext());
			//SQLiteDatabase db = controller.getWritableDatabase();
			//String tableName = "importItem_table";
			//db.execSQL("delete from " + tableName);
				//if (resultCode == RESULT_OK) {
					try {
						FileReader file = new FileReader(fileSelected.getAbsolutePath());
						System.out.println("jjpath"+file);

						BufferedReader buffer = new BufferedReader(file);
						System.out.println("buffer"+buffer);

						ContentValues contentValues = new ContentValues();
						String line = "";


					} catch (IOException e) {

						Dialog d = new Dialog(this);
						d.setTitle(e.getMessage().toString() + "first");
						d.show();
						// db.endTransaction();
					}

			}
		}



	private void onFileClick(Option o) {
		Toast.makeText(this, "File Clickedjjjj: " + o.getName(), Toast.LENGTH_SHORT)
				.show();
	}
		
}