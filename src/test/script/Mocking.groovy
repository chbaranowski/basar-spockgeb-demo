import spock.lang.*

class CacheSpec extends Specification {  
    
    def "only the first call should be forwarded"() {
        given:
        def mockService = Mock(SellerService)        
        def cache = new SimpleCache(target: mockService)        
        when: "invoke two times the cached method"
        cache.findByName('max')
        cache.findByName('max')
        then: "target method was invoked one time"
        1 * mockService.findByName('max')
    }
    
    def "cache should return result of the target"() {
        given: "a mock service object that returns OK"
        def mockService = Mock(SellerService)    
        mockService.findByName('max') >> 'OK' >> { throw new IllegalStateException() }
        and: "cache with the mock object as target"
        def cache = new SimpleCache(target: mockService)   
        when: "invoke cache the first time"
        def result = cache.findByName('max')
        then: "result is OK"
        result == 'OK'
        when: "invoke cache the second time"
        result = cache.findByName('max')
        then: "result is OK"
        result == 'OK'
    }
}

interface SellerService {  
  def findByName(String name)        
} 

class SimpleCache {
  
  def target 
  def cache = [:]
  def cacheKey = {name, args -> "$name($args)"}
    
  def invokeTarget = { String name, args ->
      def result = target."$name"(*args)
      cache.put(cacheKey(name, args), result)
      return result
  }  
           
  def methodMissing(String name, args) {
      def key = cacheKey(name, args) 
      cache.containsKey(key) ? cache.get(key) : invokeTarget(name, args)
  }           
}
    