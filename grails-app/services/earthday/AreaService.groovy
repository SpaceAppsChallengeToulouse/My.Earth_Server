package earthday

class AreaService {

    def save(Area area, List inputPoints) {
        def points = []
        inputPoints.each { Map inputPoint ->
            def coordinates = inputPoint.coordinates
            if (!(coordinates?.lon instanceof Number) || !(coordinates?.lat instanceof Number)) {
                throw new IllegalArgumentException("Error: coordinates are missing or of wrong type")
            }
            Double lon = coordinates.lon
            Double lat = coordinates.lat
            if (lon < -180 || lat < -180 || lon > 180 || lat > 180) {
                throw new IllegalArgumentException("Error: coordinates must be between -180 and 180")
            }
            points << new Point(coordinates: [lon, lat])
        }
        area.points = points
        area.save()
    }
}
