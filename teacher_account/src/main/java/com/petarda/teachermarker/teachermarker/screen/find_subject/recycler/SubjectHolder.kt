package com.petarda.teachermarker.teachermarker.screen.find_subject.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.petarda.teachermarker.teachermarker.R
import com.petarda.teachermarker.teachermarker.business.model.Schedule
import java.util.*

class SubjectHolder(itemView: View, val onHolderClicked: (id: UUID) -> Unit) : RecyclerView.ViewHolder(itemView) {
    private val lessonTypeTextView = itemView.findViewById<TextView>(R.id.lessonType)
    private val subjectNameTextView = itemView.findViewById<TextView>(R.id.subjectName)
    private val emploeesTextView = itemView.findViewById<TextView>(R.id.emploeesTextView)

    internal fun bindSchedule(schedule: Schedule) {
        itemView.setOnClickListener { onHolderClicked.invoke(schedule.id) }

        lessonTypeTextView.text = schedule.lessonType
        subjectNameTextView.text = schedule.subject
        emploeesTextView.text = StringBuilder().apply {
            schedule.employee.forEach {
                append(it)
                append('\n')
            }
        }
    }
}