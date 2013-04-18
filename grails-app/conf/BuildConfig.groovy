grails.servlet.version = "2.5"
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.7
grails.project.source.level = 1.7
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {
    inherits "global"
    log "debug" // 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true
    legacyResolve false

    repositories {
        inherits true // Whether to inherit repository definitions from plugins
        grailsCentral()
        mavenLocal()
        mavenCentral()
    }

    dependencies {}

    plugins {
        build ":tomcat:$grailsVersion"
        runtime ":cors:1.0.4"
        compile ":mongodb:1.2.0"
        //runtime ":resources:1.1.6"
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.5"
        //compile ':cache:1.0.1'
    }
}
