package hu.tvarga.dynamicfeature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.tvarga.androidpublishingtestnew.BaseSplitActivity

class MainFeatureActivity : BaseSplitActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_feature)
    }
}
