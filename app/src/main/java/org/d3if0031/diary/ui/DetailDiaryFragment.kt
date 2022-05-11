package org.d3if0031.diary.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.diary.R
import org.d3if0031.diary.data.Diary
import com.example.diary.databinding.FragmentDetailDiaryBinding

class DetailDiaryFragment : Fragment(R.layout.fragment_detail_diary) {

    private val viewModel: DiaryViewModel by activityViewModels()

    private lateinit var binding: FragmentDetailDiaryBinding

    private var diary: Diary? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        binding = FragmentDetailDiaryBinding.bind(view)
        binding.viewModel = viewModel

        viewModel.selectedDiary.observe(viewLifecycleOwner) {
            viewModel.clear()
            if (it == null) {
                findNavController().navigateUp()
            }

            diary = it
            binding.diary = it
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail_diary, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.edit -> {
                val dir = DetailDiaryFragmentDirections
                    .actionDetailDiaryFragmentToCreateEditDiaryFragment(diary?.id)
                findNavController().navigate(dir)
                true
            }
            R.id.delete -> {
                viewModel.delete(diary!!)
                true
            }
            else -> false
        }
    }

    override fun onResume() {
        super.onResume()
        diary?.let { viewModel.refresh(it.id) }
    }
}