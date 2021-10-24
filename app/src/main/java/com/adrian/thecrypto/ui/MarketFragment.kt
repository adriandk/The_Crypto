package com.adrian.thecrypto.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.thecrypto.R
import com.adrian.thecrypto.core.adapter.MainAdapter
import com.adrian.thecrypto.core.data.Resource
import com.adrian.thecrypto.core.utils.SortUtils.HIGHEST
import com.adrian.thecrypto.core.utils.SortUtils.LOWEST
import com.adrian.thecrypto.core.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_market.*
import org.koin.android.viewmodel.ext.android.viewModel

class MarketFragment : Fragment() {

    private val marketViewModel: MainViewModel by viewModel()
    private lateinit var marketAdapter: MainAdapter
    private var state: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_market, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            marketAdapter = MainAdapter()

            getData(null, HIGHEST)

            price_button.setOnClickListener {
                state = if (state) {
                    price_button.icon = ContextCompat.getDrawable(requireActivity(), R.drawable.arrow_up_icon)
                    getData(null, LOWEST)
                    false
                } else {
                    price_button.icon = ContextCompat.getDrawable(requireActivity(), R.drawable.arrow_down_icon)
                    getData(null, HIGHEST)
                    true
                }
            }

            swipe_refresh.setOnRefreshListener {
                getData(null, HIGHEST)
                swipe_refresh.isRefreshing = false
            }
            marketAdapter.onItemClick = {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.COIN_DATA, it)
                startActivity(intent)
            }
            rv_market.layoutManager = LinearLayoutManager(context)
            rv_market.setHasFixedSize(true)
            rv_market.adapter = marketAdapter
        }
    }

    private fun getData(search: String?, sort: String) {
        marketViewModel.getAllCoin(sort).observe(viewLifecycleOwner, { coin ->
            if (coin != null) {
                when (coin) {
                    is Resource.Loading -> {
                        loading_view.visibility = View.VISIBLE
                        error_view.visibility = View.GONE
                        rv_market.visibility = View.GONE
                    }
                    is Resource.Error -> {
                        loading_view.visibility = View.GONE
                        error_view.visibility = View.VISIBLE
                        rv_market.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        loading_view.visibility = View.GONE
                        error_view.visibility = View.GONE
                        rv_market.visibility = View.VISIBLE
                        if (search != null) {
                            marketViewModel.searchCoin(search.toString()).observe(viewLifecycleOwner, {
                                if (it.isNotEmpty()) {
                                    marketAdapter.setData(it)
                                } else {
                                    marketAdapter.setData(coin.data)
                                }
                            })
                        } else {
                            Log.e("MarketFragment", "${coin.data}")
                            marketAdapter.setData(coin.data)
                        }
                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        activity?.menuInflater?.inflate(R.menu.search_menu, menu)
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search_button).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))

        searchView.queryHint = "Search Coins"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                getData(query.toString(), HIGHEST)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

}