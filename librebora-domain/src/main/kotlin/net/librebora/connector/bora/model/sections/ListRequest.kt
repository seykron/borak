package net.librebora.connector.bora.model.sections

import org.joda.time.DateTime

/** Represents a request to list elements from a BORA section.
 *
 * @property sectionName Name of the section to list items.
 * @property subCategory Name of the category to list.
 * @property date Required entries date.
 * @property offset 1-based offset for pagination.
 * @property itemsPerPage Number of items per page, for pagination.
 * @property sessionId Session id required for pagination.
 */
data class ListRequest(
    val sectionName: String,
    val subCategory: String,
    val date: DateTime,
    val offset: Int,
    val itemsPerPage: Int,
    val sessionId: String
) {

    companion object {

        private const val CATEGORY_DEFAULT: String = "all"

        private const val FIELD_SECTION_NAME: String = "nombreSeccion"
        private const val FIELD_SUB_CATEGORY: String = "subCat"
        private const val FIELD_OFFSET: String = "offset"
        private const val FIELD_ITEMS_PER_PAGE: String = "itemsPerPage"
        private const val FIELD_DATE: String = "fecha"
        private const val FIELD_SESSION_ID: String = "idSesion"

        fun create(sectionName: String,
                   date: DateTime,
                   offset: Int,
                   itemsPerPage: Int,
                   sessionId: String = ""): ListRequest {

            return ListRequest(
                    sectionName = sectionName,
                    subCategory = CATEGORY_DEFAULT,
                    offset = offset,
                    itemsPerPage = itemsPerPage,
                    date = date,
                    sessionId = sessionId
            )
        }
    }

    /** Returns the request to list the next page.
     * @param nextSessionId Session id from the last response.
     */
    fun next(nextSessionId: String): ListRequest {
        return copy(
            offset = offset + 1,
            sessionId = nextSessionId
        )
    }

    fun formData(): Map<String, String> {
        return mapOf(
            FIELD_SECTION_NAME to sectionName,
            FIELD_SUB_CATEGORY to subCategory,
            FIELD_OFFSET to offset.toString(),
            FIELD_ITEMS_PER_PAGE to itemsPerPage.toString(),
            FIELD_DATE to date.toString("yyyyMMdd"),
            FIELD_SESSION_ID to sessionId
        )
    }
}
