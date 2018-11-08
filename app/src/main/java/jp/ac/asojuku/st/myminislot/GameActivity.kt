package jp.ac.asojuku.st.myminislot

import android.content.res.TypedArray
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.ImageView
import jp.ac.asojuku.st.myminislot.R.string.mycoin
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*

class GameActivity : AppCompatActivity() {

    var KAKECOIN=0
    var MYCOIN=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        //共有プレファレンスからMYCOIN.KAKECOINに代入
        MYCOIN = intent.getIntExtra("MY_COIN",0)
        KAKECOIN = intent.getIntExtra("KAKE_COIN",0)

        //変数MYCOINからKAKECOINを引く
        MYCOIN = MYCOIN-KAKECOIN


        Mycoin_text.setText(Integer.toString(MYCOIN))

        KekeCoin_text.setText(Integer.toString(KAKECOIN))
    }

    override fun onResume() {
        super.onResume()

        Stop_button.setOnClickListener { stopTapped() }

        Back_Button.setOnClickListener { finish() }
    }


    fun stopTapped(){

        //画像の配列の宣言 valuesは以下のarrayImageのimage_arrayから取り出す
        var typedArray:TypedArray = this.resources.obtainTypedArray(R.array.image_array)

        //乱数を発生させる
        val rand = Random()
        val x = rand.nextInt(9)
        val y = rand.nextInt(9)
        val z = rand.nextInt(9)



        //スロットのイメージに画像をランダムでセットする
        One_Image.setImageResource(typedArray.getResourceId(x,0))
        Two_Image.setImageResource(typedArray.getResourceId(y,0))
        Three_Image.setImageResource(typedArray.getResourceId(z,0))

        //スロットの絵が揃った時
        if(x==y && y==z){
            //Mycoin_text.setText(Integer.toString(saveData(777)))
            //Mycoin_text.setText(Integer.toString(99))
            //R.string.mycoin.plus(100)

            val pref = PreferenceManager.getDefaultSharedPreferences(this)

            Test_Image.setImageResource(R.drawable.bar)

            var wincoin = 0

            when(x){
                0 ->{
                    wincoin = KAKECOIN*2
                    saveData(wincoin)
                    var coin = pref.getInt("MY_COIN",1000)
                    Mycoin_text.setText(Integer.toString(coin))
                    msg_text.setText("バナナが揃った！掛け金*2倍！")
                }
                1 ->{
                    wincoin = KAKECOIN*5
                    saveData(wincoin)
                    var coin = pref.getInt("MY_COIN",1000)
                    Mycoin_text.setText(Integer.toString(coin))
                    msg_text.setText("BARが揃った！掛け金*5倍！")
                }
                2 ->{
                    wincoin = KAKECOIN*10
                    saveData(wincoin)
                    var coin = pref.getInt("MY_COIN",1000)
                    Mycoin_text.setText(Integer.toString(coin))
                    msg_text.setText("BIGWINが揃った！掛け金*10倍！")
                }
                3 ->{
                    wincoin = KAKECOIN*2
                    saveData(wincoin)
                    var coin = pref.getInt("MY_COIN",1000)
                    Mycoin_text.setText(Integer.toString(coin))
                    msg_text.setText("チェリーが揃った！掛け金*2倍！")
                }
                4 ->{
                    wincoin = KAKECOIN*2
                    saveData(wincoin)
                    var coin = pref.getInt("MY_COIN",1000)
                    Mycoin_text.setText(Integer.toString(coin))
                    msg_text.setText("ぶどうが揃った！掛け金*2倍！")

                }
                5 ->{
                    wincoin = KAKECOIN*2
                    saveData(wincoin)
                    var coin = pref.getInt("MY_COIN",1000)
                    Mycoin_text.setText(Integer.toString(coin))
                    msg_text.setText("レモンが揃った！掛け金*2倍！")

                }
                6 ->{
                    wincoin = KAKECOIN*2
                    saveData(wincoin)
                    var coin = pref.getInt("MY_COIN",1000)
                    Mycoin_text.setText(Integer.toString(coin))
                    msg_text.setText("オレンジが揃った！掛け金*2倍！")

                }
                7 ->{
                    wincoin = KAKECOIN*20
                    saveData(wincoin)
                    var coin = pref.getInt("MY_COIN",1000)
                    Mycoin_text.setText(Integer.toString(coin))
                    msg_text.setText("777が揃った！掛け金*20倍！")
                }
                8 ->{
                    wincoin = KAKECOIN*2
                    saveData(wincoin)
                    var coin = pref.getInt("MY_COIN",1000)
                    Mycoin_text.setText(Integer.toString(coin))
                    msg_text.setText("スイカが揃った！掛け金*2倍！")

                }


            }
        }
        else{
            val pref = PreferenceManager.getDefaultSharedPreferences(this)

            saveData(-KAKECOIN)
            var coin = pref.getInt("MY_COIN",1000)
            Mycoin_text.setText(Integer.toString(coin))
            msg_text.setText("ハズレ...")

        }


    }


    private fun saveData(Coin:Int){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val mycoin = pref.getInt("MY_COIN",1000)
        val gamecount = pref.getInt("GAME_COUNT",0)
        val editor = pref.edit()
        editor.putInt("GAME_COUNT",gamecount+1)
                .putInt("MY_COIN",mycoin+Coin)
                .apply()

    }



}
