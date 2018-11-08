package jp.ac.asojuku.st.myminislot

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var MYCOIN = 1000
    var KAKECOIN =10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onResume() {
        super.onResume()

        //共有プレファレンスからMYCOINとKAKECOINに代入
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        MYCOIN = pref.getInt("MY_COIN",1000)
        KAKECOIN = pref.getInt("KAKE_COIN",10)



        //持ちコインとかけコインの表示
        Mycoin_text.setText(Integer.toString(MYCOIN))
        KakeCoin_text.setText(Integer.toString(KAKECOIN))


        //共有のプレファレンスリセットメソッドの呼び出し :::::共有プレファレンスをリセットし、持ちコインに１０００をセット
        Reset_button.setOnClickListener { Mycoin_text.setText(Integer.toString(reset()))}



        //Startボタンを押すことでstartTappedメソッドを呼び出し実行する
        Start_button.setOnClickListener { startTapped(it) }

        Up_button.setOnClickListener { up() }
        Down_button.setOnClickListener { down()}




    }


    //Game画面遷移するメソッド　その時に共有リファレンスのMY_COINを送る
    fun startTapped(view: View?){

        var intent = Intent(this,GameActivity::class.java);

        intent.putExtra("MY_COIN",MYCOIN).putExtra("KAKE_COIN",KAKECOIN)

        startActivity(intent);

    }


    //共有プレファレンスのリセットメソッド
    fun reset():Int{
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        editor.clear().apply()

        //変数MYCOINを初期値1000にリセット
        MYCOIN =1000

        return myCoinSet()

    }

    fun myCoinSet():Int{
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val MyCoin =pref.getInt("MY_COIN",1000)
        val editor = pref.edit()
        editor.apply()

        return MyCoin
    }



    fun up(){

        KAKECOIN=KAKECOIN+10

        KakeCoin_text.setText(Integer.toString(KAKECOIN))




    }

    fun down(){

        KAKECOIN=KAKECOIN-10

        KakeCoin_text.setText(Integer.toString(KAKECOIN))

    }



}
