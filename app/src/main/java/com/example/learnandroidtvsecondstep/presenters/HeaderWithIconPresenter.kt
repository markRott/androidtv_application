package com.example.learnandroidtvsecondstep.presenters

import android.view.ViewGroup
import android.widget.TextView
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.Row
import androidx.leanback.widget.RowHeaderPresenter
import com.example.learnandroidtvsecondstep.R
import com.example.learnandroidtvsecondstep.headers.HeaderWithIcon
import com.example.learnandroidtvsecondstep.utils.inflate

class HeaderWithIconPresenter : RowHeaderPresenter() {

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val view = parent?.inflate(R.layout.item_header_with_icon)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder?, item: Any?) {
        val row = item as? Row
        val headerItem = row?.headerItem as? HeaderWithIcon

        val rootView = viewHolder?.view
        val tvHeaderLabel: TextView? = rootView?.findViewById(R.id.tv_header_label)

        if (tvHeaderLabel != null && headerItem != null) {
            tvHeaderLabel.text = headerItem.name
            tvHeaderLabel.setCompoundDrawablesWithIntrinsicBounds(
                    headerItem.iconId, 0, 0, 0
            )
        }
    }

    override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder?) {
        val rootView = viewHolder?.view
        val tvHeaderLabel: TextView? = rootView?.findViewById(R.id.tv_header_label)

        tvHeaderLabel?.run {
            text = ""
            setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        }
    }
}