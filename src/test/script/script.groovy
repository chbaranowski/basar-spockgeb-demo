def methodMissing(String name, args) {
    browser."$name"(* args)
}

def propertyMissing(String name) {
    browser."$name"    
}

def propertyMissing(String name, value) {
    browser."$name" = value
}





















