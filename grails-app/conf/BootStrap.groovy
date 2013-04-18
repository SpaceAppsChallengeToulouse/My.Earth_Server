import earthday.Point
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
            println "------------ Create sample points"
            [[40, 45, 50, 55], [80, 85, 90]].combinations().eachWithIndex { List location, index ->
                new Point(name: "point-$index", location: location).save()
            }
            println "------------ Points in the DB"
            Point.findAll().each { println it }
            println "---------------------------------------"
        }
    }

    def destroy = {}
}
