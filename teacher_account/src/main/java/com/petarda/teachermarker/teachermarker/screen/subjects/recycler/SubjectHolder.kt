package com.petarda.teachermarker.teachermarker.screen.subjects.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.petarda.teachermarker.teachermarker.R
import com.petarda.teachermarker.teachermarker.business.model.Subject

class SubjectHolder(itemView: View, val onHolderClicked: (id: Long) -> Unit) : RecyclerView.ViewHolder(itemView) {
    private val lessonTypeTextView = itemView.findViewById<TextView>(R.id.lessonType)
    private val subjectNameTextView = itemView.findViewById<TextView>(R.id.subjectName)
    private val emploeesTextView = itemView.findViewById<TextView>(R.id.emploeesTextView)
    private val lessonsAmountTextView = itemView.findViewById<TextView>(R.id.lessonsAmountTextView)

    internal fun bindSchedule(subject: Subject) {
        itemView.setOnClickListener { onHolderClicked.invoke(subject.id!!) }

        lessonTypeTextView.text = subject.lessonType
        subjectNameTextView.text = subject.subjectName
        emploeesTextView.text = subject.employees
        lessonsAmountTextView.text = subject.lessonsAmount.toString()
    }
}