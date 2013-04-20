class UrlMappings {

	static mappings = {
        "/area"(controller: "area", parseRequest: true) {
            action = [GET: "list", POST: "save"]
        }
        "/area/$id"(resource: "area")
	}
}
