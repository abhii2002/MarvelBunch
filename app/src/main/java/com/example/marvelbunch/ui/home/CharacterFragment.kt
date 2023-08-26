package com.example.marvelbunch.ui.home

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.marvelbunch.models.CharacterModel
import com.example.marvelbunch.models.Characters
import com.example.marvelbunch.R
import com.example.marvelbunch.activities.CharacterDetailActivity
import com.example.marvelbunch.adapters.CharacterAdapter
import com.example.marvelbunch.constants.Constants
import com.example.marvelbunch.constants.ScreenState
import com.example.marvelbunch.databinding.FragmentCharacterBinding
import com.example.marvelbunch.viewmodel.SharedViewModel
import com.google.android.material.snackbar.Snackbar

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class CharacterFragment : Fragment() {

    private var _binding: FragmentCharacterBinding? = null
    private lateinit var mProgressDialog: Dialog


    private val viewModel: SharedViewModel by lazy {
         ViewModelProvider(this@CharacterFragment).get(SharedViewModel::class.java)
    }
    private val adapter: CharacterAdapter by lazy { CharacterAdapter() }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.charactersLiveData.observe(viewLifecycleOwner){ it ->

             processCharacterResponse(it)

            adapter.setOnClickListener(object : CharacterAdapter.OnClickListener{
                override fun onClick(position: Int, model: Characters) {
                     val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                    val jsonAdapter: JsonAdapter<Characters> = moshi.adapter(Characters::class.java)
                    var jSonModel = jsonAdapter.toJson(model)
                   val intent = Intent(context, CharacterDetailActivity::class.java)
                    intent.putExtra(Constants.CHARACTER_DETAILS, jSonModel)
                    startActivity(intent)

                }

            })
        }



    }

    private fun processCharacterResponse(state: ScreenState<CharacterModel>){
        when(state) {

             is ScreenState.Loading -> {
                 showDialog()
             }

             is ScreenState.Success -> {
                 hideDialog()
                 adapter.setData(state.data?.results!!)
                 binding.rvFragmentHome.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                 binding.rvFragmentHome.adapter = adapter
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
        mProgressDialog.setContentView(R.layout.dialog_progress)
        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)
        mProgressDialog.show()
    }

    fun hideDialog(){
        mProgressDialog.hide()

    }
}