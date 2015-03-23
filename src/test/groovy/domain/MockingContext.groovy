package domain

import groovy.lang.Delegate;

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile

import basar.BasarApplication;
import basar.domain.Basar;

@Configuration
class MockingContext extends BasarApplication {

    @Bean
	@Primary
    Basar basar(){
        new DelegatingBasar()
    }

}

class DelegatingBasar implements Basar {
	@Delegate Basar delegate
}
