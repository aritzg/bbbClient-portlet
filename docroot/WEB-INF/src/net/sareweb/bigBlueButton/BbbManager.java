/**
 *  bbbClient : BigBlueButton Liferay portal integration portlet
 *  Copyright (C) 2011  Aritz Galdos Otermin, Sareweb
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *  
 *  Contact: aritz@sareweb.net
 */

package net.sareweb.bigBlueButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class BbbManager {
	
	public BbbManager(String apiURL, String salt){
		_apiURL = apiURL;
		_salt = salt;
		_reader = new SAXReader();
	}
	
	public boolean isMeetingRuning(String meetingId) throws DocumentException{
		String checkcum = calculateChecksum(BbbConstants.METHOD_IS_MEETING_RUNNING, BbbConstants.PARAM_MEETING_ID + "=" + meetingId, _salt);
		
		Document document = _reader.read(_apiURL + BbbConstants.METHOD_IS_MEETING_RUNNING + "?" + "meetingID=" + meetingId + "&checksum="+ checkcum);
		Element root = document.getRootElement();
		Iterator<Element> i = root.elementIterator();
		while(i.hasNext()) {
            Element element = (Element) i.next();
            if("running".equals(element.getName()) && "true".equals(element.getText())){
            	return true;
            }
        }
		return false;
	}
	
	public void createMeeting(String meetingName, String meetingId, String attendeePwd, String moderatorPwd) throws DocumentException{
		String params = BbbConstants.PARAM_NAME + "=" + meetingName +"&" +
						BbbConstants.PARAM_MEETING_ID+ "=" + meetingId +"&" +
						BbbConstants.PARAM_ATTENDEE_PW + "=" + attendeePwd +"&" +
						BbbConstants.PARAM_MODERATOR_PW + "=" + moderatorPwd;
		
		String checkcum = calculateChecksum(BbbConstants.METHOD_CREATE, params, _salt);
		
		Document document = _reader.read(_apiURL + BbbConstants.METHOD_CREATE + "?" + params + "&checksum="+ checkcum);
		Element root = document.getRootElement();
		Iterator<Element> i = root.elementIterator();
		while(i.hasNext()) {
            Element element = (Element) i.next();
        }
	}
	
	public String createConnectionURL(String userName, String meetingId, String password){
		String params = BbbConstants.PARAM_FULL_NAME+ "=" + userName +"&" +
 						BbbConstants.PARAM_MEETING_ID + "=" + meetingId +"&" +
 						BbbConstants.PARAM_PASSWORD + "=" + password;
		
		String checkcum = calculateChecksum(BbbConstants.METHOD_JOIN,params, _salt);
		
		return _apiURL + BbbConstants.METHOD_JOIN + "?" + params + "&checksum="+ checkcum;
	}

	
	private String calculateChecksum(String apiMethodName, String params, String salt){
		if (apiMethodName == null || params == null || salt == null) return null;	
		String sum = apiMethodName + params + salt;
		try {
			return getHash(sum);
		} catch (Exception e) {
			return null;
		}
	}
	
	private String getHash(String message) throws NoSuchAlgorithmException {
		MessageDigest md;
	    byte[] buffer, digest;
	    String hash = "";
	    
		buffer = message.getBytes();
        md = MessageDigest.getInstance("SHA1");
        md.update(buffer);
        digest = md.digest();

        for(byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) hash += "0";
            hash += Integer.toHexString(b);
        }

        return hash;
    }
	
	String _salt;
	String _apiURL;
	SAXReader _reader;
}
