package web.commands;

import business.entities.Sport;
import business.exceptions.UserException;
import business.services.BmiFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManageSportsCommand extends CommandProtectedPage {
    BmiFacade bmiFacade;

    public ManageSportsCommand(String pageToShow, String role) {
        super(pageToShow, role);
        bmiFacade = new BmiFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        String deleteId = request.getParameter("delete");
        String editId = request.getParameter("edit");
        String update = request.getParameter("update");
        if (deleteId != null) {
            int rowsAffected = bmiFacade.deleteSport(Integer.parseInt(deleteId));
            if (rowsAffected > 0) {
                //Update sportsList
                request.getServletContext().setAttribute("sportList", bmiFacade.getAllSports());

            } else {
                request.setAttribute("error", "You cannot delete this sport because it is being used.");
            }
        } else if (editId != null) {
            Sport sport = bmiFacade.getSportById(Integer.parseInt(editId));
            request.setAttribute("sport", sport);
            return "editsportspage";
        } else if (update != null) {
            String name = request.getParameter("name");
            String sportId = request.getParameter("sports_id");
            int rowsAffected = bmiFacade.updateSport(Integer.parseInt(sportId), name);
            if (rowsAffected == 1) {
                request.getServletContext().setAttribute("sportList", bmiFacade.getAllSports());
            }
        }

        return pageToShow;
    }
}
