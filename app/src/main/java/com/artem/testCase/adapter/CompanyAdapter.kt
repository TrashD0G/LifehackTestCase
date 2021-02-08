package com.artem.testCase.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.artem.testCase.POJO.CompanyPOJO
import com.artem.testCase.databinding.CompanyItemBinding
import com.artem.testCase.ui.companyList.CompanyListFragmentDirections
import com.bumptech.glide.Glide

class CompanyAdapter(private val context: Context): RecyclerView.Adapter<CompanyAdapter.ViewHolder>() {

    private var companyList = emptyList<CompanyPOJO>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = CompanyItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding,context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val companyPOJOData: CompanyPOJO = companyList[position]
        holder.bind(companyPOJOData)

        holder.itemView.setOnClickListener {
            val action = CompanyListFragmentDirections.actionCompanyListFragmentToCompanyFragment(companyPOJOData)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return companyList.size
    }

    class ViewHolder(private var itemBinding: CompanyItemBinding, private val context: Context) : RecyclerView.ViewHolder(itemBinding.root){

        fun bind(companyPOJOData: CompanyPOJO){
            itemBinding.txtCompanyName.text = companyPOJOData.companyName
            Glide.with(context).load("https://lifehack.studio/test_task/" + companyPOJOData.avatar).centerCrop().into(itemBinding.imgAvatar)
        }
    }

    fun setData(newCompanyPOJOList: ArrayList<CompanyPOJO>){
        companyList = newCompanyPOJOList
        notifyDataSetChanged()
    }
}