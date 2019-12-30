package what.the.bigimagesample

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var type = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Glide.get(this).clearMemory()
        Thread(Runnable { Glide.get(this@MainActivity).clearDiskCache() }).start()

        Glide.with(this)
            .load(R.drawable.bigimage3)
            .format(DecodeFormat.PREFER_ARGB_8888)
//            .diskCacheStrategy(DiskCacheStrategy.NONE)
//            .skipMemoryCache(true)
//            .priority(Priority.IMMEDIATE)
//            .override(Target.SIZE_ORIGINAL)
            .into(image_big)

        image_big.setOnClickListener {
            image_big.setImageDrawable(null)

            if (type == 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    image_big.setImageDrawable(getDrawable(R.drawable.bigimage3))
                }
                Toast.makeText(this, "디바이스 내부 리소스 직접 접근", Toast.LENGTH_LONG).show()
                type = 1
            } else {
                Glide.with(this)
                    .load(R.drawable.bigimage3)
                    .format(DecodeFormat.PREFER_ARGB_8888)
                    .override(Target.SIZE_ORIGINAL)
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .skipMemoryCache(true)
                    .into(image_big)
                Toast.makeText(this, "Glide로 접근", Toast.LENGTH_LONG).show()
                type = 0
            }
        }
    }
}
