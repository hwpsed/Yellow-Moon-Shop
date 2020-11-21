/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongtq.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import truongtq.daos.ProductDAO;
import truongtq.dtos.ProductDTO;
import truongtq.dtos.ShoppingCart;

/**
 *
 * @author harry
 */
public class AddToCartController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AddToCartController.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            if (cart == null) {
                cart = new ShoppingCart();
            }

            int id = Integer.parseInt(request.getParameter("txtId"));

            ProductDAO dao = new ProductDAO();
            ProductDTO dto = dao.getProductById(id);
            dto.setStock(1);

            cart.addToCart(dto);

            session.setAttribute("cart", cart);

        } catch (Exception ex) {
            LOGGER.error("AddOrderDetailController " + ex.getMessage());
        } finally {
//            request.getRequestDispatcher("index.jsp").forward(request, response);
            response.sendRedirect("index.jsp");
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
