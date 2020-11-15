package com.mmajka.waluty.Fragments.AllCurrencies

import android.app.Application
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.mmajka.waluty.Adapters.RecyclerAdapter
import com.mmajka.waluty.Adapters.onClickInterface
import com.mmajka.waluty.CurrencyDetails.CurrencyDetails
import com.mmajka.waluty.Database.AllCurrenciesLocal
import com.mmajka.waluty.Database.DataSource
import com.mmajka.waluty.MainActivity.ConnectionChecker
import com.mmajka.waluty.Models.CurrencyModelItem
import com.mmajka.waluty.Models.Rate
import com.mmajka.waluty.R
import com.mmajka.waluty.REST.ApiClient
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response

class AllCurrencies: Fragment(), onClickInterface {

    //----------DATA
    private var currencyList = listOf<CurrencyModelItem>()
    private var rateList = listOf<Rate>()



    //----------VIEWS
    private lateinit var allRecycler: RecyclerView
    private lateinit var updateDate: TextView
    private lateinit var allCurrencies: ConstraintLayout

    var localData = mutableListOf<AllCurrenciesLocal>()
    private var dataSource = DataSource()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.all_currencies, null)
        val networkConnection = ConnectionChecker(context!!)
        //----------INIT
        allRecycler = view.findViewById(R.id.allRecycler)
        updateDate = view.findViewById(R.id.updateDate)
        allCurrencies = view.findViewById(R.id.allCurrencies)


        //----------METHOD CALLS
        //setupRecycler()

        Log.d("RESPONSE_TAG", "${rateList}")

        runBlocking {
            val job = launch {
                localData = dataSource.updateLocalData()
                delay(500)
                Log.d("SIZE_2", "${localData.size}")
                insertData()
            }
            job.start()
        }


        return view
    }

    private fun getData(){
        val viewModel = ViewModelProvider(this).get(AllCurrenciesViewModel::class.java)
        val list = mutableListOf<Rate>()
        for (i in 0..100){
            list.add(viewModel.getAllCurrenciesAsync())
        }
        allRecycler.layoutManager = LinearLayoutManager(context)
        allRecycler.adapter = RecyclerAdapter(list, this)

    }

//    private fun setupRecycler(){
//
//        val viewModel = ViewModelProvider(this).get(AllCurrenciesViewModel::class.java)
//            viewModel.getAllCurrencies().observe(viewLifecycleOwner, Observer {
//                allRecycler.layoutManager = LinearLayoutManager(context)
//                allRecycler.adapter = RecyclerAdapter(it, this)
//            })
//
//
//
//    }

    private fun insertData(){
        val viewModel = ViewModelProvider(this).get(AllCurrenciesViewModel::class.java)
        var index = 0
        do {
            val allCurrenciesLocal = localData[index]
            viewModel.insertCurrencies(allCurrenciesLocal)
        }while (index < localData.size)

    }

    override fun onClickListener(rate: AllCurrenciesLocal, position: Int, view: View) {
        val intent = Intent(context, CurrencyDetails::class.java)
        intent.putExtra("code", rate.code)
        intent.putExtra("name", rate.currency)
        intent.putExtra("rate", rate.mid.toString())
        Toast.makeText(context,"${rate.mid}", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    override fun onLongClickListener(rate: AllCurrenciesLocal, position: Int, view: View) {

    }
}