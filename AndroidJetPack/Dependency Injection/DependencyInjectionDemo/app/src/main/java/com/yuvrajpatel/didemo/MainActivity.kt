package com.yuvrajpatel.didemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yuvrajpatel.didemo.model.SIMCard
import com.yuvrajpatel.didemo.model.SmartPhone
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var smartPhone: SmartPhone
    // Getting simCard dependency using declaration of variable with inject annotation
    @Inject
    lateinit var simCard: SIMCard


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//  Constructor dependency injection
//        DaggerSmartPhoneComponent.create()
//            .getSmartPhone()
//            .makeACallWithRecording()

        // Field Dependency injection better approach when,
        // we have multiple dependencies with multiple activities and fragments
//        DaggerSmartPhoneComponent.builder()
//            .batteryModule(BatteryModule(5000))
//            .build()
//            .inject(this)


        // Dependency injection through application in case,
        // when application has immutable data such as network client object or data persistence and crash reporting
        // To avoid writing same code of initialization in all activities, use followings
        (application as App).smartPhoneComponent
            .inject(this)

        smartPhone.makeACallWithRecording()
        simCard.getConnection()

    }
}
