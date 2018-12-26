package com.petarda.teachermarker.teachermarker.screen.login

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.cardpay.digipass.android.base.BaseFragment
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.petarda.teachermarker.teachermarker.R
import com.petarda.teachermarker.teachermarker.screen.authentication_base.AuthenticationActivity
import com.petarda.teachermarker.teachermarker.screen.register.RegisterFragment
import com.petarda.teachermarker.teachermarker.screen.subjects.SubjectsActivity
import com.petarda.teachermarker.teachermarker.util.hideKeyboard
import com.petarda.teachermarker.teachermarker.util.showToast
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment() {
    override val fragmentResId: Int
        get() = R.layout.fragment_login

    private lateinit var viewModel: LoginViewModel
//    private lateinit var user: UserState

    companion object {
        fun newInstance(mode: Mode): LoginFragment {
            val fragment = LoginFragment()
            val arguments = Bundle()
            arguments.putSerializable(MODE_KEY, mode)
            fragment.arguments = arguments
            return fragment
        }

        private const val MODE_KEY = "mode"

        enum class Mode {
            LOGIN,
            REGISTER
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

        initLoginButton()
        initRegisterButton()

        observeUIEvents()
    }

    private fun initViewModel() {
        viewModel = bindViewModel(LoginViewModel::class.java)
    }

    fun initAllEditText() {
        RxTextView.textChanges(loginEditText)
            .subscribe {
                viewModel.login.value = it
            }
            .let(this::addHardDisposable)

        RxTextView.textChanges(passwordEditText)
            .subscribe {
                viewModel.password.value = it
            }
            .let(this::addHardDisposable)
    }

    private fun initLoginButton() {
        RxView.clicks(signInButton)
            .compose(RxPermissions(this).ensure(Manifest.permission.READ_PHONE_STATE))
            .compose(RxPermissions(this).ensure(Manifest.permission.ACCESS_COARSE_LOCATION))
            .subscribe {
                initAllEditText()
                viewModel.authUserAndGoToChatList()
            }
            .let(this::addHardDisposable)
    }

    private fun initRegisterButton() {
        RxView.clicks(signUpButton)
            .compose(RxPermissions(this).ensure(Manifest.permission.READ_PHONE_STATE))
            .compose(RxPermissions(this).ensure(Manifest.permission.ACCESS_COARSE_LOCATION))
            .subscribe {
                viewModel.uiEvents.onNext(LoginViewModel.UIEvents.GO_TO_REGISTER)
            }
            .let(this::addHardDisposable)
    }

    private fun observeUIEvents() {
        viewModel.uiEvents
            .subscribe {
                when (it) {
                    LoginViewModel.UIEvents.NO_EXIST_USER -> showErrorNoExistUser()
                    LoginViewModel.UIEvents.INCORRECT_PASSWORD -> showErrorPassword()
                    LoginViewModel.UIEvents.INPUT_VALUE_IS_EMPTY -> showError()

                    LoginViewModel.UIEvents.GO_TO_REGISTER -> moveToNextFragment()
                    LoginViewModel.UIEvents.GO_TO_LOGIN_WITH_NAME -> moveToNextFragmentWithName()
                    LoginViewModel.UIEvents.GO_TO_LOGIN_WITHOUT_NAME -> moveToNextFragmentWithoutName()
                    LoginViewModel.UIEvents.MOVE_TO_NEXT_SCREEN -> moveToNextScreen()
                    LoginViewModel.UIEvents.HIDE_ERROR -> hideError()
                }
            }.let(this::addHardDisposable)
    }

    private fun moveToNextFragment() {
        hideKeyboard()
        (activity as? AuthenticationActivity)?.moveTo(RegisterFragment.newInstance(RegisterFragment.Companion.Mode.REGISTER))
    }

    private fun moveToNextFragmentWithName() {
        RxTextView.textChanges(loginEditText)
            .observeOn(Schedulers.io())
            .subscribe {
                viewModel.userName.postValue(it)
            }
            .let(this::addHardDisposable)
        /* val intent = Intent(activity, AuthenticationActivity::class.java)
         startActivity(intent)
         activity?.finish()*/
    }

    private fun moveToNextFragmentWithoutName() {
        /*val intent = Intent(activity, AuthenticationActivity::class.java)
        startActivity(intent)
        activity?.finish()*/
    }

    private fun showErrorNoExistUser() {
        loginEditText.error = getString(R.string.no_exist_uset_sign_in)
    }

    private fun showError() {
        showToast("Enter data. Value is empty")
    }

    private fun showErrorPassword() {
        passwordEditText.error = getString(R.string.wrong_password_sign_in)
    }

    private fun hideError() {
        loginEditText.error = null
        passwordEditText.error = null
    }

    private fun moveToNextScreen() {
        val intent = Intent(activity, SubjectsActivity::class.java)
        startActivity(intent)
    }
}