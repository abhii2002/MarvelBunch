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
import com.example.marvelbunch.models.Events


class EventsAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var eventsList = emptyList<Events>()

    private var onClickListener: OnClickListener? = null

//    inner class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val title: TextView = itemView.findViewById(R.id.character_name)
//        val thumbnailImageView: ImageView = itemView.findViewById(R.id.character_thumbnail)
//
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_events_item, parent, false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val eventsList = eventsList[position]
        if (holder is MyViewHolder) {
            holder.itemView.findViewById<TextView>(R.id.tv_events_title).text = eventsList.title
           // holder.itemView.findViewById<TextView>(R.id.tv_description).text = comicsList.pageCount
            val path = eventsList.thumbnail.path
            val extension = eventsList.thumbnail.extension
            val image = "${path}.${extension}"

            holder.itemView.findViewById<ImageView>(R.id.iv_events).load(image)


            holder.itemView.setOnClickListener {
                if (onClickListener != null) {
                    onClickListener!!.onClick(position, eventsList)
                }
            }

            Log.d("thumbs", "$image")

        }
    }

        override fun getItemCount(): Int {
            return eventsList.size
        }


        interface OnClickListener {
            fun onClick(position: Int, model: Events)
        }

        fun setOnClickListener(onClickListener: OnClickListener) {
            this.onClickListener = onClickListener
        }

        fun setData(eventsList: List<Events>) {
            this.eventsList = eventsList
            notifyDataSetChanged()
        }


        private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)



    }

