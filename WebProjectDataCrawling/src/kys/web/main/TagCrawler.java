package kys.web.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TagCrawler{
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		int maxPage =43;
		int cnt=0;
		
		
		TagDAO tdao=new TagDAO();
		TagVO vo=new TagVO();
		RestaurantVO rvo=new RestaurantVO();
		
		
		try {
			  tdao.getConnection();
		} catch (Exception e) {
			System.out.println("error");
		}
		
		
		
		String rname="";
		for(int page=0; page<maxPage; page++) {
			
			
			try {
						Document pageDoc = Jsoup.connect("https://www.tripadvisor.co.kr/Restaurants-g294197-oa"+30*page+"-Seoul.html").get();
						
						
						
						Element detailList = pageDoc.getElementsByAttributeValueStarting("class", "restaurants-list-List__wrapper").first();
						Elements detailItems = detailList.getElementsByAttribute("data-test");
						
						for(int nth=1; nth<detailItems.size(); nth++)
						{
							if(nth==6)
								continue;
							if(nth==12)
								continue;
							if(nth==18)
								continue;
							if(nth==24)
								continue;
							if(nth==30)
								continue;
							if(nth==36)
								continue;
							Document detailDoc = Jsoup.connect("https://www.tripadvisor.co.kr"+detailItems.get(nth).selectFirst("a").attr("href")).get();
							
	
					
								// rname
							
								try {
									rname = detailDoc.selectFirst("h1.ui_header").text();
									System.out.println((page+1) + " page " + (nth+1) + " th Restaurant : " + rname);
								
									vo.setRname(rname);

								} catch (Exception e) {
									System.out.println((page+1) + " page " + (nth+1) + " th Restaurant doesn't have name.");
									continue;
								}
							
								
								// info - tags개념
								try {
									String info = detailDoc.selectFirst("div.header_links").text();
								
									
									String[] tags=info.split(",");			
									System.out.println("첫번째 태그: "+tags[0]);
									System.out.println("두번째 태그: "+tags[1]);
									System.out.println("세번째 태그: "+tags[2]);
									System.out.println("네번째 태그: "+tags[3]);
									
									vo.setTagname(info);
									
								} catch (Exception e) {
									System.out.println(rname + " doesn't have any tags.");
									
								} 
								
								
								
								 int no = tdao.getLocNoByName(rname);	
								 if(no!=0)tdao.tagInsert(vo);
								 Thread.sleep(50);
								 System.out.println("++++++++++++++"+no+"++++++++++++++");
															
								
								
				
				System.out.println("------------------------------------------------------------------------");
				} // detailItems for end
					
					
						//31
						cnt++;
						
			

			} catch (Exception e) {
				e.getMessage();
				System.out.println("+++++값이 안들어온다!+++");
				
				continue;
			} // get pageDoc try catch
			//31
			
			
		} // page for end
		//31
		

		long end = System.currentTimeMillis();
		System.out.println("Crawling end in " + ((end - start) / 1000) + " seconds.");
	} // main end

	public static String getDataByEnd(String raw, String start, String end, int length) throws Exception {
		String trim = "";
		for(int i=0; i<length; i++){
			trim += " ";
		}
		raw = trim + raw + trim;
		raw = raw.substring(raw.lastIndexOf(end) - length, raw.lastIndexOf(end));
		raw = raw.substring(raw.lastIndexOf(start) + start.length());

		return raw;
	}

	public static String getDataByStart(String raw, String start, String end, int length) throws Exception {
		if(raw.indexOf(start)<0) throw new Exception();
		String trim = "";
		for(int i=0; i<length; i++){
			trim += " ";
		}
		raw = trim + raw + trim;
		raw = raw.substring(raw.indexOf(start) + start.length(), raw.indexOf(start) + start.length() + length);
		raw = raw.substring(0, raw.indexOf(end));

		return raw;
	}

	
}