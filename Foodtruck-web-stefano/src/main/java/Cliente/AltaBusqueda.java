package Cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.ClienteDAO;
import data.PedidoDAO;
import entities.Cliente;
import entities.Pedido;

/**
 * Servlet implementation class AltaBusqueda
 */
@WebServlet("/AltaBusqueda")
public class AltaBusqueda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaBusqueda() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
    	HttpSession session = request.getSession();
    	
		String nombre =  request.getParameter("nombre");
		String dni =  request.getParameter("dni");
		String direccion =  request.getParameter("direccion");
		
		PedidoDAO pdao = new PedidoDAO();
		ClienteDAO cdao = new ClienteDAO();
		Cliente c = new Cliente();
		Cliente busquedaC = new Cliente();
		
		c.setDni(dni);
		c.setDireccion(direccion);
		c.setNombre(nombre);
		
		busquedaC = cdao.getCliente(c);
		
		if(busquedaC != null) {

		Pedido pedido = (Pedido)session.getAttribute("pedido");
		pedido.setCliente(c);
		session.setAttribute("pedido", pedido);
		
		pdao.newPedido(pedido);
		response.sendRedirect("ListadoPedidoDeliveri");
			
		}
		else {
		
			cdao.newCliente(c);
			Pedido pedido = (Pedido)session.getAttribute("pedido");
			pedido.setCliente(c);
			session.setAttribute("pedido", pedido);
			pdao.newPedido(pedido);
			response.sendRedirect("ListadoPedidoDeliveri");
			
			
			
			
		}
		
	}

}
