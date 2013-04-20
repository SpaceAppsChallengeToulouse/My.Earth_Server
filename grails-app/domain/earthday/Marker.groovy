package earthday

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString(includeNames=true)
class Marker {

    String id
    List coordinates
    Double data

    static mapping = {
        coordinates geoIndex:true
    }
}
