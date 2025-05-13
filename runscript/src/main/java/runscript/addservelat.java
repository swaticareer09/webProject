package runscript;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/runscript")
public class addservelat extends HttpServlet {
	
	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String param1 = request.getParameter("param1"); // e.g., from a form field
	        String param2 = request.getParameter("param2"); // e.g., from a form field

	        ProcessBuilder processBuilder = new ProcessBuilder("/home/investwell/code/InvestwellAutomation/script.sh",param1,param2);
	        processBuilder.redirectErrorStream(true);
	        Process process = processBuilder.start();

	        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	        int exitCode = -1;
		    try {
		      exitCode = process.waitFor();
		    } catch (InterruptedException e) {
		      e.printStackTrace();
		    }
	        String line;
	        response.setContentType("text/plain");
	        PrintWriter out = response.getWriter();
	        while ((line = reader.readLine()) != null) {
	            out.println(line);
	        }
	        if (exitCode == 0) {
		        out.println("Script executed successfully:\n" + reader.toString());
		    } else {
		        out.println("Script execution failed with code " + exitCode + ":\n" + reader.toString());
		    }
	    }

//	@Override
//	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
//	      throws ServletException, IOException {
//	    String scriptPath = "/home/investwell/code/InvestwellAutomation/script.sh"; // Replace with the actual path
//	    String[] command = {"sh", scriptPath};
//	    Process process = new ProcessBuilder(command).start();
//
//	    StringBuilder output = new StringBuilder();
//	    try (java.util.Scanner scanner = new java.util.Scanner(process.getInputStream())) {
//	      while (scanner.hasNextLine()) {
//	        output.append(scanner.nextLine()).append("\n");
//	      }
//	    }
//
//	    int exitCode = -1;
//	    try {
//	      exitCode = process.waitFor();
//	    } catch (InterruptedException e) {
//	      e.printStackTrace();
//	    }
//
//	    response.setContentType("text/plain");
//	    PrintWriter out = response.getWriter();
//	     if (exitCode == 0) {
//	        out.println("Script executed successfully:\n" + output.toString());
//	    } else {
//	        out.println("Script execution failed with code " + exitCode + ":\n" + output.toString());
//	    }
//	  }
//		
		
	
}
