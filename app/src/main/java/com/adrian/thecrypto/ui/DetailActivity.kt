package com.adrian.thecrypto.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.adrian.thecrypto.R
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
        showDetailCoin(coinData)
    }

    @SuppressLint("SetTextI18n")
    private fun showDetailCoin(coin: Crypto?) {
        coin?.let {
            supportActionBar?.title = coin.name
            content.crypto_price_detail.text = Formatter.getPrice(coin.price!!)
            val percentage = coin.percent.toString().substring(0,5)
            content.percent_detail.text = "${percentage}%"
            content.ath_detail.text = Formatter.getPrice(coin.ath!!)
            content.supply_detail.text = Formatter.getPrice(coin.supply!!)
            content.high_detail.text = Formatter.getPrice(coin.high!!)
            content.low_detail.text = Formatter.getPrice(coin.low!!)
            if (coin.percent!! > 0) {
                content.percent_detail.setTextColor(ContextCompat.getColor(this, R.color.green))
            } else {
                content.percent_detail.setTextColor(ContextCompat.getColor(this, R.color.red))
            }
            content.volume_detail.text = Formatter.getPrice(coin.volume!!)
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