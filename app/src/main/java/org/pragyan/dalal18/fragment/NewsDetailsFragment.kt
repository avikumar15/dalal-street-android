package org.pragyan.dalal18.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_news_details.*
import org.pragyan.dalal18.R
import org.pragyan.dalal18.utils.MiscellaneousUtils.parseDate

class NewsDetailsFragment: Fragment() {
    private lateinit var loadingDialog:AlertDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_news_details,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = resources.getString(R.string.news_details)

        news_details_head.text = arguments?.getString("title")
        news_details_content.text = arguments?.getString("content")
        news_details_created_at.text = parseDate(arguments?.getString("created-at"))

        Picasso.with(activity).load("https://dalal.pragyan.org/public/src/images/news/" + arguments?.getString("image-path")!!).into(news_details_image)
        //The toolbar code has not been converted
        activity?.title = getString(R.string.news_details)
        loadingDialog.dismiss()
        news_details_created_at.visibility = (View.VISIBLE)
        news_details_content.visibility = (View.VISIBLE)
        news_details_head.visibility = (View.VISIBLE)
        news_details_image.visibility = (View.VISIBLE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dialogBox = LayoutInflater.from(activity).inflate(R.layout.progress_dialog,null)
        dialogBox.findViewById<TextView>(R.id.progressDialog_textView).setText(R.string.getting_latest_news)
        loadingDialog = AlertDialog.Builder(activity).setView(dialogBox).setCancelable(false).create()
        loadingDialog.show()
    }


}