package com.example.marvelbunch.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.marvelbunch.models.Characters
import com.example.marvelbunch.R


class CharacterAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var characterList = emptyList<Characters>()

    private var onClickListener: OnClickListener? = null

//    inner class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val title: TextView = itemView.findViewById(R.id.character_name)
//        val thumbnailImageView: ImageView = itemView.findViewById(R.id.character_thumbnail)
//
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_character_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val characterList = characterList[position]
        if (holder is MyViewHolder) {
            holder.itemView.findViewById<TextView>(R.id.character_name).text = characterList.title
            val path = characterList.thumbnail.path
            val extension = characterList.thumbnail.extension
            val image = "${path}.${extension}"

            holder.itemView.findViewById<ImageView>(R.id.character_thumbnail).load(image)


            holder.itemView.setOnClickListener {
                if (onClickListener != null) {
                    onClickListener!!.onClick(position, characterList)
                }
            }

            Log.d("thumbs", "$image")

        }
    }

        override fun getItemCount(): Int {
            return characterList.size
        }


        interface OnClickListener {
            fun onClick(position: Int, model: Characters)
        }

        fun setOnClickListener(onClickListener: OnClickListener) {
            this.onClickListener = onClickListener
        }

        fun setData(charactersList: List<Characters>) {
            this.characterList = charactersList
            notifyDataSetChanged()
        }


        private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    }

