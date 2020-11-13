//SAX방식의 파싱을 지원하는 핸들러
package day1113.xml.down;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MovieHandler extends DefaultHandler{
	ArrayList<Movie> movieList;
	Movie movie;
	
	boolean isTitle;
	boolean isUrl;
	
	
	@Override
	public void startDocument() throws SAXException {

	}
	
	@Override
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		if(tag.equals("movies")) {
			movieList = new ArrayList<Movie>();
		}else if(tag.equals("movie")) {
			movie  = new Movie();
		}else if(tag.equals("title")) {
			isTitle=true;
		}else if(tag.equals("url")) {
			isUrl=true;
		}

	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String data = new String(ch,start,length);
		if(isTitle) {
			movie.setTitle(data);
		}else if(isUrl) {
			movie.setUrl(data);
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String tag) throws SAXException {
		//flag값 다시 돌려놓기
		if(tag.equals("title")) {
			isTitle = false;
		}else if(tag.equals("url")) {
			isUrl=false;
		}else if(tag.equals("movie")) {
			movieList.add(movie);
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		for(Movie obj : movieList) {
			System.out.println(obj.getTitle());
			System.out.println(obj.getUrl());
			System.out.println("-------------------------------------");
		}
	}
}
