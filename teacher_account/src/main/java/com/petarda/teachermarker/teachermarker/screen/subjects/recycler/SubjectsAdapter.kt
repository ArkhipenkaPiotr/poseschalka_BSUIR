package com.petarda.teachermarker.teachermarker.screen.subjects.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.petarda.teachermarker.teachermarker.R
import com.petarda.teachermarker.teachermarker.business.model.Schedule
import com.petarda.teachermarker.teachermarker.business.model.Subject
import com.petarda.teachermarker.teachermarker.screen.subjects.recycler.SubjectHolder
import java.util.*

class SubjectsAdapter(var list: List<Subject>, val onHolderClicked: (id: Long) -> Unit) :
    RecyclerView.Adapter<SubjectHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): SubjectHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_subject_with_lessons, viewGroup, false)
        return SubjectHolder(view, onHolderClicked)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SubjectHolder, position: Int) = holder.bindSchedule(list[position])
}