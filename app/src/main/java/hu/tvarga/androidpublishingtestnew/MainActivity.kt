package hu.tvarga.androidpublishingtestnew

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

import hu.tvarga.androidpublishingtest.R

private const val packageName = "hu.tvarga.dynamicfeature"
private const val mainFeaturesActivity = "$packageName.MainFeatureActivity"

class MainActivity : AppCompatActivity() {

    /** Listener used to handle changes in state for install requests. */
    private val listener = SplitInstallStateUpdatedListener { state ->
        when (state.status()) {
            SplitInstallSessionStatus.DOWNLOADING -> {
                //  In order to see this, the application has to be uploaded to the Play Store.
//                displayLoadingState(state, "Downloading $names")
            }
            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                /*
                  This may occur when attempting to download a sufficiently large module.

                  In order to see this, the application has to be uploaded to the Play Store.
                  Then features can be requested until the confirmation path is triggered.
                 */
                startIntentSender(state.resolutionIntent()?.intentSender, null, 0, 0, 0)
            }
            SplitInstallSessionStatus.INSTALLED -> {
//                onSuccessfulLoad(names, launch = !multiInstall)
            }

            SplitInstallSessionStatus.INSTALLING -> {
//                displayLoadingState(state, "Installing $names")
            }
            SplitInstallSessionStatus.FAILED -> {
//                toastAndLog("Error: ${state.errorCode()} for module ${state.moduleNames()}")
            }
        }
    }

    private val dynamicFeature by lazy { getString(R.string.module_feature_dynamic) }

    private lateinit var manager: SplitInstallManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = SplitInstallManagerFactory.create(this)

        findViewById<Button>(R.id.startFeature).setOnClickListener {
            loadAndLaunchModule(dynamicFeature)
        }
    }

    override fun onResume() {
        // Listener can be registered even without directly triggering a download.
        manager.registerListener(listener)
        super.onResume()
    }

    override fun onPause() {
        // Make sure to dispose of the listener once it's no longer needed.
        manager.unregisterListener(listener)
        super.onPause()
    }


    private fun loadAndLaunchModule(name: String) {
//        updateProgressMessage("Loading module $name")
        // Skip loading if the module already is installed. Perform success action directly.
        if (manager.installedModules.contains(name)) {
//            updateProgressMessage("Already installed")
            onSuccessfulLoad(name, launch = true)
            return
        }

        // Create request to install a feature module by name.
        val request = SplitInstallRequest.newBuilder()
                .addModule(name)
                .build()

        // Load and install the requested feature module.
        manager.startInstall(request)

//        updateProgressMessage("Starting install for $name")
    }

    private fun onSuccessfulLoad(moduleName: String, launch: Boolean) {
        if (launch) {
            when (moduleName) {
                dynamicFeature -> launchActivity(mainFeaturesActivity)
            }
        }

    }

    /** Launch an activity by its class name. */
    private fun launchActivity(className: String) {
        Intent().setClassName(packageName, className)
                .also {
                    startActivity(it)
                }
    }
}
