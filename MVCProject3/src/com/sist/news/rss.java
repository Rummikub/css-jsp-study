package com.sist.news;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/*
 * 			JAXP ;  Java Api For XML Parse    ==> 설정파일 읽기(MyBatis,Spring..)
 * 						= DOM (Document Object Model) => 메모리에 저장 (수정,삭제,추가)
 * 					V	= SAX  (Simple Api for XML) => readOnly 
 * 			JAXB ; Java Api FOr XML Bind => Annotation     ======================> 빅데이터
 * 						=> Marshall ; Java Class에 있는 데이터 => XML 변환
 * 						=> UnMarshall ; xml => java Object로 변환
 * 
 *     <rss>
 *     			<channel>
 *     					<item>
 *     						<title></title>
 *     						<author></author>
 *     						<description></description>
 *     						<link></link>
 *     					</item>  -> class
 *     			</channel>  -> class
 *     </rss>  -> class
 *   => newssearch.naver.com/search.naver?where=rss&query=코로나
 */
@XmlRootElement
public class rss {
	private Channel channel=new Channel();

	public Channel getChannel() {
		return channel;
	}
	@XmlElement
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
}
