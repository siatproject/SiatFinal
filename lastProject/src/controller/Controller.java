package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import service.MapService;


@WebServlet("/con")
public class Controller extends HttpServlet {
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getParameter("utf-8");
		String command = request.getParameter("command");
		try {
			if(command.equals("mapPage")) {			//≥¨Ω√ µ•¿Ã≈Õ ∏ ¿∏∑Œ √‚∑¬
				mapPage(request,response);
			}
		}catch(Exception e){
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
	}

	private void mapPage(HttpServletRequest request, HttpServletResponse response) {
		String regin = request.getParameter("regin");
		String fishSpecies = request.getParameter("fishSpecies");
		try {
			ArrayList<JSONObject> alldata = MapService.getSearchData(regin,fishSpecies);
			request.setAttribute("jsonFile", alldata);
			request.getRequestDispatcher("mapData.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
