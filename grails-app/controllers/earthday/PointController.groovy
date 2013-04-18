package earthday

import grails.converters.JSON

class PointController {

    def save() {
        if (!params.name || !params.lat || !params.long) {
            render status: 400, text: "Error: Missing parameter"
            return
        }
        def location = [params.double('long'), params.double('lat')]
        def point = new Point(name: params.name, location: location)
        point.save()
        render status: 201, text: point as JSON
    }

    def show() {
        if (!params.id) {
            render status: 400, text: "Error: Missing ID"
            return
        }
        def point = Point.get(params.id)
        if (!point) {
            render status: 404, text: "Error: Resource with the given ID cannot be found"
            return
        }
        render point as JSON
    }

    def update() {
        if (!params.id) {
            render status: 400, text: "Error: Missing ID"
            return
        }
        def point = Point.get(params.id)
        if (!point) {
            render status: 404, text: "Error: Resource with the given ID cannot be found"
            return
        }
        if (params.name) {
            point.name = params.name
            point.save()
        }
        render point as JSON
    }

    def delete() {
        if (!params.id) {
            render status: 400, text: "Error: Missing ID"
            return
        }
        def point = Point.get(params.id)
        if (!point) {
            render status: 404, text: "Error: Resource with the given ID cannot be found"
            return
        }
        point.delete()
        render status: 204, text: "Resource deleted successfully"
    }
}
