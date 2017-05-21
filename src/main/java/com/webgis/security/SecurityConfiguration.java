package com.webgis.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javafx.fxml.expression.Expression;
import com.webgis.security.ajax.AjaxAuthenticationProvider;
import com.webgis.security.ajax.AjaxLoginProcessingFilter;
import com.webgis.security.jwt.JwtAuthenticationProvider;
import com.webgis.security.jwt.JwtAuthenticationTokenFilter;
import com.webgis.security.jwt.SkipPathRequestMatcher;
import com.webgis.security.jwt.extractor.TokenExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

/**
 * @author 谢天帝
 * @version v0.1 2017/3/17.
 */

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String HEADER_TOKEN = "X-Auth-Token";

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired private TokenExtractor tokenExtractor;
    @Autowired private AjaxAuthenticationProvider ajaxAuthenticationProvider;
    @Autowired private JwtAuthenticationProvider jwtAuthenticationProvider;
    @Autowired private ObjectMapper objectMapper;

    @Autowired private AuthenticationSuccessHandler successHandler;
    public static final String LOGIN_ENTRY_POINT = "/auth/token";
    public static final String REGISTER_ENTRY_POINT = "/account/accounts";
    public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/**";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

        http
                .authorizeRequests()
//                .antMatchers(REGISTER_ENTRY_POINT).permitAll()
//                .antMatchers(LOGIN_ENTRY_POINT).permitAll()
//                .antMatchers(TOKEN_BASED_AUTH_ENTRY_POINT).permitAll()
                .antMatchers( "/**").permitAll()// FOR TEST
                .anyRequest().authenticated();

       // http
                //.addFilterBefore(ajaxLoginProcessingFilterBean(), UsernamePasswordAuthenticationFilter.class)
                //.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        http.headers().cacheControl();

    }

    /**
     * 指定内存用户，For test.
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("admin").roles("USER");
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService);
    }

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1
        corsConfiguration.addAllowedHeader("x-requeed-stwith"); // 2
        corsConfiguration.addAllowedHeader("content-type");
        corsConfiguration.addAllowedMethod("*");
//        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST","PUT","OPTIONS","DELETE"));
        return corsConfiguration;
    }

    protected AjaxLoginProcessingFilter ajaxLoginProcessingFilterBean() throws Exception {
        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(LOGIN_ENTRY_POINT,successHandler,objectMapper);
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        List<String> pathsToSkip = Arrays.asList(REGISTER_ENTRY_POINT,LOGIN_ENTRY_POINT);
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, TOKEN_BASED_AUTH_ENTRY_POINT);
        JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter(tokenExtractor,matcher);
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4
        return new CorsFilter(source);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(ajaxAuthenticationProvider);
        auth.authenticationProvider(jwtAuthenticationProvider);
    }
}
