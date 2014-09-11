package org.a805.struts2.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.a805.service.SystemService;
import org.a805.service.UserService;
import org.a805.tools.DateFormatTransfer;
import org.apache.struts2.ServletActionContext;

import com.hibernate.entity.TLoginLog;
import com.hibernate.entity.TUser;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private String username;
	private String pwd;
	private String recertCode;
	private UserService userService;
	private SystemService systemService;
	private String oldPwd;
	private String newPwd;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRecertCode() {
		return recertCode;
	}

	public void setRecertCode(String recertCode) {
		this.recertCode = recertCode;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * ��¼����
	 * 
	 * @return
	 */
	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String certCode = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("certCode");
		
		if(recertCode == null){
			recertCode = "";
		}
		
		

		// �û��ѵ�¼
		if (session.getAttribute("loginName") != null) {
			return "success";
		}

		// ��֤�����
		if (!this.recertCode.equals(certCode)) {
			return "error";
		}

		// ��֤�˻�����	
		TUser tUser = userService.checkUser(username);
		if (tUser == null) {
			//�û���������
			return "error";
		}
		else if(tUser.getPwd().equals(pwd)){
			//��֤ͨ��,��ʼд��¼��־
			TLoginLog loginLog = new TLoginLog();
			String loginHost = request.getRemoteHost();// ��õ�¼�ͻ��˵���������
			String loginIp = request.getRemoteAddr();// ��õ�¼�ͻ��˵�IP
			Date date = new Date();
			
			loginLog.setUsername(tUser.getUsername());
			loginLog.setRealname(tUser.getRealname());
			loginLog.setLoginHost(loginHost);
			loginLog.setLoginIp(loginIp);
			loginLog.setLoginTime(new Date());
					
			
			systemService.save(loginLog);
		
			
			session.setAttribute("loginName", tUser.getRealname());
			session.setAttribute("loginer", tUser);
			return "success";
		}
		else{
			//�������
			return "error";
		}


	}


	/**
	 * ע��
	 * 
	 * @return
	 */
	public String register() {
		

		return "success";
	}
	/**
	 * ���µ�¼
	 */
	public String reLogin(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.invalidate();
		
		return "success";
	}
	
	/**
	 * �޸�����
	 * @return
	 */
	public String resetPwd(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		TUser tuser = (TUser)session.getAttribute("loginer");
		String flag = userService.changePwd(tuser, oldPwd, newPwd);
		if(flag.equals("error")){
			request.setAttribute("msg", "ԭ�������");
			return "error";
		}
		request.setAttribute("msg", "�����޸ĳɹ���");
		return "success";
	}

}
