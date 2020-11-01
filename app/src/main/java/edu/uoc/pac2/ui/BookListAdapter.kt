package edu.uoc.pac2.ui

import android.app.ActivityOptions
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.uoc.pac2.R
import edu.uoc.pac2.data.Book

/**
 * Adapter for a list of Books.
 */

class BooksListAdapter(private var books: List<Book>) : RecyclerView.Adapter<BooksListAdapter.ViewHolder>() {

    private val evenViewType = 0
    private val oddViewType = 1

    private fun getBook(position: Int): Book {
        return books[position]
    }

    fun setBooks(books: List<Book>) {
        this.books = books
        // Reloads the RecyclerView with new adapter data
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) {
            evenViewType
        } else {
            oddViewType
        }
    }

    // Creates View Holder for re-use
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = when (viewType) {
            evenViewType -> {
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.row_book_list_content_even, parent, false)
            }
            oddViewType -> {
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.row_book_list_content_odd, parent, false)
            }
            else -> {
                throw IllegalStateException("Unsupported viewType $viewType")
            }
        }
        return ViewHolder(view)
    }

    // Binds re-usable View for a given position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = getBook(position)
        holder.titleView.text = book.title
        holder.authorView.text = book.author

        // TODO: Set View Click Listener
        holder.view.setOnClickListener(View.OnClickListener {
            val detailIntent = Intent(it.context, BookDetailActivity::class.java)
            detailIntent.putExtra(BookDetailFragment.ARG_ITEM_ID, book.uid)
            it.context.startActivity(detailIntent)
            (it.context as BookListActivity).overridePendingTransition(R.anim.translate_in_bottom, R.anim.translate_out_bottom)
        })
    }

    // Returns total items in Adapter
    override fun getItemCount(): Int {
        return books.size
    }

    // Holds an instance to the view for re-use
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.title)
        val authorView: TextView = view.findViewById(R.id.author)
    }

}
