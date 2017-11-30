/**
 * 
 */
package org.dimigo.util;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * <pre>
 * org.dimigo.util
 *  |_ Message
 * 
 * 1. 개요 :
 * 2. 작성일 : 2017. 10. 1.
 * </pre>
 *
 * @author : teacher
 * @version : 1.0
 */
public class Message {
	
	 public static String getMessage(String code, Object... args) {
         ResourceBundle rb = ResourceBundle.getBundle("message");

         if(CommonUtil.isEmpty(code)) return "";
         
         // 한글은 8859_1 캐릭터 셋으로만 읽혀지므로 인코딩 변환을 해주어야 함
         try {
        	 String msg = new String(rb.getString(code).getBytes("ISO-8859-1"), "UTF-8");
        	 
        	 MessageFormat formatter = new MessageFormat(msg);
             return formatter.format(args);
             
		 } catch (Exception e) {
			 return code;
		 }
    }

    public static void main(String args[]) {
         System.out.println(Message.getMessage("E0001"));
         System.out.println(Message.getMessage("E0002", "test"));
    }
}
