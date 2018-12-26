package com.petarda.teachermarker.teachermarker.screen.find_subject

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.petarda.teachermarker.teachermarker.R
import kotlinx.android.synthetic.main.dialog_pick_lessons.*

class LessonsPickerFragmentDialog : DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_pick_lessons, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOkButton()
    }

    private fun initOkButton() =
        okButton.setOnClickListener {
            (activity as? FindSubjectsActivity)?.viewModel?.onLessonsAmountPicked(lessonsAmountEditText.text.toString().toInt())
        }
}