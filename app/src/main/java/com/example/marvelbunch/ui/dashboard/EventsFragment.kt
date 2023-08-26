package com.example.marvelbunch.ui.dashboard

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelbunch.R
import com.example.marvelbunch.activities.EventsDetailActivity
import com.example.marvelbunch.adapters.EventsAdapter
import com.example.marvelbunch.constants.Constants
import com.example.marvelbunch.constants.ScreenState
import com.example.marvelbunch.databinding.FragmentEventsBinding
import com.example.marvelbunch.models.Events

import com.example.marvelbunch.models.EventsModel
import com.example.marvelbunch.viewmodel.SharedViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson

class EventsFragment : Fragment() {

    private var _binding: FragmentEventsBinding? = null

    private  var mProgressDialog: Dialog? = null


    private val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this@EventsFragment).get(SharedViewModel::class.java)
    }
    private val adapter: EventsAdapter by lazy { EventsAdapter() }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEventsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.eventsLiveData.observe(viewLifecycleOwner){ it ->

            processCharacterResponse(it)

            adapter.setOnClickListener(object : EventsAdapter.OnClickListener{
                override fun onClick(position: Int, model: Events) {
                    val gson = Gson()
                    var jSonModel = gson.toJson(model)
                    val intent = Intent(context, EventsDetailActivity::class.java)
                    intent.putExtra(Constants.EVENTS_DETAILS, jSonModel)
                    startActivity(intent)

                }

            })
        }
    }


    private fun processCharacterResponse(state: ScreenState<EventsModel>){
        when(state) {

            is ScreenState.Loading -> {
                showDialog()
            }

            is ScreenState.Success -> {
                hideDialog()
                adapter.setData(state.data?.results!!)
                binding.rvEvents.layoutManager = LinearLayoutManager(requireContext() )
                binding.rvEvents.adapter = adapter
            }

            is ScreenState.Error ->{
                hideDialog()
                val view = binding.root
                Snackbar.make(view, state.message!!, Snackbar.LENGTH_LONG).show()
            }
        }

    }

    fun showDialog(){
        mProgressDialog = Dialog(requireContext())
        mProgressDialog!!.setContentView(R.layout.dialog_progress)
        mProgressDialog!!.setCancelable(false)
        mProgressDialog!!.setCanceledOnTouchOutside(false)
        mProgressDialog!!.show()
    }

    fun hideDialog(){
        mProgressDialog?.hide()

    }

}