package com.beyzaatilgan.bilgisaklamaprojesi

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.beyzaatilgan.bilgisaklamaprojesi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //sharedPreferences : bilgi saklama.bir .xml dosyası oluşturuyor ve cihazın hafızasında saklıyor.Böylelikle bilgi okunabilir erişilebilir oluyor.
    //key-value şeklinde bir iki tane veriyi saklamak için ideal.
    lateinit var sharedPreferences: SharedPreferences // bu dosyanın her yerinden erişmek için.
    var alinanKullaniciAdi : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
         sharedPreferences = this.getSharedPreferences("com.beyzaatilgan.bilgisaklamaprojesi",
            Context.MODE_PRIVATE)// name karışıklık olmasın diye dosya adı. mode private başka hiç bir uygulama erişemesin diye.

        alinanKullaniciAdi = sharedPreferences.getString("isim","")
        if(alinanKullaniciAdi == ""){
            binding.textView.text = "kaydedilen isim "
        }else{
            binding.textView.text = "kaydedilen isim ${alinanKullaniciAdi}"
        }


    }
    fun kaydet(view: View){
        val kullaniciIsmi = binding.editText.text.toString()
        if(kullaniciIsmi == ""){
            Toast.makeText(this@MainActivity,"isminizi boş bırakmayın!",Toast.LENGTH_LONG).show()
        }else{
            sharedPreferences.edit().putString("isim",kullaniciIsmi).apply()
            binding.textView.text = "kaydedilen isim ${kullaniciIsmi}"
        }


    }
    fun sil(view: View){
        alinanKullaniciAdi = sharedPreferences.getString("isim","")
        if(alinanKullaniciAdi != ""){
            sharedPreferences.edit().remove("isim").apply()
        }
        binding.textView.text = "kaydedilen isim"


    }
}