/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other
 * contributors as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a full listing of
 * individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.infinispan.quickstart.jcache;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

import javax.cache.Cache;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.infinispan.jcache.JCacheManager;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

public class JCacheServlet extends HttpServlet {

	private static final long serialVersionUID = 2886005874009212569L;

	private static final String CACHE_NAME = "my-cache";

	private JCacheManager cacheManager;
	private Cache<String, String> cache;

	public void init() throws ServletException {
		System.out.println(super.getServletName() + ":");
		System.out.print(super.getServletContext().getContextPath());
		EmbeddedCacheManager cm = new DefaultCacheManager(true);
		cacheManager = new JCacheManager(URI.create(this.getClass().getName()),
				cm, null);
		cache = cacheManager.getCache(CACHE_NAME);
		System.out.println("Cache created:" + CACHE_NAME);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String key = getKeyFromContext(request);

		String value = "no such key" + key;
		if (cache.containsKey(key))
			value = cache.get(key);

		buildResponse(value, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		String key = getKeyFromContext(request);
		String value = request.getParameter("value");
		if ( "".equals(value)) 
			throw new IllegalArgumentException("Missing parameter '" + "value" + "' with value associated to key:" + key);
		cache.put(key, value);

		buildResponse(value, response);

	}
	
	public void doDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		String key = getKeyFromContext(request);

		if (cache.containsKey(key))
			cache.remove(key);

		buildResponse("Value associated with key " + key
				+ " has been removed from cache", response);
	}

	public void destroy() {
		cache.close();
		if (!cache.isClosed())
			throw new IllegalStateException("Cache " + CACHE_NAME
					+ " not properly closed");
		cacheManager.close();
		if (!cacheManager.isClosed())
			throw new IllegalStateException("CacheManager not properly closed.");
	}

	private static String getKeyFromContext(HttpServletRequest request) {
		return request.getContextPath().replaceFirst("/", "");
	}

	private static void buildResponse(String message,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<h1>" + message + "</h1>");
		out.flush();
		out.close();
	}
}
