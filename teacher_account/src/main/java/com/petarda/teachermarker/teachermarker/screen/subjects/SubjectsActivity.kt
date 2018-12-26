package com.petarda.teachermarker.teachermarker.screen.subjects

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.petarda.teachermarker.teachermarker.R
import com.petarda.teachermarker.teachermarker.base.BaseActivity
import com.petarda.teachermarker.teachermarker.screen.find_subject.FindSubjectsActivity
import com.petarda.teachermarker.teachermarker.screen.subjects.recycler.SubjectsAdapter
import kotlinx.android.synthetic.main.activity_subjects.*

class SubjectsActivity : BaseActivity() {
    override val layoutResId: Int
        get() = R.layout.activity_subjects

    private lateinit var viewModel: SubjectsViewModel
    private lateinit var subjectsAdapter: SubjectsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindViewModel()

        initSubjectsRecyclerView()
        initAddSubjectButton()
        observeSubjects()
    }

    private fun bindViewModel() {
        viewModel = bindViewModel(SubjectsViewModel::class.java)
    }

    private fun initSubjectsRecyclerView() {
        subjectsAdapter = SubjectsAdapter(listOf(), viewModel::onSubjectsClicked)
        subjectsRecyclerView.adapter = subjectsAdapter
        subjectsRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun observeSubjects() {
        viewModel.subjects.observe(this, Observer {
            subjectsAdapter.list = it ?: listOf()
            subjectsAdapter.notifyDataSetChanged()
        })
    }

    private fun initAddSubjectButton() {
        addSubjectButton.setOnClickListener { _ ->
            Intent(this, FindSubjectsActivity::class.java).let {
                startActivity(it)
            }
        }
    }
}
