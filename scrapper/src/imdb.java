
import java.sql.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class imdb {

    public static void main(String[] args) throws Exception {
    	Connection cn=DBInfo.getConn();
    	int i=1;
        final Document document = Jsoup.connect("http://www.imdb.com/chart/top").get();

        for (Element row : document.select("table.chart.full-width tr")) {

            final String title = row.select(".titleColumn a").text(); //extracting title
            final String rating = row.select(".imdbRating").text();   //extracting rating
            final String link ="http://www.imdb.com"+row.select(".titleColumn a").attr("href"); //extracting link
            
            
            String titles=title.replace("'","''");  
           
            if(title.isEmpty()== false) {
            	try {
            	    
            		Statement stmt =cn.createStatement();
            		String query="Select Name from imdb where name like '"+titles+"' ;" ;
            		ResultSet rs=stmt.executeQuery(query);
            		
            		if (rs.next() == false)
            		{ //insertion in table imdb
                    	String str="insert into imdb(Name, Rating, Link) values(?,?,?);";
                        
                    	PreparedStatement ps = null;
            			ps = cn.prepareStatement(str);
            			
            			ps.setString(1, title);
            			ps.setString(2, rating);
            			ps.setString(3, link);
            			ps.executeUpdate(); 
            			System.out.println(title + " -> Rating: " + rating + " -> Link: " + link);
            		} else 
            		  {     
            			//Update the previous stored values..
            			    String str1= " update imdb set rating ='"+rating+"', link = '"+link+"' where name like '"+titles+"' ;";
            			    stmt.executeUpdate(str1);
            			    System.out.println("successfully updated row "+i);
            		      
            		  }

              
                  }
    			catch (SQLException e) {
    				e.printStackTrace();
    			}
            	 i++;
            }
           
            }
            
    }
}