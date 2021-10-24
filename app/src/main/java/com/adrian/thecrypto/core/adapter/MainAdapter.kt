package com.adrian.thecrypto.core.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.adrian.thecrypto.R
import com.adrian.thecrypto.core.domain.model.Crypto
import com.adrian.thecrypto.core.utils.Formatter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list.view.*

class MainAdapter: RecyclerView.Adapter<MainAdapter.ViewHolder>(){

    private val coinList = ArrayList<Crypto>()
    var onItemClick: ((Crypto) -> Unit)? = null

    fun setData(listDataCoin: List<Crypto>?) {
        if (listDataCoin == null) return
        coinList.clear()
        coinList.addAll(listDataCoin)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(coinList[position])
    }

    override fun getItemCount(): Int = coinList.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind (coin: Crypto) {
            with(itemView) {
                crypto_id.text = coin.symbol
                crypto_name.text = coin.name
                crypto_price.text = Formatter.getPrice(coin.price!!)
                val percentage = coin.percent.toString().substring(0,5)
                percent_text.text = "${percentage}%"
                if (coin.percent!! > 0) {
                    percent_text.setTextColor(ContextCompat.getColor(context, R.color.green))
                } else {
                    percent_text.setTextColor(ContextCompat.getColor(context, R.color.red))
                }
                Glide.with(itemView.context)
                    .load(coin.image)
                    .into(crypto_image)
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(coinList[position])
            }
        }
    }
}