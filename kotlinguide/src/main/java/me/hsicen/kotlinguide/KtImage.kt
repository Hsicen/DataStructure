package me.hsicen.kotlinguide

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.awt.Color
import java.io.File
import java.net.URL
import java.nio.file.Paths
import javax.imageio.ImageIO

/**
 * @author: hsicen
 * @date: 2022/6/29 11:06
 * @email: codinghuang@163.com
 * description: 图片处理
 */

class Image(private val pixels: Array<Array<Color>>) {

  fun height(): Int {
    return pixels.size
  }

  fun width(): Int {
    return pixels[0].size
  }

  fun getPixel(y: Int, x: Int): Color {
    return pixels[y][x]
  }
}

object KtImage {
  val BASE_PATH = "${Paths.get("").toAbsolutePath()}/kotlinguide/src/main/resources/images/"

  /**
   * 加载本地图片
   */
  fun loadImage(imageFile: File) =
    ImageIO.read(imageFile)
      .let {
        Array(it.height) { y ->
          Array(it.width) { x ->
            Color(it.getRGB(x, y))
          }
        }
      }.let {
        Image(it)
      }

  /**
   * 图片裁切
   * 待实现
   */
  fun Image.crop(startY: Int, startX: Int, width: Int, height: Int): Image {
    val newPixels = Array(height) { y ->
      Array(width) { x ->
        getPixel(y + startY, x + startX)
      }
    }

    return Image(newPixels)
  }

  /**
   * 横向翻转图片
   * 待实现
   */
  fun Image.flipHorizontal(): Image {
    val newPixels = Array(height()) { y ->
      Array(width()) { x ->
        getPixel(y, width() - x - 1)
      }
    }

    return Image(newPixels)
  }

  /**
   * 纵向翻转图片
   * 待实现
   */
  fun Image.flipVertical(): Image {
    val newPixels = Array(height()) { y ->
      Array(width()) { x ->
        getPixel(height() - y - 1, x)
      }
    }

    return Image(newPixels)
  }

  /**
   * 挂起函数，以http的方式下载图片，保存到本地
   */
  suspend fun downloadImage(url: String, outputFile: File): Boolean =
    withContext(Dispatchers.IO) {
      kotlin.runCatching {
        URL(url).openStream().use {
          outputFile.writeBytes(it.readAllBytes())
        }
      }
    }.isSuccess
}

suspend fun main() {
  val path = "https://t7.baidu.com/it/u=3406125714,2513313326&fm=193"
  val file = File("${KtImage.BASE_PATH}test.png")
  val result = KtImage.downloadImage(path, file)

  println("图片下载结果: $result")
}