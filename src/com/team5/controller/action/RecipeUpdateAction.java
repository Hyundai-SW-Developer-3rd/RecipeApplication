package com.team5.controller.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.team5.dao.RecipeDAO;
import com.team5.dao.UserDAO;
import com.team5.vo.RecipeVO;
import com.team5.vo.UserVO;



/**
 * @author    : seop
 * @Date      : 2022. 3. 11.
 * @ClassName : RecipeDeleteAction
 * @Comment   : 레시피 수정 Action
 */
public class RecipeUpdateAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "app?command=recipe_view";
		
		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        RecipeVO recipeVO = new RecipeVO();
        
        recipeVO.setTitle(request.getParameter("title"));
        recipeVO.setIntro(request.getParameter("intro"));
        recipeVO.setCategory(request.getParameter("category"));
        recipeVO.setIngredients(request.getParameter("ingredients"));
        recipeVO.setDetails(request.getParameter("details"));
        recipeVO.setImage(request.getParameter("image"));
		
		
        if (loginUser == null) {
			url = "app?command=login_form";
		}
		else {
			url += "&recipeId="+recipeId;
			RecipeDAO recipeDAO = RecipeDAO.getInstance();
			recipeDAO.updateRecipe(recipeId,recipeVO);

			System.out.println("RecipeUpdateAction");
		}
		
        response.sendRedirect(url);
	}
}

