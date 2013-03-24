package basar

import data.User
import domain.Basar
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile
import org.springframework.beans.factory.annotation.Autowired

import static org.mockito.Mockito.*

class JavaScriptDemoSpec extends BasarWebSpec {

    @Autowired
    Basar basarMock

    def "create a new seller"() {
        given:
            when(basarMock.findAllUsers()).thenReturn([
                new User(id: 1L, basarNumber: "100", name: "Christian"),
                new User(id: 2L, basarNumber: "101", name: "Martin"),
            ])
        when:
            go "$basarUrl/static/sellers.html"
            waitFor { $("#newUser") }
            def users = js.exec('''
                        var users = []
                        var rows = $("#usersBody tr")
                        rows.each(function(){
                            var cells = $(this).children().not(".rightCell")
                            var user = {
                                 basarNumber: $(cells[0]).text(),
                                 vorname: $(cells[1]).text(),
                                 nachname: $(cells[2]).text(),
                                 email: $(cells[3]).text()
                            }
                            users.push(user)
                        })
                        return users
                    ''')

        then:
            users == [[basarNumber:"100", vorname: "Christian", nachname: "", email: ""],
                      [basarNumber:"101", vorname: "Martin",    nachname: "", email: ""]]
    }

}
