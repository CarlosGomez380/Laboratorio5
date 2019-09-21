package edu.eci.cvds.servlet;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.io.IOException;
import java.io.Writer;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.eci.cvds.servlet.model.Todo;
import edu.eci.cvds.servlet.Service;

@WebServlet(
    urlPatterns = "/DiceServlet"
)

public class PuntoOnceServlet extends HttpServlet{
    static final long serialVersionUID = 35L;

    @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Writer responseWriter = resp.getWriter();
       Optional<String> optName = Optional.ofNullable(req.getParameter("name"));
       String name = optName.isPresent() && !optName.get().isEmpty() ? optName.get() : "";
	   
	   try{
		   int id = Integer.parseInt(name);
		   if(id>=1 && id<=200){
			   ArrayList<Todo> todos = new ArrayList<Todo>();
			   todos.add(Service.getTodo(id));
			   Service.todosToHTMLTable(todos);
			   responseWriter.write(Service.todosToHTMLTable(todos));
			   resp.setStatus(HttpServletResponse.SC_OK);
			   responseWriter.flush();
		   }else{
			   resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			   responseWriter.flush();
		   }
	   }catch(NumberFormatException e){
		   resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		   responseWriter.flush();
	   }catch(MalformedURLException e){
		   resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		   responseWriter.flush();
	   }catch(Exception e){
		   resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		   responseWriter.flush();
	   }	        
   }
   
    @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Writer responseWriter = resp.getWriter();
       Optional<String> optName = Optional.ofNullable(req.getParameter("id"));
       String id1 = optName.isPresent() && !optName.get().isEmpty() ? optName.get() : "";
	   
		try{
			int id = Integer.parseInt(id1);
			if(id>=1 && id<=200){
				ArrayList<Todo> todos = new ArrayList<Todo>();
				todos.add(Service.getTodo(id));
				Service.todosToHTMLTable(todos);
				responseWriter.write(Service.todosToHTMLTable(todos));
				resp.setContentType("text/html");
				responseWriter.flush();
			}else{
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				responseWriter.flush();
			}
		}catch(MalformedURLException e){
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			responseWriter.flush();
		}catch(Exception e){
		   resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		   responseWriter.flush();
		}
   }
   
   
   
   
   
   
   
   
}