package geb

import geb.Browser;
import geb.Configuration;
import geb.ConfigurationLoader;
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise


abstract class GebSpec extends Specification {

    String gebConfEnv = null
    String gebConfScript = null

    @Shared Browser _browser

    Configuration createConf() {
        new ConfigurationLoader(gebConfEnv).getConf(gebConfScript)
    }

    Browser createBrowser() {
        new Browser(createConf())
    }

    Browser getBrowser() {
        if (_browser == null) {
            _browser = createBrowser()
        }
        _browser
    }

    void resetBrowser() {
        if (_browser?.config?.autoClearCookies) {
            _browser.clearCookiesQuietly()
        }
        _browser = null
    }

    def methodMissing(String name, args) {
        getBrowser()."$name"(* args)
    }

    def propertyMissing(String name) {
        getBrowser()."$name"
    }

    def propertyMissing(String name, value) {
        getBrowser()."$name" = value
    }

    private isSpecStepwise() {
        this.class.getAnnotation(Stepwise) != null
    }

    def cleanup() {
        if (!isSpecStepwise()) resetBrowser()
    }

    def cleanupSpec() {
        if (isSpecStepwise()) resetBrowser()
    }
}
