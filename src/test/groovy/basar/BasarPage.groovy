package basar

import geb.Page;

class BasarPage extends Page {
	
	static url = "http://localhost:8881/static/basar.html"
	
	static at = { title == "Basar" }
	
	static content = {
		
		basarForm { $("form") }
		
		addButton { $("#addCartItem") }
		
		basarNumber { i -> 
			waitFor { $("td")[i] }
			$("td")[0].text() 
		}
		
	}

}
