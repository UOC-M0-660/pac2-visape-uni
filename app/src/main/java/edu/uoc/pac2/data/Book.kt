package edu.uoc.pac2.data

import java.util.*

/**
 * A book Model representing a piece of content.
 */

data class Book(
        val title: String? = null,
        val author: String? = null,
        val description: String? = null,
        val publicationDate: String? = null,
        val uid: Int? = null,
        val urlImage: String? = null
)