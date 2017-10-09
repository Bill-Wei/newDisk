package swun.iot.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorizationInterceptor extends AbstractInterceptor {
	
	private String ignoreActions;
	
	public String getIgnoreActions() {
		return ignoreActions;
	}

	public void setIgnoreActions(String ignoreActions) {
		this.ignoreActions = ignoreActions;
	}

	//重写intercept，实现拦截
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		Map session = ctx.getSession();
		//从session中获取用户名
		String user = (String)session.get("username");
		boolean ignore = false;
		//获得当前Action的名字
		String currentAction = invocation.getProxy().getActionName();
		String[] actions = ignoreActions.split(",");
		for (String action : actions) {
			//通过正则表达式过滤不需要控制的Action
			if (currentAction.matches(action.trim())) {
				ignore = true;
				break;
			}
		}
		if (user != null || ignore == true) {
			return invocation.invoke();
		}else{
			return Action.LOGIN;
		}
			
	}

}
