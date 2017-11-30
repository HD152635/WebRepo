/**
 * 
 */
package org.dimigo.util;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dimigo.vo.ActionVO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * <pre>
 * org.dimigo.util
 *  |_ ActionMapper
 * 
 * 1. 개요 :
 * 2. 작성일 : 2017. 10. 5.
 * </pre>
 *
 * @author : teacher
 * @version : 1.0
 */
public class ActionMapper {

	private static ActionMapper mapper;
	private static final String ACTION_MAPPER_FILE_NAME = "/action_mapper.xml";
	
	private static Map<String, ActionVO> actions = new HashMap<>();
	
	private ActionMapper() throws Exception {
		parsing();
	}	

	public static synchronized ActionMapper getInstance() throws Exception {
		if(mapper == null) {
			mapper = new ActionMapper();
		}
		return mapper;
	}
	
	private void parsing() throws Exception {		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document document = builder.parse(getClass().getResourceAsStream(ACTION_MAPPER_FILE_NAME));

			NodeList nodeList = document.getDocumentElement().getChildNodes();			
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					ActionVO vo = new ActionVO();
					
					// name 속성명 얻기
					Node nameNode = node.getAttributes().getNamedItem("name");
					if(nameNode != null) {
						vo.setName(nameNode.getNodeValue());
					}
					
					// class 속성명 얻기
					Node classNode = node.getAttributes().getNamedItem("class");
					if(classNode != null) {
						vo.setClassName(classNode.getNodeValue());
					}
					
					// 서브 노드 가져오기
					Element elem = (Element) node;
					NodeList resultList = elem.getElementsByTagName("result");
					
					for(int j=0; j<resultList.getLength(); j++) {
						Node result = resultList.item(j);
						Node resultNode = result.getAttributes().getNamedItem("name");
						if(resultNode != null) {
							String text = result.getTextContent();
							if("success".equals(resultNode.getNodeValue())) {								
								vo.setSuccess(text);
							} else if("error".equals(resultNode.getNodeValue())) {
								vo.setError(text);
							}
						}
					}
					
					actions.put(vo.getName(), vo);
				}
				
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	public ActionVO getAction(String action) throws Exception {
		if(action == null || "".equals(action)) throw new Exception("action string is empty");
		ActionVO vo = actions.get(action);
		
		if(vo == null) throw new Exception("action info is empty");
		return vo;
    }
	
	public static void main(String[] args) {
		try {
			ActionMapper am = ActionMapper.getInstance();
			System.out.println(am.getAction("openlogin"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
