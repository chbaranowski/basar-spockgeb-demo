package basar

import geb.bind.spock.GebBindSpec;
import geb.spock.GebSpec;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import basar.data.User;
import basar.domain.Basar;

@ContextConfiguration(classes = [BasarApplication])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BasarBindSpec extends GebBindSpec {

    User testUser

    @Autowired
    Basar basar
	
	@LocalServerPort
	int port

    def setup() {
		browser.baseUrl = "http://localhost:${port}"
        testUser = new User(basarNumber: "100")
        basar.saveUser(testUser)
    }

    def cleanup() {
        basar.deleteUserWithId(testUser.getId())
    }

    def "add a item to the cart basarNumber='#basarNumber' price='#price'"() {
        given:
        BasarBind basar = start BasarBind

        when:
        basar {
           enter basarNumber into basarNumberField
           enter price into priceField
           click addButton
        }

        then:
        basar.sum == sum

        where:
        basarNumber | price  ||   sum
        '100'       | '10'   || '10,00'
        '100'       | '0,50' || '0,50'
    }


    def "add two item to the cart"() {
        given:
        BasarBind basar = start BasarBind

        when:
        basar {
            enter '100' into basarNumberField
            enter '10' into priceField
            click addButton
            enter '100' into basarNumberField
            enter '20,50' into priceField
            click addButton
        }

        then:
        basar.sum == '30,50'
        basar.cartContentTable.size() == 2
        basar.cartContentTable[0][0].text() == '100'
        basar.cartContentTable[0][1].text() == '20,50'
        basar.cartContentTable[1][0].text() == '100'
        basar.cartContentTable[1][1].text() == '10,00'
    }

    def "add a item with unknown basar number to the cart"() {
        given:
        BasarBind basar = start BasarBind

        when: "enter a unknown basar number"
        basar {
            enter '101' into basarNumberField
            enter '10' into priceField
            click addButton
        }

        then: "a error text message should be displayed"
        basar.errors.size() == 1
        basar.errors[0].text().contains('Die Basarnummer existiert nicht')
    }

}
