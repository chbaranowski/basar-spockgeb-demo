package basar

import data.User
import domain.Basar
import org.springframework.beans.factory.annotation.Autowired

class SellerSpec extends BasarWebSpecification {

    @Autowired
    Basar basar
	
	Basar basarMock

	def setup() {
		basarMock = Mock(Basar)
		basar.delegate = basarMock;
	}
    
    def "create a new seller"(User seller) {
        given:
			basarMock.findAllUsers() >> []
        when:
            go "/static/sellers.html"
            waitFor { $("#newUser") }
            $("#newUser").click()
            waitFor { $("#basarNumber") }
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
