package basar

import groovy.sql.Sql

import javax.sql.DataSource;

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;

import spock.lang.Shared;
import spock.lang.Unroll;

import domain.Basar;
import domain.PriceUtils;
import data.Position;
import data.User;
import data.UserRepository;

import static org.mockito.Mockito.*;

class BasarSpec extends BasarWebSpec {

    User testUser

    @Autowired
    UserRepository userRepository

    @Override
    def enviorment() { "production" }

    def setup() {
        testUser = userRepository.save(new User(basarNumber: "100"))
    }

    def cleanup() {
        userRepository.delete(testUser.getId())
    }

    @Unroll
    def "buy some articles with number='#numberValue' and price='#priceValue'"(String numberValue, String priceValue, String sumValue) {
        given:
            to BasarPage
        when: "add a article into the cart"
            basarForm.with {
                basarNumber = numberValue
                price = priceValue
            }
            addButton.click()
        then: "the article should be in the cart"
            basarNumber(cartItem: 0)    == numberValue
            price(cartItem: 0)          == sumValue
            sum == sumValue
        where:
            numberValue   | priceValue   ||   sumValue
            "100"         |   "10"       ||   "10,00"
            "100"         |   "10,50"    ||   "10,50"
    }
}
