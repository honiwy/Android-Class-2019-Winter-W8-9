package app.appworks.school.stylish.chat


import android.os.Bundle
import android.os.UserManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import app.appworks.school.stylish.R
import java.net.URISyntaxException
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import android.text.TextUtils
import android.util.Log
import android.widget.EditText
import app.appworks.school.stylish.data.Message
import app.appworks.school.stylish.databinding.FragmentChatBinding
import org.json.JSONException
import org.json.JSONObject
import com.github.nkzawa.emitter.Emitter


/**
 * A simple [Fragment] subclass.
 */

class ChatFragment : Fragment() {

    private var mSocket: Socket? = null
    lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentChatBinding.inflate(inflater, container, false)

        try {
            mSocket = IO.socket("http://practicestylish.com:5000");
            mSocket?.on("new message", onNewMessage)
            mSocket?.connect()
            Log.i("TAG","send new message${mSocket?.connect()} ")
        } catch (e: URISyntaxException) {
            Log.i("TAG","URISyntaxException=${e.message}")
        }


        return binding.root
        // Inflate the layout for this fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    // Sending data
    private val mInputMessageView: EditText? = null

    private fun attemptSend() {
        val keyInMessage = mInputMessageView!!.text.toString().trim { it <= ' ' }
        if (TextUtils.isEmpty(keyInMessage)) {
            return
        }

        mInputMessageView.setText("")

//        val messageSend = Message(chatuser , message)

    mSocket!!.emit("new message", keyInMessage)


    }

    //  listen on new message from other users
    private val onNewMessage = Emitter.Listener { args ->
        activity!!.runOnUiThread(Runnable {
            val data = args[0] as JSONObject
            val username: String
            val message: String
            try {
                username = data.getString("username")
                message = data.getString("message")
            } catch (e: JSONException) {
                return@Runnable
            }
        })

    }


    override fun onDestroy() {
        super.onDestroy()

        mSocket?.disconnect()
        mSocket?.off("new message", onNewMessage)
    }
}


