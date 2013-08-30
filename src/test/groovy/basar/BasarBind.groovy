package basar

import geb.bind.*

class BasarBind extends Bind {

    def start() {
        go "/static/basar.html"
        waitFor { title == "Basar" }
    }

    InputText basarNumberField = inputText {
        $("#basarNumber")
    }

    InputText priceField = inputText {
        $("#price")
    }

    Button addButton = button onClick: {
        def beforeClickCartSize = $('tr').size()
        $('#addCartItem').click()
        waitFor {
            $('tr').size() > beforeClickCartSize ||
            $('.text-error').size() > 0
        }
    }

    Text sum = text {
        $('#sum')
    }

    List errors = list {
        $('.text-error')
    }

    Table cartContentTable = table {
        $('#cartBody').find('tr')
    }

}
