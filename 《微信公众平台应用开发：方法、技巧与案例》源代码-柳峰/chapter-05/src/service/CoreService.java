package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import message.resp.Article;
import message.resp.NewsMessage;
import message.resp.TextMessage;
import util.MessageUtil;


public class CoreService {

	public static String processRequest(HttpServletRequest request) {
		String respXml = null;
		try {
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			String fromUserName = requestMap.get("FromUserName");
			String toUserName = requestMap.get("ToUserName");
			String msgType = requestMap.get("MsgType");

			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				String eventType = requestMap.get("Event");
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					textMessage.setContent("您好，欢迎关注华软MySise");
					respXml = MessageUtil.messageToXml(textMessage);
				}
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {}
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					String eventKey = requestMap.get("EventKey");
					if (eventKey.equals("sise1")) {
						Article article = new Article();
						article.setTitle("网页MySise");
						article.setDescription("最传统的华软教务系统");
						article.setPicUrl("http://img5.imgtn.bdimg.com/it/u=1790581034,3983552959&fm=21&gp=0.jpg");
						article.setUrl("http://class.sise.com.cn:7001/sise/login.jsp");
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);

						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respXml = MessageUtil.messageToXml(newsMessage);
					} else if (eventKey.equals("sise2")) {
						Article article = new Article();
						article.setTitle("网页MySise");
						article.setDescription("WeChat明文");
						article.setPicUrl("http://img5.imgtn.bdimg.com/it/u=1790581034,3983552959&fm=21&gp=0.jpg");
						article.setUrl("http://class.sise.com.cn:7001/sise/login.jsp");
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respXml = MessageUtil.messageToXml(newsMessage);
					}
				}
			}

			else {
				textMessage.setContent("通过菜单操作mysise！");
				respXml = MessageUtil.messageToXml(textMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
}
