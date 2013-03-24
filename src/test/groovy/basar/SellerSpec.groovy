package basar
import data.User
import domain.Basar
import org.mockito.ArgumentCaptor
import org.springframework.beans.factory.annotation.Autowired

import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

class SellerSpec extends BasarWebSpec {

    @Autowired
    Basar basarMock

    def "create a new seller"() {
        given:
            def user = [basarNumber: "100", name: "Christian"]
            when(basarMock.findAllUsers()).thenReturn([])
        when:
            go "/static/sellers.html"
            waitFor { $("#newUser") }
            $("#newUser").click()
            waitFor { $("#basarNumber") }
            $("#basarNumber").value(user.basarNumber)
            $("#name").value(user.name)
            $("#saveUser").click()
            waitFor { $("#successfullCreated") }
        then:
            ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User)
            verify(basarMock).saveUser(userArgumentCaptor.capture())
        and:
            User newUser = userArgumentCaptor.value
            newUser.basarNumber == user.basarNumber
            newUser.name == user.name
    }

}
