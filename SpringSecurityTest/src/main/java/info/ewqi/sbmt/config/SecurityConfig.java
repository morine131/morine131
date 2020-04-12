package info.ewqi.sbmt.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity web)throws Exception{

		web.formLogin(). //form認証を行う
			loginPage("/login"). //ログインフォームがあるページ
			defaultSuccessUrl("/"). //ログイン成功時に遷移するページ
			failureUrl("/login-error"). //エラー時に遷移するページ
			permitAll(); //全てのユーザーはログインページにアクセスできる

		// /css,/images,/js下にあるファイルはログインせずアクセス可能
		web.authorizeRequests().
			antMatchers("/css/**", "/images/**", "/js/**").permitAll().anyRequest().authenticated();
	}
}