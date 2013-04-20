package earthday

import grails.converters.JSON

class AreaController {

    def areaService

    def save() {
        def area = new Area()
        bindData(area, params, [include: ['type', 'value', 'author', 'description']])
        try {
            areaService.save(area, params.list('points'), params.startDate, params.endDate)
        } catch (Exception e) {
            render status: 400, text: e.getMessage()
            return
        }
        render status: 201, text: area as JSON, contentType: "application/json"
    }

    def show() {
        if (!params.id) {
            render status: 400, text: "Error: Missing ID"
            return
        }
        def area = Area.get(params.id)
        if (!area) {
            render status: 404, text: "Error: Resource with the given ID cannot be found"
            return
        }
        render area as JSON
    }

    def update() {
        render status: 405, text: "Error: This Resource doesn't accept update for the moment"
    }

    def delete() {
        if (!params.id) {
            render status: 400, text: "Error: Missing ID"
            return
        }
        def area = Area.get(params.id)
        if (!area) {
            render status: 404, text: "Error: Resource with the given ID cannot be found"
            return
        }
        area.delete()
        render status: 204, text: "Resource deleted successfully"
    }

    def list() {
        render text: [areas: Area.findAll()] as JSON, contentType: "application/json"
    }
}
