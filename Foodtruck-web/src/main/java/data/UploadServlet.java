package data;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.File;
import javax.servlet.http.Part;

import entities.Plato;

/**
 * Servlet implementation class UploadServlet
 */

@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
	maxFileSize=1024*1024*50,      	// 50 MB
	maxRequestSize=1024*1024*100)   	// 100 MB
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String UPLOAD_DIR = "uploads";
	
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/altaPlato.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//getInfoByForm
		 String nombre = request.getParameter("nombre");
		 float precio = Float.parseFloat(request.getParameter("precio"));
		 String descripcion = request.getParameter("descripcion");
		 Plato p = new Plato(nombre,precio,descripcion);
	        
		
        // gets absolute path of the web application
        String applicationPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
        
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
        
        String fileName = null;
        //Get all the parts from request and write it to the file on server
        for (Part part : request.getParts()) {
            fileName = getFileName(part);
            part.write(uploadFilePath + File.separator + fileName);
        }
        
        
        p.setFoto(UPLOAD_DIR+File.separator +fileName);
        PlatoDAO pdao = new PlatoDAO();
        pdao.newPlato(p);
        System.out.println("deberia guardarlo en bd");
       // request.getRequestDispatcher("WEB-INF/listadoPlatos").forward(request, response);
 
        /*
        request.setAttribute("message", fileName + " File uploaded successfully!");
        request.setAttribute("img", UPLOAD_DIR+File.separator +fileName);
        getServletContext().getRequestDispatcher("/WEB-INF/response.jsp").forward(
                request, response);
        */
        
	}
	
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }

}