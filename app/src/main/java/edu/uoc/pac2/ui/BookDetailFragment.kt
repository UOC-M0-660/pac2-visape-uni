package edu.uoc.pac2.ui

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import edu.uoc.pac2.MyApplication
import edu.uoc.pac2.R
import edu.uoc.pac2.data.Book
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.fragment_book_detail.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStream
import java.net.URL

/**
 * A fragment representing a single Book detail screen.
 * This fragment is contained in a [BookDetailActivity].
 */
class BookDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get Book for this detail screen
        loadBook()
    }


    // TODO: Get Book for the given {@param ARG_ITEM_ID} Book id
    private fun loadBook() {
        val itemId = arguments?.getInt(ARG_ITEM_ID)
        if (itemId != null) {
            GlobalScope.launch {
                val book = (activity?.applicationContext as MyApplication).getBooksInteractor().getBookById(itemId)
                if (book != null) {
                    initUI(book)
                }
            }
        }
    }

    // TODO: Init UI with book details
    private fun initUI(book: Book) {
        (activity as BookDetailActivity).setTitle(book.title)
        author_detail.text = book.author
        date_detail.text = book.publicationDate
        description_detail.text = book.description

        activity?.runOnUiThread(Runnable {
            Picasso.get().load(book.urlImage)
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.progress_animation)
                    .into((activity as BookDetailActivity).image_detail)
        })

        activity?.findViewById<FloatingActionButton>(R.id.floatingButton)?.setOnClickListener(View.OnClickListener {
            shareContent(book)
        })
    }

    // TODO: Share Book Title and Image URL
    private fun shareContent(book: Book) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, ("Title: "+book.title+" URL: " +book.urlImage))
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    companion object {
        /**
         * The fragment argument representing the item title that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "itemIdKey"

        fun newInstance(itemId: Int): BookDetailFragment {
            val fragment = BookDetailFragment()
            val arguments = Bundle()
            arguments.putInt(ARG_ITEM_ID, itemId)
            fragment.arguments = arguments
            return fragment
        }
    }
}