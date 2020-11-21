/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongtq.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import truongtq.daos.ProductDAO;
import truongtq.dtos.ProductDTO;

/**
 *
 * @author harry
 */
public class SearchCakeController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SearchCakeController.class);

    public static final String LOGIN = "login.jsp";
    public static final String SUCCESS = "index.jsp";
    public static final String ERROR = "error.jsp";

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

            String search = request.getParameter("txtSearch");

            String price1 = request.getParameter("txtPrice1");
            String price2 = request.getParameter("txtPrice2");

            ProductDAO dao = new ProductDAO();

            search = search == null ? "" : search;
            
            price1 = price1 == null ? "" : price1;
            price2 = price2 == null ? "" : price2;
            List<ProductDTO> list = null;

            //Redirect to page 1 if F5
            int index = 1;
            if (request.getParameter("page") != null) {
                index = Integer.parseInt(request.getParameter("page"));
            }
            String btnAction = request.getParameter("btnAction");
            if (btnAction != null) {
                if (btnAction.equals("Next")) {
                    ++index;
                }
                if (btnAction.equals("Previous")) {
                    --index;
                }
            }
            request.setAttribute("INDEX", index);

            int pageSize = 20;
            int totalPage = 0;

            if (!price1.isEmpty() && !price2.isEmpty() && search.isEmpty()) {
                list = dao.findProductByPrice(price1, price2, index);
                totalPage = dao.findTotalPagePrice(price1, price2);
                request.setAttribute("INFO", list);
                url = SUCCESS;
            } else if (price1.isEmpty() && price2.isEmpty()){
                list = dao.findProductByName(search, index);
                totalPage = dao.findTotalPage(search);
                request.setAttribute("INFO", list);
                url = SUCCESS;
            } else {
                list = dao.findProductByPrice(price1, price2, index);
                totalPage = dao.findTotalPagePrice(price1, price2);
                request.setAttribute("INFO", list);
                url = SUCCESS;
            }
            
            int total = totalPage / pageSize;
            if (totalPage % pageSize > 0) {
                total++;
            }
            request.setAttribute("SIZE", total);

        } catch (SQLException ex) {
            LOGGER.error("SearchCakeController_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.error("SearchCakeController_Naming" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            LOGGER.error("SearchCakeController_ClassNotFound" + ex.getMessage());
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
