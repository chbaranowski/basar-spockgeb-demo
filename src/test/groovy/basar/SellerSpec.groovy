package basar

import geb.spock.GebSpec;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import domain.MockingContext;
import basar.data.User
import basar.domain.Basar

@ContextConfiguration(classes = [MockingContext])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SellerSpec extends GebSpec {

    @Autowired
    Basar basar
	
	Basar basarMock

	@LocalServerPort	
	int port

	def setup() {
		browser.baseUrl = "http://localhost:${port}"
		basarMock = Mock(Basar)
		basar.delegate = basarMock;
	}
    
    def "create a new seller"(User seller) {
        given:
			basarMock.findAllUsers() >> []
        when:
            go "/sellers.html"
            waitFor { $("#newUser") }
            $("#newUser").click()
            waitFor { $("#basarNumber").value("") }
            $("#basarNumber").value(seller.basarNumber)
            $("#name").value(seller.name)
            $("#lastname").value(seller.lastname)
            $("#email").value(seller.email)
            $("#saveUser").click()
            waitFor { $("#successfullCreated") }
        then:
			1 * basarMock.saveUser({ newUser ->  
				newUser.basarNumber == seller.basarNumber
				newUser.name        == seller.name
				newUser.lastname    == seller.lastname
				newUser.email       == seller.email
			})
        where:
            seller << [ new User(basarNumber: "100", name: "Christian", lastname: "", email: ""),
                        new User(basarNumber: "ABC", name: "",          lastname: "", email: "")]
    }

}
