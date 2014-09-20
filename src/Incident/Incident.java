package Incident;

import java.util.List;

import android.graphics.Bitmap;
import android.media.Image;

public class Incident {

	private String image;
	private String description;

	public Incident(String image, String description){
		this.image = image;
		this.description = description;
	}

	public void setImage(String img){
		image = img;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getImage(){
		return image;
	}

	public String getDescription(){
		return description;
	}

}
