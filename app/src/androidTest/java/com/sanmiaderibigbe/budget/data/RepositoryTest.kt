package com.sanmiaderibigbe.budget.data

import android.app.Application
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.sanmiaderibigbe.budget.data.model.User
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepositoryTest{

    private lateinit var repository: Repository

    @Before
    fun setup(){
        repository = Repository.getRepository(InstrumentationRegistry.getTargetContext().applicationContext as Application, true)
        repository.deleteAllForTestingPurposes()
    }

    @Test
    fun createNewUser_User_ShouldSaveNewUser(){
        val user  = User(0, "sanmi", 1000f)
        repository.createNewUser(user)
        val currentListOfTransacation = repository.getUsers().blockingObserve()?.size
        Assert.assertEquals(currentListOfTransacation, 1)
    }




}