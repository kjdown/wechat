package util;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import message.resp.Article;
import message.resp.ImageMessage;
import message.resp.MusicMessage;
import message.resp.NewsMessage;
import message.resp.TextMessage;
import message.resp.VideoMessage;
import message.resp.VoiceMessage;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class MessageUtil {
	// ������Ϣ���ͣ��ı�
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";
	// ������Ϣ���ͣ�ͼƬ
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
	// ������Ϣ���ͣ�����
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
	// ������Ϣ���ͣ���Ƶ
	public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
	// ������Ϣ���ͣ�����λ��
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
	// ������Ϣ���ͣ�����
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	// ������Ϣ���ͣ��¼�����
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	// �¼����ͣ�subscribe(����)
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	// �¼����ͣ�unsubscribe(ȡ������)
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	// �¼����ͣ�scan(�û��ѹ�עʱ��ɨ���������ά��)
	public static final String EVENT_TYPE_SCAN = "scan";
	// �¼����ͣ�LOCATION(�ϱ�����λ��)
	public static final String EVENT_TYPE_LOCATION = "LOCATION";
	// �¼����ͣ�CLICK(�Զ���˵�)
	public static final String EVENT_TYPE_CLICK = "CLICK";

	// ��Ӧ��Ϣ���ͣ��ı�
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";
	// ��Ӧ��Ϣ���ͣ�ͼƬ
	public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
	// ��Ӧ��Ϣ���ͣ�����
	public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
	// ��Ӧ��Ϣ���ͣ���Ƶ
	public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
	// ��Ӧ��Ϣ���ͣ�����
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
	// ��Ӧ��Ϣ���ͣ�ͼ��
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream = request.getInputStream();
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		Element root = document.getRootElement();

		List<Element> elementList = root.elements();

		// ���������ӽڵ�
		for (Element e : elementList)
			map.put(e.getName(), e.getText());


		inputStream.close();
		inputStream = null;

		return map;
	}


	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				boolean cdata = true;

				@SuppressWarnings("unchecked")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	public static String messageToXml(TextMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

	public static String messageToXml(ImageMessage imageMessage) {
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}

	public static String messageToXml(VoiceMessage voiceMessage) {
		xstream.alias("xml", voiceMessage.getClass());
		return xstream.toXML(voiceMessage);
	}

	public static String messageToXml(VideoMessage videoMessage) {
		xstream.alias("xml", videoMessage.getClass());
		return xstream.toXML(videoMessage);
	}


	public static String messageToXml(MusicMessage musicMessage) {
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}


	public static String messageToXml(NewsMessage newsMessage) {
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}
}
