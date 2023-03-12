package io.github.skullhound.boodschappenlijst

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class UpdateModifyDialogFragment(private val item: ItemsViewModel) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
            val builder = AlertDialog.Builder(it)
            val textInput = EditText(it)

            textInput.setText(item.text)

            builder.setTitle(getString(R.string.modify_title))
                .setView(textInput)
                .setCancelable(true)
                .setPositiveButton(R.string.update
                ) { _, _ ->
                    if (textInput.text.isNotEmpty()) {
                        (it as MainActivity).updateItem(item, textInput.text.toString())
                    }
                }
                .setNegativeButton(R.string.remove,
                ) { _, _ ->
                    (it as MainActivity).removeItem(item)
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        const val TAG = "UpdateModifyDialog"
    }

}