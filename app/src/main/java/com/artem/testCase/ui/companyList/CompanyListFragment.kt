package com.artem.testCase.ui.companyList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.artem.testCase.adapter.CompanyAdapter
import com.artem.testCase.databinding.FragmentCompanyListBinding


class CompanyListFragment : Fragment() {

    private var fragmentCompanyListBinding: FragmentCompanyListBinding? = null
    private lateinit var companyListViewModel: CompanyListVIewModel
    private val companyAdapter by lazy { CompanyAdapter(requireContext()) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Binding
        fragmentCompanyListBinding = FragmentCompanyListBinding.inflate(inflater,container,false)


        //ViewModel
        companyListViewModel = ViewModelProvider(this).get(CompanyListVIewModel::class.java)
        companyListViewModel.getCompany()
        companyListViewModel.lifehackResponse.observe(viewLifecycleOwner, {response ->
            if (response.isSuccessful){
                setupRecyclerview()
                response.body()?.let { companyAdapter.setData(it) }
            } else {

                Toast.makeText(requireContext()," Ошибка !", Toast.LENGTH_LONG).show()
            }
        })

        companyListViewModel.errorChecker.observe(viewLifecycleOwner, { error ->
            if (error) {
                setupRecyclerview()

                Toast.makeText(requireContext(), "Ошибка подключения!", Toast.LENGTH_LONG).show()
            }
        })


        return fragmentCompanyListBinding?.root
    }



    private fun setupRecyclerview(){
        fragmentCompanyListBinding?.recyclerCompany?.adapter = companyAdapter
        fragmentCompanyListBinding?.recyclerCompany?.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentCompanyListBinding = null
    }


}

