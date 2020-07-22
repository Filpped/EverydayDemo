package com.example.lib3

import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext
/**
 *1.添加依赖库Gradle
 *Java : 进程 线程  [一个进程（正在进行的程序）中可以有多个线程]
 *       进程：用来管理程序运行中需要的资源
 *       线程：进程中真正执行任务的最小单位
 *如果有任务需要花费大量的时间 -> 选择创建独立的线程来执行这个任务
 *
 * 什么是协程：是多线程的一个顶层封装，可以自动实现线程的切换，
 *          代码顺序可以按照正常的顺序书写
 *
 * 为什么需要协程
 *          耗时的操作会阻塞主线程 网络IO 文件IO 需要花费大量时间的逻辑计算
 *
 *
 * 要了解：
 *      1.如何创建一个协程{//要使用协程 必须先创建协程的上下文 == 运行环境
 *      可以创建多个协程的运行环境 会自动创建一个线程
 *      (1.) GlobalScope.launch {
 *                  GlobalScope.launch {
 *                       println("1----${Thread.currentThread().name}")
 *                   delay(1000)
 *                   }
 *                   println("2----${Thread.currentThread().name}")
 *                   delay(3000)
 *           }   不会阻塞主线程
 *      （2.） runBlocking{
 *              println("0----${Thread.currentThread().name}")
 *              runBlocking {
 *                   delay(3000)
 *                   println("1----${Thread.currentThread().name}")
 *              }
 *              println("2----${Thread.currentThread().name}")
 *           }  用来测试用的，会阻塞当前线程，默认会运行在当前线程中
 *
 *          必须是有耗时操作的时候 才需要使用协程}
 *
 *      delay 会挂起当前线程 使用时候必须放在可以挂起的函数或者协程中
 *
 *
 *      2.协程的作用域
 *      //协程调度器Dispatchers
 *      //默认就是.Default
 *      println("0----${Thread.currentThread().name}")
 *      launch(Dispatchers.Default) {
 *          println("1----${Thread.currentThread().name}")
 *      }
 *      launch(Dispatchers.IO) {
 *          println("2----${Thread.currentThread().name}")
 *      }
 *      //就在当前线程执行任务调用Unconfined
 *      launch(Dispatchers.Unconfined) {
 *          println("3----${Thread.currentThread().name}")
 *      }
 *
 *
 *      3.协程的线程切换
 *      withContext 用于线程切换 当这个任务执行完毕 会自动恢复到当前线程 自动切换到当前线程
 *      fun main() = runBlocking<Unit> {
         //切换线程
         //默认就是.Default
          println("0----${Thread.currentThread().name}")
          var v1 = withContext(Dispatchers.IO){
             println("1----${Thread.currentThread().name}")
             downloadFromWeb()
            }
          println("2----${Thread.currentThread().name}")

        var v2 = withContext(Dispatchers.IO){
            println("3----${Thread.currentThread().name}")
            downloadFromWeb()
        }
        println("$v2 和 $v1")
        }
    //耗时操作
        suspend  fun downloadFromWeb() : String{
         delay(2000)
         return "完成下载！"
        }


 *      4.协程的挂起和阻塞
*/

fun main() = runBlocking {
    val job = launch {
        var i = 1
        while (i<=20){
            println(i)
            delay(100)
            i++
        }
    }
    println("主进程main")
    //线程的等待
    job.join()
   val job2 = launch {
        var i = 1
        while (i<=20){
            println("$i -----")
            delay(100)
            i++
        }
    }

    delay(400)
    //线程的取消
    job.cancel()
}

