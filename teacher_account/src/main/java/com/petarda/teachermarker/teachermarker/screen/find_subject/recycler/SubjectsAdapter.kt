package com.petarda.teachermarker.teachermarker.screen.find_subject.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.petarda.teachermarker.teachermarker.R
import com.petarda.teachermarker.teachermarker.business.model.Schedule
import java.util.*

class SubjectsAdapter(var list: List<Schedule>, val onHolderClicked: (id: UUID) -> Unit) :
    RecyclerView.Adapter<SubjectHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): SubjectHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_subject, viewGroup, false)
        return SubjectHolder(view, onHolderClicked)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SubjectHolder, position: Int) = holder.bindSchedule(list[position])
}