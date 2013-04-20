package earthday

import grails.validation.ValidationException

class AreaService {

    def save(Area area, List inputPoints, inputStartDate, inputEndDate) {
        // Points
        List<Point> points = extractPoints(inputPoints)
        points.each { it.area = area }
        area.points = points
        // Dates
        Date startDate, endDate
        try {
            startDate = new Date(inputStartDate)
            endDate = new Date(inputEndDate)
        } catch (Exception e) {
            throw new IllegalArgumentException("Error: Invalid dates, only timestamps are accepted")
        }
        if (startDate.after(endDate)) {
            throw new IllegalArgumentException("Error: start date cannot be after end date ")
        }
        area.startDate = startDate
        area.endDate = endDate
        try {
            area.save()
        } catch (ValidationException ve) {
            throw new IllegalArgumentException("Error in validation : $ve.message")
        }
    }

    private List<Point> extractPoints(inputPoints) {
        def points = []
        inputPoints.each { Map inputPoint ->
            points << extractPoint(inputPoint)
        }
        points
    }

    private Point extractPoint(Map inputPoint) {
        def coordinates = inputPoint.coordinates
        if (!(coordinates?.lon instanceof Number) || !(coordinates?.lat instanceof Number)) {
            throw new IllegalArgumentException("Error: coordinates are missing or of wrong type")
        }
        Double lon = coordinates.lon
        Double lat = coordinates.lat
        if (lon < -180 || lat < -180 || lon > 180 || lat > 180) {
            throw new IllegalArgumentException("Error: coordinates must be between -180 and 180")
        }
        new Point(coordinates: [lon, lat])
    }
}
