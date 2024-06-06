package com.dicoding.courseschedule.ui

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.courseschedule.data.DataRepository
import java.lang.reflect.InvocationTargetException


class ViewModelFactory(private val repository: DataRepository?) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        try {
            return modelClass.getConstructor(DataRepository::class.java).newInstance(repository)
        }catch (e:InstantiationException){
            throw RuntimeException("Couldn't create the instance of $modelClass",e)
        }catch (e:IllegalAccessException){
            throw RuntimeException("Couldn't create the instance of $modelClass",e )
        }catch (e:NoSuchFieldException){
            throw RuntimeException("Couldn't create the instance of $modelClass",e)
        }catch (e:InvocationTargetException){
            throw RuntimeException("Couldn't create the instance of $modelClass",e)
        }
    }

    companion object{
        fun createFac(activity: Activity):ViewModelFactory{
            val context =activity.applicationContext?:throw java.lang.IllegalStateException("Haven't initialize in the application")
            return ViewModelFactory(DataRepository.getInstance(context))
        }
    }
}