package com.example.bancodedados.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bancodedados.model.Usuario


@Dao
interface UsuarioDao {

    @Insert
    fun inserir(listaUsuarios: MutableList<Usuario>)

    @Query("SELECT * FROM tabela_usuarios ORDER BY nome ASC")
    fun get(): MutableList<Usuario>

    @Query("UPDATE tabela_usuarios SET nome = :novoNome, sobrenome = :novoSobrenome, idade = :novaIdade, peso = :novoPeso, altura = :novaAltura, imc = :novoImc " +
           "WHERE UID = :id")

    fun atualizar(
        id: Int,
        novoNome: String,
        novoSobrenome: String,
        novaIdade: String,
        novoPeso: String,
        novaAltura: String,
        novoImc: String
    )
    @Query("DELETE FROM tabela_usuarios WHERE uid = :id")
    fun deletar(id: Int)
}