package com.edon.demo.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.edon.demo.model.Vehicle;
import com.edon.demo.model.Role;
import com.edon.demo.model.User;
import com.edon.demo.repository.VehicleRepository;
import com.edon.demo.repository.UserRepository;
import com.edon.demo.services.MyUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
		
		// setup default admin & user for the app
		String encodedPass = new BCryptPasswordEncoder().encode("pass");
		User user1 = new User("George", "Takei", "george@gmail.com", encodedPass, encodedPass);
		user1.addRole(new Role("ADMIN", user1));
		userRepository.save(user1);
		
		User user2 = new User("Edon", "Haziri", "edonhaziri2013@gmail.com", encodedPass, encodedPass);
		user2.addRole(new Role("ADMIN", user2));
		userRepository.save(user2);
		
		User user3 = new User("Let", "Me", "keto@gmail.com", encodedPass, encodedPass);
		user3.addRole(new Role("EMPLOYEE", user3));
		user3.addRole(new Role("CUSTOMER", user3));
		userRepository.save(user3);
		
		User user4 = new User("Jason", "Bourne", "Jason@gmail.com", encodedPass, encodedPass);
		user4.addRole(new Role("EMPLOYEE", user4));
		user4.addRole(new Role("CUSTOMER", user4));
		userRepository.save(user4);
		
		// add some fake vehicles
		Vehicle cons1 = new Vehicle(
			"Small description",
			"1126 Highfield Ct",
			"Bethel Park",
			"Pennsylvania",
			15102,
			"USA",
			14.5,
			"USD"
		);
		cons1.setUser(user1);
		cons1.setOwnerName(user1.getFirstName() + " " + user1.getLastName());
		cons1.setReceived(false);
		user1.addVehicle(cons1);
		vehicleRepository.save(cons1);
		
		Vehicle cons2 = new Vehicle(
			"BMW",
			"153 Christian Ave",
			"Stony Brook",
			"New York",
			11790,
			"USA",
			56.5,
			"USD"
		);
		cons2.setUser(user1);
		cons2.setOwnerName(user1.getFirstName() + " " + user1.getLastName());
		cons2.setReceived(true);
		user1.addVehicle(cons2);
		vehicleRepository.save(cons2);
		
		Vehicle cons3 = new Vehicle(
			"Cloths for summer",
			"61 Ngo Thi Thu Minh",
			"Tan Binh",
			"Ho Chi Minh",
			70000,
			"Vietnam",
			76.5,
			"USD"
		);
		cons3.setReceived(false);
		cons3.setUser(user4);
		cons3.setOwnerName(user4.getFirstName() + " " + user4.getLastName());
		cons3.setAssignedEmployee(user3);
		cons3.setAssignedUserName(user3.getFirstName() + " " + user3.getLastName());
		user3.assignVehicle(cons3);
		user4.addVehicle(cons3);
		vehicleRepository.save(cons3);

	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    
		http.csrf().disable().authorizeRequests()
			.antMatchers("/users/all").hasAuthority("ADMIN")
			.antMatchers("/roles/**").hasAuthority("ADMIN")
			.antMatchers("/admin/**").hasAuthority("ADMIN")
			.antMatchers("/users/**").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/register").permitAll()
			.antMatchers("/sendingEmail").permitAll()
			.anyRequest().authenticated()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// excluded the preflight requests from authorization 
		// our API expects an authorization token in the OPTIONS request as well
		// therefore, we need to exclude OPTIONS requests from authorization checks
		http.cors();
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}	


	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
}
