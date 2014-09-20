package com.example.priortest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.*;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	 private final String TEMP_FILE_NAME = "wpta_temp_file1.txt";
    EditText etContent;
    ImageView image;
    File tempFile;
    String tempimage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

		/** Getting reference to btn_save of the layout activity_main */
		final Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        Button btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

               FileWriter writer=null;
                try {
                    writer = new FileWriter(tempFile);

                    /** Saving the contents to the file*/
                    writer.write(etContent.getText().toString());

                    /** Closing the writer object */
                    writer.close();

                    Toast.makeText(getBaseContext(), "Temporarily saved contents in " + tempFile.getPath(), Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                saveToCacheFile(bmp);
            }
        	});

		image = (ImageView) findViewById(R.id.imageView1);
        Button btnload = (Button) findViewById(R.id.btn_load);
        btnload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	image.setImageBitmap(loadFromCacheFile());


            }
       });


        /** Getting Cache Directory */
        File cDir = getBaseContext().getCacheDir();

        /** Getting a reference to temporary file, if created earlier */
        tempFile = new File(cDir.getPath() + "/" + TEMP_FILE_NAME) ;

        /** Getting reference to edittext et_content */
        etContent = (EditText) findViewById(R.id.et_content);

        String strLine="";
        StringBuilder text = new StringBuilder();

        /** Reading contents of the temporary file, if already exists */
        /*try {
            FileReader fReader = new FileReader(tempFile);
            BufferedReader bReader = new BufferedReader(fReader);

            //Reading the contents of the file , line by line
            while( (strLine=bReader.readLine()) != null  ){
                text.append(strLine+"\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

        //Setting content from file
        etContent.setText(text);*/


	}

	  public String getCacheFilename() {
	      File f = getBaseContext().getCacheDir();
	      return f.getAbsolutePath() + "/cache.png";
	  }

	  public Bitmap loadFromFile(String filename) {
	      try {
	          File f = new File(filename);
	          if (!f.exists()) { return null; }
	          Bitmap tmp = BitmapFactory.decodeFile(filename);
	          return tmp;
	      } catch (Exception e) {
	          return null;
	      }
	  }
	  public Bitmap loadFromCacheFile() {
	      return loadFromFile(getCacheFilename());
	  }
	  public  void saveToCacheFile(Bitmap bmp) {
	      saveToFile(getCacheFilename(),bmp);
	  }
	  public  void saveToFile(String filename,Bitmap bmp) {
	      try {
	          FileOutputStream out = new FileOutputStream(filename);
	          bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
	          out.flush();
	          out.close();
	      } catch(Exception e) {}
	  }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

/** Setting the click event listener for the button btn_save */




	public File getTempFile(Context context, String url) {
	    File file = null;
	    try {
	        String fileName = Uri.parse(url).getLastPathSegment();
	        file = File.createTempFile(fileName, null, context.getCacheDir());
	    }catch (IOException e) {
	        // Error while creating file
	    }
	    return file;
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
}
