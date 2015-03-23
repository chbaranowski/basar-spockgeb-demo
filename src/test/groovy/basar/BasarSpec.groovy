package basar

import geb.spock.GebSpec;
import groovy.sql.Sql

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import basar.data.Position;
import basar.data.User;
import basar.data.UserRepository;
import basar.domain.Basar;
import basar.domain.PriceUtils;
import spock.lang.Shared;
import spock.lang.Unroll;

@ContextConfiguration(loader = SpringApplicationContextLoader, classes = [BasarApplication])
@WebAppConfiguration
@IntegrationTest('server.port:0')
class BasarSpec extends GebSpec {

    User testUser

    @Autowired
    Basar basar
	
	@Value('${local.server.port}')
	int port

    def setup() {
		browser.baseUrl = "http://localhost:${port}"
        testUser = new User(basarNumber: "100")
        basar.saveUser(testUser)
    }

    def cleanup() {
        basar.deleteUserWithId(testUser.getId())
    }

    def "add article to cart with number='#numberValue' and price='#priceValue'"(String numberValue, String priceValue, String sumValue) {
        given:
            to BasarPage
        when: "add a article into the cart"
            basarForm.with {
                basarNumber = numberValue
                price = priceValue
            }
            addButton.click()
        then: "the article should be in the cart"
            basarNumber(cartItem: 1) == numberValue
            price(cartItem: 1) == sumValue
            sum == sumValue
        where:
            numberValue   | priceValue   ||   sumValue
            "100"         |   "10"       ||   "10,00"
            "100"         |   "10,50"    ||   "10,50"
    }
}
