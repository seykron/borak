package net.librebora.connector.bora.nlp

import net.librebora.connector.bora.model.Partner

class PartnersParser {

    companion object {
        private val PARTNERS_PARSER: Regex = Regex("DNI[\\s]*(\\d{1,3}\\.?\\d{3}\\.?\\d{3})")
    }

    fun parse(text: String): List<Partner> {
        return PARTNERS_PARSER.findAll(text).map { matchResult ->
            Partner(
                documentId = matchResult.groupValues[1]
                    .replace(".", "")
                    .trim()
            )
        }.distinctBy { partner ->
            partner.documentId
        }.toList()
    }
}
