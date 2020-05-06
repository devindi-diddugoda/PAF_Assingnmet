package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Doctor;

/**
 * Servlet implementation class DoctorsAPI
 */

@WebServlet("/DoctorsAPI")

public class DoctorsAPI extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	Doctor doctorObject = new Doctor();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    
	public DoctorsAPI() {
        
		super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//NOT USED
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			//response.setContentType("application/json");
			String output = doctorObject.insertDoctor(request.getParameter("firstname"),
										request.getParameter("lastname"),
								        request.getParameter("age"),
								        request.getParameter("gender"),
								        request.getParameter("email"),
								        request.getParameter("phonenumber"),
								        request.getParameter("address"),
								        request.getParameter("specialization"),
								        request.getParameter("password")); 
			
			response.getWriter().write(output);
		}
	
	/**
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse
	 * 		response)
	 */
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//response.setContentType("application/json");
		Map paras = getParasMap(request);
		
		String output = doctorObject.updateDoctor(paras.get("hideDOC_idSave").toString(), 
												  paras.get("firstname").toString().replace('+', ' '),
												  paras.get("lastname").toString().replace('+', ' '), 
												  paras.get("age").toString(),
												  paras.get("gender").toString().replace('+', ' '), 
												  paras.get("email").toString().replace("%40", "@"),
												  paras.get("phonenumber").toString(), 
												  paras.get("address").toString().replace('+', ' '), 
												  paras.get("specialization").toString().replace('+', ' '), 
												  paras.get("password").toString().replace('+', ' '));
												  
		response.getWriter().write(output);
	}
	
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest request, HttpServletResponse
	 * 		response)
	 */
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//response.setContentType("application/json");
		Map paras = getParasMap(request);
		
		String output = doctorObject.deleteDoctor(paras.get("DOC_id").toString());
												  
		response.getWriter().write(output);
	}
	
	//Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String quearyString = scanner.hasNext() ? 
								  scanner.useDelimiter("\\A").next() : "";
			
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











