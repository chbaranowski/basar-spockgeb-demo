package domain

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

import static org.mockito.Mockito.mock

@Configuration
@Profile("mocking")
class MockingContext {

    @Bean
    Basar basar(){
        mock(Basar)
    }

}
