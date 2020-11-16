/*
 * 
 * SAX Parsing 시 모든 node(요소,텍스트 등 xml을 이루는 요소들을 일컬음)마다 이벤트를 발생시켜주는 객체
 *
 */

package day1116.publicapi;

import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import day1113.xml.Pet;

public class MountainHandler extends DefaultHandler{
	//발견되는 산이 있을 때 VO로 생성 후 , 그 vo를 담게 될 백터
	
	Vector<Mountain> mtList;
	Mountain mt; //산의 정보 1건을 담을 변수
	
	//현재 실행부가 어느 위치를 지났는지 알기 위한 변수
	boolean isMntnid;
	boolean isMntnnm; 
	boolean isMntninfopoflc;
	boolean isMntninfohght;
	
	
	@Override
	public void startDocument() throws SAXException {
	}
	
	@Override
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
//		System.out.println("<"+tag+">");
		
		if(tag.equals("items")){ //벡터 만들고
			mtList = new Vector<Mountain>();			
		}else if(tag.equals("item")) { //VO생성
			mt = new Mountain();
		}else if(tag.equals("mntnid")) { //지나감 표시 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
			isMntnid=true;
		}else if(tag.equals("mntnnm")) {	
			isMntnnm=true;
		}else if(tag.equals("mntninfopoflc")) {
			isMntninfopoflc=true;
		}else if(tag.equals("mntninfohght")) {
			isMntninfohght=true;
		}
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String data = new String(ch,start,length);
//		System.out.println(data);
		
		if(isMntnid) {
			mt.setMntnid(Integer.parseInt(data));
		}else if(isMntnnm) {
			mt.setMntnnm(data);
		}else if(isMntninfopoflc) {
			mt.setMntninfopoflc(data);
		}else if(isMntninfohght) {
			mt.setMntninfohght(Integer.parseInt(data));
		}
		
	
	}
	
	@Override
	public void endElement(String uri, String localName, String tag) throws SAXException {
//		System.out.println("/<"+tag+">");
		
		if(tag.equals("item")) {
			mtList.add(mt);
		}else if(tag.equals("mntnid")) {
			isMntnid=false;
		}else if(tag.equals("mntnnm")) {
			isMntnnm=false;
		}else if(tag.equals("mntninfopoflc")) {
			isMntninfopoflc=false;
		}else if(tag.equals("mntninfohght")) {
			isMntninfohght=false;
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		//벡터에 담겨진 갯수 및 출력
		System.out.println("검색된 산의 갯수: "+mtList.size());
		for(int i =0;i<mtList.size();i++) {
			Mountain obj = mtList.get(i);
			System.out.println("산 ID: "+obj.getMntnid());
			System.out.println("산 이름: "+obj.getMntnnm());
			System.out.println("산 위치: "+obj.getMntninfopoflc());
			System.out.println("산 높이: "+obj.getMntninfohght());
			System.out.println("--------------------------------------------------------------------");
		}
		
	}
	
}
