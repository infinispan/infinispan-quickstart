package org.infinispan.quickstart.jbossas7;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/log")
public class LogServlet extends HttpServlet {
   
   static String PAGE_HEADER = "<html><head /><body>\n";

   static String PAGE_FOOTER = "</body></html>";
   
   @Inject @CacheEvents
   List<String> events;
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html");
      PrintWriter writer = resp.getWriter();
      writer.write(PAGE_HEADER);
      writer.write("<h1>Cache Events</h1>\n" + 
                     "<table>");
      for (String event : events)
         writer.write("<tr><td>" + event + "</td></tr>");
      writer.write("</table>\n");
      writer.write(PAGE_FOOTER);
   }

}
