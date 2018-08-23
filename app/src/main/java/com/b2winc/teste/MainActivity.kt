package com.b2winc.teste

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var isUp: Boolean = false
    private val totalIndicadores = 5
    private val tempoDuracaoAnimacao = 700L
    private var totalSelecionado = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        my_view.visibility = View.INVISIBLE

        Handler().postDelayed({ executeSlide() }, 500)

    }

    private fun getTamanhoItem(): Float {
        return my_view.height.toFloat() / totalIndicadores
    }

    private fun slideUp() {
        my_view.visibility = View.VISIBLE

        val animate = TranslateAnimation(
                0f,
                0f,
                my_view.height.toFloat(),
                getTamanhoItem() * totalSelecionado)
        animate.duration = tempoDuracaoAnimacao
        animate.fillAfter = true

        animate.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                voltaUm()
            }

        })

        my_view.startAnimation(animate)
    }

    private fun voltaUm() {
        val animate = TranslateAnimation(
                0f,
                0f,
                getTamanhoItem() * totalSelecionado,
                getTamanhoItem() * (totalSelecionado + 1))
        animate.duration = tempoDuracaoAnimacao
        animate.fillAfter = true
        my_view.startAnimation(animate)
        totalSelecionado = (totalSelecionado + 1)
    }

    private fun slideDown() {
        val animate = TranslateAnimation(
                0f,
                0f,
                getTamanhoItem() * totalSelecionado,
                my_view.height.toFloat())
        animate.duration = tempoDuracaoAnimacao
        animate.fillAfter = true
        my_view.startAnimation(animate)
        totalSelecionado = 1
    }

    fun onSlideViewButtonClick(view: View) {
        executeSlide()
    }

    fun executeSlide() {
        if (isUp) {
            slideDown()
            my_button.text = "Slide up"
        } else {
            slideUp()
            my_button.text = "Slide down"
        }
        isUp = !isUp
    }
}
