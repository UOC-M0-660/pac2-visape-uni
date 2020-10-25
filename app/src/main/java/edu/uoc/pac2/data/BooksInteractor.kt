package edu.uoc.pac2.data

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * This class Interacts with {@param bookDao} to perform operations in the local database.
 *
 * Could be extended also to interact with Firestore, acting as a single entry-point for every
 * book-related operation from all different datasources (Room & Firestore)
 *
 * Created by alex on 03/07/2020.
 */
class BooksInteractor(private val bookDao: BookDao) {

    // TODO: Get All Books from DAO
    suspend fun getAllBooks(): List<Book> {
        return bookDao.getAllBooks()
    }

    // TODO: Save Book
    suspend fun saveBook(book: Book) {
        bookDao.saveBook(book)
    }

    // TODO: Save List of Books
    suspend fun saveBooks(books: List<Book>) {
        books.forEach { saveBook(it) }
    }

    // TODO: Get Book by id
    suspend fun getBookById(id: Int): Book? {
        return bookDao.getBookById(id)
    }

}