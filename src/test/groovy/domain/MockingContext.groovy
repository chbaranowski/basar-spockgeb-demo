package domain

import groovy.lang.Delegate;

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("mocking")
class MockingContext {

    @Bean
    Basar basar(){
        new DelegatingBasar()
    }

}

class DelegatingBasar implements Basar {
	@Delegate Basar delegate
}
