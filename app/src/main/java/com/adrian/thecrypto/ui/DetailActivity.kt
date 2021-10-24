package com.adrian.thecrypto.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.adrian.thecrypto.R
import com.adrian.thecrypto.core.data.Resource
import com.adrian.thecrypto.core.domain.model.Crypto
import com.adrian.thecrypto.core.utils.Formatter
import com.adrian.thecrypto.core.viewmodel.DetailViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val COIN_DATA = "COIN_DATA"
    }

    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        val coinData = intent.getParcelableExtra<Crypto>(COIN_DATA)
        getData(coinData!!)
    }

    private fun getData(coin: Crypto) {
        detailViewModel.getDetailCoin(coin.id).observe(this, { coinDetail ->
            Log.e("agatha", "${coinDetail.data}")
            Log.e("agatha", "$coinDetail")
            if (coinDetail != null) {
                when(coinDetail) {
                    is Resource.Loading -> {
                        content.visibility = View.GONE
                        error_view.visibility = View.VISIBLE
                    }
                    is Resource.Error -> {
                        content.visibility = View.GONE
                        error_view.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        content.visibility = View.VISIBLE
                        error_view.visibility = View.GONE
                        showDetailCoin(coinDetail.data!!)
                    }
                }
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun showDetailCoin(coin: Crypto) {

        supportActionBar?.title = coin.name
        content.crypto_price_detail.text = Formatter.getPrice(coin.price!!)
        val percentage = coin.percent.toString().substring(0,5)
        content.percent_detail.text = "${percentage}%"
        if (coin.percent!! > 0) {
            content.percent_detail.setTextColor(ContextCompat.getColor(this, R.color.green))
        } else {
            content.percent_detail.setTextColor(ContextCompat.getColor(this, R.color.red))
        }
        content.volume_detail.text = Formatter.getPrice(coin.volume!!)
        content.crypto_desc.text = coin.description
        Glide.with(this)
            .load(coin.image)
            .into(image_detail)

        var favoriteStatus = coin.favorite
        setFavoriteStatus(favoriteStatus)
        favorite_button.setOnClickListener {
            favoriteStatus = !favoriteStatus
            detailViewModel.setFavoriteCoin(coin, favoriteStatus)
            setFavoriteStatus(favoriteStatus)
        }
    }

    private fun setFavoriteStatus(state: Boolean) {
        if (state) {
            favorite_button.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.favorite_icon)
            )
        } else {
            favorite_button.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.not_favorite_icon)
            )
        }
    }
}