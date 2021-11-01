package WebUI.Servlets.Tables;

import WebUI.Constants;
import com.google.gson.Gson;
import data.transfer.objects.BestSolutionDTO;
import data.transfer.objects.DTOEngineInformation;
import data.transfer.objects.HistoryDataDTO;
import evolution.engine.EvolutionEngine;
import evolution.engine.Population;
import javafx.util.Pair;
import time.table.problem.Quintet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GraphDataServlet", urlPatterns = {"/BestSolutionGraph"})
public class GraphDataServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            Gson gson = new Gson();
            List<HistoryDataDTO> historyData = new ArrayList<>();
            EvolutionEngine<Quintet> engine = (EvolutionEngine<Quintet>) request.getSession().getAttribute(Constants.ENGINE);
            //Map<Integer, Pair<Integer, Population>> oldPopulationList = engine.getOldPopulationList();
         /*   for (Pair<Integer,Population> pairPop: oldPopulationList.values()) {
                historyData.add(new HistoryDataDTO(pairPop.getValue().getBestFitnessScore()));
            }*/
            for (Integer num : engine.getMaxFitnessList()) {
                historyData.add(new HistoryDataDTO(num));
            }
            String json = gson.toJson(historyData);
            out.println(json);
            out.flush();
        } catch (Exception e) {
            response.setStatus(401);
            response.getOutputStream().println(e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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