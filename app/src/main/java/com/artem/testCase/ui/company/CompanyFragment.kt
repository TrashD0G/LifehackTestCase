package com.artem.testCase.ui.company

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.artem.testCase.databinding.FragmentCompanyBinding
import com.bumptech.glide.Glide


class CompanyFragment : Fragment() {

    private var fragmentCompanyBinding: FragmentCompanyBinding? = null
    private lateinit var companyViewModel: CompanyViewModel
    private val args by navArgs<CompanyFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Binding
        fragmentCompanyBinding = FragmentCompanyBinding.inflate(inflater, container, false)

        //ViewModel
        companyViewModel = ViewModelProvider(this).get(CompanyViewModel::class.java)

        companyViewModel.getCompanyInformation(args.CompanyPOJO.id)

        companyViewModel.lifehackResponse.observe(viewLifecycleOwner, {response ->
            if (response.isSuccessful){

                Log.i("MyTag","Company Fragment: Vse v poradke")
                response.body()?.let { companyViewModel.setData(it) }
            } else {

                Toast.makeText(requireContext(),"Company Fragment: Ошибка !", Toast.LENGTH_LONG).show()
            }
        })

        companyViewModel.errorChecker.observe(viewLifecycleOwner, { error ->
            if (error) {

                fragmentCompanyBinding?.txtDescription?.text = "Произошла ошибка ! :("
                Log.i("MyTag", "Error: " + error.toString())
                Toast.makeText(requireContext(), "Company Fragment: Ошибка подключения!", Toast.LENGTH_LONG).show()
            }
        })




        companyViewModel.companyName.observe(requireActivity(),{
            fragmentCompanyBinding?.txtCompanyName?.text = it
        })
        companyViewModel.description.observe(requireActivity(),{
            fragmentCompanyBinding?.txtDescription?.text = it
        })
        companyViewModel.website.observe(requireActivity(),{
            fragmentCompanyBinding?.txtWebSite?.text = it
        })
        companyViewModel.phone.observe(requireActivity(),{
            fragmentCompanyBinding?.txtPhone?.text = it.toString()
        })
        companyViewModel.img.observe(requireActivity(),{
            Glide.with(requireContext()).load("https://lifehack.studio/test_task/" + it).into(fragmentCompanyBinding!!.imgCompany)
        })



        return fragmentCompanyBinding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentCompanyBinding = null
    }

}