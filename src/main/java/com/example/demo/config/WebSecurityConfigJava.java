package com.example.demo.config;


import com.example.demo.security.JwtAuthenticationEntryPoint;
import com.example.demo.security.JwtAuthenticationTokenFilter;
import com.example.demo.security.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@SuppressWarnings("SpringJavaAutowiringInspection")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigJava extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()

                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // allow anonymous resource requests
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/**/*.html",
                       "/**/*.css",
                       "/**/*.js"
               ).permitAll()
               .antMatchers("/auth/**",
                            "/user",
                        "/user/activate",
                        "/user/activate/{code}",
                        "/user/{id}",
                        "/user/verify",
                       "/search/users",
                       "/add/hall",
                        "/search/halls",
                        "/edit/user",
                        "/user/retrieve/{email}",
                        "/user/salary/{id}/{salary}",
                        "/msg",
                        "/getmsg",
                       "/create/team/{id}",
                       "/search/teams",
                        "/search/teams/{id}",
                        "/answer",
                        "/delete/msg",
                        "/invite/team",
                        "/invitation/player/{id}",
                        "/halls/inactive",
                       "/halls/activate/{id}",
                       "/invitation/deny/{idPlayer}/{idTeam}",
                       "/invitation/accept/{idPlayer}/{idTeam}",
                       "/team/remove/player/{id}",
                       "/edit/team/{id}",
                       "/team/remove/{id}"

                        ).permitAll()
               .anyRequest().authenticated();

        // Custom JWT based security filter
        httpSecurity
             .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        httpSecurity.headers().cacheControl();
    }
}
