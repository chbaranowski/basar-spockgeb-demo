package basar

import geb.Page;

class BasarPage extends Page {

    static url = "/static/basar.html"

    static at = { title == "Basar" }

    static content = {

        basarForm { $("form") }

        addButton { $("#addCartItem") }

        sum { $("#sum").text() }

        basarNumber(wait:true) { attr ->
            def index = attr.cartItem * 4
            $("td")[index].text()
        }

        price(wait:true) { attr ->
            def index = (attr.cartItem * 4) + 1
            $("td")[index].text()
        }
    }
}
