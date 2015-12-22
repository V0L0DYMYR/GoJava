package ua.com.goit.gojava7.kickstarter.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import ua.com.goit.gojava7.kickstarter.dao.CategoryDao;
import ua.com.goit.gojava7.kickstarter.dao.ProjectDao;
import ua.com.goit.gojava7.kickstarter.dao.RewardDao;

@WebServlet("/rewards")
public class RewardsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RewardDao rewardDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public void init() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		int projectId = Integer.parseInt(request.getParameter("projectId"));
		
		request.setAttribute("category", categoryDao.get(projectDao.get(projectId).getCategoryId()));	
		request.setAttribute("project", projectDao.get(projectId));
		request.setAttribute("rewards", rewardDao.getByProject(projectId));
		request.getRequestDispatcher("/WEB-INF/jsp/rewards.jsp").forward(request, response);
	}
}
