package basar

import geb.spock.GebSpec;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import domain.MockingContext;
import basar.data.User;
import basar.domain.Basar;
import spock.lang.Ignore

@ContextConfiguration(loader = SpringApplicationContextLoader, classes = [MockingContext])
@WebAppConfiguration
@IntegrationTest('server.port:0')
class JavaScriptDemoSpec extends GebSpec {

    @Autowired
    Basar basar
	
	@Value('${local.server.port}')
	int port
	
	Basar basarMock
	
	def setup() {
		browser.baseUrl = "http://localhost:${port}"
		basarMock = Mock(Basar)
		basar.delegate = basarMock;
	}

    def "create a new seller"() {
        given:
			basarMock.findAllUsers() >> [
                new User(id: 1L, basarNumber: "100", name: "Christian"),
                new User(id: 2L, basarNumber: "101", name: "Martin")]
        when:
            go "/sellers.html"
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
