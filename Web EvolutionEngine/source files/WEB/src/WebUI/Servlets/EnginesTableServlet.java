package WebUI.Servlets;

import WebUI.Utils.ServletUtils;
import WebUI.Utils.SessionUtils;
import WebUI.users.Client;
import WebUI.users.ClientsList;
import com.google.gson.Gson;
import data.transfer.objects.EnginesTableDTO;
import evolution.engine.EvolutionEngine;
import time.table.problem.Quintet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class EnginesTableServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<EnginesTableDTO> enginesToShowOnTable = new ArrayList<>();

        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            Gson gson = new Gson();
            String username = SessionUtils.getUsername(request);
            ClientsList clientList = ServletUtils.getClientList(getServletContext());
            Client client = clientList.getClient(username);
            List<EvolutionEngine<Quintet>> enginesList = client.getEnginesList();
            enginesList.forEach(engine -> {
                EnginesTableDTO temp;
                if(engine.getBestSolutionInAllGens()!=null) {
               //     temp = new EnginesTableDTO(engine.getProblemName(), engine.getIsRunning(), engine.getEngineInfo().getInitialPopulation(), engine.getDays(), engine.getHours(), engine.getTimeTable().getTeachers().size(), engine.getTimeTable().getStudyClasses().size(), engine.getTimeTable().getSubjects().size(), engine.getSoftRules(), engine.getHardRules(), engine.getDTOEngine().getBestFitnessint());
                    temp = new EnginesTableDTO(engine.getProblemName(), engine.getIsRunning(), engine.getEngineInfo().getInitialPopulation(), engine.getDays(), engine.getHours(), engine.getTimeTable().getTeachers().size(), engine.getTimeTable().getStudyClasses().size(), engine.getTimeTable().getSubjects().size(), engine.getSoftRules(), engine.getHardRules(), engine.getMaxFitness());
                }else
                {
                    temp = new EnginesTableDTO(engine.getProblemName(), engine.getIsRunning(), engine.getEngineInfo().getInitialPopulation(), engine.getDays(), engine.getHours(), engine.getTimeTable().getTeachers().size(), engine.getTimeTable().getStudyClasses().size(), engine.getTimeTable().getSubjects().size(), engine.getSoftRules(), engine.getHardRules(), 0);
                }
                enginesToShowOnTable.add(temp);

            });
           String json = gson.toJson(enginesToShowOnTable);
            out.println(json);
            out.flush();
        } catch (Exception e) {
            //  response.reset();
            // response.getOutputStream();
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
