package basar
import data.User
import domain.Basar
import org.mockito.ArgumentCaptor
import org.springframework.beans.factory.annotation.Autowired

import static org.mockito.Mockito.*
import static org.mockito.BDDMockito.*

class SellerSpec extends BasarWebSpec {

    @Autowired
    Basar basar
    
    ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User)

    def "create a new seller"(User seller) {
        given:
            reset(basar)
            given(basar.findAllUsers()).willReturn([])
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
            verify(basar).saveUser(userCaptor.capture())
            User newUser = userCaptor.value
            newUser.basarNumber == seller.basarNumber
            newUser.name == seller.name
            newUser.lastname == seller.lastname
            newUser.email == seller.email
        where:
            seller << [new User(basarNumber: "100", name: "Christian", lastname: "", email: ""), 
                       new User(basarNumber: "ABC", name: "",          lastname: "", email: "")]
    }

}
