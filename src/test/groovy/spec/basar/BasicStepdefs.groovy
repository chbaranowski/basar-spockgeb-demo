package spec.basar

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

When(~"add a article into the cart") { ->
    println "When add a article into the cart"
    result = 1
}

Then(~"(.*) article should be in the cart") { double count ->
    println "Then the article should be in the cart"
    assert count == result
}
