package com.internalcontentviewer.contactsData


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.internalcontentviewer.R
import com.internalcontentviewer.contactsData.view.ContactListItemAdapter
import com.internalcontentviewer.core.BaseActivity
import com.internalcontentviewer.core.BaseViewModelFactory
import com.internalcontentviewer.core.DataFetchState
import com.internalcontentviewer.core.StateMachine
import com.internalcontentviewer.error.StandardError
import com.internaldatapicker.contactsData.ContactListViewModel
import kotlinx.android.synthetic.main.activity_contacts_list.*
import kotlinx.coroutines.launch

class ContactsListActivity : BaseActivity() {
    private val factory = object : BaseViewModelFactory(){
        override fun createViewModel() = ContactListViewModel()
    }
    private val viewModel by viewModels<ContactListViewModel> { factory }
    private val adapter by lazy { ContactListItemAdapter() }
    private val stateMachine = StateMachine()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_list)

        setupToolbar(ToolbarConfig("ContactList", true, {
            onBackPressedDispatcher.onBackPressed()
        }))

        init()

    }

    private fun init() {

        contactListRecyclerView.layoutManager = LinearLayoutManager(this)
        contactListRecyclerView.adapter = adapter

        stateMachine.observe(this) {
            when(it) {
                is DataFetchState.Loading -> loading()
                is DataFetchState.Success -> success()
                is DataFetchState.Error   -> error(it.error)
            }
        }

        getContacts()

    }

    private fun loading() {
        Log.e(TAG, "loading: " )
    }

    private fun success(){
        setAdapter()
        Log.e(TAG, "success: " )
    }

    private fun error(standardError: StandardError) {
        Log.e(TAG, "error: ${standardError.displayError}" )
    }

    private fun setAdapter() {
        adapter.updateData(viewModel.items)
    }

    private fun getContacts() {
        lifecycleScope.launch {
            viewModel.getAllContacts(stateMachine)
            setAdapter()
        }
    }
}