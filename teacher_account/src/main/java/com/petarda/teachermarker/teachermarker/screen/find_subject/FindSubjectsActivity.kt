package com.petarda.teachermarker.teachermarker.screen.find_subject

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.petarda.teachermarker.teachermarker.R
import com.petarda.teachermarker.teachermarker.base.BaseActivity
import com.petarda.teachermarker.teachermarker.screen.find_subject.recycler.SubjectsAdapter
import com.petarda.teachermarker.teachermarker.util.hideKeyboard
import kotlinx.android.synthetic.main.activity_find_subjects.*

class FindSubjectsActivity : BaseActivity() {
    override val layoutResId: Int
        get() = R.layout.activity_find_subjects

    internal lateinit var viewModel: FindSubjectViewModel
    private lateinit var subjectsAdapter: SubjectsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindViewModel()

        initFindButton()
        initSubjectsRecyclerView()

        observeSchedules()
        observeUIEvents()
    }

    private fun bindViewModel() {
        viewModel = bindViewModel(FindSubjectViewModel::class.java)
    }

    private fun initFindButton() {
        startSearchButton.setOnClickListener {
            viewModel.onGetSubjectsButtonClicked(groupNumberEditText.text.toString())
        }
    }

    private fun observeSchedules() {
        viewModel.schedules.observe(this, Observer {
            subjectsAdapter.list = it ?: listOf()
            subjectsAdapter.notifyDataSetChanged()
        })
    }

    private fun initSubjectsRecyclerView() {
        subjectsAdapter = SubjectsAdapter(listOf(), viewModel::onSubjectClicked)
        subjectsRecyclerView.adapter = subjectsAdapter
        subjectsRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun observeUIEvents() {
        viewModel.uiEvents.observe(this, Observer {
            when (it) {
                FindSubjectViewModel.UIEvents.SHOW_PROGRESS -> searchProgressBar.visibility = View.VISIBLE
                FindSubjectViewModel.UIEvents.HIDE_PROGRESS -> {
                    searchProgressBar.visibility = View.GONE
                    hideKeyboard()
                }
                FindSubjectViewModel.UIEvents.HIDE_KEYBOARD -> hideKeyboard()
                FindSubjectViewModel.UIEvents.SHOW_LESSONS_AMOUNT_PICKER -> showLessonPickerDialog()
                FindSubjectViewModel.UIEvents.CLOSE_ACTIVITY -> this.finish()
            }
        })
    }

    private fun showLessonPickerDialog() =
        LessonsPickerFragmentDialog()
            .show(supportFragmentManager, "PICK_LESSONS_AMOUNT")
}