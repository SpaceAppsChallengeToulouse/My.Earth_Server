package earthday

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString(includeNames=true)
class Area {

    String id
    String type
    Double value

    static hasMany = [points: Point]

    static constraints = {
        value nullable: false
        type nullable: false
    }
}
