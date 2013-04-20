import earthday.Area
import earthday.Data
import earthday.Point
import grails.converters.JSON
import grails.util.Environment

class BootStrap {

    def mongo
    def grailsApplication

    def init = { servletContext ->
        if (Environment.current == Environment.DEVELOPMENT) {
            //def springContext = WebApplicationContextUtils.getWebApplicationContext(servletContext)

            def dbName = grailsApplication.config.grails.mongo.databaseName
            def db = mongo.getDB(dbName)
            println "------------ Drop DB $dbName"
            db.dropDatabase()

            /*println "------------ Create sample markers"
            [[40, 45, 50, 55], [80, 85, 90]].combinations().eachWithIndex { List coordinates, index ->
                new Point(data: index * 5, coordinates: coordinates).save()
            }
            println "------------ Markers in the DB"
            Point.findAll().each { println it }
            println "---------------------------------------"*/
        }

        JSON.registerObjectMarshaller(Point) {
            [coordinates: [lon: it.coordinates[0], lat: it.coordinates[1]]]
        }

        JSON.registerObjectMarshaller(Area) {
            [
                id: it.id,
                author: it.author,
                description: it.description,
                startDate: it.startDate.time,
                endDate: it.endDate.time,
                type: it.type,
                value: it.value,
                points: it.points
            ]
        }
    }

    def destroy = {}
}
