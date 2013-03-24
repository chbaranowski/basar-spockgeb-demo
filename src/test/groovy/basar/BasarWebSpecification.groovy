package basar

import geb.GebSpec;

import org.springframework.beans.factory.annotation.Autowired
import runner.Webapp

abstract class BasarWebSpecification extends GebSpec {

    static webapp

    def setupSpec() {
        System.setProperty("spring.profiles.active", enviorment())
        webapp = new Webapp()
        webapp.start()
    }

    def setup() {
        browser.baseUrl = basarUrl
        def applicationContext = webapp.webApplicationContext
        assert applicationContext != null
        getClass().declaredFields.each { field ->
            if (field.getAnnotation(Autowired)) {
                def bean = applicationContext.getBean(field.type)
                assert bean != null
                field.setAccessible(true)
                field.set(this, bean)
            }
        }
    }

    def cleanupSpec() {
        webapp.stop()
    }

    def getBasarUrl() {
        "http://localhost:${webapp.port}"
    }

    def enviorment() {
        "mocking"
    }
}
