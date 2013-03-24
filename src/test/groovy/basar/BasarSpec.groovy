package basar

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;

import spock.lang.Unroll;

import domain.Basar;
import domain.PriceUtils;
import data.Position;
import data.User;

import static org.mockito.Mockito.*;

class BasarSpec extends BasarWebSpec {
	
	@Autowired
	Basar basar;
	
	@Unroll
	def "buy some articles #number and #preis"() {
		given:
			def user = new User(basarNumber: number, name: "Christian")
			when(basar.findAllUsers()).thenReturn([user])
			when(basar.getTotal()).thenReturn(0L)
			when(basar.findByBasarNumber(number)).thenReturn(user)
			def longPrice = PriceUtils.formatPriceToLong(preis)
			when(basar.createPosition(number, longPrice, "")).thenReturn(new Position(id: 1, seller: user, price: longPrice))
		when:
			to BasarPage
		then:
			at BasarPage
		and:
			basarForm.with {
				basarNumber = number
				price = preis
			}
			addButton.click()
		then:
			basarNumber(0) == number
		where:
			number        | preis
			"100"         | "10"
			"100"         | "10,50"		
	}
	

}
