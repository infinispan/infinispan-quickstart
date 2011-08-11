package org.infinispan.quickstart.jbossas7;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.infinispan.Cache;

@WebServlet(urlPatterns="/seed")
public class SeedDataServlet extends HttpServlet {
   
   static String PAGE_HEADER = "<html><head /><body>\n";

   static String PAGE_FOOTER = "</body></html>";
   
   @Inject
   Cache<Object, Object> cache;
   
   public void seedData() {
      for (int i = 0; i < 10; i++) {
         cache.put("key" + i, "value" + i);
      }
   }
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      seedData();
      
      PrintWriter writer = resp.getWriter();
      writer.write(PAGE_HEADER);
      writer.write("<h1>Data seeded</h1>\n" +
            "<p>Visit the <a href=\"log\">Log</a> to view output</p>");
      writer.write(PAGE_FOOTER);
   }

}
