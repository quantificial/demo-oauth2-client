package demo.oauth2.client;

import java.security.Principal;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableOAuth2Client
public class DemoOauth2ClientApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(DemoOauth2ClientApplication.class, args);
	}
	

	@Autowired
	OAuth2ClientContext oauth2ClientContext;

	@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.antMatcher("/**").authorizeRequests()
			.antMatchers("/", "/login**", "/webjars/**", "/error**","/fonts/**").permitAll()
			.anyRequest().authenticated().and().exceptionHandling()
				.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
			.and()
				.logout()
				.logoutSuccessUrl("/").permitAll()
				.logoutSuccessUrl("http://localhost:8081/auth/exit")
			.and()
				.csrf().disable()
			//.and()
			//	.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			;
		
			http
				.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
		// @formatter:on
	}
	
	@Bean
	public FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
		FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<OAuth2ClientContextFilter>();
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}

	private Filter ssoFilter() {
		
		// the entry point for the application sso login
		OAuth2ClientAuthenticationProcessingFilter oauth2Filter = 
				new OAuth2ClientAuthenticationProcessingFilter("/login/sso");
		
		// create the oauth2 rest template for the communication with oauth2 server
		OAuth2RestTemplate oauth2Template = 
				new OAuth2RestTemplate(appAuthorizationCodeResourceDetails(), oauth2ClientContext);
				
		
		oauth2Filter.setRestTemplate(oauth2Template);
		
		UserInfoTokenServices tokenServices = new UserInfoTokenServices(appResourceServerProperties().getUserInfoUri(),
				appAuthorizationCodeResourceDetails().getClientId());
		
		tokenServices.setRestTemplate(oauth2Template);
		oauth2Filter.setTokenServices(tokenServices);
		
		return oauth2Filter;
	}

	@Bean
	@ConfigurationProperties("application.client")
	public AuthorizationCodeResourceDetails appAuthorizationCodeResourceDetails() {
		return new AuthorizationCodeResourceDetails();
	}

	@Bean
	@ConfigurationProperties("application.resource")
	public ResourceServerProperties appResourceServerProperties() {
		return new ResourceServerProperties();
	}	

}
