package earthday

import grails.converters.JSON

class MarkerController {

    def save() {
        if (params.data == null || !params.lat == null || !params.lon == null) {
            render status: 400, text: "Error: Missing parameter"
            return
        }
        def coordinates = [params.double('lon'), params.double('lat')]
        def marker = new Marker(data: params.data, coordinates: coordinates)
        marker.save()
        render status: 201, text: marker as JSON
    }

    def show() {
        if (!params.id) {
            render status: 400, text: "Error: Missing ID"
            return
        }
        def point = Marker.get(params.id)
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
        def point = Marker.get(params.id)
        if (!point) {
            render status: 404, text: "Error: Resource with the given ID cannot be found"
            return
        }
        if (params.data) {
            point.data = params.data
            point.save()
        }
        render point as JSON
    }

    def delete() {
        if (!params.id) {
            render status: 400, text: "Error: Missing ID"
            return
        }
        def point = Marker.get(params.id)
        if (!point) {
            render status: 404, text: "Error: Resource with the given ID cannot be found"
            return
        }
        point.delete()
        render status: 204, text: "Resource deleted successfully"
    }

    def list() {
        render text: [markers: Marker.findAll()] as JSON
    }
}
