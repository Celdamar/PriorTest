package Incident;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;

import android.content.Context;

public class FileIncident {
	// yaya


	public static void saveIncident(Incident obj, String fileName, Context context) throws IOException{
		FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
		ObjectOutputStream os = new ObjectOutputStream(fos);
		os.writeObject(obj);
		os.close();
	}


	public static Incident loadIncident(String fileName, Context context) throws OptionalDataException, ClassNotFoundException, IOException{
		FileInputStream fis = context.openFileInput(fileName);
		ObjectInputStream is = new ObjectInputStream(fis);
		return (Incident)is.readObject();
	}

}
