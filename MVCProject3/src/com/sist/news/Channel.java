package com.sist.news;
import java.util.*;
/*     <rss>
*     			<channel>
*     					<item>
*     						<title></title>
*     						<author></author>
*     						<description></description>
*     						<link></link>
*     					</item>  -> class
*     			</channel>  -> class
*     </rss>  -> class
*/
public class Channel {
			private List<Item> item=new ArrayList<Item>();

			public List<Item> getItem() {
				return item;
			}

			public void setItem(List<Item> item) {
				this.item = item;
			}
			
}
