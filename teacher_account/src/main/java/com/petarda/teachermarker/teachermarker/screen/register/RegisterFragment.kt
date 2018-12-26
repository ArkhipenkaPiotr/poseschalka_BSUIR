package com.petarda.teachermarker.teachermarker.screen.register

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.cardpay.digipass.android.base.BaseFragment
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.petarda.teachermarker.teachermarker.R
import com.petarda.teachermarker.teachermarker.screen.subjects.SubjectsActivity
import com.petarda.teachermarker.teachermarker.util.showToast
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : BaseFragment() {
    override val fragmentResId: Int
        get() = R.layout.fragment_register

    private lateinit var viewModel: RegisterViewModel

    companion object {
        fun newInstance(mode: Mode): RegisterFragment {
            val fragment = RegisterFragment()
            val arguments = Bundle()
            arguments.putSerializable(MODE_KEY, mode)
            fragment.arguments = arguments
            return fragment
        }

        private const val MODE_KEY = "mode"

        enum class Mode {
            REGISTER
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

        initConfirmButton()
        observeUIEvents()
    }

    private fun initViewModel() {
        viewModel = bindViewModel(RegisterViewModel::class.java)
    }

    fun initAllEditText() {
        RxTextView.textChanges(loginEditTextRegister)
            .subscribe {
                viewModel.login.value = it
            }

            .let(this::addHardDisposable)
        RxTextView.textChanges(firsPasswordEditTextRegister)
            .subscribe {
                viewModel.firstPassword.value = it
            }

            .let(this::addHardDisposable)
        RxTextView.textChanges(secondPasswordEditTextRegister)
            .subscribe {
                viewModel.secondPassword.value = it
            }
            .let(this::addHardDisposable)
    }

    private fun initConfirmButton() {
        RxView.clicks(signUpButtonRegister)
            .subscribe {
                initAllEditText()
                viewModel.registerUserAndGoToChatList()
            }
            .let(this::addHardDisposable)
    }

    private fun observeUIEvents() {
        viewModel.uiEvents
            .subscribe {
                when (it) {
                    RegisterViewModel.UIEvents.MOVE_TO_NEXT_SCREEN -> moveToNextScreen()
                    RegisterViewModel.UIEvents.PASSWORD_DOESNT_MATCH_ERROR -> showPasswordErrorDoesntMatchError()
                    RegisterViewModel.UIEvents.INCORRECT_PASSWORD_ERROR -> showPasswordIncorrectError()
                    RegisterViewModel.UIEvents.USER_EXIST -> showMessageUserExist()
                    RegisterViewModel.UIEvents.REGISTER_INPUT_VALUE_ERROR -> showRegisterInputValueError()
                    RegisterViewModel.UIEvents.HIDE_ERROR -> hideError()
                }
            }
            .let(this::addHardDisposable)
    }

    private fun moveToNextScreen() {
//        val intent = Intent(activity, SubjectsActivity::class.java)
//        startActivity(intent)
        activity?.setResult(RESULT_OK)
        activity?.finish()
    }

    private fun showPasswordIncorrectError() {
        firsPasswordEditTextRegister.error = getString(R.string.incorrect_password)
//         errorTextViewRegister.visibility = View.GONE
//          errorTextViewRegister.text = getString(R.string.sign_up_password_error)
//          errorTextViewRegister.setTextColor(Color.BLACK)
    }

    private fun showPasswordErrorDoesntMatchError() {
        secondPasswordEditTextRegister.error = getString(R.string.doesnt_match_error)
    }

    private fun showRegisterInputValueError() {
        showToast("Enter data. Value is empty")
    }

    private fun showMessageUserExist() {
        loginEditTextRegister.error = getString(R.string.exist_user_sign_up)
    }

    private fun hideError() {
        loginEditTextRegister.error = null
        firsPasswordEditTextRegister.error = null
        secondPasswordEditTextRegister.error = null
    }
}