import java.io.*;
import java.util.*;
class publish
{
	public static void main(String ar[]) throws Exception
	{
		int a;
		for(a=0;a<ar.length;a++)
		{
			ar[a]="posts/"+ar[a];
			FileReader fr=new FileReader(ar[a]);
			BufferedReader st=new BufferedReader(fr);
			String title="", summary="", url="", date="", month="", year="", datetodisplay="";
			String line;
			String outputFile="";
			
			//parse head of post and get variables ready
			st.readLine();
			while(!(line = st.readLine()).equals("---"))
			{
				StringTokenizer k=new StringTokenizer(line);
				char c=k.nextToken().charAt(0);
				k.nextToken();
				switch(c)
				{
					case 't':
						while(k.hasMoreTokens())
						{
							title=title+k.nextToken()+" ";
						}
						break;
					case 'u':
						url=k.nextToken();
						break;
					case 'm':
						month=k.nextToken();
						break;
					case 'd':
						date=k.nextToken();
						break;
					case 'y':
						year=k.nextToken();
						break;
					case 's':
						while(k.hasMoreTokens())
						{
							summary=summary+k.nextToken()+" ";
						}
						break;
				}
			}
			datetodisplay=month+" "+date+", "+year;

			outputFile="blog/"+url+".html";
			FileWriter fileWriter=new FileWriter(outputFile,false);
			PrintWriter p=new PrintWriter(fileWriter);

			p.println("<!DOCTYPE HTML>");
			p.println("<html>");
			p.println("<head>");
			p.println("<title>"+title+"- Rajat Agarwal </title>");
			FileReader header=new FileReader("blog/header");
			BufferedReader z=new BufferedReader(header);
			while(!(line = z.readLine()).equals("eof"))
			{
				p.println(line);
			}
			z.close();
			header.close();

			p.println(title+"</h2>");
			p.println("<h5>Posted on "+datetodisplay+"</h5><p></p>");
			while(!(line = st.readLine()).equals("eof"))
			{
				p.println(line);
			}

			FileReader footer=new FileReader("blog/footer");
			BufferedReader zz=new BufferedReader(footer);
			while(!(line = zz.readLine()).equals("eof"))
			{
				p.println(line);
			}
			zz.close();
			footer.close();

			p.close();
         	fileWriter.close();
        	st.close();
        	fr.close();

        	FileWriter fileWriter2=new FileWriter("blog/index1.html",false);
			PrintWriter pp=new PrintWriter(fileWriter2);
			
			FileReader index=new FileReader("blog/index.html");
			BufferedReader zz2=new BufferedReader(index);
			while(!(line = zz2.readLine()).equals("<h2> Recent Post </h2>"))
			{
				pp.println(line);
			}
			pp.println(line);
			zz2.readLine();
			pp.println("<h3> <a href=\""+ url+".html\" >"+title+"</a> </h3>");
			zz2.readLine();
			pp.println("<p> "+summary+" </p>");
			zz2.readLine();
			pp.println("<h2> All Posts </h2>");
			zz2.readLine();
			pp.println("<ul>");
			pp.println("<li>"+datetodisplay+"&nbsp &nbsp &nbsp &nbsp <a href=\""+url+".html\">"+title+"</a></li>");
			while(!(line = zz2.readLine()).equals("</html>"))
			{
				pp.println(line);
			}
			pp.println(line);

			zz2.close();
			index.close();


			pp.close();
			fileWriter2.close();
		}
	}
}