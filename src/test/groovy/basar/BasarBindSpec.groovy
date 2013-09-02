package basar
import data.User
import domain.Basar
import org.springframework.beans.factory.annotation.Autowired

class BasarBindSpec extends BasarWebSpecification {

    User testUser

    @Autowired
    Basar basar

    @Override
    def enviorment() { "production" }

    def setup() {
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
