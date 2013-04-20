package earthday

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString(includeNames=true)
class Area {

    String id
    String type
    String author
    String description
    Date startDate
    Date endDate
    Double value

    static hasMany = [points: Point]

    static constraints = {
        value nullable: false
        type nullable: false
        startDate nullable: false
        endDate nullable: false
        author nullable: true
        description nullable: true
    }
}
