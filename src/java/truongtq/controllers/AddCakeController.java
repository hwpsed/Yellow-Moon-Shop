/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongtq.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import truongtq.daos.ProductDAO;
import truongtq.dtos.AccountDTO;
import truongtq.dtos.ProductDTO;

/**
 *
 * @author harry
 */
public class AddCakeController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AddCakeController.class);
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SearchCakeController";
    private static final String FORM = "insertCake.jsp";

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
        String url = ERROR;
        try {
            AccountDTO user = (AccountDTO) request.getSession().getAttribute("ACCOUNT");
            String action = request.getParameter("add");
            if (user != null) {
                if (user.getRole().equals("admin")) {
                    if (action.equals("Add Cake")) {
                        String name = request.getParameter("txtName");
//                        String image = "images/" + request.getParameter("txtImage");
                        File theFile = new File(request.getParameter("txtImage"));
                        String image = "images/" + theFile.getName();
                        float price = Float.parseFloat(request.getParameter("txtPrice"));
                        int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
                        int categoryId = Integer.parseInt(request.getParameter("listCake"));
                        String expDate = request.getParameter("txtExpDate");
                        String creDate = request.getParameter("txtCreDate");
                        ProductDAO dao = new ProductDAO();
                        ProductDTO product = new ProductDTO(categoryId, name, creDate, expDate, image, price, quantity);
                        if (dao.addProduct(product)) {
                            url = SUCCESS;
                        } else {
                            url = FORM;
                            request.setAttribute("INSERT_ERROR", "Add failed");
                        }
                    } else {
                        url = FORM;
                    }
                } else {
                    request.setAttribute("ERROR", "Your role is not allow to access");
                    request.setAttribute("URL", "SearchCakeController");
                }
            } else {
                request.setAttribute("ERROR", "No permission");
                request.setAttribute("URL", "login.jsp");
            }
        } catch (SQLException ex) {
            LOGGER.error("AddCakeController_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.error("AddCakeController_Naming" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            LOGGER.error("AddCakeController_ClassNotFound" + ex.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
