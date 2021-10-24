package com.adrian.thecrypto.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.thecrypto.R
import com.adrian.thecrypto.core.adapter.MainAdapter
import com.adrian.thecrypto.core.viewmodel.FavoriteViewModel
import com.adrian.thecrypto.core.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var favoriteAdapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            favoriteAdapter = MainAdapter()
            setDataToAdapter()
            rv_favorite.layoutManager = LinearLayoutManager(context)
            rv_favorite.setHasFixedSize(true)
            rv_favorite.adapter = favoriteAdapter
        }
    }

    private fun setDataToAdapter() {
        favoriteViewModel.favoriteCoin.observe(viewLifecycleOwner, { coin ->
            if (coin.isNotEmpty()) {
                favoriteAdapter.setData(coin)
                favoriteAdapter.onItemClick = {
                    val intent = Intent(activity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.COIN_DATA, it)
                    startActivity(intent)
                }
                rv_favorite.visibility = View.VISIBLE
                error_view.visibility = View.GONE
            } else {
                rv_favorite.visibility = View.GONE
                error_view.visibility = View.VISIBLE
            }
        })
    }
}