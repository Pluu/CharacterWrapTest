package com.pluu.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.pluu.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edit.doAfterTextChanged {
            binding.edit2.setText(it.toString())
        }

        binding.edit2.addTextChangedListener(object : TextWatcher {
            private var previous = ""
            private var start = 0
            private var isDelete = false

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                previous = s.toString()
                this.start = start
                Log.d(
                    "TAG",
                    "Previous start=${start}, count=${count}, after=${after}"
                )
                Log.d(
                    "TAG",
                    "Previous=[${previous.toCode()}]"
                )
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                Log.d("TAG", "After=[${s.toString().toCode()}]")
                binding.edit2.removeTextChangedListener(this)
                isDelete = previous.length > s.length
                if (isDelete) {
                    // Delete
                    if (start > 0 && previous[start] == '⁠') {
                        s.delete(start - 1, start)
                    }
                    Log.d("TAG", "D $start ${previous[start]}=(${previous[start].code})")
                } else {
                    Log.d("TAG", "I $start ${s[start]}=(${s[start].code})")
                }

                val result = CharacterWrap.toWrap(s.toString())
                binding.iv.text = result.toCode()

                s.replace(0, s.length, result)

                Log.d("TAG", "Changed=[${s.toString().toCode()}]")
                binding.edit2.addTextChangedListener(this)
            }
        })
    }

    private fun CharSequence.toCode() = toString().toCharArray().joinToString {
        "$it=${it.code}"
    }
}