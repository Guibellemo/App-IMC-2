package com.example.bancodedados

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.example.bancodedados.dao.UsuarioDao
import com.example.bancodedados.databinding.ActivityCadastroUsuariosBinding
import com.example.bancodedados.model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CadastroUsuarios : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroUsuariosBinding
    private var usuarioDao: UsuarioDao? = null
    private var listaUsuarios: MutableList<Usuario> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroUsuariosBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btCadastrar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val nome = binding.editNome.text.toString()
                val sobrenome = binding.editSobrenome.text.toString()
                val idade = binding.editIdade.text.toString()
                val peso = binding.editPeso.text.toString()
                val altura = binding.editAltura.text.toString()
                val imc: String
                val mensagem: Boolean

                if(nome.isEmpty() || sobrenome.isEmpty() || idade.isEmpty() || peso.isEmpty() || altura.isEmpty()){
                    mensagem = false

                }else{
                    mensagem = true

                    val pesoIn = binding.editPeso.text.toString().toDouble()
                    val alturaIn = binding.editAltura.text.toString().toDouble()
                    val resultado = Math.round(pesoIn / (alturaIn/100*alturaIn/100))



                    binding.textoResultado.text = "$resultado"

                    imc = resultado.toString()


                    cadastrar(nome,sobrenome,idade,peso,altura,imc)

                }

                withContext(Dispatchers.Main){
                    if(mensagem){
                        Toast.makeText(applicationContext,"Cadastro realizado com sucesso.", Toast.LENGTH_SHORT).show()
                        finish()
                    }else{
                        Toast.makeText(applicationContext,"Preencher todos os campos.", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }






    private fun cadastrar(nome: String, sobrenome: String, idade: String, peso: String, altura: String, imc: String )
    {
        val usuario = Usuario(nome, sobrenome, idade, peso, altura, imc)
        listaUsuarios.add(usuario)
        usuarioDao = AppDatabase.getInstance(this).usuarioDao()
        usuarioDao!!.inserir(listaUsuarios)

    }

}