package com.example.marvelbunch.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.marvelbunch.R
import com.example.marvelbunch.models.Comics


class ComicsAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var comicsList = emptyList<Comics>()

    private var onClickListener: OnClickListener? = null

//    inner class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val title: TextView = itemView.findViewById(R.id.character_name)
//        val thumbnailImageView: ImageView = itemView.findViewById(R.id.character_thumbnail)
//
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_comic_layout, parent, false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val comicsList = comicsList[position]
        if (holder is MyViewHolder) {
            holder.itemView.findViewById<TextView>(R.id.tv_title).text = comicsList.title
            holder.itemView.findViewById<TextView>(R.id.tv_pageCount).text = comicsList.pageCount
            val path = comicsList.thumbnail.path
            val extension = comicsList.thumbnail.extension
            val image = "${path}.${extension}"

            holder.itemView.findViewById<ImageView>(R.id.iv_thumbnail).load(image)


            holder.itemView.setOnClickListener {
                if (onClickListener != null) {
                    onClickListener!!.onClick(position, comicsList)
                }
            }

            Log.d("thumbs", "$image")

        }
    }

        override fun getItemCount(): Int {
            return comicsList.size
        }


        interface OnClickListener {
            fun onClick(position: Int, model: Comics)
        }

        fun setOnClickListener(onClickListener: OnClickListener) {
            this.onClickListener = onClickListener
        }

        fun setData(comicList: List<Comics>) {
            this.comicsList = comicList
            notifyDataSetChanged()
        }


        private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)



    }

