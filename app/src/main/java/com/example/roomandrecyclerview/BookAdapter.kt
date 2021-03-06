package com.example.roomandrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomandrecyclerview.db.Book
import kotlinx.android.synthetic.main.book_row.view.*

class BookAdapter(private val bookList: List<Book>, val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.book_row, parent, false)

        return BookViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {

        val currentItem = bookList[position]
        holder.title.text = currentItem.title
        holder.author.text = currentItem.author
        holder.id.text = currentItem.id.toString()

        holder.bind(currentItem,itemClickListener)

    }

    override fun getItemCount() = bookList.size

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.tvTitle
        val author: TextView = itemView.tvAuthor
        val id: TextView = itemView.tvId

        fun bind(book: Book, clickListener: OnItemClickListener) {
            title.text = book.title
            author.text =  book.author
            id.text = book.id.toString()

            itemView.setOnClickListener {
                clickListener.onClickedItem(book)
            }
        }

    }

}

interface OnItemClickListener {
    fun onClickedItem(book: Book)
}

