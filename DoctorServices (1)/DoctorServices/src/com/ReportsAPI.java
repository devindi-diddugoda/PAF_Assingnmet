package com;

import model.Report;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

	/**
	 * 
	 * Servlet implementation class ReportsAPI
	 */

	@WebServlet("/ReportsAPI")
	public class ReportsAPI extends HttpServlet 
	{
		private static final long serialVersionUID =1L;
		
		Report reportObject = new Report();

		//private static String queryString;
		
		/**
		 * @see HttpServlet#HttpServlet()
		 */
		
		public ReportsAPI()
		{
			super();
			//TODO Auto-generated constructor stub
		}
		
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
		 * 		response)
		 */
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
			//NOT USED
		}
		
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
		 * 		response)
		 */
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
			String output = reportObject.insertReport(request.getParameter("hospital_id"), 
													  request.getParameter("appid"), 
													  request.getParameter("pat_id"), 
													  request.getParameter("DOC_id"), 
													  request.getParameter("Date"),
													  request.getParameter("Time"), 
													  request.getParameter("Description")); 
													  
			response.getWriter().write(output);
		}
		
		/**
		 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse
		 * 		response)
		 */
		
		protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
			Map paras = getParasMap(request);
			
			String output = reportObject.updateReport(paras.get("hideREP_idSave").toString(), 
													  paras.get("hospital_id").toString(), 
													  paras.get("appid").toString(), 
													  paras.get("pat_id").toString(), 
													  paras.get("DOC_id").toString(), 
													  paras.get("Date").toString().replace('+', ' '), 
													  paras.get("Time").toString().replace('+', ' '),  
													  paras.get("Description").toString().replace('+', ' '));
													  
			response.getWriter().write(output);
		}
		
		/**
		 * @see HttpServlet#doDelete(HttpServletRequest request, HttpServletResponse
		 * 		response)
		 */
		
		protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
			Map paras = getParasMap(request);
			
			String output = reportObject.deleteReport(paras.get("REP_id").toString());
													  
			response.getWriter().write(output);
		}
		
		//Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request)
		{
			Map<String, String> map = new HashMap<String, String>();
			
			try
			{
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
				String quearyString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
				
				scanner.close();
				
				String queryString = null;
				String[] params = queryString.split("&");
				for (String param : params)
				{
					String[] p = param.split("=");
					map.put(p[0], p[1]);
				}
			}
			catch (Exception e)
			{
			}
			return map;
		}
	}






