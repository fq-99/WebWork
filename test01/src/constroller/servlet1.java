package constroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import entity.User;


@WebServlet("/servlet/servlet1")
public class servlet1 extends HttpServlet {

	

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.setIntHeader("Refresh", 5);

		response.setContentType("text/html;charset=UTF-8");
		Date tasktime=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd:mm:ss");  //设置日期输出格式
		//格式化输出
		String nowTime = df.format(tasktime);
		PrintWriter out = response.getWriter();
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String userCode = request.getParameter("code");
//		System.out.println(name +"---"+ password);
		// 判断验证码是否正确
		HttpSession session = request.getSession();
		String code = (String)session.getAttribute("vCode");
		if(code.equals(userCode)){
			// 验证码输入正确
			// 通过用户名和密码进行验证
			UserDao userDao = new UserDao();
			User user = userDao.login(userName);
			if(null != user && null != user.getUserName()){
				//用户名存在
				if(user.getPassword().equals(password)){
					//密码正确登陆成功
					request.setAttribute("user", user);
					session.setAttribute("loginUser", user);
					request.getRequestDispatcher("/success.jsp").forward(request, response); 
				}else{
					// 密码错误登陆失败
					request.setAttribute("info","密码错误，登陆失败");
					request.getRequestDispatcher("/error.jsp").forward(request, response); 
				}
			}else{
				//用户名不存在
				request.setAttribute("info","用户名不存在请重新输入");
				request.getRequestDispatcher("/error.jsp").forward(request,response); 
			}
		}else{
			// 验证码验证失败
			request.setAttribute("info","请输入正确的验证码");
			request.getRequestDispatcher("/error.jsp").forward(request,response); 
		}
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		Date tasktime=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd:mm:ss");  //设置日期输出格式
		//格式化输出
		String nowTime = df.format(tasktime);
		PrintWriter out = response.getWriter();
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String userCode = request.getParameter("code");
//		System.out.println(name +"---"+ password);
		// 判断验证码是否正确
		HttpSession session = request.getSession();
		String code = (String)session.getAttribute("vCode");
		if(code.equals(userCode)){
			// 验证码输入正确
			// 通过用户名和密码进行验证
			UserDao userDao = new UserDao();
			User user = userDao.login(userName);
			if(null != user && null != user.getUserName()){
				//用户名存在
				if(user.getPassword().equals(password)){
					//密码正确登陆成功
					request.setAttribute("user", user);
					request.getRequestDispatcher("/success.jsp").forward(request, response); 
				}else{
					// 密码错误登陆失败
					request.setAttribute("info","密码错误，登陆失败");
					request.getRequestDispatcher("/error.jsp").forward(request, response); 
				}
			}else{
				//用户名不存在
				request.setAttribute("info","用户名不存在请重新输入");
				request.getRequestDispatcher("/error.jsp").forward(request,response); 
			}
		}else{
			// 验证码验证失败
			request.setAttribute("info","请输入正确的验证码");
			request.getRequestDispatcher("/error.jsp").forward(request,response); 
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		
		// Put your code here
	}

}
