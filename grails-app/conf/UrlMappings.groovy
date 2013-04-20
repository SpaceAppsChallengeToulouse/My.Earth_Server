class UrlMappings {

	static mappings = {
        "/area/$id?"(resource: "area")
        "/areas"(controller: "area", action: "list")
	}
}
