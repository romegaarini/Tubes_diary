package org.d3if0031.diary.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.diary.R
import com.example.diary.databinding.FragmentListFavoriteDiaryBinding

class ListFavoriteDiaryFragment : Fragment(R.layout.fragment_list_favorite_diary) {

    private lateinit var binding: FragmentListFavoriteDiaryBinding

    private val viewModel: DiaryViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ListDiaryAdapter(viewModel)

        binding = FragmentListFavoriteDiaryBinding.bind(view)
        binding.rvListFavoriteDiary.adapter = adapter

        viewModel.favoriteDiaries.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        }

        viewModel.openDetail.observe(viewLifecycleOwner) {
            if (!it) return@observe
            val dir = ListFavoriteDiaryFragmentDirections
                .actionListFavoriteDiaryFragmentToDetailDiaryFragment()
            findNavController().navigate(dir)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }
}