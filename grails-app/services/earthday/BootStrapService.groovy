package earthday

class BootStrapService {

    static final GERS = [
            [43.9096d, 0.0604d],
            [44.0183d, 0.2307d],
            [44.0657d, 0.618d],
            [44.0361d, 0.8405d],
            [43.9056d, 0.8185d],
            [43.759d, 0.9613d],
            [43.5563d, 1.17d],
            [43.3729d, 0.9695d],
            [43.325d, 0.64d],
            [43.3709d, 0.184d],
            [43.5941d, -0.0494d],
            [43.6299d, -0.2829d],
            [43.8938d, -0.2417d]
    ]

    def populate() {
        def author = "Conseil général du Gers"
        def description = "Data collected by Gers council in "
        def date = Date.parse("yyyy-MM-dd", "2000-01-01")
        def value = 345
        for (int i = 1; i < 13; i++) {
            def area = new Area(
                    author: author,
                    description: description + " " + date.format("yyyy"),
                    startDate: date,
                    endDate: date + 365,
                    type: "Co2 pollution",
                    value: value
            )
            def points = []
            GERS.eachWithIndex { coordinates, index ->
                points << new Point(coordinates: [coordinates[1], coordinates[0]], area: area, order: index)
            }
            area.points = points
            area.save()
            date += 365
            value += i*30
        }
    }

}
