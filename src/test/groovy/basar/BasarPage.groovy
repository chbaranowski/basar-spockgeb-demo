package basar

import geb.Page;

class BasarPage extends Page {

    static url = "/basar.html"

    static at = { title == "Basar" }

    static content = {

        basarForm { $("form") }

        addButton { $("#addCartItem") }

        sum { $("#sum").text() }
        
        cartSize { $("tr").size() - 1}

        basarNumber(wait:true) { attr ->
            def index = (attr.cartItem -1) * 4
            $("td")[index].text()
        }

        price(wait:true) { attr ->
            def index = ((attr.cartItem -1) * 4) + 1
            $("td")[index].text()
        }
    }
}
