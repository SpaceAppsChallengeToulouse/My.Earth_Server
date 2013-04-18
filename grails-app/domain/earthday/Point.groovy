package earthday

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString(includeNames=true)
class Point {

    String id
    String name
    List location

    static mapping = {
        location geoIndex:true
    }
}
