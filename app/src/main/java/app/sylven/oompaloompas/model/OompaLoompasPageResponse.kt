package app.sylven.oompaloompas.model

data class OompaLoompasPageResponse(
    val current: Int,
    val results: List<OompaLoompaPageItem>,
    val total: Int
)
