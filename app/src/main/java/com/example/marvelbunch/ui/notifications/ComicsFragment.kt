package com.example.marvelbunch.ui.notifications

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelbunch.R
import com.example.marvelbunch.adapters.ComicsAdapter
import com.example.marvelbunch.constants.ScreenState

import com.example.marvelbunch.databinding.FragmentComicsBinding

import com.example.marvelbunch.models.MarvelComicData
import com.example.marvelbunch.viewmodel.SharedViewModel
import com.google.android.material.snackbar.Snackbar

class ComicsFragment : Fragment() {

    private var _binding: FragmentComicsBinding? = null
    private  var mProgressDialog: Dialog? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this@ComicsFragment).get(SharedViewModel::class.java)
    }
    private val adapter: ComicsAdapter by lazy { ComicsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentComicsBinding.inflate(inflater, container, false)
        val root: View = binding.root



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.comicsLiveData.observe(viewLifecycleOwner){
            processCharacterResponse(it)
        }
    }

    private fun processCharacterResponse(state: ScreenState<MarvelComicData>){
        when(state) {

            is ScreenState.Loading -> {
                showDialog()
            }

            is ScreenState.Success -> {
                hideDialog()
                adapter.setData(state.data?.results!!)
                binding.rvFragmentComics.layoutManager = LinearLayoutManager(requireContext() )
                binding.rvFragmentComics.adapter = adapter
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