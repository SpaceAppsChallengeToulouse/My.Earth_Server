class UrlMappings {

	static mappings = {
        "/marker/$id?"(resource: "marker")
        "/markers"(controller: "marker", action: "list")
	}
}
