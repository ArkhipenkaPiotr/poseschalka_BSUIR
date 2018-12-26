package com.petarda.teachermarker.teachermarker.screen.authentication_base

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.cardpay.digipass.android.base.BaseFragmentActivity
import com.petarda.teachermarker.teachermarker.R
import com.petarda.teachermarker.teachermarker.screen.login.LoginFragment
import com.petarda.teachermarker.teachermarker.screen.register.RegisterFragment

class AuthenticationActivity : BaseFragmentActivity() {
    override val fragmentContainerResId: Int
        get() = R.id.authenticationContainer
    override val layoutResId: Int
        get() = R.layout.activity_authentication

    private lateinit var viewModel: AuthenticationViewModel


    private fun bindViewModel() {
        viewModel = bindViewModel(AuthenticationViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            checkExtra()
        }
    }

    private fun checkExtra() {
        val mode = intent.getSerializableExtra(MODE_KEY)
        if (mode == null) {
            bindViewModel()

            /**Если изменится логика входа, можно убрать строчку перехода на LoginFragment,
            и прослушивать events в зависимости от логики. Сейчас же реализован автоматически переход.
             */
            moveToWithoutHistory(LoginFragment.newInstance(LoginFragment.Companion.Mode.LOGIN))
            observeUIEvents()
        }
    }

    private fun observeUIEvents() =
        viewModel.uiEvents.observe(this, Observer {
            when (it) {
                AuthenticationViewModel.UIEvents.GO_TO_LOGIN -> moveToWithoutHistory(LoginFragment())
                AuthenticationViewModel.UIEvents.GO_TO_REGISTER -> moveToWithoutHistory(RegisterFragment())
            }
        })

    companion object {
        fun newIntent(context: Context, attachFragment: MODE): Intent {
            val intent = Intent(context, AuthenticationActivity::class.java)
            intent.putExtra(MODE_KEY, attachFragment)

            return intent
        }

        private const val MODE_KEY = "mode"

        enum class MODE {
            LOGIN,
            REGISTER
        }
    }
}