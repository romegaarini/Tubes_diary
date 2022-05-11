package org.d3if0031.diary.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if0031.diary.data.Diary
import com.example.diary.databinding.ViewListDiaryBinding


class ListDiaryAdapter(private val viewModel: DiaryViewModel) :
    ListAdapter<Diary, ListDiaryAdapter.ViewHolder>(TaskDiffCallback()) {

    fun notifyUpdate(position: Int) {
        notifyItemChanged(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(
        val binding: ViewListDiaryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewListDiaryBinding.inflate(layoutInflater)
                return ViewHolder(binding)
            }
        }

        fun bind(viewModel: DiaryViewModel, diary: Diary) = binding.apply {
            this.viewModel = viewModel
            this.diary = diary
            executePendingBindings()
        }
    }
}

class TaskDiffCallback : DiffUtil.ItemCallback<Diary>() {
    override fun areItemsTheSame(oldItem: Diary, newItem: Diary): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Diary, newItem: Diary): Boolean {
        return oldItem == newItem
    }
}
