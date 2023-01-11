package vn.ansv.management.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;

/**
 * @author pavan.solapure
 *
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/error", "/css/**", "/js/**", "/fonts/**", "/images/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/login").permitAll() // Allow everyone can access
				.antMatchers("/**").fullyAuthenticated() // Must do login
				.and().formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/appLogin")
				.usernameParameter("username")
				.passwordParameter("password")
				.defaultSuccessUrl("/dashboard") // Login success -> Return dashboard page
				.and().logout()
				.logoutUrl("/appLogout")
				.logoutSuccessUrl("/login") // Logout success -> Return login page
				.and().exceptionHandling()
				.accessDeniedPage("/accessDenied");
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.ldapAuthentication()
				.userSearchBase("CN=Users,DC=ansv,DC=vn")
				.userSearchFilter("(&(objectClass=user)(userPrincipalName={0}))")
				.ldapAuthoritiesPopulator(ldapAuthoritiesPopulator())
				.contextSource()
				.url("ldap://172.24.104.6:389")
				.managerDn("CN=Le Van Thanh,CN=Users,DC=ansv,DC=vn")
				.managerPassword("Thanh0204");
	}

	/**
	 * populator that doesn't check user's attributes
	 * just return ROLE_USER
	 * 
	 * @return
	 */
	private LdapAuthoritiesPopulator ldapAuthoritiesPopulator() {
		return new LdapAuthoritiesPopulator() {
			@Override
			public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData,
					String username) {
				return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
			}
		};
	}

	/**
	 * not required
	 * to have UserDetailsService as bean for using in other components
	 */
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		return super.userDetailsService();
	}
}
