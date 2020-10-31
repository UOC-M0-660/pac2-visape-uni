package edu.uoc.pac2.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import edu.uoc.pac2.R
import kotlinx.android.synthetic.main.activity_book_detail.*

/**
 * An activity representing a single Book detail screen.
 */
class BookDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val itemID = intent.getIntExtra(BookDetailFragment.ARG_ITEM_ID, -1)
            val fragment = BookDetailFragment.newInstance(itemID)
            supportFragmentManager.beginTransaction()
                    .add(R.id.frameLayout, fragment)
                    .commit()
        }
        //Actionbar
        setSupportActionBar(toolbar_detail)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    public fun setTitle(title: String) {
        collapsing_toolbar_detail.title = title
    }

    // TODO: Override finish animation for actionbar back arrow
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            overridePendingTransition(R.anim.translate_in_top, R.anim.translate_out_top)
            return true
        }
        return false
    }

    // TODO: Override finish animation for phone back button
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.translate_in_top, R.anim.translate_out_top)
    }

}