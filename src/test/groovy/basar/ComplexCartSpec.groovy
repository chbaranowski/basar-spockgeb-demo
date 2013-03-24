package basar

import org.springframework.beans.factory.annotation.Autowired;

import data.User;
import data.UserRepository;
import domain.Basar;
import spock.lang.Stepwise;

@Stepwise
class ComplexCartSpec extends BasarWebSpecification {
    
    @Override
    def enviorment() { "production" }
    
    def testUsers = [new User(basarNumber: "100")]
    
    @Autowired
    Basar basar
    
    def setup() {
        testUsers.each {
            basar.saveUser(it)
        }
    }

    def cleanup() {
        testUsers.each {
            basar.deleteUserWithId(it.getId())
        }
    }
    
    def "When add some items in into the cart"() {
        given:
            to BasarPage
        expect:
            basarForm.with {
                basarNumber = numberValue
                price = priceValue
            }
            def oldCartSize = cartSize
            addButton.click() 
            waitFor {cartSize > oldCartSize}
        where:
            numberValue   | priceValue 
            "100"         |   "10"     
            "100"         |   "10,50"  
    }
    
    def "Then verify that the items are in the cart"() {
        given:
            at BasarPage
        expect:
            basarNumber(cartItem: item) == numberValue
            price(cartItem: item) == priceValue
        where:
            item  |  numberValue   | priceValue
              1   |    "100"       |   "10,50"
              2   |    "100"       |   "10,00"
    }
    
    def "Then verify the sum of the cart is 20,50"() {
        given:
            at BasarPage
        expect:
            sum == "20,50"
    }

}
