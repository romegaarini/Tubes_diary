package org.d3if0031.diary.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.diary.R
import com.example.diary.databinding.FragmentAddEditDiaryBinding

class AddEditDiaryFragment : Fragment(R.layout.fragment_add_edit_diary) {

    private lateinit var binding: FragmentAddEditDiaryBinding

    private val viewModel: AddEditDiaryViewModel by viewModels()
    private val args: AddEditDiaryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        requireActivity().title = if (args.id != null) "Edit" else "Create"

        binding = FragmentAddEditDiaryBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initialize(args.id)

        viewModel.viewState.observe(viewLifecycleOwner) {
            it.diary?.let { diary ->
                binding.title.setText(diary.title)
                binding.content.setText(diary.content)
                viewModel.clear()
            }

            if (it.error.isNotEmpty()) {
                Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
            }

            if (it.isFinished) {
                findNavController().navigateUp()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add_edit_diary, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save -> {
                val title = binding.title.text.toString()
                val content = binding.content.text.toString()
                viewModel.save()
                true
            }
            else -> false
        }
    }
}