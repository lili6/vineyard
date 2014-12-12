package com.example.facelovely;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.jar.Attributes;

/**
 * Created by liguofang on 2014/12/11.
 */
public class IotdHandler extends DefaultHandler {
	private String url ="http://www.nasa.gov/rss/image_of_the_day.rss";
	private boolean inUrl = false;
	private boolean inTitle = false;
	private boolean inDescription = false;
	private boolean inItem =false;
	private boolean inDate = false;
	private Bitmap image = null;
	private String imageUrl=null;
	private String title = null;
	private StringBuffer description = new StringBuffer();
	private String date = null;

	public void processFeed(){
		try {
//			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//			StrictMode.setThreadPolicy(policy);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			reader.setContentHandler(this);
			InputStream inputStream = new URL(url).openStream();  //make an input stream from the feed URL
			reader.parse(new InputSource(inputStream)); //Start the parsing!
			Log.d("PROCESS","解析完毕");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(new String("Got Exception General"));
		}
	}

	private static  Bitmap getBitmap(String url) {
		try{
			HttpURLConnection connection =
					(HttpURLConnection) new URL(url).openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			System.out.println("input====="+input.available());
			System.out.println("BitmapFactory=====");

			Bitmap bitmap = BitmapFactory.decodeStream(input);
			input.close();
			return bitmap;
		}   catch (IOException ioe)
		{
			ioe.printStackTrace();
			System.out.println(new String("IOException in reading Image"));
			return null;
		}
		catch (Exception ioe)
		{
			ioe.printStackTrace();
			System.out.println(new String("IOException GENERAL"));
			return null;
		}
	}

//	public void startElement (String uri, String localName,
//	                          String qName, org.xml.sax.Attributes attributes)
//
	@Override
	public void startElement(String uri,String localName,String qName,
	                         org.xml.sax.Attributes attributes) throws SAXException {
		if(localName.equals("url")){inUrl = true;}
		else {inUrl=false;}

		if (localName.startsWith("item")){inItem = true;}
		else if (inItem) {
			if (localName.equals("title")) {inTitle=true;}
			else {inTitle=false;}
			if (localName.equals("description")) {inDescription=true;}
			else {inDescription=false;}
			if (localName.equals("pubDate")) {inDate=true;}
			else {inDate=false;}
			if(localName.equals("enclosure")){
				String url = attributes.getValue(0);
				getBitmap(url);
			}
		}
	}
	@Override
	public void characters(char ch[],int start, int length){
		Log.d("length",String.valueOf(length));
		String chars = new String(ch).substring(start,start+length);
		if(inUrl &&url == null) {image = getBitmap(chars);}
		if (inTitle && title == null) { title = chars; }
		if (inDescription) { description.append(chars); }
		if (inDate && date == null) { date = chars; }
	}

	public Bitmap getImage() {
		return image;
	}

	public String getTitle() {
		return title;
	}

	public StringBuffer getDescription() {
		return description;
	}

	public String getDate() {
		return date;
	}

	public static void main(String[] args) {
		System.out.print(getBitmap("http://www.nasa.gov/sites/default/files/styles/946xvariable_height/public/orion2014-4259_0.jpg?itok=TeD785wz")) ;
	}

}
