package com.xiaolan.config;

import com.xiaolan.common.utils.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "security")
@PropertySource("classpath:config/security.properties")
public class WebSecurityConfigSettings {
	static public final String LOGIN_PAGE = "/login";// 登录页面的访问地址
	static public final String DEFAULT_SUCCESS_URL = "/index";// 登录成功后转向的页面
	static public final String FAILURE_URL = "/login?error";// 登录失败后转向的页面
	static public final String LOGOUT_URL = "/logout";// 注销的URL路径
	static public final String LOGOUT_SUCCESS_URL = LOGIN_PAGE;// 注销成功后转向的页眉
	static public final String HOME = "/home";
	static public final Integer TOKEN_VALIDITY_SECONDS = 604800;// 指定cookie有效期为604800秒

	private String loginPage = LOGIN_PAGE;// 登录页面的访问地址
	private String defaultSuccessUrl = DEFAULT_SUCCESS_URL;// 登录成功后转向的页面
	private String failureUrl = FAILURE_URL;// 登录失败后转向的页面
	private String logoutUrl = LOGOUT_URL;// 注销的URL路径
	private String logoutSuccessUrl = LOGOUT_SUCCESS_URL;// 注销成功后转向的页眉
	private String permitUrl = HOME;// #不用登录,允许访问的地址(多个用,英文逗号隔开)
	private String tokenValiditySeconds = null;// 指定cookie有效期为604800秒
	private String ignoringMatchers = "";

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public String getDefaultSuccessUrl() {
		return defaultSuccessUrl;
	}

	public void setDefaultSuccessUrl(String defaultSuccessUrl) {
		this.defaultSuccessUrl = defaultSuccessUrl;
	}

	public String getFailureUrl() {
		return failureUrl;
	}

	public void setFailureUrl(String failureUrl) {
		this.failureUrl = failureUrl;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public String getLogoutSuccessUrl() {
		return logoutSuccessUrl;
	}

	public void setLogoutSuccessUrl(String logoutSuccessUrl) {
		this.logoutSuccessUrl = logoutSuccessUrl;
	}

	public String getPermitUrl() {
		return permitUrl;
	}

	public String[] getPermitUrlArray() {
		return permitUrl.split(",");
	}

	public void setPermitUrl(String permitUrl) {
		this.permitUrl = permitUrl;
	}

	public String getTokenValiditySeconds() {
		return tokenValiditySeconds;
	}

	public int getIntTokenValiditySeconds() {
		try {
			if (tokenValiditySeconds == null && tokenValiditySeconds.trim().length() == 0) {
				return TOKEN_VALIDITY_SECONDS;
			}
			return Integer.valueOf(tokenValiditySeconds);
		} catch (NumberFormatException e) {
			return StringUtils.calculateToInteger(tokenValiditySeconds);
		}

	}

	public void setTokenValiditySeconds(String tokenValiditySeconds) {
		this.tokenValiditySeconds = tokenValiditySeconds;
	}

	public String getIgnoringMatchers() {
		return ignoringMatchers;
	}

	public String[] getIgnoringMatchersArray() {
		return ignoringMatchers.split(",");
	}

	public void setIgnoringMatchers(String ignoringMatchers) {
		this.ignoringMatchers = ignoringMatchers;
	}

	@Override
	public String toString() {
		return "SecuritySettings [loginPage=" + loginPage + ", defaultSuccessUrl=" + defaultSuccessUrl
				+ ", failureUrl=" + failureUrl + ", logoutUrl=" + logoutUrl + ", logoutSuccessUrl=" + logoutSuccessUrl
				+ ", permitUrl=" + permitUrl + ", tokenValiditySeconds=" + tokenValiditySeconds + "]";
	}

}
