package net.borak.connector.bora.nlp.parser

import org.joda.time.DateTime

data class FileInfo(val creationDate: DateTime,
                    val fileId: String)